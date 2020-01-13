/*
 * Copyright (c) 2011-2020 JFXtras
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the organization nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

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
