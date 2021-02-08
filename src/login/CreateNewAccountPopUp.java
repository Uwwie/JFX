package login;
import data.Code;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Random;


public class CreateNewAccountPopUp {
    private static int cube = 0;
    private static int chooseExclamation;
    private static Random random = new Random();
    private Image popupImage, confirm, confirmPress, exit, exitPress, exclamation1, exclamation2,
            exclamation3, exclamation4, exclamation5, exclamation6, exclamation7, exclamation8, exclamation9;
    private ImageView cm, cmp, et, etp, ex1, ex2, ex3, ex4, ex5, ex6, ex7, ex8, ex9, ip;
    Code createUser;
    TextField userTextField;


    public void popup(Stage parentStage, ListView<String> list){

        // Create popup window and upload pics

        createUser = new Code();
        Stage pop = new Stage();
        PressButtom pbs = new PressButtom();
        pop.initOwner(parentStage);
        pop.initStyle(StageStyle.TRANSPARENT);
        pop.initModality(Modality.NONE);
        pop.setTitle("");
        InitFrames();
        InitFrameXY();
        Group root = new Group();
        root.getChildren().addAll(ip,ex1,ex2,ex3,ex4,ex5,ex6,ex7,ex8,ex9);

        Button button1 = new Button();
        button1.setStyle("-fx-background-color: transparent;");
        button1.setTranslateX(63);
        button1.setTranslateY(120);
        button1.setPrefSize(1,1);
        button1.setGraphic(cm);
        root.getChildren().add(button1);

        Button button2 = new Button();
        button2.setStyle("-fx-background-color: transparent;");
        button2.setTranslateX(193);
        button2.setTranslateY(120);
        button2.setPrefSize(1,1);
        button2.setGraphic(et);
        root.getChildren().add(button2);

        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);
        grid1.setPadding(new Insets(60, 1, 1, 83));
        userTextField = new TextField();
        userTextField.setPrefWidth(140);
        userTextField.setMaxWidth(140);
        userTextField.setStyle("-fx-font-size: 15; -fx-font-weight:bold; -fx-border-color: pink; -fx-background-color: mistyrose; -fx-text-inner-color: darkorange;");
        userTextField.setAlignment(Pos.CENTER);
        grid1.add(userTextField, 1, 1);

        userTextField.textProperty().addListener(new ChangeListener<String>() {
            public void changed(final ObservableValue<? extends String> observableValue, final String oldValue,
                                final String newValue) {
                ex2.setVisible(false);
                if (newValue.length() > 10)
                    userTextField.setText(oldValue);
            }});

        button1.setOnMousePressed(e -> {
            button1.setGraphic(cmp);

        });
        button1.setOnMouseReleased(e -> {
            button1.setGraphic(cm);
            pbs.pressAndSound("res/iMusic/mouseclick1.mp3");

            createUser.verify();
            AccountVerification(list);
        });

        button2.setOnMousePressed(e -> {
            button2.setGraphic(etp);

        });
        button2.setOnMouseReleased(e -> {
            button2.setGraphic(et);
            pbs.pressAndSound("res/iMusic/mouseclick1.mp3");
            pop.close();
        });

        root.getChildren().add(grid1);
        Scene scene = new Scene(root,395,278);
        scene.setFill(Color.TRANSPARENT);
        pop.setScene(scene);
        pop.showAndWait();
    }


    private void InitFrames(){
        popupImage = new Image("file:res/iLogin/CreationWindow/CreatePopup.png");
        ip = new ImageView(popupImage);
        confirm = new Image("file:res/iLogin/CreationWindow/Confirm.png");
        cm = new ImageView(confirm);
        confirmPress = new Image("file:res/iLogin/CreationWindow/ConfirmP.png");
        cmp = new ImageView(confirmPress);
        exit = new Image("file:res/iLogin/CreationWindow/Exit.png");
        et = new ImageView(exit);
        exitPress = new Image("file:res/iLogin/CreationWindow/ExitP.png");
        etp = new ImageView(exitPress);
        exclamation1 = new Image("file:res/iLogin/CreationWindow/Exclamations/1.png");
        ex1 = new ImageView(exclamation1);
        exclamation2 = new Image("file:res/iLogin/CreationWindow/Exclamations/2.png");
        ex2 = new ImageView(exclamation2);
        exclamation3 = new Image("file:res/iLogin/CreationWindow/Exclamations/3.png");
        ex3 = new ImageView(exclamation3);
        exclamation4 = new Image("file:res/iLogin/CreationWindow/Exclamations/4.png");
        ex4 = new ImageView(exclamation4);
        exclamation5 = new Image("file:res/iLogin/CreationWindow/Exclamations/5.png");
        ex5 = new ImageView(exclamation5);
        exclamation6 = new Image("file:res/iLogin/CreationWindow/Exclamations/6.png");
        ex6 = new ImageView(exclamation6);
        exclamation7 = new Image("file:res/iLogin/CreationWindow/Exclamations/7.png");
        ex7 = new ImageView(exclamation7);
        exclamation8 = new Image("file:res/iLogin/CreationWindow/Exclamations/8.png");
        ex8 = new ImageView(exclamation8);
        exclamation9 = new Image("file:res/iLogin/CreationWindow/Exclamations/9.png");
        ex9 = new ImageView(exclamation9);
    }


    private void InitFrameXY(){
        ex1.setTranslateX(11); ex1.setTranslateY(160);
        ex2.setTranslateX(11); ex2.setTranslateY(160);
        ex3.setTranslateX(11); ex3.setTranslateY(160);
        ex4.setTranslateX(11); ex4.setTranslateY(160);
        ex5.setTranslateX(11); ex5.setTranslateY(160);
        ex6.setTranslateX(11); ex6.setTranslateY(160);
        ex7.setTranslateX(11); ex7.setTranslateY(160);
        ex8.setTranslateX(11); ex8.setTranslateY(160);
        ex9.setTranslateX(11); ex9.setTranslateY(160);

        ex1.setVisible(false);
        ex2.setVisible(false);
        ex3.setVisible(false);
        ex4.setVisible(false);
        ex5.setVisible(false);
        ex6.setVisible(false);
        ex7.setVisible(false);
        ex8.setVisible(false);
        ex9.setVisible(false);
    }

    private void AccountVerification(ListView<String> list){
        if (createUser.singularity(userTextField.getText()) == false) CaseAll(1);
         else
        if ((userTextField.getText().length()>3) &&
                (!(Character.toString(userTextField.getText().charAt(0)).equals(" ") ||
                        Character.toString(userTextField.getText().charAt(userTextField.getText().length()-1)).equals(" "))))
        {
            createUser.createAccount(userTextField.getText()); ex1.setVisible(false); ex2.setVisible(false);
            list.getItems().add(userTextField.getText());
            ++cube;
            if (cube>=7) CaseAll(2);
             else CaseAll(3);

        }
        else if (!(userTextField.getText().length()>3) || (Character.toString(userTextField.getText().charAt(0)).equals(" ") ||
                Character.toString(userTextField.getText().charAt(userTextField.getText().length()-1)).equals(" "))) CaseAll(4);
    }

    private void PrintImage(){
        switch (chooseExclamation) {
            case 4: ex4.setVisible(true);
                break;
            case 5: ex5.setVisible(true);
                break;
            case 6: ex6.setVisible(true);
                break;
            case 7: ex7.setVisible(true);
                break;
            case 8: ex8.setVisible(true);
                break;
            case 9: ex9.setVisible(true);
        }
    }

    private void CaseAll(int h){
        switch (h){
            case 1:
                ex1.setVisible(true); ex2.setVisible(false); ex3.setVisible(false); ex4.setVisible(false); ex5.setVisible(false);
                ex6.setVisible(false); ex7.setVisible(false); ex8.setVisible(false); ex9.setVisible(false);
                cube = 0;
                break;
            case 2:
                cube = 0;
                ex3.setVisible(true); ex4.setVisible(false); ex5.setVisible(false); ex1.setVisible(false); ex2.setVisible(false);
                ex6.setVisible(false); ex7.setVisible(false); ex8.setVisible(false); ex9.setVisible(false);
                break;
            case 3:
                ex3.setVisible(false); ex4.setVisible(false); ex5.setVisible(false); ex1.setVisible(false); ex2.setVisible(false);
                ex6.setVisible(false); ex7.setVisible(false); ex8.setVisible(false); ex9.setVisible(false);
                chooseExclamation = 4 + (int) (6*Math.random());
                PrintImage();
                break;
            case 4:
                ex2.setVisible(true); ex1.setVisible(false); ex3.setVisible(false); ex4.setVisible(false); ex5.setVisible(false);
                ex6.setVisible(false); ex7.setVisible(false); ex8.setVisible(false); ex9.setVisible(false);
                cube = 0;
        }
    }


}
