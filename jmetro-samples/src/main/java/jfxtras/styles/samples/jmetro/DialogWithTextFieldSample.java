package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.*;

import java.util.Optional;

public class DialogWithTextFieldSample extends Application {
    private ToggleButton withOwner;
    private ComboBox<Style> styleComboBox;

    private static final Style STYLE = Style.LIGHT;

    public static void main(String[] args) {
        launch(args);
    }

    public DialogWithTextFieldSample() {
    }

    @Override
    public void start(Stage stage) {
        JMetro mainStageJMetro = new JMetro(Style.LIGHT);

        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("background");
        ToolBar topLeftToolbar = new ToolBar();

        withOwner = new ToggleButton("With Owner");

        styleComboBox = new ComboBox<>();
        styleComboBox.getItems().addAll(Style.LIGHT, Style.DARK);
        styleComboBox.setValue(Style.LIGHT);

        topLeftToolbar.getItems().addAll(withOwner, styleComboBox);

        ToolBar topRightToolBar = new ToolBar();
        ComboBox<Style> mainStageStyleComboBox = new ComboBox<>();
        mainStageStyleComboBox.getItems().addAll(Style.LIGHT, Style.DARK);
        mainStageStyleComboBox.setValue(mainStageJMetro.getStyle());
        mainStageStyleComboBox.valueProperty().addListener(observable -> mainStageJMetro.setStyle(mainStageStyleComboBox.getValue()));
        topRightToolBar.getItems().add(mainStageStyleComboBox);

        BorderPane topToolBarBorderPane = new BorderPane();
        topToolBarBorderPane.getStyleClass().add("background");
        topToolBarBorderPane.setLeft(topLeftToolbar);
        topToolBarBorderPane.setRight(topRightToolBar);

        borderPane.setTop(topToolBarBorderPane);

        VBox vBox = new VBox();
        vBox.getStyleClass().add("background");
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);

        HBox jmetroDialogs = new HBox();
        jmetroDialogs.setSpacing(4);
        Button jMetroDialog = new Button("Show Dialog");
        jMetroDialog.setOnAction(actionEvent -> showJMetroDialog(stage));

        jmetroDialogs.getChildren().addAll(jMetroDialog);
        jmetroDialogs.getStyleClass().add("background");
        jmetroDialogs.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(jmetroDialogs);
        VBox.setMargin(jmetroDialogs, new Insets(30, 0, 0, 0));

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 900, 250);

        mainStageJMetro.setScene(scene);

        stage.setTitle("Dialog Sample");
        stage.setScene(scene);
        stage.show();
    }

    private void showJMetroDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());
        FlatDialog<ButtonType> dialog = new FlatDialog<>();
        dialog.setHeaderText("Test Textfield Inside Dialog");

        VBox box = new VBox();

        CheckBox enableCheckBox = new CheckBox("Enable TextField");
        TextField textField = new TextField();
        box.getChildren().addAll(enableCheckBox, textField);

        textField.disableProperty().bind(enableCheckBox.selectedProperty().not());

        dialog.getDialogPane().setContent(box);

        if (withOwner.isSelected()) {
            dialog.initOwner(owner);
        } else {
            jMetro.setScene(dialog.getDialogPane().getScene());
        }

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        result.ifPresent(buttonType -> System.out.println("Result is = " + buttonType));
    }
}