package sample;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MultiGame implements Runnable {
    @Override
    public void run() {
        if(Thread.currentThread().isInterrupted())
            return;
        Stage stage = new Stage();
        StackPane second= new StackPane();
        Maze playScreen = new Maze();
        second.getChildren().add(playScreen);
        Scene scene = new Scene(second, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT, Color.BLACK);
        stage.setScene(scene);
        stage.show();
    }
}
