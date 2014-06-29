package jfxtras.styles.jmetro8;

import com.sun.javafx.scene.text.TextLayout;
import com.sun.javafx.tk.Toolkit;
import javafx.scene.text.Font;
import javafx.scene.text.TextBoundsType;

/**
 * Created by pedro_000 on 6/28/2014.
 */
public class Utils {

    /* Using TextLayout directly for simple text measurement.
     * Instead of restoring the TextLayout attributes to default values
     * (each renders the TextLayout unable to efficiently cache layout data).
     * It always sets all the attributes pertinent to calculation being performed.
     * Note that lineSpacing and boundsType are important when computing the height
     * but irrelevant when computing the width.
     *
     * Note: This code assumes that TextBoundsType#VISUAL is never used by controls.
     * */
    private static final TextLayout layout = Toolkit.getToolkit().getTextLayoutFactory().createLayout();

    public static double computeTextWidth(Font font, String text, double wrappingWidth) {
        layout.setContent(text != null ? text : "", font.impl_getNativeFont());
        layout.setWrapWidth((float)wrappingWidth);
        return layout.getBounds().getWidth();
    }

    public static double computeTextHeight(Font font, String text, double wrappingWidth, double lineSpacing, TextBoundsType boundsType) {
        layout.setContent(text != null ? text : "", font.impl_getNativeFont());
        layout.setWrapWidth((float)wrappingWidth);
        layout.setLineSpacing((float)lineSpacing);
        if (boundsType == TextBoundsType.LOGICAL_VERTICAL_CENTER) {
            layout.setBoundsType(TextLayout.BOUNDS_CENTER);
        } else {
            layout.setBoundsType(0);
        }
        return layout.getBounds().getHeight();
    }

}
