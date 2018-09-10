package jfxtras.styles.jmetro8;

import org.jfxtras.styles.jmetro8.JMetro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TextFieldSample extends Application {

	private static final JMetro.Style STYLE = JMetro.Style.LIGHT;

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(
				getClass()
				.getResource("/jfxtras/styles/jmetro8/JMetro TextField.fxml"));
		
		Scene scene = new Scene(root);
		new JMetro(STYLE).applyTheme(scene);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("TextField Sample");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
