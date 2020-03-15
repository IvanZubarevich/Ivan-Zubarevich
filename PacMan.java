package sample;

import java.util.Random;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Data;


import static java.lang.System.exit;

public class PacMan extends Moving {
    public int dotEatenCount;
    public SimpleIntegerProperty score;
    private static final int[] ROTATION_DEGREE = new int[]{0, 90, 180, 270};
    private int keyboardBuffer;
    private final SimpleIntegerProperty currentDirection;

    public PacMan(Maze maze, int x, int y) {
        this.maze = maze;
        this.x = x;
        this.y = y;
        Image defaultImage = new Image("file:resources/images/left1.png");
        try {

            this.images = new Image[]{defaultImage, new Image("file:resources/images/left2.png"), defaultImage, new Image("file:resources/images/round.png")};
        }
        catch (NullPointerException ex) {
            exit(1);
        }
        this.dotEatenCount = 0;
        this.score = new SimpleIntegerProperty(0);
        this.currentDirection = new SimpleIntegerProperty(0);
        this.imageX = new SimpleIntegerProperty(Data.calcGridInt(x));
        this.imageY = new SimpleIntegerProperty(Data.calcGridInt(y));
        this.xDirection = -1;
        this.yDirection = 0;
        ImageView pacmanImage = new ImageView(defaultImage);
        pacmanImage.xProperty().bind(this.imageX.add(Data.IMAGE_OFFSET));
        pacmanImage.yProperty().bind(this.imageY.add(Data.IMAGE_OFFSET));
        pacmanImage.imageProperty().bind(this.imageBinding);
        IntegerBinding rotationBinding = new IntegerBinding() {
            {
                super.bind(PacMan.this.currentDirection);
            }
            protected int computeValue() {
                return PacMan.ROTATION_DEGREE[PacMan.this.currentDirection.get()];
            }
        };
        pacmanImage.rotateProperty().bind(rotationBinding);
        this.keyboardBuffer = -1;
        this.getChildren().add(pacmanImage);
    }

    private void autoMod() {

    }

    private void moveHorizontally() {

    }

    private void moveVertically() {

    }

    private void moveRight() {

    }

    private void moveLeft() {

    }

    private void moveUp() {

    }

    private void moveDown() {

    }

    private void handleKeyboardInput() {

    }

    public void setKeyboardBuffer(int keyboard) {
        this.keyboardBuffer = keyboard;
    }

    public int getKeyboardBuffer() {
        return this.keyboardBuffer;
    }

    private void updateScore() {

    }

    public void moveOneStep() {


    }

    public void resetStatus() {

}
