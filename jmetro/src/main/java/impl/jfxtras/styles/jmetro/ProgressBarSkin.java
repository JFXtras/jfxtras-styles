package impl.jfxtras.styles.jmetro;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ProgressBarSkin extends SkinBase<ProgressBar> {

    private static final int NUMBER_DOTS = 5;
    private static final String DOT_STYLE_CLASS = "dot";
    private static final String SINGLE_DOT_STYLE_CLASS_PREFIX = "dot_";

    // When translating, we translate the dot off the screen by this amount. This is so we see the dot actually leaving offscreen.
    private static final int SCREEN_OFFSET = 50;
    private static final int MS_BETWEEN_DOTS = 200;

    private double barWidth;

    private StackPane bar;
    private StackPane track;

    protected List<Region> dots;

    private Animation indeterminateAnimation;

    private Rectangle clip;

    private double previousWidth = -1, previousHeight = -1;

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public ProgressBarSkin(ProgressBar control) {
        super(control);

        dots = new ArrayList<>(NUMBER_DOTS);

        barWidth = ((int) (control.getWidth() - snappedLeftInset() - snappedRightInset()) * 2 * Math.min(1, Math.max(0, control.getProgress()))) / 2.0F;
        control.progressProperty().addListener(observable -> updateProgress());
        control.widthProperty().addListener(observable -> updateProgress());

        // TODO: We should care about when the indeterminate property changes

        initialize();
    }

    protected void initialize() {
        createDots();
        clip = new Rectangle();

        track = new StackPane();
        track.getStyleClass().setAll("track");

        bar = new StackPane();
        bar.getStyleClass().setAll("bar");

        getChildren().setAll(track, bar);
        getChildren().addAll(dots);
    }

    protected void updateProgress() {
        ProgressIndicator control = getSkinnable();

        final boolean isIndeterminate = control.isIndeterminate();
        if (!isIndeterminate) {
            barWidth = ((int) (control.getWidth() - snappedLeftInset() - snappedRightInset()) * 2 * Math.min(1, Math.max(0, control.getProgress()))) / 2.0F;
            getSkinnable().requestLayout();
        }
    }

    private void createDots() {
        for (int i = 0; i < NUMBER_DOTS; ++i) {
            Region dot = new Region();
            dot.getStyleClass().setAll(DOT_STYLE_CLASS, SINGLE_DOT_STYLE_CLASS_PREFIX + (i + 1));
            dot.setLayoutX(-SCREEN_OFFSET); // At first make it appear off screen so that we don't see all dots initially starting at 0 when the animation starts
            dots.add(dot);
        }
    }

    private Transition createAnimation() {
        ParallelTransition parallelTransition = new ParallelTransition();

        for (int i = 0; i < NUMBER_DOTS; ++i) {
            Region dot = dots.get(i);
            Transition transition = createAnimationForDot(dot, i);
            transition.setDelay(Duration.millis(i * MS_BETWEEN_DOTS));
            parallelTransition.getChildren().add(transition);
        }

        parallelTransition.setCycleCount(Animation.INDEFINITE);

        return parallelTransition;
    }

    private Transition createAnimationForDot(Region dot, int dotNumber) {
        double controlWidth = getSkinnable().getBoundsInLocal().getWidth();
        double firstStop = 0.6 * controlWidth;

        SequentialTransition sequentialTransition = new SequentialTransition();

        TranslateTransition firstTranslation = new TranslateTransition(Duration.millis(1800), dot);
        firstTranslation.setFromX(0);
        firstTranslation.setToX(firstStop - dotNumber * 8);
        firstTranslation.setInterpolator(Interpolator.SPLINE(0.2135, 0.9351, 0.7851, 0.9640));

        TranslateTransition secondTranslation = new TranslateTransition(Duration.millis(600), dot);
        secondTranslation.setToX(controlWidth + 50);
        secondTranslation.setInterpolator(Interpolator.SPLINE(0.9351, 0.2135, 0.9640, 0.7851));

        sequentialTransition.getChildren().addAll(firstTranslation, secondTranslation);

        return sequentialTransition;
     }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        ProgressBar control = getSkinnable();
        boolean isIndeterminate = control.isIndeterminate();

        track.resizeRelocate(contentX, contentY, contentWidth, contentHeight);

        // things should be invisible only when well below minimum length
        track.setVisible(true);

        // width might have changed so recreate our animation if needed
        if (isIndeterminate) {
            if (indeterminateAnimation != null) {
                indeterminateAnimation.stop();
            }
            // Layout indeterminate progress bar nodes
            for(int i=0; i < NUMBER_DOTS; ++i) {
                Region dot =  dots.get(i);
                dot.resize(dot.prefWidth(-1), dot.prefHeight(-1));
                dot.setLayoutY(0);
            }
            // Setup Animation
            indeterminateAnimation = createAnimation();
            if (previousWidth == -1 || previousHeight == -1 || previousWidth != contentWidth || previousHeight != contentHeight) {
                indeterminateAnimation.play();
            }
            // Set clip
            clip.setLayoutY(contentX);
            clip.setLayoutY(contentY);
            clip.setWidth(contentWidth);
            clip.setHeight(contentHeight);
            control.setClip(clip);
        } else {
            if (indeterminateAnimation != null) {
                indeterminateAnimation.stop();
                indeterminateAnimation = null;
            }
            // remove clip
            control.setClip(null);

            bar.resizeRelocate(contentX, contentY, barWidth, contentHeight);
        }

        previousWidth = contentWidth;
        previousHeight = contentHeight;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().isIndeterminate()) {
            double maxDotsHeight = 0;

            for (Region dot : dots) {
                maxDotsHeight = Math.max(dot.prefHeight(-1), maxDotsHeight);
            }

            return topInset + maxDotsHeight + bottomInset;
        } else {
            return topInset + track.prefHeight(-1) + bottomInset;
        }
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().isIndeterminate()) {
            double maxDotsHeight = 0;

            for (Region dot : dots) {
                maxDotsHeight = Math.max(dot.maxHeight(-1), maxDotsHeight);
            }

            return topInset + maxDotsHeight + bottomInset;
        } else {
            return topInset + track.maxHeight(-1) + bottomInset;
        }
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        if (getSkinnable().isIndeterminate()) {
            double maxDotsHeight = 0;

            for (Region dot : dots) {
                maxDotsHeight = Math.max(dot.minHeight(-1), maxDotsHeight);
            }

            return topInset + maxDotsHeight + bottomInset;
        } else {
            return topInset + track.minHeight(-1) + bottomInset;
        }
    }
}
