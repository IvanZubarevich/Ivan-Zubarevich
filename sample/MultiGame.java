package sample;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;


public class MultiGame implements Runnable {
    public static ArrayList<Thread> threads = new ArrayList<>();
    @Override
    public void run() {
        if(Thread.currentThread().isInterrupted())
            return;
        Maze playScreen = new Maze();
        MainMenu.content.getChildren().clear();
        MainMenu.content.getChildren().add(playScreen);
        playScreen.requestFocus();

    }
}
