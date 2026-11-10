package jfxtras.styles.jmetro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import static jfxtras.styles.jmetro.FlatDialog.initDialog;
import static jfxtras.styles.jmetro.FlatDialog.initDialogIcon;

/**
 * An Alert with a flat style.
 * The title is initially empty and a white placeholder icon is added by default.
 * The {@link #iconlessProperty() iconless} property controls whether that icon
 * is added again when the window's icon list changes.
 */
public class FlatAlert extends Alert {
    private BooleanProperty iconless = new SimpleBooleanProperty(true);

    private boolean isResettingIcon;

    /**
     * Creates an alert of the given type.
     *
     * @param alertType the type of alert
     */
    public FlatAlert(AlertType alertType) {
        super(alertType);
        init();
    }

    /**
     * Creates an alert with the specified type, content and buttons.
     *
     * @param alertType the type of alert
     * @param contentText the content text
     * @param buttons the buttons available to dismiss the alert
     */
    public FlatAlert(AlertType alertType, String contentText, ButtonType... buttons) {
        super(alertType, contentText, buttons);
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
