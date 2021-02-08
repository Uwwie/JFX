package testtemplate;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;



public class Sample {
    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
    private ArrayList<Node> platforms = new ArrayList<Node>();
    private ArrayList<Node> coins = new ArrayList<Node>();
    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Pane uiRoot = new Pane();
    private Node player;
    private Point2D playerVelocity = new Point2D(0, 0);
    private boolean canJump = true;
    private int levelWidth;
    private boolean dialogEvent = false, running = true;
    private File f;
    private Media media;
    private MediaPlayer mediaplayer;
    int p;
    int gogo = 0;

    Image img = new Image("file:res/iLogin/tester.png");
    ImageView image = new ImageView(img);
    ImagePattern imagePattern = new ImagePattern(img);

    public void window(Stage secondStage, String name){
        Stage primaryStage = new Stage();
        initContent();
        Scene scene = new Scene(appRoot);
        scene.setOnKeyPressed(event -> keys.put(event.getCode(), true));
        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.setWidth(1080);
        primaryStage.setHeight(1920);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.initOwner(secondStage);

        image.setTranslateX(0); image.setTranslateY(600);
        image.setVisible(true);



        AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    if (running) {
                        update();
                    }
                    if (dialogEvent) {
                        dialogEvent = false;
                        keys.keySet().forEach(key -> keys.put(key, false));
                        running = true;
                    }
                }
            };
        timer.start();
        primaryStage.show();
    }

    private void initContent() {
        f = new File("res/iMusic/test.mp3");
        media = new Media(f.toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setVolume(0.1);
        mediaplayer.play();



        Rectangle bg = new Rectangle(1920, 1080);
        bg.setFill(Color.WHITESMOKE);


        levelWidth = LevelData.LEVEL1[0].length()*100 * 60;

        for (int i = 0; i < LevelData.LEVEL1.length; i++) {
            String line = LevelData.LEVEL1[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)) {
                    case '0':
                        break;
                    case '1':
                        Node platform = createEntity(j*60, i*60, 60, 60, Color.BROWN);
                        platforms.add(platform);
                        break;
                }
            }
        }

        player = createEntity(0, 600, 40, 40, Color.BLUE);

        player.translateXProperty().addListener((obs, old, newValue) -> {
            int offset = newValue.intValue();

            if (offset > 640 && offset < levelWidth - 640) {
                gameRoot.setLayoutX(-(offset - 640));
            }
        });

        appRoot.getChildren().addAll(bg, gameRoot, uiRoot);
    }

    private void update() {
        if (isPressed(KeyCode.W) && player.getTranslateY() >= 5) {
            jumpPlayer();
        }

        if (isPressed(KeyCode.A) && player.getTranslateX() >= 5) {
            movePlayerX(-5);
            player.setScaleX(-1);
        }

        if (isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth - 5) {
            movePlayerX(5);
            player.setScaleX(1);

            }

            if (isPressed(KeyCode.D)) {
                ++gogo;

                if (gogo>=20) {
                    for (Node platform : platforms) {
                        if (!player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                            p = (int) player.getTranslateY() - (int)Math.random()*50;
                            p = p + (int)Math.random()*600;
                            if ((p<300) || (p>1000)) p = 800;
                            Node platform2 = createEntity((int) player.getTranslateX() + 90 + (int)Math.random()*120, p, 60, 60, Color.RED);
                            platforms.add(platform2);
                            gogo = 0; break;
                        }
                    }
            }

        }

        if (playerVelocity.getY() < 10) {
            playerVelocity = playerVelocity.add(0, 1);
        }

        movePlayerY((int)playerVelocity.getY());

    }

    private void movePlayerX(int value) {
        boolean movingRight = value > 0;


        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (player.getTranslateX() + 40 == platform.getTranslateX()) {
                            return;
                        }
                    }
                    else {
                        if (player.getTranslateX() == platform.getTranslateX() + 60) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    private void movePlayerY(int value) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (player.getTranslateY() + 40 == platform.getTranslateY()) {
                            player.setTranslateY(player.getTranslateY() - 1);
                            canJump = true;
                            return;
                        }
                    }
                    else {
                        if (player.getTranslateY() == platform.getTranslateY() + 60) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

    private void jumpPlayer() {
        if (canJump) {
            playerVelocity = playerVelocity.add(0, -30);
            canJump = false;
        }
    }

    private Node createEntity(int x, int y, int w, int h, Color color) {
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        if (color.equals(Color.BLUE)) entity.setFill(imagePattern); else
        entity.setFill(color);
        entity.getProperties().put("alive", true);

        gameRoot.getChildren().add(entity);
        return entity;
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }

}
