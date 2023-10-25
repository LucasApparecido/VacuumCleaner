package ueg.Back;

import javax.swing.*;

public class UniversalBack {

    private static UniversalBack instance;

    private ImageIcon vacuumRight = new ImageIcon(getClass().getResource("/Right.png"));

    private ImageIcon vacuumLeft = new ImageIcon(getClass().getResource("/Left.png"));

    private ImageIcon vacuumUp = new ImageIcon(getClass().getResource("/Up.png"));

    private ImageIcon vacuumDown = new ImageIcon(getClass().getResource("/Down.png"));

    private ImageIcon catPiss = new ImageIcon(getClass().getResource("/catPiss.jpg"));


    private Boolean[][] dirtyFloor = new Boolean[5][5];

    private int y = 0;

    private int x = 0;

    private UniversalBack() {
        for (int i = 0; i < dirtyFloor.length; i++) {
            for (int j = 0; j < dirtyFloor[i].length; j++) {
                dirtyFloor[i][j] = false;
            }
        }
    }

    public static UniversalBack getInstance() {
        if (instance == null) {
            instance = new UniversalBack();
        }
        return instance;
    }

    public ImageIcon getVacuumRight() {
        return vacuumRight;
    }

    public ImageIcon getVacuumLeft() {
        return vacuumLeft;
    }

    public ImageIcon getVacuumUp() {
        return vacuumUp;
    }

    public ImageIcon getVacuumDown() {
        return vacuumDown;
    }

    public ImageIcon getCatPiss() {
        return catPiss;
    }

    public Boolean[][] getDirtyFloor() {
        return dirtyFloor;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setDirtyFloor(Boolean isDirty, int line, int column) {
        dirtyFloor[line][column] = isDirty;
    }
}

