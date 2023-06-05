import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.util.Duration;

public class Main extends Application {
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    Scene scene;
    Group root;
    Button play;
    Paddle paddle;
    Ball ball;
    Brick[][] bricks = new Brick[7][7];
    public static void main(String[] args) {
        System.out.println("Hello world!");
        launch(args);
    }

    public void start (Stage stage) {
        root = new Group();
        makePlay();
        makeBlocks();
        makeBall();
        makePaddle();
        scene = new Scene(root, 450, 600, Color.WHITE);

        stage.setScene(scene);
        stage.show();

        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY)));
        animation.play();
    }

    private void makePlay() {
        play = new Button("Play");
        play.setLayoutX(400);
        play.setLayoutY(10);
        play.setVisible(true);
        play.setOnAction(event -> {
            play.setVisible(false);
            moveCircleOnKeyPress(scene, paddle);
        });
        addNode(play);
    }

    private void makeBlocks() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++){
                Brick b = new Brick(50 * i + 50, 30 * j + 50);
                bricks[i][j] = b;
                addNode(b.r);
            }
        }
    }

    private void makeBall() {
        ball = new Ball();
        addNode(ball.c);
    }

    private void makePaddle() {
        paddle = new Paddle();
        addNode(paddle.r);
    }

    private void addNode(Node n) {
        root.getChildren().add(n);
    }

    private void moveCircleOnKeyPress(Scene scene, Paddle p) {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case L: p.moveRight(); break;
                    case J: p.moveLeft(); break;
                    case Q: end(); break;
                }
            }
        });
    }

    private void step (double elapsedTime) {
        if (!play.isVisible()) {
            ball.move();
            if(ball.getX() > 370 || ball.getX() < 60) { ball.hitX(); }
            if(ball.getY() < 50) { ball.hitY(); }
            if(ball.getY() > 580) {
                play.setVisible(true);
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Sorry, you lost");
                alert.show();
                alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                        reset();
                    }
                });
            }
            if (ball.c.getBoundsInParent().intersects(paddle.r.getBoundsInParent())) {
                if(ball.getX() - paddle.getX() < 20 && ball.getSpeed() > 0) {
                    ball.hitX();
                }
                else if(ball.getX() - paddle.getX() > 40 && ball.getSpeed() < 0) {
                    ball.hitX();
                }
                ball.hitY();
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 7; j++){
                    if (bricks[i][j] == null) {
                        continue;
                    }
                    else if (ball.c.getBoundsInParent().intersects(bricks[i][j].r.getBoundsInParent())) {
                        bricks[i][j].disappear();
                        bricks[i][j] = null;
                        ball.hitY();
                    }
                }
            }
        }
    }

    private void reset() {
        root.getChildren().clear();
        makeBall();
        makeBlocks();
        makePaddle();
        makePlay();
    }

    private void end(){
        System.exit(0);
    }
}