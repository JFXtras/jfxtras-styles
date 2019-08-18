package jfxtras.styles.samples.jmetro.controlssample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressIndicatorController implements Initializable {
    @FXML
    private ProgressIndicator determinateProgressIndicator;

    private double progress = 0.1;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        progress = 0;

        EventHandler<ActionEvent> onTimeChanged = event -> {
            progress += 0.01;
            progress = progress % 1;
            determinateProgressIndicator.setProgress(progress);
        };

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50), onTimeChanged));
        timeline.play();
    }
}
