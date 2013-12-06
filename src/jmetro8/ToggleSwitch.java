//package jfxtras.styles.jmetro;
//
//import com.sun.javafx.scene.control.skin.ToggleButtonSkin;
//import javafx.beans.property.BooleanProperty;
//import javafx.beans.property.BooleanPropertyBase;
//import javafx.css.PseudoClass;
//import javafx.event.ActionEvent;
//import javafx.geometry.Pos;
//import javafx.scene.control.ButtonBase;
//import javafx.scene.control.Skin;
//import javafx.scene.control.Toggle;
//
///**
// * Created by pedro_000 on 12/4/13.
// */
//public class ToggleSwitch extends ButtonBase {
//
//    /**
//     * Indicates whether this toggle switch is selected. This can be manipulated
//     * programmatically.
//     */
//    private BooleanProperty selected;
//
//    /***************************************************************************
//     *                                                                         *
//     * Stylesheet Handling                                                     *
//     *                                                                         *
//     **************************************************************************/
//
//    private static final String DEFAULT_STYLE_CLASS = "toggle-switch";
//    private static final PseudoClass PSEUDO_CLASS_SELECTED =
//            PseudoClass.getPseudoClass("selected");
//
//
//    /***************************************************************************
//     *                                                                         *
//     * Constructors                                                            *
//     *                                                                         *
//     **************************************************************************/
//
//    /**
//     * Creates a check box with an empty string for its label.
//     */
//    public ToggleSwitch() {
//        initialize();
//    }
//
//    /**
//     * Creates a check box with the specified text as its label.
//     *
//     * @param text A text string for its label.
//     */
//    public ToggleSwitch(String onText, String offText) {
//        initialize();
//    }
//
//    private void initialize() {
//        getStyleClass().setAll(DEFAULT_STYLE_CLASS);
//    }
//
//    /***************************************************************************
//     *                                                                         *
//     * Properties                                                              *
//     *                                                                         *
//     **************************************************************************/
//
//
//    public final void setSelected(boolean value) {
//        selectedProperty().set(value);
//    }
//
//    public final boolean isSelected() {
//        return selected == null ? false : selected.get();
//    }
//
//    public final BooleanProperty selectedProperty() {
//        if (selected == null) {
//            selected = new BooleanPropertyBase() {
//                @Override protected void invalidated() {
//                    pseudoClassStateChanged(PSEUDO_CLASS_SELECTED, get());
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitch.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "selected";
//                }
//            };
//        }
//        return selected;
//    }
//
//    /***************************************************************************
//     *                                                                         *
//     * Methods                                                                 *
//     *                                                                         *
//     **************************************************************************/
//
//    /** {@inheritDoc} */
//    @Override public void fire() {
//        if (!isDisabled()) {
//            setSelected(!isSelected());
//            fireEvent(new ActionEvent());
//        }
//    }
//
//    /** {@inheritDoc} */
//    @Override protected Skin<?> createDefaultSkin() {
//        return new ToggleButtonSkin(this);
//    }
//
//
//}
//
