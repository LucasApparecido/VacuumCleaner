package ueg.Front.Updates;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UniversalFront {

    private static UniversalFront instance;

    private JPanel statusMovement;

    private JPanel statusWall;

    private JPanel statusClean;

    private JPanel statusPiss;

    private JLabel[][] floorTiles = new JLabel[5][5];

    private List<ScreenObserver> observers = new ArrayList<>();

    private ImageIcon sensorOn = new ImageIcon(getClass().getResource("/sensorOn.png"));

    private ImageIcon sensorOff = new ImageIcon(getClass().getResource("/sensorOff.png"));

    private ImageIcon floorImage = new ImageIcon(getClass().getResource("/floor.png"));


    private UniversalFront() {
        statusMovement = createStatusPanel("Movimento", sensorOff);
        statusWall = createStatusPanel("Parede", sensorOff);
        statusClean = createStatusPanel("Limpeza", sensorOff);
        statusPiss = createStatusPanel("Sujo", sensorOff);
    }

    public static UniversalFront getInstance() {
        if (instance == null) {
            instance = new UniversalFront();
        }
        return instance;
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

    public void addObserver(ScreenObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (ScreenObserver observer : observers) {
            observer.update();
        }
    }

    public JPanel getStatusMovement() {
        return statusMovement;
    }

    public void setStatusMovement(JPanel statusMovement) {
        this.statusMovement = statusMovement;
        notifyObservers();
    }

    public JPanel getStatusWall() {
        return statusWall;
    }

    public void setStatusWall(JPanel statusSpin) {
        this.statusWall = statusSpin;
        notifyObservers();
    }

    public JPanel getStatusClean() {
        return statusClean;
    }

    public void setStatusClean(JPanel statusClean) {
        this.statusClean = statusClean;
        notifyObservers();
    }

    public JPanel getStatusPiss() {
        return statusPiss;
    }

    public void setStatusPiss(JPanel statusPiss) {
        this.statusPiss = statusPiss;
        notifyObservers();
    }

    public JLabel[][] getFloorTiles() {
        return floorTiles;
    }

    public void setFloorTiles(JLabel[][] floorTiles) {
        this.floorTiles = floorTiles;
        notifyObservers();
    }

    public ImageIcon getSensorOn() {
        return sensorOn;
    }

    public ImageIcon getSensorOff() {
        return sensorOff;
    }


    public ImageIcon getFloorImage() {
        return floorImage;
    }

    public void modifyImage(JPanel statusPanel, ImageIcon newImage) {
        Component[] components = statusPanel.getComponents();

        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getIcon() != null) {
                    label.setIcon(newImage);
                    notifyObservers();
                    break;
                }
            }
        }
    }
}

