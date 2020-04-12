package jfxtras.styles.jmetro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ColorPickerThrowsRuntimeException extends Application {

    @Override
        public void start(Stage stage) {
            System.setProperty("prism.lcdtext", "false");

            Scene scene = new Scene(new Pane(new ColorPicker()), 800, 600);
            stage.setScene(scene);
            JMetro jMetro = new JMetro(scene, Style.DARK);
            stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}

