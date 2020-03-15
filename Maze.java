package sample;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import sample.Data;
import sample.Menu;

public class Maze extends Parent {
    public PacMan pacMan;
    public final Ghost[] ghosts;
    public final Ghost[] allGhosts;
    private int ghostEatenCount;
    public BooleanProperty waitForStart;
    private final MessageBox messageStartBox;
    private final MessageBox messagePauseBox;
    private final MessageBox messageLoseBox;
    public final MessageBox messageWinBox;
    public BooleanProperty gamePaused;
    private final Group group;

    public Maze() {
        this.setFocused(true);
        Data.resetMaze();
        this.pacMan = new PacMan(this, Data.INITIAL_PACMAN_X, Data.INITIAL_PACMAN_Y);
        this.group = new Group();
        this.gamePaused = new SimpleBooleanProperty(false);
        this.waitForStart = new SimpleBooleanProperty(true);

        Ghost ghostBlinky = new Ghost(new Image("file:resources/images/ghostred1.png"), new Image("file:resources/images/ghostred2.png"), this, this.pacMan, 15, 14, 0, -1, 1);
        Ghost ghostPinky = new Ghost(new Image("file:resources/images/ghostpink1.png"), new Image("file:resources/images/ghostpink2.png"), this, this.pacMan, 14, 15, 1, 0, 5);
        Ghost ghostInky = new Ghost(new Image("file:resources/images/ghostcyan1.png"), new Image("file:resources/images/ghostcyan2.png"), this, this.pacMan, 12, 15, 1, 0, 20);
        Ghost ghostClyde = new Ghost(new Image("file:resources/images/ghostorange1.png"), new Image("file:resources/images/ghostorange2.png"), this, this.pacMan, 16, 15, 1, 0, 30);
        this.allGhosts = new Ghost[]{ghostBlinky, ghostPinky, ghostInky, ghostClyde};
        this.ghosts = new Ghost[Data.GHOST_NUMBER];

        for(int i = 0; i < Data.GHOST_NUMBER; ++i) {
            this.ghosts[i] = this.allGhosts[i];
        }

        Text textScore = new Text(Data.SCORE + pacMan.score);
        textScore.textProperty().bind(pacMan.score.asString(Data.SCORE +" %1d "));
        textScore.setFont(new Font(Data.FONT_SIZE));
        textScore.setFill(Color.YELLOW);
        textScore.setCache(true);
        HBox scoreView = new HBox(10.0D, textScore);
        scoreView.setTranslateX((double)Data.calcGrid(0.0F));
        scoreView.setTranslateY((double)Data.calcGrid(32.0F));

        ImageView livesImage1 = new ImageView(Data.PACMAN_IMAGE);
        livesImage1.visibleProperty().bind(Data.livesCount.greaterThan(-1));
        livesImage1.setTranslateY(5.0D);
        livesImage1.setCache(true);
        ImageView livesImage2 = new ImageView(Data.PACMAN_IMAGE);
        livesImage2.visibleProperty().bind(Data.livesCount.greaterThan(0));
        livesImage2.setTranslateY(5.0D);
        livesImage2.setCache(true);
        ImageView livesImage3 = new ImageView(Data.PACMAN_IMAGE);
        livesImage3.visibleProperty().bind(Data.livesCount.greaterThan(1));
        livesImage3.setTranslateY(5.0D);
        livesImage3.setCache(true);
        Text textLives = new Text(Data.LIVES);
        textLives.setFont(new Font(Data.FONT_SIZE));
        textLives.setFill(Color.YELLOW);
        HBox livesView = new HBox(10.0D, new Node[]{textLives, livesImage1, livesImage2, livesImage3});
        livesView.setTranslateX((double)Data.calcGrid(17.7F));
        livesView.setTranslateY((double)Data.calcGrid(32.0F));
        this.messageStartBox = new MessageBox(this, Data.PK_TO_START);
        this.messagePauseBox = new MessageBox(this, Data.PAUSE);
        this.messageLoseBox = new MessageBox(this, Data.YOU_LOSE);
        this.messageWinBox = new MessageBox(this, Data.YOU_WIN);
        this.messagePauseBox.setVisible(false);
        this.messageLoseBox.setVisible(false);
        this.messageWinBox.setVisible(false);
        this.setMaze(this.group);
        this.setDots();
        this.setOnKeyPressed((event) -> {
            this.onKeyPressed(event);
        });
        this.group.getChildren().add(this.pacMan);
        this.group.getChildren().add(scoreView);
        this.group.getChildren().addAll(this.ghosts);
        this.group.getChildren().addAll(this.messageStartBox, this.messagePauseBox, this.messageLoseBox, this.messageWinBox);
        this.group.getChildren().addAll(livesView);
        this.getChildren().add(this.group);
    }

    public void setMaze(Group group) {

    }

    public void setDots() {

    }

    public final Dot createDot(int x1, int y1, int type) {

    }

    public final void putDotHorizontally(int x1, int x2, int y) {

    }

    public final void putDotVertically(int x, int y1, int y2) {

    }

    public void onKeyPressed(KeyEvent event) {
        Maze.СontrolThread сontrolThread = new Maze.СontrolThread(event);
        сontrolThread.start();
    }

    public void changeState(KeyEvent event) {

    }

    public void setMenu() {
        this.pacMan.stop();
        Data.content.getChildren().clear();
        Data.content.getChildren().add(Menu.getMainMenu());
        Menu.getMainMenu().requestFocus();
    }

    public void resume() {
        if (this.pacMan.isPaused()) {
            this.pacMan.start();
        }

        for(int y = 0; y < Data.GHOST_NUMBER; ++y) {
            this.ghosts[y].start();
        }

        this.waitForStart.set(false);
        this.messagePauseBox.setVisible(false);
        this.gamePaused.set(false);
    }

    public void restart() {
        this.pacMan.resetStatus();

        int y;
        for(int x = 1; x <= Data.X_PIXELS-1; ++x) {
            for(y = 1; y <= Data.Y_PIXELS-1; ++y) {
                Dot dot = (Dot)Data.getDot(x, y);
                if (dot != null && !dot.isVisible()) {
                    dot.setVisible(true);
                }
            }
        }

        for(y = 0; y < Data.GHOST_NUMBER; ++y) {
            this.ghosts[y].resetStatus();
        }

        this.messageStartBox.setVisible(false);
        this.waitForStart.set(false);
    }

    public void makeGhostsHollow() {
        this.ghostEatenCount = 0;
        for(int y = 0; y < Data.GHOST_NUMBER; ++y) {
            this.ghosts[y].changeToHollowGhost();
        }

    }

    public boolean hasMet(Ghost ghost) {

    }

    public void pacManMeetsGhosts() {



    }

    public void pacManEatsGhost(Ghost ghost) {

    }

    private class СontrolThread extends Thread {
        private KeyEvent event;

        public СontrolThread(KeyEvent event) {
            this.event = event;
        }
        public void run() {
            synchronized(this) {
                Maze.this.changeState(this.event);
            }
        }
    }
}
