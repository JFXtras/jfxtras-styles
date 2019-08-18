module org.jfxtras.styles.jmetro {
    requires transitive javafx.controls;
    requires static org.controlsfx.controls;

    exports jfxtras.styles.jmetro;
    exports impl.jfxtras.styles.jmetro to javafx.controls;

    opens jfxtras.styles.jmetro;
}