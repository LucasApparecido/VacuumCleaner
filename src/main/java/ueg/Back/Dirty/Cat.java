package ueg.Back.Dirty;

import ueg.Back.UniversalBack;

import javax.swing.*;
import java.util.Random;

public class Cat {

    private UniversalBack universalBack = UniversalBack.getInstance();

    private ImageIcon catPiss = universalBack.getCatPiss();


    public void piss(JLabel[][] room) {
        Random randomValue = new Random();
        int amountOfPiss = randomValue.nextInt(25);


        for (int i = 1; i <= amountOfPiss; i++) {
            int line = randomValue.nextInt(5);
            int column = randomValue.nextInt(5);
            Boolean isDirty = universalBack.getDirtyFloor()[line][column];
            if (!isDirty & !(line == 0 && column == 0)) {
                room[line][column].setIcon(catPiss);
                universalBack.setDirtyFloor(true, line, column);
            }
        }
    }

}
