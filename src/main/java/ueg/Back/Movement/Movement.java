package ueg.Back.Movement;

import ueg.Back.UniversalBack;
import ueg.Front.Image.Combine;
import ueg.Front.Updates.UniversalFront;

import javax.swing.*;
import java.awt.*;

public class Movement {

    private UniversalFront universalFront = UniversalFront.getInstance();

    private UniversalBack universalBack = UniversalBack.getInstance();

    private int y = universalBack.getY();

    private int x = universalBack.getX();

    private JLabel[][] room = universalFront.getFloorTiles();

    private ImageIcon vacuumRight = universalBack.getVacuumRight();

    private ImageIcon vacuumLeft = universalBack.getVacuumLeft();

    private ImageIcon vacuumUp = universalBack.getVacuumUp();

    private ImageIcon vacuumDown = universalBack.getVacuumDown();

    private ImageIcon floorImage = universalFront.getFloorImage();

    private Image cleaned;

    private Combine combine = new Combine();

    Boolean isDirty;

    public void moveUp() {
        if (y > 0) {
            room[y][x].setIcon(floorImage);
            y--;
            move(vacuumUp);
            isDirty = universalBack.getDirtyFloor()[y][x];
            if (isDirty) {
                universalBack.notifyObservers(2);
                dirty(vacuumUp);
            }
            universalBack.setY(y);
        } else {
            universalBack.notifyObservers(4);
        }
    }

    public void moveDown() {
        if (y < 4) {
            room[y][x].setIcon(floorImage);
            y++;
            move(vacuumDown);
            isDirty = universalBack.getDirtyFloor()[y][x];
            if (isDirty) {
                universalBack.notifyObservers(2);
                dirty(vacuumDown);
            }
            universalBack.setY(y);
        } else {
            universalBack.notifyObservers(4);
        }
    }

    public void moveRight() {
        if (x < 4) {
            room[y][x].setIcon(floorImage);
            x++;
            move(vacuumRight);
            isDirty = universalBack.getDirtyFloor()[y][x];
            if (isDirty) {
                universalBack.notifyObservers(2);
                dirty(vacuumRight);
            }
            universalBack.setX(x);
        } else {
            universalBack.notifyObservers(4);
        }
    }

    public void moveLeft() {
        if (x > 0) {
            room[y][x].setIcon(floorImage);
            x--;
            move(vacuumLeft);
            isDirty = universalBack.getDirtyFloor()[y][x];
            if (isDirty) {
                universalBack.notifyObservers(2);
                dirty(vacuumLeft);
            }
            universalBack.setX(x);

        } else {
            universalBack.notifyObservers(4);
        }
    }

    public void dirty(ImageIcon vacuumImage) {
        universalBack.setDirtyFloor(false, y, x);
        universalBack.notifyObservers(1);
        universalBack.time(1000);
    }

    public void move(ImageIcon vacuumImage) {
        cleaned = combine.getOverlayedImage(floorImage.getImage(), vacuumImage.getImage());
        room[y][x].setIcon(new ImageIcon(cleaned));
        universalBack.notifyObservers(3);
        universalBack.time(1000);
    }

}
