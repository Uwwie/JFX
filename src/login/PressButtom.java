package login;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

// play sound (path - string s)
// used for buttons only

public class PressButtom {

    Media media;
    MediaPlayer mediaplayer;
    File f;

    public void pressAndSound(String s){
        f = new File(s);
        media = new Media(f.toURI().toString());
        mediaplayer = new MediaPlayer(media);
        mediaplayer.setVolume(0.1);
        mediaplayer.play();
    }
}



