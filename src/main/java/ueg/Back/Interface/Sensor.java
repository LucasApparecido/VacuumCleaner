package ueg.Back.Interface;

import ueg.Back.UniversalBack;

public class Sensor {

    UniversalBack universalBack = UniversalBack.getInstance();

    private Boolean[][] dirtyFloor = universalBack.getDirtyFloor();

    private int y = universalBack.getY();

    private int x = universalBack.getX();

}
