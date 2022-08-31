package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;

public class DatePickerAlignmentTestSample extends Application {

    @Override
    public void start(Stage stage) {
        System.setProperty("prism.lcdtext", "false");

        DatePicker datePicker1 = new DatePicker();
        DatePicker datePicker2 = new DatePicker();

        HBox container = new HBox(datePicker1, datePicker2);
        container.getStyleClass().add(JMetroStyleClass.BACKGROUND);
        Scene scene = new Scene(container, 800, 600);
        stage.setScene(scene);
        new JMetro(scene, Style.LIGHT);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}