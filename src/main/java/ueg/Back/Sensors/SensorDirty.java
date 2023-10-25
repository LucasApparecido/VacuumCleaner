package ueg.Back.Sensors;

import ueg.Back.UniversalBack;
import ueg.Front.Updates.UniversalFront;

import javax.swing.*;

public class SensorDirty implements SensorObserver{

    private UniversalFront universalFront = UniversalFront.getInstance();

    private UniversalBack universalBack = UniversalBack.getInstance();

    private ImageIcon sensorOn = universalFront.getSensorOn();

    private ImageIcon sensorOff = universalFront.getSensorOff();

    private JPanel panel = universalFront.getStatusPiss();

    @Override
    public void updateStatus() {
        universalFront.modifyImage(panel, sensorOn);
        universalBack.time(500);
        universalFront.modifyImage(panel, sensorOff);
        universalBack.time(500);
    }
}
