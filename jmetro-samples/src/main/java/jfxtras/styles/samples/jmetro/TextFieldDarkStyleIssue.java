package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.JMetroStyleClass;
import jfxtras.styles.jmetro.Style;

public class TextFieldDarkStyleIssue extends Application {

    public final static int LOGIN_WINDOW_WIDTH = 720;
    public final static int LOGIN_WINDOW_HEIGHT = 348;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("textFieldDarkStyleIssue.fxml"));

        root.getStyleClass().add(JMetroStyleClass.BACKGROUND);
        primaryStage.setTitle("Hello World");

        Scene mainScene = new Scene(root, LOGIN_WINDOW_WIDTH, LOGIN_WINDOW_HEIGHT);
        JMetro jMetro = new JMetro(Style.DARK);
        jMetro.setScene(mainScene);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
