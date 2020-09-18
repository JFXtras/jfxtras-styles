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

import com.sun.javafx.css.converters.EnumConverter;
import com.sun.javafx.css.converters.SizeConverter;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableDoubleProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.ToggleSwitch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToggleSwitchSkin extends SkinBase<ToggleSwitch> {
    private HBox mainContainer = new HBox();

    private final Label label = new Label();
    private final StackPane labelContainer = new StackPane();
    private final InnerToggleSwitch innerToggleSwitch;

    private InvalidationListener thumbDisplayChanged = observable -> this.updateThumbDisplay(getThumbDisplay());

    /**
     * How many milliseconds it should take for the thumb to go from
     * one edge to the other
     */
    private DoubleProperty thumbMoveAnimationTime = null;

    private StyleableObjectProperty<ThumbDisplay> thumbDisplay = new SimpleStyleableObjectProperty<ThumbDisplay>(THUMB_DISPLAY_META_DATA, ThumbDisplay.LEFT) {
        @Override
        protected void invalidated() {
            ToggleSwitchSkin.this.getSkinnable().requestLayout();
        }
    };

    /**
     * Constructor for all ToggleSwitchSkin instances.
     *
     * @param control The ToggleSwitch for which this Skin should attach to.
     */
    public ToggleSwitchSkin(ToggleSwitch control) {
        super(control);

        innerToggleSwitch = new InnerToggleSwitch(this);

        label.textProperty().bind(control.textProperty());

        mainContainer.getStyleClass().setAll("main-container");

        getChildren().setAll(mainContainer);

        labelContainer.getChildren().addAll(label);
        StackPane.setAlignment(label, Pos.CENTER_LEFT);
        labelContainer.getStyleClass().setAll("label-container");

        updateThumbDisplay(getThumbDisplay());
        thumbDisplay.addListener(thumbDisplayChanged);
    }

    private void updateThumbDisplay(ThumbDisplay thumbDisplay) {
        switch (thumbDisplay) {
            case LEFT:
                mainContainer.getChildren().setAll(innerToggleSwitch, labelContainer);
                break;
            case RIGHT:
                mainContainer.getChildren().setAll(labelContainer, innerToggleSwitch);
                break;
            case THUMB_ONLY:
                mainContainer.getChildren().setAll(innerToggleSwitch);
                break;
        }

    }

    @Override
    public void dispose() {
        thumbDisplay.removeListener(thumbDisplayChanged);

        super.dispose();
    }

    /***************************************************************************
     *                                                                         *
     *                            Properties                                   *
     *                                                                         *
     **************************************************************************/

    // --- thumb move animation time

    private DoubleProperty thumbMoveAnimationTimeProperty() {
        if (thumbMoveAnimationTime == null) {
            thumbMoveAnimationTime = new StyleableDoubleProperty(200) {

                @Override
                public Object getBean() {
                    return ToggleSwitchSkin.this;
                }

                @Override
                public String getName() {
                    return "thumbMoveAnimationTime";
                }

                @Override
                public CssMetaData<ToggleSwitch,Number> getCssMetaData() {
                    return THUMB_MOVE_ANIMATION_TIME_META_DATA;
                }
            };
        }
        return thumbMoveAnimationTime;
    }

    private double getThumbMoveAnimationTime() {
        return thumbMoveAnimationTime == null ? 200 : thumbMoveAnimationTime.get();
    }

    // --- thumb display

    private StyleableObjectProperty<ThumbDisplay> thumbDisplayProperty() { return thumbDisplay; }

    private ThumbDisplay getThumbDisplay() { return thumbDisplay.get(); }


    /***************************************************************************
     *                                                                         *
     *                            CSS Handling                                 *
     *                                                                         *
     **************************************************************************/

    private static final CssMetaData<ToggleSwitch, Number> THUMB_MOVE_ANIMATION_TIME_META_DATA =
            new CssMetaData<ToggleSwitch, Number>("-thumb-move-animation-time",
                    SizeConverter.getInstance(), 200) {

                @Override
                public boolean isSettable(ToggleSwitch toggleSwitch) {
                    final ToggleSwitchSkin skin = (ToggleSwitchSkin) toggleSwitch.getSkin();
                    return skin.thumbMoveAnimationTime == null ||
                            !skin.thumbMoveAnimationTime.isBound();
                }

                @Override
                public StyleableProperty<Number> getStyleableProperty(ToggleSwitch toggleSwitch) {
                    final ToggleSwitchSkin skin = (ToggleSwitchSkin) toggleSwitch.getSkin();
                    return (StyleableProperty<Number>) skin.thumbMoveAnimationTimeProperty();
                }
            };

    private static final CssMetaData<ToggleSwitch, ThumbDisplay> THUMB_DISPLAY_META_DATA =
            new CssMetaData<ToggleSwitch, ThumbDisplay>("-toggle-display", new EnumConverter<>(ThumbDisplay.class)) {

                @Override
                public boolean isSettable(ToggleSwitch toggleSwitch) {
                    final ToggleSwitchSkin skin = (ToggleSwitchSkin) toggleSwitch.getSkin();
                    return !skin.thumbDisplay.isBound();
                }

                @Override
                public StyleableProperty<ThumbDisplay> getStyleableProperty(ToggleSwitch toggleSwitch) {
                    final ToggleSwitchSkin skin = (ToggleSwitchSkin) toggleSwitch.getSkin();
                    return skin.thumbDisplayProperty();
                }
            };

    private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    static {
        final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<>(SkinBase.getClassCssMetaData());
        styleables.add(THUMB_MOVE_ANIMATION_TIME_META_DATA);
        styleables.add(THUMB_DISPLAY_META_DATA);
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

    /****************************************************************************
     *                                                                          *
     * Supporting Classes / Enums                                               *
     *                                                                          *
     ***************************************************************************/

    private enum ThumbDisplay {
        LEFT, RIGHT, THUMB_ONLY
    }

    private static class InnerToggleSwitch extends Region {
        private boolean isFirstLayoutPass = true;
        private final ToggleSwitchSkin toggleSwitchSkin;
        private final TranslateTransition transition;

        private final StackPane thumb = new StackPane();

        private InvalidationListener selectionChanged = observable -> this.selectedStateChanged();
        private EventHandler<MouseEvent> mouseReleased;

        public InnerToggleSwitch(ToggleSwitchSkin toggleSwitchSkin) {
            transition = new TranslateTransition(Duration.millis(toggleSwitchSkin.getThumbMoveAnimationTime()), thumb);
            transition.setInterpolator(Interpolator.EASE_OUT);

            this.toggleSwitchSkin = toggleSwitchSkin;

            getStyleClass().setAll("thumb-area");
            thumb.getStyleClass().setAll("thumb");

            getChildren().add(thumb);

            thumb.setMouseTransparent(true);

            ToggleSwitch skinnable = toggleSwitchSkin.getSkinnable();

            mouseReleased = event -> onMouseReleased(skinnable);
            setOnMouseReleased(mouseReleased);

            initThumbTransformX();
            skinnable.selectedProperty().addListener(selectionChanged);
        }

        private void onMouseReleased(ToggleSwitch toggleSwitch) {
            toggleSwitch.setSelected(!toggleSwitch.isSelected());
        }

        private void initThumbTransformX() {
            if (!toggleSwitchSkin.getSkinnable().isSelected()) {
                thumb.setTranslateX(0);
            } else {
                thumb.setTranslateX(calculateSelectedToggleTranslateX());
            }
        }

        private void selectedStateChanged() {
            ToggleSwitch toggleSwitch = toggleSwitchSkin.getSkinnable();
            transition.stop();
            transition.setDuration(Duration.millis(toggleSwitchSkin.getThumbMoveAnimationTime()));

            double thumbTranslateSelectedX = calculateSelectedToggleTranslateX();

            if (!toggleSwitch.isSelected()) {  // selected to not selected
                transition.setFromX(thumbTranslateSelectedX);
                transition.setToX(0);
            } else {                           // not selected to selected
                transition.setFromX(0);
                transition.setToX(thumbTranslateSelectedX);
            }
            transition.setCycleCount(1);
            transition.play();
        }

        private double calculateSelectedToggleTranslateX() {
            double thumbAreaWidth = getWidth() - (snappedLeftInset() + snappedRightInset());
            double thumbWidth = snapSize(thumb.prefWidth(-1));
            return thumbAreaWidth - thumbWidth;
        }

        @Override
        protected void layoutChildren() {
            final double x = snappedLeftInset();
            final double y = snappedTopInset();
            final double height = getHeight() - (snappedTopInset() + snappedBottomInset());

            double thumbWidth = thumb.prefWidth(-1);
            double thumbHeight = thumb.prefHeight(-1);
            thumb.resize(snapSize(thumbWidth), snapSize(thumbHeight));

            thumb.setLayoutX(snapPosition(x));
            thumb.setLayoutY(snapPosition(y + height / 2d) - thumbHeight / 2);

            if (isFirstLayoutPass) {
                initThumbTransformX();
            }
        }
    }
}

