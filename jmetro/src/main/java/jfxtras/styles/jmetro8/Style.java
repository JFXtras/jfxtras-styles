package jfxtras.styles.jmetro8;

public enum Style {
    LIGHT,
    DARK;

    protected String getStyleSheetFileName() {
        if (this == Style.LIGHT) return "JMetroLightTheme.css";
        else if (this == Style.DARK) return "JMetroDarkTheme.css";

        return "JMetroLightTheme.css";
    }
}
