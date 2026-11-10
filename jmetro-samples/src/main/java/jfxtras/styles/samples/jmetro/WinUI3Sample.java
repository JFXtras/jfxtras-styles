package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import org.controlsfx.control.ToggleSwitch;

/** A live comparison of the Windows 10 and Windows 11 theme variants. */
public class WinUI3Sample extends Application {
    @Override
    public void start(Stage stage) {
        JMetro jMetro = new JMetro(Style.WIN11_LIGHT);
        ComboBox<Style> styles = new ComboBox<>();
        styles.getItems().setAll(Style.values());
        styles.setValue(jMetro.getStyle());
        jMetro.styleProperty().bind(styles.valueProperty());

        Label title = new Label("Windows design");
        title.getStyleClass().add("header");
        Button primary = new Button("Apply");
        primary.setDefaultButton(true);
        Button secondary = new Button("Cancel");
        Button disabled = new Button("Unavailable");
        disabled.setDisable(true);
        ToggleButton toggle = new ToggleButton("Pinned");
        toggle.setSelected(true);
        CheckBox check = new CheckBox("Notifications");
        check.setSelected(true);
        RadioButton radio = new RadioButton("Recommended");
        radio.setSelected(true);
        ToggleSwitch toggleSwitch = new ToggleSwitch("Sync settings");
        toggleSwitch.setSelected(true);
        TextField name = new TextField();
        name.setPromptText("Your name");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        ComboBox<String> options = new ComboBox<>();
        options.getItems().addAll("Personal", "Work", "School");
        options.getSelectionModel().selectFirst();
        DatePicker datePicker = new DatePicker();
        MenuButton menu = new MenuButton("Actions", null, new MenuItem("New"), new MenuItem("Open"));
        ListView<String> list = new ListView<>();
        list.getItems().addAll("General", "Appearance", "Privacy", "Accessibility");
        list.getSelectionModel().select(1);
        list.setPrefHeight(160);
        TextArea notes = new TextArea();
        notes.setPromptText("Notes");
        notes.setPrefRowCount(2);
        VBox root = new VBox(16, new HBox(12, new Label("Style"), styles), title,
                new HBox(12, primary, secondary, disabled, toggle),
                new HBox(16, check, radio, toggleSwitch),
                new HBox(12, name, password),
                new HBox(12, options, datePicker, menu),
                new Slider(0, 100, 40), new ProgressBar(0.65), list, notes);
        root.setPadding(new Insets(28));
        root.getStyleClass().add("background");
        Scene scene = new Scene(root, 760, 650);
        jMetro.setScene(scene);
        stage.setTitle("JMetro - Windows 10 / WinUI 3");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
