package ui.screens.audioplayer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import model.domainobjects.GameData;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AudioPlayerPresenter implements Initializable {

    // JavaFX injected node variables
    @FXML
    private MediaView mediaView;
    @FXML
    private Slider progressSlider;
    @FXML
    private Label currentTimeLbl;
    @FXML
    private Label fullTimeLbl;


    // Other variables
    private GameData gameData;
    private String fileString;
    private Media audioMedia;
    private MediaPlayer audioMediaPlayer;
    private SimpleIntegerProperty audioProgress;

    // Constructors

    // Getters & Setters
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }


    public void setFileString(String fileString) {
        this.fileString = fileString;
        Path path = Paths.get(gameData.getAudioDirectory().toString(), fileString);
        audioMedia = new Media(new File(path.toString()).toURI().toString());
        audioMediaPlayer = new MediaPlayer(audioMedia);
        audioMediaPlayer.currentTimeProperty().addListener((Observable ov) -> {
            Duration currentTime = audioMediaPlayer.getCurrentTime();
            Duration endTime = audioMediaPlayer.getStopTime();
            currentTimeLbl.setText(formatDuration(currentTime));
            progressSlider.setValue(currentTime.divide(endTime.toMillis()).toMillis() * 100);
        });
        fullTimeLbl.setText(formatDuration(audioMediaPlayer.getTotalDuration()));
        mediaView.setMediaPlayer(audioMediaPlayer);
        /*progressSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(progressSlider.isValueChanging()) {
                    if(duration)
                }
            }
        });*/
    }

    // Initialization methods
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    // UI event methods
    public void startAudio() {
        audioMediaPlayer.seek(audioMediaPlayer.getStartTime());
        audioMediaPlayer.play();
    }

    public void pauseAudio() {
        if(audioMediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            audioMediaPlayer.play();
        } else if(audioMediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            audioMediaPlayer.pause();
        }
    }

    public void stopAudio() {
        audioMediaPlayer.stop();
    }

    // Other methods
    private String formatDuration(Duration duration) {
        int i_mins = 0, i_secs = 0;
        String s_mins = "00", s_secs = "00";
        if(duration.toMinutes() >= 1) {
            i_mins = (int)duration.toMinutes();
        }
        if(i_mins < 10) {
            s_mins = "0" + i_mins;
        } else if(i_mins < 1) {
        } else {
            s_mins = String.valueOf(i_mins);
        }

        if(duration.toSeconds() >=1) {
            i_secs = (int)duration.toSeconds();
        }
        if(i_secs < 10) {
            s_secs = "0" + i_secs;
        } else if(i_secs < 1) {
        } else {
            s_secs = String.valueOf(i_secs);
        }

        return s_mins + ":" + s_secs;
    }


}