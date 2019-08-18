package jfxtras.styles.samples.jmetro.controlssample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class ProgressBarController implements Initializable {
    @FXML
    private ProgressBar determinateProgressBar;
    @FXML
    private Label progressLabel;

    private double progress = 0.1;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        progress = 0;

        EventHandler<ActionEvent> onTimeChanged = event -> {
            progress += 0.01;
            progress = progress % 1;
            determinateProgressBar.setProgress(progress);
        };
        InvalidationListener onProgressChanged = observable -> progressLabel.setText(String.format("%.0f", determinateProgressBar.getProgress() * 100));

        determinateProgressBar.progressProperty().addListener(onProgressChanged);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(50), onTimeChanged));
        timeline.play();
    }
}
