package ueg.Front;

import ueg.Back.Cleaner.Cat;
import ueg.Back.UniversalBack;
import ueg.Front.Image.Combine;
import ueg.Front.Updates.ScreenObserver;
import ueg.Front.Updates.UniversalFront;

import javax.swing.*;
import java.awt.*;

public class Screen extends JFrame implements ScreenObserver {

    private static int WIDTH = 500;
    private static int HEIGHT = 400;
    private static Screen instance;

    private JPanel main;
    private JPanel room;


    private JPanel panel;

    private JPanel groupSensor;

    private UniversalFront universalFront = UniversalFront.getInstance();

    private UniversalBack universalBack = UniversalBack.getInstance();

    private JPanel statusMovement = universalFront.getStatusMovement();

    private JPanel statusSpin = universalFront.getStatusSpin();

    private JPanel statusClean = universalFront.getStatusClean();

    private JPanel statusPiss = universalFront.getStatusPiss();

    private JLabel[][] floorTiles = universalFront.getFloorTiles();

    private ImageIcon floorImage = universalFront.getFloorImage();

    private ImageIcon vacuumRight = universalBack.getVacuumRight();

    private Combine combine = new Combine();

    private Cat cat = new Cat();


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
                floorTiles[i][j].setIcon(floorImage);
                room.add(floorTiles[i][j]);
            }
        }

        floorTiles[0][0].setIcon(new ImageIcon(combine.getOverlayedImage(floorImage.getImage(), vacuumRight.getImage())));
        cat.piss(floorTiles);

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

        universalFront.setStatusMovement(statusMovement);
        universalFront.setStatusSpin(statusSpin);
        universalFront.setStatusClean(statusClean);
        universalFront.setStatusPiss(statusPiss);
        universalFront.setFloorTiles(floorTiles);

        getContentPane().add(main);
    }

    public void update() {
        statusMovement = universalFront.getStatusMovement();
        statusSpin = universalFront.getStatusSpin();
        statusClean = universalFront.getStatusClean();
        statusPiss = universalFront.getStatusPiss();
        floorTiles = universalFront.getFloorTiles();

        revalidate();
        repaint();
    }
}
