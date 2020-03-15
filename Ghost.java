package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Data;

import static java.lang.System.exit;

public class Ghost extends Moving {
    private final PacMan pacMan;
    private static Image HOLLOW_IMAGE1;
    private static Image HOLLOW_IMAGE2;
    private static Image HOLLOW_IMAGE3;

    private static final Image[] HOLLOW_IMG;
    private static final Image[] FLASH_HOLLOW_IMG;
    private int hollowCounter;
    private final Image[] defaultImg;
    private final int initialLocationX;
    private final int initialLocationY;
    private final int initialDirectionX;
    private final int initialDirectionY;
    private final int trapTime;
    public int trapCounter;

    public boolean isHollow;

    static {

        try {
            HOLLOW_IMAGE1 = new Image("file:resources/images/ghosthollow2.png");
            HOLLOW_IMAGE2 = new Image("file:resources/images/ghosthollow3.png");
            HOLLOW_IMAGE3 = new Image("file:resources/images/ghosthollow1.png");
        } catch (NullPointerException ex) {
            exit(1);
        }

        HOLLOW_IMG = new Image[]{HOLLOW_IMAGE1, HOLLOW_IMAGE2, HOLLOW_IMAGE1, HOLLOW_IMAGE2};
        FLASH_HOLLOW_IMG = new Image[]{HOLLOW_IMAGE1, HOLLOW_IMAGE3, HOLLOW_IMAGE1, HOLLOW_IMAGE3};
    }

    public Ghost(Image defaultImage1, Image defaultImage2, Maze maze, PacMan pacMan, int x, int y, int xDirection, int yDirection, int trapTime) {
        this.maze = maze;
        this.pacMan = pacMan;
        this.x = x;
        this.y = y;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.trapTime = trapTime;
        this.defaultImg = new Image[]{defaultImage1, defaultImage2, defaultImage1, defaultImage2};
        this.images = this.defaultImg;
        this.isHollow = false;
        this.trapCounter = 0;
        this.initialLocationX = x;
        this.initialLocationY = y;
        this.initialDirectionX = xDirection;
        this.initialDirectionY = yDirection;
        this.imageX = new SimpleIntegerProperty(Data.calcGridInt(x));
        this.imageY = new SimpleIntegerProperty(Data.calcGridInt(y));
        ImageView ghostNode = new ImageView(defaultImage1);
        ghostNode.xProperty().bind(this.imageX.add(Data.IMAGE_OFFSET));
        ghostNode.yProperty().bind(this.imageY.add(Data.IMAGE_OFFSET));
        ghostNode.imageProperty().bind(this.imageBinding);
        ghostNode.setCache(true);
        this.getChildren().add(ghostNode);
    }

    public void resetStatus() {
        this.x = this.initialLocationX;
        this.y = this.initialLocationY;
        this.xDirection = this.initialDirectionX;
        this.yDirection = this.initialDirectionY;
        this.isHollow = false;
        this.moveCounter = 0;
        this.trapCounter = 0;
        this.currentImage.set(0);
        this.imageX.set(Data.calcGridInt(this.x));
        this.imageY.set(Data.calcGridInt(this.y));
        this.images = this.defaultImg;
        this.state = Data.TRAPPED;
        this.setVisible(true);
        this.start();
    }

    public void changeToHollowGhost() {
        this.hollowCounter = 0;
        this.isHollow = true;
        this.images = HOLLOW_IMG;
    }

    private void changeDirectionXtoY(boolean mustChange) {

    }

    private void changeDirectionYtoX(boolean mustChange) {

    }

    private void moveHorizontally() {


    }

    private void moveVertically() {

    }

    private void moveHorizontallyInCage() {

    }

    private void moveVerticallyInCage() {


    }

    public void moveOneStep() {

}
