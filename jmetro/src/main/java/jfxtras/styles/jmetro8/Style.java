package jfxtras.styles.jmetro8;

/**
 * Defined the JMetro style. This can be either dark or light.
 */
public enum Style {
    LIGHT,
    DARK;

    private static final String LIGHT_STYLE_SHEET_URL = Style.class.getResource("JMetroLightTheme.css").toExternalForm();
    private static final String DARK_STYLE_SHEET_URL = Style.class.getResource("JMetroDarkTheme.css").toExternalForm();

    /**
     * Returns the stylesheet that defines the variable override that make up this {@Link Style} instance.
     *
     * @return returns the stylesheet that defines the variable override that make up this {@Link Style} instance.
     */
    public String getStyleStylesheetURL() {
        String stylesheet = null;
        switch (this) {
            case LIGHT:
                stylesheet = LIGHT_STYLE_SHEET_URL;
                break;
            case DARK:
                stylesheet = DARK_STYLE_SHEET_URL;
                break;
        }
        return stylesheet;
    }
}
