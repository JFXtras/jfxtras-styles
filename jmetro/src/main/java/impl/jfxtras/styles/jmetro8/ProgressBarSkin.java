package impl.jfxtras.styles.jmetro8;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ProgressBarSkin extends SkinBase<ProgressBar> {

    private static final int NUMBER_DOTS = 5;
    private static final String DOT_STYLE_CLASS = "dot";

    protected List<Region> dots;

    private Animation animation;

    private Rectangle clip;

    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    public ProgressBarSkin(ProgressBar control) {
        super(control);

        dots = new ArrayList<>(NUMBER_DOTS);

        createDots();

        getSkinnable().widthProperty().addListener(observable -> {
            if(control.getWidth() > 0) {
                if (animation != null) {
                    animation.stop();
                }
                animation = createAnimation();
                animation.play();
            }
        });

        getChildren().addAll(dots);
    }

    private void createDots() {
        for (int i = 0; i < NUMBER_DOTS; ++i) {
            Region dot = new Region();
            dot.getStyleClass().setAll(DOT_STYLE_CLASS);
            dots.add(dot);
        }
    }

    private Transition createAnimation() {
        ParallelTransition parallelTransition = new ParallelTransition();

        for (int i = 0; i < NUMBER_DOTS; ++i) {
            Region dot = dots.get(i);
            Transition transition = createAnimationForDot(dot);
            transition.setDelay(Duration.millis(i * 400));
            parallelTransition.getChildren().add(transition);
        }

        parallelTransition.setCycleCount(Animation.INDEFINITE);

        return parallelTransition;
    }

    private Transition createAnimationForDot(Region dot) {
        double controlWidth = getSkinnable().getBoundsInLocal().getWidth();
        double firstStop = 0.4 * controlWidth;
        double secondStop = 0.6 * controlWidth;

        Interpolator easeOut = Interpolator.SPLINE(0.4384, 0.4901, 0.2000, 0.8000);
        Interpolator easeIn = Interpolator.SPLINE(0.25, 0.1, 0.25, 1);


        SequentialTransition sequentialTransition = new SequentialTransition();

        TranslateTransition firstTranslation = new TranslateTransition(Duration.millis(800), dot);
        firstTranslation.setFromX(0);
        firstTranslation.setToX(firstStop);
        firstTranslation.setInterpolator(easeOut);

        TranslateTransition secondTranslation = new TranslateTransition(Duration.millis(1200), dot);
        secondTranslation.setToX(secondStop);
        secondTranslation.setInterpolator(Interpolator.LINEAR);

        TranslateTransition thirdTranslation = new TranslateTransition(Duration.millis(800), dot);
        thirdTranslation.setToX(controlWidth + 50);
        thirdTranslation.setInterpolator(easeIn);

        sequentialTransition.getChildren().addAll(firstTranslation, secondTranslation, thirdTranslation);

        return sequentialTransition;
     }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        ProgressBar control = getSkinnable();

        for(int i=0; i < NUMBER_DOTS; ++i) {
            Region dot =  dots.get(i);
            dot.resize(dot.prefWidth(-1), dot.prefHeight(-1));
            dot.setLayoutY(0);
        }

        // Set clip
        clip  = new Rectangle(contentWidth, contentHeight);
        clip.setLayoutX(contentX);
        clip.setLayoutY(contentY);
        control.setClip(clip);
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        double maxDotsHeight = 0;

        for (Region dot : dots) {
            maxDotsHeight = Math.max(dot.prefHeight(-1), maxDotsHeight);
        }

        return topInset + maxDotsHeight + bottomInset;
    }

    @Override
    protected double computeMaxHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        double maxDotsHeight = 0;

        for (Region dot : dots) {
            maxDotsHeight = Math.max(dot.maxHeight(-1), maxDotsHeight);
        }

        return topInset + maxDotsHeight + bottomInset;
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        double maxDotsHeight = 0;

        for (Region dot : dots) {
            maxDotsHeight = Math.max(dot.minHeight(-1), maxDotsHeight);
        }

        return topInset + maxDotsHeight + bottomInset;
    }
}
