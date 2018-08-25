package impl.jfxtras.styles.jmetro8;

import com.sun.javafx.css.converters.BooleanConverter;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.scene.control.Button;
import javafx.scene.control.SkinBase;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FluentButtonSkin extends ButtonSkin {

    private static final Duration SCALE_TRANSITION_DURATION = Duration.millis(400);
    private static final double SCALE_ON_PRESS = 0.97;

    private boolean keyPressed = false;

    public FluentButtonSkin(Button button) {
        super(button);

        button.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> onButtonPressed(button));
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> onButtonReleased(button));
        button.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                onButtonPressed(button);
                keyPressed = true;
            }
        });
        button.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (keyPressed) {
                onButtonReleased(button);
                keyPressed = false;
            }
        });
    }

    private void onButtonPressed(Button button) {
        if (isShrinkAnimateOnPress()) {
            button.setScaleX(SCALE_ON_PRESS);
            button.setScaleY(SCALE_ON_PRESS);
        }
    }

    private void onButtonReleased(Button button) {
        if (isShrinkAnimateOnPress()) {
            ScaleTransition scaleTransition = new ScaleTransition(SCALE_TRANSITION_DURATION, button);
            scaleTransition.setInterpolator(Interpolator.EASE_OUT);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();
        }
    }



    /********** CSS Properties ****************/

    private static final CssMetaData<Button, Boolean> SHRINK_ANIMATE_ON_PRESS_META_DATA =
            new CssMetaData<Button, Boolean>("-shrink-animate-on-press",
                    BooleanConverter.getInstance(), true) {

                @Override
                public boolean isSettable(Button button) {
                    final FluentButtonSkin skin = (FluentButtonSkin) button.getSkin();
                    return !skin.shrinkAnimateOnPress.isBound();
                }

                @Override
                public StyleableProperty<Boolean> getStyleableProperty(Button button) {
                    final FluentButtonSkin skin = (FluentButtonSkin) button.getSkin();
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
