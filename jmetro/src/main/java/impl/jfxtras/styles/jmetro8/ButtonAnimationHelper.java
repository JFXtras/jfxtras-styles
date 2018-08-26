package impl.jfxtras.styles.jmetro8;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

class ButtonAnimationHelper {
    public static final String SHRINK_ANIMATE_ON_PRESS_PROPERTY_NAME = "-shrink-animate-on-press";

    private static final Duration SCALE_TRANSITION_DURATION = Duration.millis(400);
    private static final double SCALE_ON_PRESS = 0.97;

    private boolean keyPressed = false;

    private BooleanProperty buttonShrinkAnimateOnPressProperty;

    private ButtonAnimationHelper(ButtonBase button, BooleanProperty buttonShrinkAnimateOnPressProperty) {
        this.buttonShrinkAnimateOnPressProperty = buttonShrinkAnimateOnPressProperty;

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


    static ButtonAnimationHelper setupButton(ButtonBase button, BooleanProperty buttonShrinkAnimateOnPressProperty) {
        return new ButtonAnimationHelper(button, buttonShrinkAnimateOnPressProperty);
    }

    private void onButtonPressed(ButtonBase button) {
        if (buttonShrinkAnimateOnPressProperty.get()) {
            button.setScaleX(SCALE_ON_PRESS);
            button.setScaleY(SCALE_ON_PRESS);
        }
    }

    private void onButtonReleased(ButtonBase button) {
        if (buttonShrinkAnimateOnPressProperty.get()) {
            ScaleTransition scaleTransition = new ScaleTransition(SCALE_TRANSITION_DURATION, button);
            scaleTransition.setInterpolator(Interpolator.EASE_OUT);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();
        }
    }
}
