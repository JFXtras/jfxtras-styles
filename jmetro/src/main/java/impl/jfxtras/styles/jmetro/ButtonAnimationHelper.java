package impl.jfxtras.styles.jmetro;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.control.ButtonBase;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

class ButtonAnimationHelper {
    static final String SHRINK_ANIMATE_ON_PRESS_PROPERTY_NAME = "-shrink-animate-on-press";

    private static final Duration SCALE_TRANSITION_DURATION = Duration.millis(400);
    private static final double SCALE_ON_PRESS = 0.97;

    private EventHandler<MouseEvent> buttonPressed = this::onButtonPressed;
    private EventHandler<MouseEvent> buttonReleased = this::onButtonReleased;
    private EventHandler<KeyEvent> keyPressed = this::onKeyPressed;
    private EventHandler<KeyEvent> keyReleased = this::onKeyReleased;

    private boolean isKeyPressed = false;

    private BooleanProperty buttonShrinkAnimateOnPressProperty;

    private ButtonBase button;

    private ButtonAnimationHelper(ButtonBase button, BooleanProperty buttonShrinkAnimateOnPressProperty) {
        this.buttonShrinkAnimateOnPressProperty = buttonShrinkAnimateOnPressProperty;

        this.button = button;

        button.addEventHandler(MouseEvent.MOUSE_PRESSED, buttonPressed);
        button.addEventHandler(MouseEvent.MOUSE_RELEASED, buttonReleased);
        button.addEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
        button.addEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
    }

    static ButtonAnimationHelper setupButton(ButtonBase button, BooleanProperty buttonShrinkAnimateOnPressProperty) {
        return new ButtonAnimationHelper(button, buttonShrinkAnimateOnPressProperty);
    }

    private void onButtonPressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() != MouseButton.PRIMARY) {
            return;
        }

        performShrink();
    }

    private void onButtonReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() != MouseButton.PRIMARY) {
            return;
        }

        performUnshrink();
    }

    private void onKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
            performShrink();
            isKeyPressed = true;
        }
    }

    private void onKeyReleased(KeyEvent event) {
        if (isKeyPressed) {
            performUnshrink();
            isKeyPressed = false;
        }
    }

    private void performShrink() {
        if (buttonShrinkAnimateOnPressProperty.get()) {
            button.setScaleX(SCALE_ON_PRESS);
            button.setScaleY(SCALE_ON_PRESS);
        }
    }

    private void performUnshrink() {
        if (buttonShrinkAnimateOnPressProperty.get()) {
            ScaleTransition scaleTransition = new ScaleTransition(SCALE_TRANSITION_DURATION, button);
            scaleTransition.setInterpolator(Interpolator.EASE_OUT);
            scaleTransition.setToX(1);
            scaleTransition.setToY(1);

            scaleTransition.play();
        }
    }

    void dispose() {
        button.removeEventHandler(MouseEvent.MOUSE_PRESSED, buttonPressed);
        button.removeEventHandler(MouseEvent.MOUSE_RELEASED, buttonReleased);
        button.removeEventHandler(KeyEvent.KEY_PRESSED, keyPressed);
        button.removeEventHandler(KeyEvent.KEY_RELEASED, keyReleased);
    }
}
