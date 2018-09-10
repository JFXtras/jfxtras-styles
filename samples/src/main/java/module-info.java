module jfxtras.samples {
	exports jfxtras.styles.jmetro8.controlssample;
	exports jfxtras.styles.jmetro8;
	opens jfxtras.styles.jmetro8.controlssample;
	opens jfxtras.styles.jmetro8;

	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	
	requires controlsfx;
	requires org.jfxtras.jmetro;
}