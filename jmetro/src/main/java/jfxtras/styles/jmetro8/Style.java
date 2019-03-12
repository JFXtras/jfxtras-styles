package jfxtras.styles.jmetro8;

public enum Style {
    LIGHT,
    DARK;

    protected String getStyleSheetFileName() {
        String stylesheet = null;

        switch (this) {
            case LIGHT:
                stylesheet = "JMetroLightTheme.css";
                break;
            case DARK:
                stylesheet = "JMetroDarkTheme.css";
                break;
        }

        return stylesheet;
    }
}
