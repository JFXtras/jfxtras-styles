package impl.jfxtras.styles.jmetro;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;

class SliderPopup extends PopupControl {
    private static final String DEFAULT_STYLE_CLASS = "slider-popup";



    SliderPopup() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);

    }


    private DoubleProperty value = new SimpleDoubleProperty();

    DoubleProperty valueProperty() { return value; }

    double getValue() { return value.get(); }

    void setValue(double value) {
        this.value.set(value);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SliderPopupSkin(this);
    }

    private class SliderPopupSkin implements Skin<SliderPopup> {

        SliderPopup skinnable;
        Label valueText;

        /**
         * Constructor for all SkinBase instances.
         *
         * @param control The control for which this Skin should attach to.
         */
        SliderPopupSkin(SliderPopup control) {
            skinnable = control;
            valueText = new Label();

            valueText.textProperty().bind(new StringBinding() {
                {
                    super.bind(skinnable.valueProperty());
                }

                @Override
                protected String computeValue() {
                    return String.valueOf(Math.round(skinnable.getValue()));
                }
            });
        }

        @Override
        public SliderPopup getSkinnable() {
            return skinnable;
        }

        @Override
        public Node getNode() {
            return valueText;
        }

        @Override
        public void dispose() {

        }
    }
}
