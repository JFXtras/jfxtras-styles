package jfxtras.styles.jmetro8;

import javafx.beans.property.*;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.scene.accessibility.Attribute;
import javafx.scene.control.Control;
import javafx.scene.control.Labeled;
import javafx.scene.control.Skin;

/**
 * Created by pedro_000 on 6/15/2014.
 */
public class ToggleSwitch extends Labeled {

    /***************************************************************************
     *                                                                         *
     * Constructors                                                            *
     *                                                                         *
     **************************************************************************/

    /**
     * Creates a toggle switch with empty strings for its labels.
     */
    public ToggleSwitch() {
        initialize();
    }

    /**
     * Creates a toggle switch with the specified on text and off text.
     *
     * @param turnOnText A text string for the on state.
     */
    public ToggleSwitch(String turnOnText, String turnOffText) {
        setTurnOnText(turnOnText);
        setTurnOffText(turnOffText);
        initialize();
    }

    private void initialize() {
        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
    }

    /***************************************************************************
     *                                                                         *
     * Properties                                                              *
     *                                                                         *
     **************************************************************************/

    /**
     * Indicates whether this ToggleSwitch is selected.
     */
    private BooleanProperty selected;
    public final void setSelected(boolean value) {
        selectedProperty().set(value);
    }

    public final boolean isSelected() {
        return selected == null ? false : selected.get();
    }

    public final BooleanProperty selectedProperty() {
        if (selected == null) {
            selected = new BooleanPropertyBase() {
                @Override protected void invalidated() {
                    final Boolean v = get();
                    pseudoClassStateChanged(PSEUDO_CLASS_SELECTED, v);
                    accSendNotification(Attribute.SELECTED);
                }

                @Override
                public Object getBean() {
                    return ToggleSwitch.this;
                }

                @Override
                public String getName() {
                    return "selected";
                }
            };
        }
        return selected;
    }

    /**
     * The text to show when this switch is on. The text may be null.
     */
    public final StringProperty turnOnTextProperty() {
        if (turnOnText == null) {
            turnOnText = new SimpleStringProperty(this, "turnOnText", "");
        }
        return turnOnText;
    }
    private StringProperty turnOnText;
    public final void setTurnOnText(String value) { turnOnTextProperty().setValue(value); }
    public final String getTurnOnText() { return turnOnText == null ? "" : turnOnText.getValue(); }

    /**
     * The text to show when this switch is off. The text may be null.
     */
    public final StringProperty turnOffTextProperty() {
        if (turnOffText == null) {
            turnOffText = new SimpleStringProperty(this, "turnOffText", "");
        }
        return turnOffText;
    }
    private StringProperty turnOffText;
    public final void setTurnOffText(String value) { turnOffTextProperty().setValue(value); }
    public final String getTurnOffText() { return turnOffText == null ? "" : turnOffText.getValue(); }

    /***************************************************************************
     *                                                                         *
     * Methods                                                                 *
     *                                                                         *
     **************************************************************************/

    /**
     * Toggles the state of the {@code ToggleSwitch}. If allowIndeterminate is
     * true, then each invocation of this function will advance the CheckBox
     * through the states checked, unchecked, and undefined. If
     * allowIndeterminate is false, then the CheckBox will only cycle through
     * the checked and unchecked states, and forcing indeterminate to equal to
     * false.
     */
    public void fire() {
        if (!isDisabled()) {
            setSelected(!isSelected());
            fireEvent(new ActionEvent());
        }
    }

    /** {@inheritDoc} */
    @Override protected Skin<?> createDefaultSkin() {
        return new ToggleSwitchSkin(this);
    }


    /***************************************************************************
     *                                                                         *
     * Stylesheet Handling                                                     *
     *                                                                         *
     **************************************************************************/

    private static final String DEFAULT_STYLE_CLASS = "toggle-switch";

    private static final PseudoClass PSEUDO_CLASS_SELECTED =
            PseudoClass.getPseudoClass("selected");

}
