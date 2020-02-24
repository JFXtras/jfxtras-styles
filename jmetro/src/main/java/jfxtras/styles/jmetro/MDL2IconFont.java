package jfxtras.styles.jmetro;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class MDL2IconFont extends Label {

    public MDL2IconFont() {
        init();
    }

    public MDL2IconFont(String text) {
        super(text);
        init();
    }

    private void init() {
        setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        getStyleClass().addAll("icon-font", "mdl2-assets");
    }

    public void setSize(int pixelSize) {
        Font font = getFont();
        Font newFont = new Font(font.getName(), pixelSize);
        setFont(newFont);
    }
}
