package jfxtras.styles.jmetro;

import java.util.List;

public class JMetroStyleClass {

    /***************************************************************************
     *                                                                         *
     *                           Constants                                     *
     *                                                                         *
     **************************************************************************/

    /**
     * StyleClass that defines a node as a background. Its color will change according to whether JMetro is set with
     * Style.LIGHT or Style.DARK. You should use this in any Pane that is a background in your application.
     */
    public static final String BACKGROUND = "background";

    /**
     * StyleClass that defines a new alternate style for TabPanes. Add this styleclass to a TabPane and it will show
     * an alternate style where the selected tab has an underline.
     */
    public static final String UNDERLINE_TAB_PANE = "underlined";

    /**
     * StyleClass that defines an alternate style for Buttons, ToggleButtons, MenuButtons and SplitMenuButtons. With this style
     * these controls look less prominent and more integrated with their container (for instance, they don't show a border around them).
     */
    public static final String LIGHT_BUTTONS = "light";

    /*===========================================================================*/
    /*                      Table like controls StyleClasses                     */
    /*===========================================================================*/

    /**
     * StyleClass that defines an alternate style for TableView, TreeTableView, TreeView and ListViews. With this style these
     * controls will have their rows with alternate colors (i.e. one color for even rows another color for odd rows).
     * This has the advantage of increasing the legibility of these controls especially when this controls with cells are data
     * heavy and with the control has a larger width.
     */
    public static final String ALTERNATING_ROW_COLORS = "alternating-row-colors";

    /**
     * Styleclass that adds grid lines to table like controls
     */
    public static final String TABLE_GRID_LINES = "column-grid-lines";

    /***************************************************************************
     *                                                                         *
     *                           Methods                                       *
     *                                                                         *
     **************************************************************************/

    /**
     * Adds a style class to a List if it is not already there.
     * The List is usually the styleClass collection of a JavaFX Node.
     *
     * @param collection the collection of styleclasses
     * @param styleclass the styleclass to add
     */
    public static void addIfNotPresent(List<String> collection, String styleclass) {
        if (!collection.contains(styleclass)) {
            collection.add(styleclass);
        }
    }
}
