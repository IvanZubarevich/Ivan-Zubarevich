package sample;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class MainMenu extends Pane {

    private static HBox menuTitle;
    private static HBox menuButtons;
    public static Group content = new Group();

    public MainMenu()
    {
        MenuContentInMiddle title = new MenuContentInMiddle(Data.PAC_MAN);
        menuTitle = new HBox(title);
        menuTitle.setPrefWidth((double)Data.WINDOW_WIDTH);
        menuTitle.setAlignment(Pos.CENTER);
        menuTitle.setLayoutY(Data.WINDOW_HEIGHT/4);
        VBox buttonsBox = new VBox(Data.SPACES_BETWEEN_BUTTONS);
        MenuButton playButton = new MenuButton(Data.GAME);
        MenuButton optionsButton = new MenuButton(Data.OPTIONS);
        MenuButton aboutButton = new MenuButton(Data.ABOUT);
        MenuButton exitButton = new MenuButton(Data.EXIT);
        MenuButton continueButton = new MenuButton(Data.CONTINUE);
        buttonsBox.getChildren().addAll(playButton, continueButton, optionsButton, aboutButton, exitButton);


        playButton.setOnMouseClicked((event) -> {
            Maze playScreen = new Maze();
            Data.content.getChildren().clear();
            Data.content.getChildren().add(playScreen);
            playScreen.requestFocus();

        });
        optionsButton.setOnMouseClicked((event) -> {
            Options optionScreen = Menu.getOptionMenu();
            Data.content.getChildren().clear();
            Data.content.getChildren().add(optionScreen);
            optionScreen.requestFocus();
        });
        continueButton.setOnMouseClicked((event)->{
            try
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/Users/ivan/IdeaProjects/PACMAN/save.txt"));
                int x1 = ois.readInt();
                int y1 = ois.readInt();
                int[] x2 = new int[Data.GHOST_NUMBER];
                int[] y2 = new int[Data.GHOST_NUMBER];
                for(int i=0; i<Data.GHOST_NUMBER; i++)
                {
                    x2[i]=ois.readInt();
                    y2[i]=ois.readInt();
                }
                SimpleIntegerProperty livesCount = new SimpleIntegerProperty(ois.readInt());
                SimpleIntegerProperty score = new SimpleIntegerProperty(ois.readInt());
                Maze playScreen = new Maze(x1, y1, x2, y2, score, livesCount);
                Data.content.getChildren().clear();
                Data.content.getChildren().add(playScreen);
                playScreen.requestFocus();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
       aboutButton.setOnMouseClicked((event) -> {
            About aboutScreen = Menu.getAboutMenu();
            Data.content.getChildren().clear();
            Data.content.getChildren().add(aboutScreen);
            aboutScreen.requestFocus();
        });
        exitButton.setOnMouseClicked((event) -> {
            System.exit(0);
        });
        menuButtons = new HBox(buttonsBox);
        menuButtons.setPrefWidth((double)Data.WINDOW_WIDTH);
        menuButtons.setAlignment(Pos.CENTER);
        menuButtons.setTranslateY(Data.WINDOW_HEIGHT/2);
        this.getChildren().addAll(menuTitle, menuButtons);
    }

}
