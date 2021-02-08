package login;
import data.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.*;

public class DeleteOldAccount {
    File del;
    int index, randomcube;
    String sql,readytoparse;
    File[] listOfFiles;
    File f = new File("saves");
    ListView<String> list;
    double t = 0.05;
    int impt = 0;
    boolean reverseblink = false;
    Timeline blinkSignal,deleteSignal;
    int deleteCode = 0,sweep = 0;
    Image popupImage,exit,exitPress,delete,deletePress,confirmationWindow,confirmationYes,confirmationYesP,confirmationNo,confirmationNoP,speechtriangle,
    base1,blink1,blink2,base2,deletetemp,sweeper1,sweeper2,sweeper3,sweeper4,sweeper5,sweeper6,sweeper7,sweeper8,sweeper9,sweeper10,sweeper11,thumb;
    ImageView deltemp,ip,et,etp,dt,dtp,cw,cy,cyp,cn,cnp,b1,bl1,bl2,b2,spe,sweep1,sweep2,sweep3,sweep4,sweep5,sweep6,sweep7,sweep8,sweep9,sweep10,sweep11,tb;
    Text text;


    public void popup(Stage parentStage, Connection con, Statement st, ResultSet rs, boolean flag) throws SQLException, InterruptedException {

        Stage pop = new Stage();
        PressButtom pbs = new PressButtom();
        pop.initOwner(parentStage);
        pop.initStyle(StageStyle.TRANSPARENT);
        pop.initModality(Modality.NONE);
        pop.setTitle("");
        CreateList();
        TextCreator();
        Group root = new Group();
        InitFrames();
        InitFramesXY();
        Visibility();
        root.getChildren().addAll(ip,spe,cw,b1,b2,bl1,bl2);
        root.getChildren().addAll(sweep1,sweep2,sweep3,sweep4,sweep5,sweep6,sweep7,sweep8,sweep9,sweep10,sweep11);

        Button button1 = new Button();
        button1.setStyle("-fx-background-color: transparent;");
        button1.setTranslateX(420);
        button1.setTranslateY(120);
        button1.setPrefSize(1, 1);
        button1.setGraphic(dt);
        root.getChildren().add(button1);

        Button button2 = new Button();
        button2.setStyle("-fx-background-color: transparent;");
        button2.setTranslateX(230);
        button2.setTranslateY(395);
        button2.setPrefSize(1, 1);
        button2.setGraphic(et);
        root.getChildren().add(button2);

        Button button3 = new Button();
        button3.setStyle("-fx-background-color: transparent;");
        button3.setTranslateX(285);
        button3.setTranslateY(307);
        button3.setPrefSize(1, 1);
        button3.setGraphic(cy);
        root.getChildren().add(button3);

        Button button4 = new Button();
        button4.setStyle("-fx-background-color: transparent;");
        button4.setTranslateX(375);
        button4.setTranslateY(307);
        button4.setPrefSize(1, 1);
        button4.setGraphic(cn);
        root.getChildren().add(button4);
        button3.setVisible(false); button4.setVisible(false); cw.setVisible(false);

        root.getChildren().addAll(list,deltemp,tb,text);
        if(list.getItems().stream().count()<=6) {
            tb.setVisible(false);
        }
        Scene scene = new Scene(root, 532, 435);
        scene.setFill(Color.TRANSPARENT);

        button1.setOnMousePressed(e -> {
            button1.setGraphic(dtp);
        });
        button1.setOnMouseReleased(e -> {
            button1.setGraphic(dt);
            pbs.pressAndSound("res/iMusic/mouseclick1.mp3");
            if (!list.getSelectionModel().isEmpty()) {
                button3.setVisible(true); button4.setVisible(true); cw.setVisible(true); spe.setVisible(true);
                list.setMouseTransparent(true);
                list.setFocusTraversable(false);
                index = list.getSelectionModel().getSelectedIndex();
                try {
                    Randomizer(list, st, rs);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                text.setText(readytoparse);
                text.setVisible(true);
            }
        });

        button2.setOnMousePressed(e -> {
            button2.setGraphic(etp);

        });
        button2.setOnMouseReleased(e -> {
            button2.setGraphic(et);
            pbs.pressAndSound("res/iMusic/mouseclick1.mp3");
            blinkSignal.stop();
            pop.close();
        });

        button3.setOnMousePressed(e -> {
            button3.setGraphic(cyp);

        });
        button3.setOnMouseReleased(e -> {
            button3.setGraphic(cy);
            pbs.pressAndSound("res/iMusic/mouseclick1.mp3");
            blinkSignal.stop();
            deleteCode = 0;
            deleteSignal.play();
        });

        button4.setOnMousePressed(e -> {
            button4.setGraphic(cnp);

        });
        button4.setOnMouseReleased(e -> {
            button4.setGraphic(cn);
            pbs.pressAndSound("res/iMusic/mouseclick1.mp3");
            text.setVisible(false);
            button3.setVisible(false); button4.setVisible(false); cw.setVisible(false); spe.setVisible(false);
            list.setMouseTransparent(false);
            list.setFocusTraversable(true);
        });

        deleteSignal = new Timeline(
                new KeyFrame(Duration.seconds(t),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                switch (deleteCode) {
                                    case 0:
                                        if (sweep == 2) {
                                            b1.setVisible(false);
                                            bl1.setVisible(false);
                                            bl2.setVisible(false);
                                            b2.setVisible(true);
                                            deleteCode = 1;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 1:
                                        if (sweep == 2) {
                                            b2.setVisible(false);
                                            sweep1.setVisible(true);
                                            deleteCode = 2;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 2:
                                        if (sweep == 2) {
                                            sweep1.setVisible(false);
                                            sweep2.setVisible(true);
                                            deleteCode = 3;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 3:
                                        if (sweep == 2) {
                                            sweep2.setVisible(false);
                                            sweep3.setVisible(true);
                                            deleteCode = 4;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 4:
                                        if (sweep == 2) {
                                            sweep3.setVisible(false);
                                            sweep4.setVisible(true);
                                            deleteCode = 5;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 5:
                                        if (sweep == 2) {
                                            sweep4.setVisible(false);
                                            sweep5.setVisible(true);
                                            deleteCode = 6;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 6:
                                        if (sweep == 2) {
                                            sweep5.setVisible(false);
                                            sweep6.setVisible(true);
                                            deleteCode = 7;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 7:
                                        if (sweep == 2) {
                                            sweep6.setVisible(false);
                                            sweep7.setVisible(true);
                                            deleteCode = 8;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 8:
                                        if (sweep == 2) {
                                            sweep7.setVisible(false);
                                            sweep8.setVisible(true);
                                            deleteCode = 9;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 9:
                                        if (sweep == 2) {
                                            sweep8.setVisible(false);
                                            sweep9.setVisible(true);
                                            deleteCode = 10;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 10:
                                        if (sweep == 2) {
                                            sweep9.setVisible(false);
                                            sweep10.setVisible(true);
                                            deleteCode = 11;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 11:
                                        if (sweep == 2) {
                                            sweep10.setVisible(false);
                                            sweep11.setVisible(true);
                                            deleteCode = 12;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 12:
                                        if (sweep == 5) {
                                            sweep11.setVisible(false);
                                            sweep10.setVisible(true);
                                            deleteCode = 13;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 13:
                                        if (sweep == 5) {
                                            sweep10.setVisible(false);
                                            sweep11.setVisible(true);
                                            deleteCode = 14;
                                            sweep = 0;

                                            list.getSelectionModel().clearSelection();
                                            del = new File("saves/" + list.getItems().get(index) + ".bin");
                                            if (del.exists()) del.delete();

                                            list.getItems().remove(index);
                                            if (list.getItems().stream().count() <= 6) {
                                                list.setVisible(false);
                                                tb.setVisible(false);
                                                list.setVisible(true);
                                            }
                                            text.setVisible(false);
                                            button3.setVisible(false); button4.setVisible(false); cw.setVisible(false); spe.setVisible(false);
                                            list.setMouseTransparent(false);
                                            list.setFocusTraversable(true);
                                            if (list.getItems().size() == 0) deltemp.setVisible(true);

                                        } else ++sweep;
                                        break;
                                    case 14:
                                        if (sweep == 4) {
                                            sweep11.setVisible(false);
                                            sweep9.setVisible(true);
                                            deleteCode = 15;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 15:
                                        if (sweep == 2) {
                                            sweep9.setVisible(false);
                                            sweep8.setVisible(true);
                                            deleteCode = 16;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 16:
                                        if (sweep == 2) {
                                            sweep8.setVisible(false);
                                            sweep4.setVisible(true);
                                            deleteCode = 17;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 17:
                                        if (sweep == 2) {
                                            sweep4.setVisible(false);
                                            sweep1.setVisible(true);
                                            deleteCode = 18;
                                            sweep = 0;
                                        } else ++sweep;
                                        break;
                                    case 18:
                                        if (sweep == 2) {
                                            sweep1.setVisible(false);
                                            b1.setVisible(true);
                                            deleteCode = -1;
                                            sweep = 0;
                                            blinkSignal.play();
                                            deleteSignal.stop();
                                        } else ++sweep;
                                }
                            }
                        }));


        blinkSignal = new Timeline(
                new KeyFrame(Duration.seconds(t),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                if (b1.isVisible()) {
                                    if (impt==144) {
                                        b1.setVisible(false);
                                        bl1.setVisible(true);
                                        impt=0;
                                    } else
                                        impt = impt+ (int) Math.random()*3+1;
                                } else
                                    if (bl1.isVisible() && !reverseblink) {
                                        bl1.setVisible(false); bl2.setVisible(true);
                                    } else
                                        if (bl2.isVisible()) {
                                            bl2.setVisible(false); bl1.setVisible(true); reverseblink = true;
                                        } else
                                            if (reverseblink) {
                                                reverseblink = false; b1.setVisible(true); bl1.setVisible(false);
                                            }
                            }
                        }));

        blinkSignal.setCycleCount(Timeline.INDEFINITE);
        blinkSignal.play();
        deleteSignal.setCycleCount(Timeline.INDEFINITE);



        pop.setScene(scene);
        if (flag) {pop.show(); pop.close();} else
        pop.showAndWait();
    }

    private void Randomizer(ListView<String> list, Statement st, ResultSet rs) throws SQLException {
        readytoparse = list.getItems().get(index) + " says: ";
        randomcube = 1 + (int) (100*Math.random());
        sql = "SELECT * FROM FIRST WHERE id=" + randomcube;
        rs = st.executeQuery(sql);
        if (rs.next()) readytoparse = readytoparse + rs.getString(2)+" ";

        randomcube = 1 + (int) (100*Math.random());
        sql = "SELECT * FROM SECOND WHERE id=" + randomcube;
        rs = st.executeQuery(sql);
        if (rs.next()) readytoparse = readytoparse + rs.getString(2)+" ";

        randomcube = 1 + (int) (100*Math.random());
        sql = "SELECT * FROM THIRD WHERE id=" + randomcube;
        rs = st.executeQuery(sql);
        if (rs.next()) readytoparse = readytoparse + rs.getString(2)+" but ";

        randomcube = 1 + (int) (100*Math.random());
        sql = "SELECT * FROM FOURTH WHERE id=" + randomcube;
        rs = st.executeQuery(sql);
        if (rs.next()) readytoparse = readytoparse + rs.getString(2);
    }

    private void CreateList(){
        list = new ListView<>();
        listOfFiles = f.listFiles();
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

        list.setPrefWidth(400);
        list.setPrefHeight(238);
        list.setTranslateX(10);
        list.setTranslateY(10);
        list.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
    }

    private void InitFrames(){
        thumb = new Image("file:res/iLogin/DeleteWindow/Thumb.png");
        tb = new ImageView(thumb);
        popupImage = new Image("file:res/iLogin/DeleteWindow/DeletePopup.png");
        ip = new ImageView(popupImage);
        exit = new Image("file:res/iLogin/DeleteWindow/Exit.png");
        et = new ImageView(exit);
        exitPress = new Image("file:res/iLogin/DeleteWindow/ExitP.png");
        etp = new ImageView(exitPress);
        delete = new Image("file:res/iLogin/DeleteWindow/Delete.png");
        dt = new ImageView(delete);
        deletePress = new Image("file:res/iLogin/DeleteWindow/DeleteP.png");
        dtp = new ImageView(deletePress);
        confirmationWindow = new Image("file:res/iLogin/DeleteWindow/Confirmation/DeleteConfirm.png");
        cw = new ImageView(confirmationWindow);
        confirmationYes = new Image("file:res/iLogin/DeleteWindow/Confirmation/Yes.png");
        cy = new ImageView(confirmationYes);
        confirmationYesP = new Image("file:res/iLogin/DeleteWindow/Confirmation/YesP.png");
        cyp = new ImageView(confirmationYesP);
        confirmationNo = new Image("file:res/iLogin/DeleteWindow/Confirmation/No.png");
        cn = new ImageView(confirmationNo);
        confirmationNoP = new Image("file:res/iLogin/DeleteWindow/Confirmation/NoP.png");
        cnp = new ImageView(confirmationNoP);
        base1 = new Image("file:res/iLogin/DeleteWindow/Animation/basic.png");
        b1 = new ImageView(base1);
        blink1 = new Image("file:res/iLogin/DeleteWindow/Animation/blink/1.png");
        bl1 = new ImageView(blink1);
        blink2 = new Image("file:res/iLogin/DeleteWindow/Animation/blink/2.png");
        bl2 = new ImageView(blink2);
        base2 = new Image("file:res/iLogin/DeleteWindow/Animation/delete.png");
        b2 = new ImageView(base2);
        sweeper1 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/1.png");
        sweep1 = new ImageView(sweeper1);
        sweeper2 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/2.png");
        sweep2 = new ImageView(sweeper2);
        sweeper3 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/3.png");
        sweep3 = new ImageView(sweeper3);
        sweeper4 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/4.png");
        sweep4 = new ImageView(sweeper4);
        sweeper5 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/5.png");
        sweep5 = new ImageView(sweeper5);
        sweeper6 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/6.png");
        sweep6 = new ImageView(sweeper6);
        sweeper7 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/7.png");
        sweep7 = new ImageView(sweeper7);
        sweeper8 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/8.png");
        sweep8 = new ImageView(sweeper8);
        sweeper9 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/9.png");
        sweep9 = new ImageView(sweeper9);
        sweeper10 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/10.png");
        sweep10 = new ImageView(sweeper10);
        sweeper11 = new Image("file:res/iLogin/DeleteWindow/Animation/sweeper/11.png");
        sweep11 = new ImageView(sweeper11);
        speechtriangle = new Image("file:res/iLogin/DeleteWindow/Confirmation/speechtriangle.png");
        spe = new ImageView(speechtriangle);
        deletetemp = new Image("file:res/iLogin/DeleteWindow/DeleteTemp.png");
        deltemp = new ImageView(deletetemp);
    }

    private void InitFramesXY(){
        deltemp.setTranslateX(12); deltemp.setTranslateY(12);
        text.setTranslateX(202); text.setTranslateY(355);
        sweep1.setTranslateX(92); sweep1.setTranslateY(237);
        sweep2.setTranslateX(92); sweep2.setTranslateY(237);
        sweep3.setTranslateX(92); sweep3.setTranslateY(237);
        sweep4.setTranslateX(92); sweep4.setTranslateY(237);
        sweep5.setTranslateX(92); sweep5.setTranslateY(237);
        sweep6.setTranslateX(92); sweep6.setTranslateY(237);
        sweep7.setTranslateX(92); sweep7.setTranslateY(237);
        sweep8.setTranslateX(92); sweep8.setTranslateY(237);
        sweep9.setTranslateX(92); sweep9.setTranslateY(237);
        sweep10.setTranslateX(92); sweep10.setTranslateY(237);
        sweep11.setTranslateX(92); sweep11.setTranslateY(237);
        tb.setTranslateX(394); tb.setTranslateY(12);
        b1.setTranslateX(92); b1.setTranslateY(237);
        b2.setTranslateX(92); b2.setTranslateY(237);
        bl1.setTranslateX(92); bl1.setTranslateY(237);
        bl2.setTranslateX(92); bl2.setTranslateY(237);
        cw.setTranslateX(195); cw.setTranslateY(257);
        spe.setTranslateX(179); spe.setTranslateY(323);
    }

    private void TextCreator(){
        text = new Text();
        text.setFill(Color.rgb(33,66,100));
        text.setFont(Font.font("Verdana", FontWeight.BOLD, 8));
        text.setVisible(false);
        text.setWrappingWidth(315);
    }

    private void Visibility(){
        deltemp.setVisible(false);
        if (list.getItems().size() == 0) deltemp.setVisible(true);
        sweep1.setVisible(false); sweep2.setVisible(false); sweep3.setVisible(false); sweep4.setVisible(false);
        sweep5.setVisible(false); sweep6.setVisible(false); sweep7.setVisible(false); sweep8.setVisible(false);
        sweep9.setVisible(false); sweep10.setVisible(false); sweep11.setVisible(false);
        bl1.setVisible(false);
        bl2.setVisible(false);
        b2.setVisible(false);
        spe.setVisible(false);
    }

}
