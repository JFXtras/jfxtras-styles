package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class TextNodesSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Style startingStyle = Style.LIGHT;
        JMetro jMetro = new JMetro(startingStyle);

        BorderPane mainContainer = new BorderPane();
        mainContainer.getStyleClass().add("background");

        VBox vBox = new VBox();
        vBox.getStyleClass().add("background");

        stage.setTitle("Text Sample");
        Scene scene = new Scene(mainContainer, 400, 350);

        Label label = new Label("This is a label");
        VBox.setMargin(label, new Insets(30, 0, 0, 0));

        Text text = new Text("This is a Text node");
        TextFlow textFlow = new TextFlow(text);

        vBox.getChildren().addAll(label, textFlow);
        vBox.setSpacing(10);

        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(startingStyle);
        jmetroStyleComboBox.valueProperty().addListener(observable -> jMetro.setStyle(jmetroStyleComboBox.getValue()));

        mainContainer.setTop(jmetroStyleComboBox);
        mainContainer.setCenter(vBox);

        jMetro.setScene(scene);

        stage.setScene(scene);
        stage.show();
    }
}
