package jfxtras.styles.jmetro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Collection;

import static jfxtras.styles.jmetro.FlatDialog.initDialog;
import static jfxtras.styles.jmetro.FlatDialog.initDialogIcon;

/**
 * A dialog with a flat style.
 * The title is initially empty and a white placeholder icon is added by default.
 * The {@link #iconlessProperty() iconless} property controls whether that icon
 * is added again when the window's icon list changes.
 *
 * @param <T> The type of the items to show to the user, and the type that is returned via Dialog.getResult() when the dialog is dismissed.
 */
public class FlatChoiceDialog<T> extends ChoiceDialog<T> {
    private final BooleanProperty iconless = new SimpleBooleanProperty(true);

    private boolean isResettingIcon;

    /** Creates an initially empty choice dialog. */
    public FlatChoiceDialog() {
        super();
        init();
    }

    /**
     * Creates a choice dialog with the specified initial selection and items.
     *
     * @param defaultChoice the initial selection
     * @param choices the choices presented to the user
     */
    public FlatChoiceDialog(T defaultChoice, Collection<T> choices) {
        super(defaultChoice, choices);
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
