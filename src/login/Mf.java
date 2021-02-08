package login;

import data.Code;
import data.User;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import menu.MainMenu;
import testtemplate.Sample;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;
import java.util.Timer;
import java.util.regex.Pattern;

public class Mf extends Application {
    Media media;
    MediaPlayer mediaplayer;
    File f;
    PressButtom pressedButtom = new PressButtom();
    private static CreateNewAccountPopUp p1;
    private static DeleteOldAccount p2;
    Statement st;
    ListView<String> list;
    File[] listOfFiles;
    File f2 = new File("saves");
    ResultSet rs;
    Boolean flag = true;
    int randomcube;
    String readytoparse,sql;
    private static Sample sample = new Sample();
    Code starter = new Code();
    Connection con;
    MainMenu mm;

    public static void main(String[] args) {
        System.setProperty("quantum.multithreading", "false");
        System.setProperty("javafx.animation.fullspeed", "true");
        System.setProperty("javafx.animation.pulse", "60");
        System.setProperty("javafx.animation.framerate", "60");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws SQLException {
        FirstStage(primaryStage);
        primaryStage.show();
    }

    private void FirstStage(Stage primaryStage) throws SQLException {
        con = DriverManager.getConnection("jdbc:h2:" + "./values/speech", "root", "password");
        st = con.createStatement();
        list = new ListView<>();
        listOfFiles = f2.listFiles();
        ObjectInputStream as;
        User check;
        for (File a : listOfFiles) {
            if (a.isFile() && a.getName().contains(".bin")) {
                try {
                    as = new ObjectInputStream(new FileInputStream("saves/"+a.getName()));
                    check = (User) as.readObject();
                    list.getItems().add(check.name);
                    as.close();
                } catch (IOException | ClassNotFoundException e) { }
            }
        }

        primaryStage.setTitle("Maxi's Tale: Tenshi");
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Pattern wholeNumberPattern = Pattern.compile("\\d*");
        Image background = new Image("file:res/iLogin/BaseWindow/MainMenu.png");
        ImageView ivb = new ImageView(background);
        Group root = new Group();
        root.getChildren().addAll(ivb);

        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(145, 1, 1, 18));
        TextField userTextField = new TextField();
        userTextField.setPrefWidth(140);
        userTextField.setMaxWidth(140);
        userTextField.setStyle("-fx-font-size: 15; -fx-font-weight:bold; -fx-border-color: pink; -fx-background-color: mistyrose; -fx-text-inner-color: darkorange;");
        userTextField.setAlignment(Pos.CENTER);
        grid1.add(userTextField, 1, 1);

        f = new File("res/iMusic/kevinmacleodcherrymonday.mp3");
        media = new Media(f.toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaplayer.setAutoPlay(true);
        mediaplayer.setVolume(0.1);

        userTextField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue,
                                final String newValue) {
                if (newValue.length() > 10)
                    userTextField.setText(oldValue);
            }});
        userTextField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                System.out.println(userTextField.getText());
            }
        });

        primaryStage.getIcons().add(new Image("file:res/iLogin/BaseWindow/Icon.png"));
        Image createAccountPress = new Image("file:res/iLogin/BaseWindow/CreateP.png");
        ImageView cap = new ImageView(createAccountPress);
        Image deleteAccountPress = new Image("file:res/iLogin/BaseWindow/DeleteP.png");
        ImageView dap = new ImageView(deleteAccountPress);
        Image playGamePress = new Image("file:res/iLogin/BaseWindow/PlayP.png");
        ImageView pgp = new ImageView(playGamePress);
        Image createAccount = new Image("file:res/iLogin/BaseWindow/Create.png");
        ImageView ca = new ImageView(createAccount);
        Image deleteAccount = new Image("file:res/iLogin/BaseWindow/Delete.png");
        ImageView da = new ImageView(deleteAccount);
        Image playGame = new Image("file:res/iLogin/BaseWindow/Play.png");
        ImageView pg = new ImageView(playGame);
        Image exitGame = new Image("file:res/iLogin/BaseWindow/Exit.png");
        ImageView eg = new ImageView(exitGame);
        Image exitGamePress = new Image("file:res/iLogin/BaseWindow/ExitP.png");
        ImageView egp = new ImageView(exitGamePress);
        Image blackboard = new Image("file:res/iLogin/BaseWindow/Blackboard.png");
        ImageView bb = new ImageView(blackboard);

        bb.setTranslateX(5);
        bb.setTranslateY(600);
        bb.setVisible(true);

        Text text = new Text();
        text.setFill(Color.WHITESMOKE);
        text.setFont(Font.font("Arial",FontWeight.BOLD, 16));
        text.setVisible(true);
        text.setWrappingWidth(210);
        Randomizer();
        text.setText(readytoparse);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        ImageView temp = new ImageView(text.snapshot(parameters,null));
        temp.setTranslateX(15); temp.setTranslateY(729);
        temp.getTransforms().add(new Rotate(-6));
        text.setVisible(false);

        Group lg = new Group();
        lg.getChildren().add(bb); lg.getChildren().add(temp);
        ImageView temp2 = new ImageView(lg.snapshot(parameters,null));
        temp2.setVisible(true);
        temp2.setTranslateX(5); temp2.setTranslateY(600);

        temp2.setCache(true);
        temp2.setCacheHint(CacheHint.SPEED);

        TranslateTransition board = new TranslateTransition(Duration.seconds(0.7),temp2);
        board.setFromX(5); board.setFromY(585);
        board.setToX(5); board.setToY(350);
        board.setDelay(Duration.seconds(1));
        board.setInterpolator(Interpolator.EASE_OUT);

        TranslateTransition preboard = new TranslateTransition(Duration.seconds(2.5),temp2);
        preboard.setFromX(5); preboard.setFromY(600);
        preboard.setToX(5); preboard.setToY(585);
        preboard.setDelay(Duration.seconds(3));
        preboard.play();
        preboard.setOnFinished(e -> {
            board.play();
        });

        Button button1 = new Button();
        button1.setStyle("-fx-background-color: transparent;");
        button1.setTranslateX(18);
        button1.setTranslateY(220);
        button1.setPrefSize(1,1);
        button1.setGraphic(ca);
        root.getChildren().add(button1);

        Button button2 = new Button();
        button2.setStyle("-fx-background-color: transparent;");
        button2.setTranslateX(18);
        button2.setTranslateY(270);
        button2.setPrefSize(1,1);
        button2.setGraphic(da);
        root.getChildren().add(button2);

        Button button3 = new Button();
        button3.setStyle("-fx-background-color: transparent;");
        button3.setTranslateX(174);
        button3.setTranslateY(158);
        button3.setPrefSize(1,1);
        button3.setGraphic(pg);
        root.getChildren().add(button3);

        Button button4 = new Button();
        button4.setStyle("-fx-background-color: transparent;");
        button4.setTranslateX(90);
        button4.setTranslateY(320);
        button4.setPrefSize(1,1);
        button4.setGraphic(eg);
        root.getChildren().add(button4);

        button4.setOnMousePressed(e -> {
            button4.setGraphic(egp);
        });
        button4.setOnMouseReleased(e -> {
            button4.setGraphic(eg);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
            pressedButtom.mediaplayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    try {
                        st.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    try {
                        con.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    primaryStage.close();
                }
            });
        });

        button1.setOnMousePressed(e -> {
            button1.setGraphic(cap);
        });
        button1.setOnMouseReleased(e -> {
            button1.setGraphic(ca);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
            //CreateNewAccountPopUp.popup();
            p1 = new CreateNewAccountPopUp();
            p1.popup(primaryStage, list);
        });

        button2.setOnMousePressed(e -> {
            button2.setGraphic(dap);
        });
        button2.setOnMouseReleased(e -> {
            button2.setGraphic(da);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
            p2 = new DeleteOldAccount();
            try {
                p2.popup(primaryStage, con, st, rs,flag);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }

        });

        button3.setOnMousePressed(e -> {
            button3.setGraphic(pgp);
        });
        button3.setOnMouseReleased(e -> {
            button3.setGraphic(pg);
            pressedButtom.pressAndSound("res/iMusic/rollover1.mp3");
            if (!starter.singularity(userTextField.getText())) {
                //sample = new Sample();
                //primaryStage.hide();
                mediaplayer.stop();
                mm = new MainMenu();
                primaryStage.hide();
                mm.MainMenu(primaryStage,userTextField.getText());
                primaryStage.show();
                //sample.window(primaryStage, userTextField.getText());
                //primaryStage.close();
            }
        });

        root.getChildren().add(bb);
        root.getChildren().add(text);
        root.getChildren().add(temp);
        root.getChildren().add(temp2);
        root.getChildren().add(grid1);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        Debug(primaryStage);
    }

    private void Randomizer() throws SQLException {
        randomcube = 1 + (int) (20*Math.random());
        sql = "SELECT * FROM FACTS WHERE id=" + randomcube;
        rs = st.executeQuery(sql);
        if (rs.next()) readytoparse = rs.getString(2);
    }

    private void Debug(Stage primaryStage){
        p2 = new DeleteOldAccount();
        try {
            p2.popup(primaryStage, con, st, rs,flag);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
        flag = false;
    }

}
