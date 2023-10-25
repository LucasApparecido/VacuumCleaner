package ueg.Front;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen extends JFrame implements ScreenObserver{

    private static int WIDTH = 500;
    private static int HEIGHT = 400;
    private static Screen instance;

    private JPanel main;
    private JPanel room;


    private JPanel panel;

    private JPanel groupSensor;

    private Universal universal = Universal.getInstance();

    private JPanel statusMovement = universal.getStatusMovement();

    private JPanel statusSpin = universal.getStatusSpin();

    private JPanel statusClean = universal.getStatusClean();

    private JPanel statusPiss = universal.getStatusPiss();

    private JLabel[][] floorTiles = universal.getFloorTiles();

    private Screen() {
        setTitle("Aspirador de pó");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());

        buildLayout();

        setVisible(true);
    }

    public static Screen getInstance() {
        if (instance == null) {
            instance = new Screen();
        }
        return instance;
    }

    private void buildLayout() {
        main = new JPanel();
        main.setLayout(new BorderLayout());

        room = new JPanel();
        room.setLayout(new GridLayout(5, 5));
        room.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                floorTiles[i][j] = new JLabel();
                floorTiles[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                room.add(floorTiles[i][j]);
            }
        }

        try {
            BufferedImage floorImage = ImageIO.read(new File("src/main/resources/floor.png"));
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    ImageIcon icon = new ImageIcon(floorImage);
                    floorTiles[i][j].setIcon(icon);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        groupSensor = new JPanel();
        groupSensor.setLayout(new BorderLayout());
        groupSensor.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        statusMovement.setPreferredSize(new Dimension(150, 75));
        statusSpin.setPreferredSize(new Dimension(150, 75));
        statusClean.setPreferredSize(new Dimension(150, 75));
        statusPiss.setPreferredSize(new Dimension(150, 75));

        groupSensor.add(statusClean, BorderLayout.NORTH);
        groupSensor.add(statusPiss, BorderLayout.SOUTH);

        panel.add(statusMovement, BorderLayout.NORTH);
        panel.add(statusSpin, BorderLayout.CENTER);
        panel.add(groupSensor, BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(300, 400));

        main.add(room, BorderLayout.WEST);
        main.add(panel, BorderLayout.CENTER);

        main.setBackground(Color.WHITE);

        universal.setStatusMovement(statusMovement);
        universal.setStatusSpin(statusSpin);
        universal.setStatusClean(statusClean);
        universal.setStatusPiss(statusPiss);
        universal.setFloorTiles(floorTiles);

        getContentPane().add(main);
    }

    public void update() {
        statusMovement = universal.getStatusMovement();
        statusSpin = universal.getStatusSpin();
        statusClean = universal.getStatusClean();
        statusPiss = universal.getStatusPiss();
        floorTiles = universal.getFloorTiles();

        revalidate();
        repaint();
    }
}
