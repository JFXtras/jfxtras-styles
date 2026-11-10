package jfxtras.styles.jmetro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A dialog with a flat style.
 * The title is initially empty and a white placeholder icon is added by default.
 * The {@link #iconlessProperty() iconless} property controls whether that icon
 * is added again when the window's icon list changes.
 *
 * @param <R> The return type of the dialog, via the result property.
 */
public class FlatDialog<R> extends Dialog<R> {
    private BooleanProperty iconless = new SimpleBooleanProperty(true);

    private boolean isResettingIcon;

    /**
     * Clears a dialog's title and optionally adds the placeholder icon.
     *
     * @param dialog the dialog to initialize
     * @param iconless whether to add the white placeholder icon
     */
    public static void initDialog(Dialog<?> dialog, boolean iconless) {
        dialog.setTitle("");
        initDialogIcon(dialog, iconless);
    }

    /**
     * Adds the white placeholder icon to the dialog's stage if requested.
     * Existing icons are not removed.
     *
     * @param dialog the dialog whose stage receives the icon
     * @param isIconless whether to add the placeholder icon
     */
    public static void initDialogIcon(Dialog<?> dialog, boolean isIconless) {
        if (!isIconless) {
            return;
        }
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        Image whiteIcon = new Image(JMetro.class.getResource("whiteIcon.png").toExternalForm());
        stage.getIcons().add(whiteIcon);
    }

    /** Creates a dialog with an empty title and a placeholder icon. */
    public FlatDialog() {
        init();
    }

    private void init() {
        initDialog(this, isIconless());
        setupDialogIconsListener();
    }

    private void setupDialogIconsListener() {
        Stage stage = (Stage) getDialogPane().getScene().getWindow();
        stage.getIcons().addListener(this::dialogIconsChanged);
    }

    private void dialogIconsChanged(ListChangeListener.Change<? extends Image> c) {
        // When initOwner is called on the Dialog the icon is changed and this event is fired
        if (isResettingIcon) {
            return;
        }
        // When there is an initOwner call the Dialog icon gets reset, we need to reapply the JMetro icon
        isResettingIcon = true;
        initDialogIcon(this, isIconless());
        isResettingIcon = false;
    }

    // --- iconless
    public boolean isIconless() { return iconless.get(); }
    /**
     * Controls whether a white placeholder icon is added when the window's
     * icons change. Initially {@code true}; changing it does not immediately
     * alter the existing icons.
     *
     * @return the iconless property
     */
    public BooleanProperty iconlessProperty() { return iconless; }
    public void setIconless(boolean iconless) { this.iconless.set(iconless); }
}
