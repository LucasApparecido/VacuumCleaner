package ueg;

import ueg.Back.Movement.Movement;
import ueg.Back.Sensors.SensorClean;
import ueg.Back.Sensors.SensorDirty;
import ueg.Back.Sensors.SensorMovement;
import ueg.Back.Sensors.SensorWall;
import ueg.Back.UniversalBack;
import ueg.Front.Screen;
import ueg.Front.Updates.UniversalFront;

public class Main {

    static Movement movement = new Movement();
    
    public static void main(String[] args){
        Screen screen = Screen.getInstance();
        UniversalFront universalFront = UniversalFront.getInstance();
        universalFront.addObserver(screen);
        UniversalBack universalBack = UniversalBack.getInstance();
        SensorClean sensorClean = new SensorClean();
        SensorDirty sensorDirty = new SensorDirty();
        SensorMovement sensorMovement = new SensorMovement();
        SensorWall sensorWall = new SensorWall();
        universalBack.addObserver(sensorClean);
        universalBack.addObserver(sensorDirty);
        universalBack.addObserver(sensorMovement);
        universalBack.addObserver(sensorWall);

        universalBack.time(1000);
        movement.moveRight();
        movement.moveDown();
        movement.moveLeft();
        movement.moveLeft();
        movement.moveUp();
    }
}