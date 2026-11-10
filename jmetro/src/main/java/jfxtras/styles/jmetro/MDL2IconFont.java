package jfxtras.styles.jmetro;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

/** A label styled to display glyphs from the Segoe MDL2 Assets font. */
public class MDL2IconFont extends Label {

    /** Creates an icon label with no initial text. */
    public MDL2IconFont() {
        init();
    }

    /**
     * Creates an icon label with the supplied glyph text.
     *
     * @param text the glyph or text to display
     */
    public MDL2IconFont(String text) {
        super(text);
        init();
    }

    private void init() {
        setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        getStyleClass().addAll("icon-font", "mdl2-assets");
    }

    /**
     * Changes the font size while retaining the current font name.
     *
     * @param pixelSize the requested font size in pixels
     */
    public void setSize(int pixelSize) {
        Font font = getFont();
        Font newFont = new Font(font.getName(), pixelSize);
        setFont(newFont);
    }
}
