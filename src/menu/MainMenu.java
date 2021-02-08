package menu;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import login.CreateNewAccountPopUp;
import login.PressButtom;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MainMenu {

    PressButtom pressedButtom = new PressButtom();
    Media media,media2;
    List<Media> mediaList = new ArrayList<Media>();

    public void MainMenu(Stage primaryStage, String name){
        Image mainFont = new Image("file:res/iMainMenu/mains/main1.png");
        ImageView mf = new ImageView(mainFont);
        Image taskBar = new Image("file:res/iMainMenu/tale.png");
        ImageView tb = new ImageView(taskBar);

        Image Singleplayer = new Image("file:res/iMainMenu/buttons/Singleplayer.png");
        ImageView sr = new ImageView(Singleplayer);
        Image SingleplayerP = new Image("file:res/iMainMenu/buttons/SingleplayerP.png");
        ImageView srp = new ImageView(SingleplayerP);
        Image Quit = new Image("file:res/iMainMenu/buttons/Quit.png");
        ImageView qg = new ImageView(Quit);
        Image QuitP = new Image("file:res/iMainMenu/buttons/QuitP.png");
        ImageView qgp = new ImageView(QuitP);
        Image Campaign = new Image("file:res/iMainMenu/buttons/Campaign.png");
        ImageView cn = new ImageView(Campaign);
        Image CampaignP = new Image("file:res/iMainMenu/buttons/CampaignP.png");
        ImageView cnp = new ImageView(CampaignP);
        Image Settings = new Image("file:res/iMainMenu/buttons/Settings.png");
        ImageView ss = new ImageView(Settings);
        Image SettingsP = new Image("file:res/iMainMenu/buttons/SettingsP.png");
        ImageView ssp = new ImageView(SettingsP);
        Image Musicbox = new Image("file:res/iMainMenu/buttons/Musicbox.png");
        ImageView mx = new ImageView(Musicbox);
        Image MusicboxP = new Image("file:res/iMainMenu/buttons/MusicboxP.png");
        ImageView mxp = new ImageView(MusicboxP);
        Image Authors = new Image("file:res/iMainMenu/buttons/Authors.png");
        ImageView as = new ImageView(Authors);
        Image AuthorsP = new Image("file:res/iMainMenu/buttons/AuthorsP.png");
        ImageView asp = new ImageView(AuthorsP);
        Image Credits = new Image("file:res/iMainMenu/buttons/Credits.png");
        ImageView cs = new ImageView(Credits);
        Image CreditsP = new Image("file:res/iMainMenu/buttons/CreditsP.png");
        ImageView csp = new ImageView(CreditsP);

        tb.setTranslateX(0); tb.setTranslateY(0);
        mf.setTranslateX(0); mf.setTranslateY(0);
        Group root = new Group();
        root.getChildren().addAll(mf,tb,sr,qg,cn,ss,mx,as,cs);

        Text nameBox = new Text(name);
        nameBox.setFill(Color.rgb(164,103,106));
        nameBox.setFont(Font.font("Segoe Script", FontWeight.BOLD, 40));
        nameBox.setVisible(true);
        nameBox.setTranslateX(1620); nameBox.setTranslateY(250);
        root.getChildren().add(nameBox);
        Text nameBox2 = new Text("Uncertain");
        nameBox2.setFill(Color.rgb(164,103,106));
        nameBox2.setFont(Font.font("Segoe Script", FontWeight.BOLD, 40));
        nameBox2.setVisible(true);
        nameBox2.setTranslateX(1620); nameBox2.setTranslateY(295);
        root.getChildren().add(nameBox2);

        //test player

        //f = new File("res/iMusic/default/aurea-carmina-by-kevin-macleod-from-filmmusic-io.mp3");
        media = new Media(new File("res/iMusic/default/aurea-carmina-by-kevin-macleod-from-filmmusic-io.mp3").toURI().toString());
        media2 = new Media(new File("res/iMusic/default/pixellandbykevinmacleod.mp3").toURI().toString());
        mediaList.add(0,media); mediaList.add(1,media2);
        play(mediaList.get(0),0);

        //end

        Button button1 = new Button();
        button1.setStyle("-fx-background-color: transparent;");
        button1.setTranslateX(1548);
        button1.setTranslateY(380);
        button1.setPrefSize(1,1);
        button1.setGraphic(cn);
        root.getChildren().add(button1);

        Button button2 = new Button();
        button2.setStyle("-fx-background-color: transparent;");
        button2.setTranslateX(1530);
        button2.setTranslateY(480);
        button2.setPrefSize(1,1);
        button2.setGraphic(sr);
        root.getChildren().add(button2);

        Button button3 = new Button();
        button3.setStyle("-fx-background-color: transparent;");
        button3.setTranslateX(1548);
        button3.setTranslateY(580);
        button3.setPrefSize(1,1);
        button3.setGraphic(mx);
        root.getChildren().add(button3);

        Button button4 = new Button();
        button4.setStyle("-fx-background-color: transparent;");
        button4.setTranslateX(1569);
        button4.setTranslateY(680);
        button4.setPrefSize(1,1);
        button4.setGraphic(ss);
        root.getChildren().add(button4);

        Button button5 = new Button();
        button5.setStyle("-fx-background-color: transparent;");
        button5.setTranslateX(1615);
        button5.setTranslateY(980);
        button5.setPrefSize(1,1);
        button5.setGraphic(qg);
        root.getChildren().add(button5);

        Button buttonA = new Button();
        buttonA.setStyle("-fx-background-color: transparent;");
        buttonA.setTranslateX(1569);
        buttonA.setTranslateY(780);
        buttonA.setPrefSize(1,1);
        buttonA.setGraphic(as);
        root.getChildren().add(buttonA);

        Button buttonC = new Button();
        buttonC.setStyle("-fx-background-color: transparent;");
        buttonC.setTranslateX(1584);
        buttonC.setTranslateY(880);
        buttonC.setPrefSize(1,1);
        buttonC.setGraphic(cs);
        root.getChildren().add(buttonC);

        button1.setOnMousePressed(e -> {
            button1.setGraphic(cnp);
        });
        button1.setOnMouseReleased(e -> {
            button1.setGraphic(cn);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
        });

        button2.setOnMousePressed(e -> {
            button2.setGraphic(srp);
        });
        button2.setOnMouseReleased(e -> {
            button2.setGraphic(sr);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
        });

        button3.setOnMousePressed(e -> {
            button3.setGraphic(mxp);
        });
        button3.setOnMouseReleased(e -> {
            button3.setGraphic(mx);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
        });

        button4.setOnMousePressed(e -> {
            button4.setGraphic(ssp);
        });
        button4.setOnMouseReleased(e -> {
            button4.setGraphic(ss);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
        });

        button5.setOnMousePressed(e -> {
            button5.setGraphic(qgp);
        });
        button5.setOnMouseReleased(e -> {
            button5.setGraphic(qg);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
            primaryStage.close();
        });

        buttonA.setOnMousePressed(e -> {
            buttonA.setGraphic(asp);
        });
        buttonA.setOnMouseReleased(e -> {
            buttonA.setGraphic(as);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
        });

        buttonC.setOnMousePressed(e -> {
            buttonC.setGraphic(csp);
        });
        buttonC.setOnMouseReleased(e -> {
            buttonC.setGraphic(cs);
            pressedButtom.pressAndSound("res/iMusic/mouseclick1.mp3");
        });

        Scene scene = new Scene(root,1920,1080);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        primaryStage.setResizable(false);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
    }

    public void play(Media med, int i){
        MediaPlayer player = new MediaPlayer(med);
        player.play();
        player.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                player.stop();
                if (mediaList.size()-1>i) {
                    play(mediaList.get(i+1),i+1);
                } else {
                    play(mediaList.get(0),0);
                }
                return;
            }
        });
    }
}
