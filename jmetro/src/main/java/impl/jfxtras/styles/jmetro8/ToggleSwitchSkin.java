package impl.jfxtras.styles.jmetro8;

import com.sun.javafx.css.converters.EnumConverter;
import com.sun.javafx.css.converters.SizeConverter;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableDoubleProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.ToggleSwitch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToggleSwitchSkin extends SkinBase<ToggleSwitch>
{
    private final StackPane thumb;
    private final StackPane thumbArea;
    private final Label label;
    private final StackPane labelContainer;
    private final TranslateTransition transition;

    /**
     * Constructor for all ToggleSwitchSkin instances.
     *
     * @param control The ToggleSwitch for which this Skin should attach to.
     */
    public ToggleSwitchSkin(ToggleSwitch control) {
        super(control);

        thumb = new StackPane();
        thumbArea = new StackPane();
        label = new Label();
        labelContainer = new StackPane();
        transition = new TranslateTransition(Duration.millis(getThumbMoveAnimationTime()), thumb);
        transition.setInterpolator(Interpolator.EASE_OUT);

        label.textProperty().bind(control.textProperty());
        getChildren().addAll(labelContainer, thumbArea, thumb);
        labelContainer.getChildren().addAll(label);
        StackPane.setAlignment(label, Pos.CENTER_LEFT);

        thumb.getStyleClass().setAll("thumb");
        thumbArea.getStyleClass().setAll("thumb-area");

        thumbArea.setOnMouseReleased(event -> mousePressedOnToggleSwitch(control));
        thumb.setOnMouseReleased(event -> mousePressedOnToggleSwitch(control));
        control.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.booleanValue() != oldValue.booleanValue())
                selectedStateChanged();
        });
    }

    private void selectedStateChanged() {
        if(transition != null){
            transition.stop();
            transition.setDuration(Duration.millis(getThumbMoveAnimationTime()));
        }

        double thumbAreaWidth = snapSize(thumbArea.prefWidth(-1));
        Insets thumbAreaPadding = thumbArea.getPadding();
        double thumbWidth = snapSize(thumb.prefWidth(-1));
        double distance = thumbAreaWidth - thumbWidth - thumbAreaPadding.getRight() - thumbAreaPadding.getLeft();

        /*
         * If we are not selected, we need to go from right to left.
         */
        if (!getSkinnable().isSelected()) {
            thumb.setLayoutX(thumbArea.getLayoutX());
            transition.setFromX(distance);
            transition.setToX(0);
        } else {
            thumb.setTranslateX(thumbArea.getLayoutX());
            transition.setFromX(0);
            transition.setToX(distance);
        }
        transition.setCycleCount(1);
        transition.play();
    }

    private void mousePressedOnToggleSwitch(ToggleSwitch toggleSwitch) {
        toggleSwitch.setSelected(!toggleSwitch.isSelected());
    }


    /**
     * How many milliseconds it should take for the thumb to go from
     * one edge to the other
     */
    private DoubleProperty thumbMoveAnimationTime = null;

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

    private StyleableObjectProperty<ThumbDisplay> thumbDisplay = new SimpleStyleableObjectProperty<ThumbDisplay>(THUMB_DISPLAY_META_DATA, ThumbDisplay.RIGHT) {
        @Override
        protected void invalidated() {
            ToggleSwitchSkin.this.getSkinnable().requestLayout();
        }
    };

    private StyleableObjectProperty<ThumbDisplay> thumbDisplayProperty() { return thumbDisplay; }

    private ThumbDisplay getThumbDisplay() { return thumbDisplay.get(); }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        ToggleSwitch toggleSwitch = getSkinnable();
        double thumbAreaX;
        double labelX;

        double thumbWidth = thumb.prefWidth(-1);
        double thumbHeight = thumb.prefHeight(-1);
        thumb.resize(snapSize(thumbWidth), snapSize(thumbHeight));

        double labelWidth = labelContainer.prefWidth(-1);

        Insets thumbAreaPadding = thumbArea.getPadding();
        double thumbAreaWidth = thumbArea.prefWidth(-1);
        double thumbAreaHeight = thumbArea.prefHeight(-1);
        if (getThumbDisplay().equals(ThumbDisplay.RIGHT)) {
            thumbAreaX = contentWidth - thumbAreaWidth;
            labelX = contentX;
        } else if (getThumbDisplay().equals(ThumbDisplay.LEFT)) {
            thumbAreaX = contentX;
            labelX = contentWidth - labelWidth;
        } else { // ThumbDisplay.THUMB_ONLY
            thumbAreaX = contentX;
            labelX = 0;
        }
        double thumbAreaY = contentY;

        thumbArea.resize(snapSize(thumbAreaWidth), snapSize(thumbAreaHeight));

        thumbArea.setLayoutX(snapPosition(thumbAreaX));
        thumbArea.setLayoutY(snapPosition(thumbAreaY));

        if (!getThumbDisplay().equals(ThumbDisplay.THUMB_ONLY)) {
            labelContainer.resize(snapSize(contentWidth - thumbAreaWidth), snapSize(thumbAreaHeight));
            labelContainer.setLayoutY(snapPosition(thumbAreaY));
            labelContainer.setLayoutX(snapPosition(labelX));
        }

        if (!toggleSwitch.isSelected())
            thumb.setLayoutX(snapPosition(thumbAreaX + thumbAreaPadding.getLeft()));
        else
            thumb.setLayoutX(snapPosition(thumbAreaX + thumbAreaWidth - thumbAreaPadding.getRight() - thumbWidth));
        thumb.setLayoutY(snapPosition(thumbAreaY + thumbAreaPadding.getBottom()));
    }


    @Override protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return leftInset + label.prefWidth(-1) + thumbArea.prefWidth(-1) + rightInset;
    }

    @Override protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return topInset + Math.max(thumb.prefHeight(-1), label.prefHeight(-1)) + bottomInset;
    }

    @Override protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return leftInset + label.prefWidth(-1) + 20 + thumbArea.prefWidth(-1) + rightInset;
    }

    @Override protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return topInset + Math.max(thumb.prefHeight(-1), label.prefHeight(-1)) + bottomInset;
    }

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

    private enum ThumbDisplay {
        LEFT, RIGHT, THUMB_ONLY
    }
}

