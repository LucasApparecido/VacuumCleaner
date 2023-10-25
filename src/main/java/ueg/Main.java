package ueg;

import ueg.Front.Screen;
import ueg.Front.Universal;

public class Main {


    public static void main(String[] args){
        Screen screen = Screen.getInstance();
        Universal universal = Universal.getInstance();
        universal.addObserver(screen);
    }
}