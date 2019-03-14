package jfxtras.styles.jmetro8.controls;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import jfxtras.styles.jmetro8.helpers.ShrinkAnimationHelper;

import java.util.ArrayList;

import static jfxtras.styles.jmetro8.helpers.SceneNodeFinderHelper.getAllNodes;

/**
 * Fluent Menu Button class constructor,
 * i not think what it you needed :).
 */
public class FluentMenuButton {
    private boolean rightClickIsEnabled = false;
    private boolean shrinkAnimationIsEnabled = true;
    private boolean shrinkAnimationOnKeyPress = false;
    private double shrinkAnimationScaleFactor = 0.97;
    private double defaultScaleXFactor = 1.0;
    private double defaultScaleYFactor = 1.0;
    private Duration shrinkAnimationDuration = Duration.millis(400);
    private ShrinkAnimationHelper shrinkAnimationHelperInstance = new ShrinkAnimationHelper();

    public void init(Scene scene) {
        init(scene.getRoot());
    }

    public void init(Parent parent) {
        initProperties();

        for (MenuButton menuButton : getButtons(parent)) {
            shrinkAnimationHelperInstance.init(menuButton);
            addPressedHandler(menuButton);
        }
    }

    private void initProperties() {
        shrinkAnimationHelperInstance.defaultScaleXFactor = getDefaultScaleXFactor();
        shrinkAnimationHelperInstance.defaultScaleYFactor = getDefaultScaleYFactor();
        shrinkAnimationHelperInstance.shrinkAnimationDuration = getShrinkAnimationDuration();
        shrinkAnimationHelperInstance.shrinkAnimationIsEnabled = isShrinkAnimationIsEnabled();
        shrinkAnimationHelperInstance.shrinkAnimationOnKeyPress = isShrinkAnimationOnKeyPress();
        shrinkAnimationHelperInstance.shrinkAnimationScaleFactor = getShrinkAnimationScaleFactor();
    }

    private ArrayList<MenuButton> getButtons(Parent root) {
        ArrayList<MenuButton> menuButtons = new ArrayList<>();

        for (Node node : getAllNodes(root)) {
            if (node instanceof MenuButton) {
                menuButtons.add((MenuButton) node);
            }
        }

        return menuButtons;
    }

    private void addPressedHandler(MenuButton menuButton) {
        menuButton.addEventFilter(MouseEvent.MOUSE_PRESSED, (event -> {
            if (event.isSecondaryButtonDown()) {
                if (!isRightClickIsEnabled() && menuButton.getContextMenu() == null) {
                    event.consume();
                }
            }
        }));
    }

    /**
     * @return is enabled value for ability to right click on node.
     */
    public boolean isRightClickIsEnabled() {
        return rightClickIsEnabled;
    }

    /**
     * @param isEnabled is enabled value for ability to right click on node.
     */
    public void setRightClickIsEnabled(boolean isEnabled) {
        this.rightClickIsEnabled = isEnabled;
    }

    /**
     * @return animation is enabled value.
     */
    public boolean isShrinkAnimationIsEnabled() {
        return shrinkAnimationIsEnabled;
    }

    /**
     * @param isEnabled is enabled value for animation playing.
     */
    public void setShrinkAnimationIsEnabled(boolean isEnabled) {
        shrinkAnimationHelperInstance.shrinkAnimationIsEnabled = isEnabled;
        this.shrinkAnimationIsEnabled = isEnabled;
    }

    /**
     * @return is enabled value of key press for causing shrink animation.
     */
    public boolean isShrinkAnimationOnKeyPress() {
        return shrinkAnimationOnKeyPress;
    }

    /**
     * Additional information: animation causing when button pressed
     * and you clicking on "SPACE" or "ENTER" keys.
     *
     * @param isEnabled is enabled value for key press for causing shrink animation by key.
     */
    public void setShrinkAnimationOnKeyPress(boolean isEnabled) {
        shrinkAnimationHelperInstance.shrinkAnimationOnKeyPress = isEnabled;
        this.shrinkAnimationOnKeyPress = isEnabled;
    }

    /**
     * @return duration for node shrink animation.
     */
    public Duration getShrinkAnimationDuration() {
        return shrinkAnimationDuration;
    }

    /**
     * @param duration duration value for node shrink animation.
     */
    public void setShrinkAnimationDuration(Duration duration) {
        shrinkAnimationHelperInstance.shrinkAnimationDuration = duration;
        this.shrinkAnimationDuration = duration;
    }

    /**
     * @return shrink animation scale factor value for node.
     */
    public double getShrinkAnimationScaleFactor() {
        return shrinkAnimationScaleFactor;
    }

    /**
     * @param scaleFactor shrink animation scale factor value for node.
     */
    public void setShrinkAnimationScaleFactor(double scaleFactor) {
        shrinkAnimationHelperInstance.shrinkAnimationScaleFactor = scaleFactor;
        this.shrinkAnimationScaleFactor = scaleFactor;
    }

    /**
     * @return default scale x factor for node.
     */
    public double getDefaultScaleXFactor() {
        return defaultScaleXFactor;
    }

    /**
     * Additional information: default scale factor
     * applied after finishing animation playing,
     * it needed if you use scaled UI, e.g for you
     * default scale is 2.0, then set scaleXFactor to 2.0.
     *
     * @param scaleXFactor default scale x factor for node.
     */
    public void setDefaultScaleXFactor(double scaleXFactor) {
        shrinkAnimationHelperInstance.defaultScaleXFactor = scaleXFactor;
        this.defaultScaleXFactor = scaleXFactor;
    }

    /**
     * @return default scale y factor for node.
     */
    public double getDefaultScaleYFactor() {
        return defaultScaleYFactor;
    }

    /**
     * Additional information: default scale factor
     * applied after finishing animation playing,
     * it needed if you use scaled UI, e.g for you
     * default scale is 2.0, then set scaleYFactor to 2.0.
     *
     * @param scaleYFactor default scale y factor for node.
     */
    public void setDefaultScaleYFactor(double scaleYFactor) {
        shrinkAnimationHelperInstance.defaultScaleYFactor = scaleYFactor;
        this.defaultScaleYFactor = scaleYFactor;
    }
}
