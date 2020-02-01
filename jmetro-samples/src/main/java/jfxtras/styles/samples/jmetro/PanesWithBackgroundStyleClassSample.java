package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import jfxtras.styles.samples.jmetro.panessample.PanesWithBackgroundStyleClassController;

public class PanesWithBackgroundStyleClassSample extends Application {
    private static final String PANES_RESOURCE = "JMetro PanesWithBackgroundStyleClass.fxml";

    static final private Style STYLE = Style.LIGHT;

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.setProperty("prism.lcdtext", "false");

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(PanesWithBackgroundStyleClassSample.class.getResource(PANES_RESOURCE).openStream());
        PanesWithBackgroundStyleClassController controller = fxmlLoader.getController();
        primaryStage.setTitle("JMetro");

        JMetro jMetro = new JMetro(root, STYLE);

        controller.init(jMetro);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}

