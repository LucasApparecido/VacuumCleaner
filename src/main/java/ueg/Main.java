package ueg;

import ueg.Front.Screen;
import ueg.Front.Updates.UniversalFront;

public class Main {


    public static void main(String[] args){
        Screen screen = Screen.getInstance();
        UniversalFront universal = UniversalFront.getInstance();
        universal.addObserver(screen);
    }
}