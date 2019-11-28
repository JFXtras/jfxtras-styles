package jfxtras.styles.jmetro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A dialog with a flat style.
 * For example, by default it will appear without a window icon (can be configured via iconless property) and without
 * a window title.
 *
 * @param <R> The return type of the dialog, via the result property.
 */
public class FlatDialog<R> extends Dialog<R> {
    private BooleanProperty iconless = new SimpleBooleanProperty(true);

    private boolean isResettingIcon;

    public static void initDialog(Dialog<?> dialog, boolean iconless) {
        dialog.setTitle("");
        initDialogIcon(dialog, iconless);
    }

    public static void initDialogIcon(Dialog<?> dialog, boolean isIconless) {
        if (!isIconless) {
            return;
        }
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        Image whiteIcon = new Image(JMetro.class.getResource("whiteIcon.png").toExternalForm());
        stage.getIcons().add(whiteIcon);
    }

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
    public BooleanProperty iconlessProperty() { return iconless; }
    public void setIconless(boolean iconless) { this.iconless.set(iconless); }
}
