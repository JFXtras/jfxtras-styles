package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class TextFieldTextSample extends Application {

    private static final Style STYLE = Style.LIGHT;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        System.setProperty("prism.lcdtext", "false");

        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        stage.setTitle("Table View Sample");
        stage.setWidth(650);
        stage.setHeight(450);

        TextField textField = new TextField();

        Button textFieldSetText = new Button("Set text");
        textFieldSetText.setOnAction(event -> textField.setText("Some text"));
        Button textFieldDisable = new Button("Disable");
        textFieldDisable.setOnAction(event -> textField.setDisable(!textField.isDisabled()));

        vbox.setSpacing(40);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        VBox controlsVBox = new VBox();
        controlsVBox.getChildren().addAll(textFieldSetText, textFieldDisable);
        controlsVBox.setSpacing(10);

        vbox.getChildren().addAll(textField, controlsVBox);

        new JMetro(scene, STYLE);

        stage.setScene(scene);
        stage.show();
    }
}

