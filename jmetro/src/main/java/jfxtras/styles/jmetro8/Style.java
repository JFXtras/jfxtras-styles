package jfxtras.styles.jmetro8;

public enum Style {
    LIGHT,
    DARK;

    protected String getStyleSheetFileName() {
        String stylesheet = null;

        if (this == Style.LIGHT) stylesheet = "JMetroLightTheme.css";
        else if (this == Style.DARK) stylesheet = "JMetroDarkTheme.css";

        return stylesheet;
    }
}
