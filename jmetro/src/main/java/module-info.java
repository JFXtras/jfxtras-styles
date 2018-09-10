module org.jfxtras.jmetro {
	exports org.jfxtras.styles.jmetro8;
	exports org.jfxtras.styles.jmetro8.impl to javafx.controls;
	
	requires transitive controlsfx;
	
	requires javafx.base;
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
}