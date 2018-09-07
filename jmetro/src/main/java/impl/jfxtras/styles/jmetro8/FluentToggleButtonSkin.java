package impl.jfxtras.styles.jmetro8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.converter.BooleanConverter;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.skin.ToggleButtonSkin;

public class FluentToggleButtonSkin extends ToggleButtonSkin {

    public FluentToggleButtonSkin(ToggleButton button) {
        super(button);

        ButtonAnimationHelper.setupButton(button, shrinkAnimateOnPressProperty());
    }

    /********** CSS Properties ****************/

    private static final CssMetaData<ToggleButton, Boolean> SHRINK_ANIMATE_ON_PRESS_META_DATA =
            new CssMetaData<ToggleButton, Boolean>(ButtonAnimationHelper.SHRINK_ANIMATE_ON_PRESS_PROPERTY_NAME,
                    BooleanConverter.getInstance(), true) {

                @Override
                public boolean isSettable(ToggleButton button) {
                    final FluentToggleButtonSkin skin = (FluentToggleButtonSkin) button.getSkin();
                    return !skin.shrinkAnimateOnPress.isBound();
                }

                @Override
                public StyleableProperty<Boolean> getStyleableProperty(ToggleButton button) {
                    final FluentToggleButtonSkin skin = (FluentToggleButtonSkin) button.getSkin();
                    return (StyleableProperty<Boolean>) skin.shrinkAnimateOnPressProperty();
                }
            };

    private BooleanProperty shrinkAnimateOnPress = new SimpleStyleableBooleanProperty(SHRINK_ANIMATE_ON_PRESS_META_DATA, true);

    private BooleanProperty shrinkAnimateOnPressProperty() { return shrinkAnimateOnPress; }

    private boolean isShrinkAnimateOnPress() { return shrinkAnimateOnPress.get(); }


    /* Setup styleables for this Skin */
    private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    static {
        final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<>(SkinBase.getClassCssMetaData());
        styleables.add(SHRINK_ANIMATE_ON_PRESS_META_DATA);
        STYLEABLES = Collections.unmodifiableList(styleables);
    }

    /**
     * @return The CssMetaData associated with this class, which may include the
     * CssMetaData of its super classes.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return STYLEABLES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

}
