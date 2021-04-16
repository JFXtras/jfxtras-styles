module org.jfxtras.styles.samples.jmetro {
    /* requires */
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.swing;
    requires javafx.graphics;

    requires java.logging;

    requires org.controlsfx.controls;

    requires org.jfxtras.styles.jmetro;

    /* exports */
    exports jfxtras.styles.samples.jmetro to javafx.graphics;
    exports jfxtras.styles.samples.jmetro.themetester to javafx.graphics;

    opens jfxtras.styles.samples.jmetro.controlssample to javafx.fxml;
    opens jfxtras.styles.samples.jmetro.panessample to javafx.fxml;
    opens jfxtras.styles.samples.jmetro to javafx.base;
    opens jfxtras.styles.samples.jmetro.themetester;

    /* Scenic View */
//    requires org.scenicview.scenicview;
}