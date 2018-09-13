package jfxtras.styles.jmetro8;

import org.jfxtras.styles.jmetro8.JMetro;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DatePickerSample extends Application {

	private static final JMetro.Style STYLE = JMetro.Style.DARK;

	@Override
	public void start(Stage primaryStage) throws Exception {
		AnchorPane root = FXMLLoader.load(
				getClass()
				.getResource("/jfxtras/styles/jmetro8/JMetro DatePicker.fxml"));
		
		Scene scene = new Scene(root);
		new JMetro(STYLE).applyTheme(scene);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Date Picker Sample");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
