package jfxtras.styles.jmetro8.helpers;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ShrinkAnimationHelper {
    public boolean shrinkAnimationIsEnabled;
    public boolean shrinkAnimationOnKeyPress;
    public Duration shrinkAnimationDuration;
    public double shrinkAnimationScaleFactor;
    public double defaultScaleXFactor;
    public double defaultScaleYFactor;

    private boolean keyPressed = false;
    private ScaleTransition scaleTransition = new ScaleTransition();

    public void init(Node node) {
        attachShrinkAnimation(node);
    }

    private void attachShrinkAnimation(Node node) {
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
                pressedAnimation(node));

        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event ->
                releasedAnimation(node));

        node.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                if (shrinkAnimationOnKeyPress) {
                    pressedAnimation(node);
                    keyPressed = true;
                }
            }
        });

        node.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (keyPressed) {
                if (shrinkAnimationOnKeyPress) {
                    releasedAnimation(node);
                    keyPressed = false;
                }
            }
        });
    }

    private void pressedAnimation(Node node) {
        if (shrinkAnimationIsEnabled) {
            node.setScaleX(shrinkAnimationScaleFactor);
            node.setScaleY(shrinkAnimationScaleFactor);
        }
    }

    private void releasedAnimation(Node node) {
        scaleTransition.setOnFinished(event ->
                fixNodeScaling(node));

        if (shrinkAnimationIsEnabled) {
            if (scaleTransition.getStatus() != Animation.Status.RUNNING) {
                scaleTransition.setInterpolator(Interpolator.EASE_OUT);
                scaleTransition.setDuration(shrinkAnimationDuration);
                scaleTransition.setNode(node);
                scaleTransition.setToX(defaultScaleXFactor);
                scaleTransition.setToY(defaultScaleYFactor);
                scaleTransition.play();
            } else {
                scaleTransition.stop();
                node.setScaleX(defaultScaleXFactor);
                node.setScaleY(defaultScaleYFactor);
            }

        }
    }

    private void fixNodeScaling(Node node) {
        if (node.getScaleX() != defaultScaleXFactor) {
            node.setScaleX(defaultScaleXFactor);
        }

        if (node.getScaleY() != defaultScaleYFactor) {
            node.setScaleY(defaultScaleYFactor);
        }
    }
}
