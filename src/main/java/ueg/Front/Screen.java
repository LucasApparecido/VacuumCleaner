package ueg.Front;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Screen extends JFrame {

    private static int WIDTH = 500;
    private static int HEIGHT = 400;
    private static Screen instance;

    private JPanel main;
    private JPanel room;

    private JPanel statusMovement;
    private JPanel statusSpin;
    private JPanel statusClean;
    private JPanel statusPiss;

    private JPanel panel;
    private JLabel[][] floorTiles;

    private JPanel groupSensor;
    private ImageIcon sensorOn = new ImageIcon(getClass().getResource("/sensorOn.png"));
    private ImageIcon sensorOff = new ImageIcon(getClass().getResource("/sensorOff.png"));

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

        floorTiles = new JLabel[5][5];
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

        statusMovement = createStatusPanel("Movimento", sensorOff);
        statusSpin = createStatusPanel("Giro", sensorOff);
        statusClean = createStatusPanel("Limpando", sensorOff);
        statusPiss = createStatusPanel("Xixi", sensorOff);

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

        getContentPane().add(main);
    }

    private JPanel createStatusPanel(String labelText, ImageIcon icon) {
        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
        statusPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        statusPanel.setBackground(Color.WHITE);

        JLabel label = new JLabel(labelText);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(icon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        statusPanel.add(Box.createVerticalGlue());
        statusPanel.add(label);
        statusPanel.add(imageLabel);
        statusPanel.add(Box.createVerticalGlue());

        return statusPanel;
    }
}
