package sample;

import javafx.application.Platform;
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
        buttonsBox.getChildren().addAll(playButton, optionsButton, aboutButton, exitButton);

        playButton.setOnMouseClicked((event) -> {
            Stage stage = new Stage();
            Scene scene = new Scene(MainMenu.content, Data.WINDOW_WIDTH, Data.WINDOW_HEIGHT, Color.BLACK);
            stage.setScene(scene);
            stage.show();
            MultiGame game = new MultiGame();
            MultiGame.threads.add(new Thread(game));
            MultiGame.threads.get(MultiGame.threads.size()-1).start();

        });
        optionsButton.setOnMouseClicked((event) -> {
            Options optionScreen = Menu.getOptionMenu();
            Data.content.getChildren().clear();
            Data.content.getChildren().add(optionScreen);
            optionScreen.requestFocus();
        });
        /*aboutButton.setOnMouseClicked((event) -> {
            About aboutScreen = Menu.getAboutMenu();
            Data.content.getChildren().clear();
            Data.content.getChildren().add(aboutScreen);
            aboutScreen.requestFocus();
        });*/
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
