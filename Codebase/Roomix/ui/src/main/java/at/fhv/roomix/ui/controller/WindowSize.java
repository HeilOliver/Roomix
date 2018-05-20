package at.fhv.roomix.ui.controller;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

/**
 * Created by Uray Örnek on 4/5/2018.
 */
public class WindowSize {

    public static double WIDTH;
    public static double HEIGHT;
    public static final double SCREENCUT = 20;


    public static void getScreenSize() {
        Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
        WIDTH = screenSize.getWidth();
        HEIGHT = screenSize.getHeight()-SCREENCUT;
    }
}
