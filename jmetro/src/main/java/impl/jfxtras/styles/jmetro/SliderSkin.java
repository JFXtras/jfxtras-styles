package impl.jfxtras.styles.jmetro;

import javafx.animation.FadeTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.converter.BooleanConverter;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SliderSkin extends javafx.scene.control.skin.SliderSkin {
    private StackPane fill = new StackPane();

    private StackPane thumb, track;

    private double trackToTickGap = 2;

    private SliderPopup popup = new SliderPopup();
    private static final int POPUP_DISTANCE_FROM_THUMB = 50;
    private static final Duration POPUP_FADE_DURATION = Duration.millis(200);

    public SliderSkin(Slider control) {
        super(control);

        track = (StackPane) getSkinnable().lookup(".track");
        thumb = (StackPane) getSkinnable().lookup(".thumb");

        fill.getStyleClass().add("fill");

        // Add fill right above track
        getChildren().add(getChildren().indexOf(track) + 1, fill);

        track.addEventHandler(MouseEvent.MOUSE_PRESSED, this::mousePressedOnTrack);
        track.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::mouseDraggedOnTrack);
        track.addEventHandler(MouseEvent.MOUSE_RELEASED, this::mouseReleasedFromTrack);
        fill.addEventHandler(MouseEvent.MOUSE_PRESSED, this::mousePressedOnTrack);
        fill.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::mouseDraggedOnTrack);
        fill.addEventHandler(MouseEvent.MOUSE_RELEASED, this::mouseReleasedFromTrack);
        thumb.addEventHandler(MouseEvent.MOUSE_PRESSED, this::mousePressedOnThumb);
        thumb.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::mouseDraggedOnThumb);
        thumb.addEventHandler(MouseEvent.MOUSE_RELEASED, this::mouseReleasedFromThumb);

        registerChangeListener(control.showTickMarksProperty(), this::thickMarksChanged);
        registerChangeListener(control.showTickLabelsProperty(), this::thickMarksChanged);
    }

    private void thickMarksChanged(ObservableValue<?> observableValue) {
        /* When this method is called setShowTickMarks of super class has already been called
           on that method the children's list has been cleared so we need to re-add any Nodes that this Class adds
           to the Slider */

        // Add fill right above track
        getChildren().add(getChildren().indexOf(track) + 1, fill);
    }

    private void mousePressedOnTrack(MouseEvent mouseEvent) {
        showValuePopup();
    }

    private void mouseDraggedOnTrack(MouseEvent mouseEvent) {
        displaceValuePopup();
    }

    private void mouseReleasedFromTrack(MouseEvent mouseEvent) {
        hideValuePopup();
    }

    private void mousePressedOnThumb(MouseEvent mouseEvent) {
        showValuePopup();
    }

    private void mouseDraggedOnThumb(MouseEvent mouseEvent) {
        displaceValuePopup();
    }

    private void mouseReleasedFromThumb(MouseEvent mouseEvent) {
        hideValuePopup();
    }

    private void showValuePopup() {
        if (!isShowValueOnInteraction()) {
            return;
        }


        popup.setValue(getSkinnable().getValue());

        Point2D thumbScreenPos = thumb.localToScreen(thumb.getBoundsInLocal().getMinX(), thumb.getBoundsInLocal().getMinY());

        Orientation orientation = getSkinnable().getOrientation();
        if (orientation.equals(Orientation.HORIZONTAL)) {
            popup.show(thumb, thumbScreenPos.getX() + thumb.getWidth() / 2, thumbScreenPos.getY() - POPUP_DISTANCE_FROM_THUMB);
            popup.setX(popup.getX() - popup.getWidth() / 2); /* We only know the Popup's bounds after we show it */
        } else if (orientation.equals(Orientation.VERTICAL)){
            popup.show(thumb, thumbScreenPos.getX() - POPUP_DISTANCE_FROM_THUMB, thumbScreenPos.getY() + thumb.getHeight() / 2);
            popup.setY(popup.getY() - popup.getHeight() / 2); /* We only know the Popup's bounds after we show it */
        }

        FadeTransition fadeInTransition = new FadeTransition(POPUP_FADE_DURATION, popup.getScene().getRoot());
        fadeInTransition.setFromValue(0);
        fadeInTransition.setToValue(1);
        fadeInTransition.play();
    }

    private void displaceValuePopup() {
        if (!isShowValueOnInteraction()) {
            return;
        }

        if (popup.isShowing()) {
            popup.setValue(getSkinnable().getValue());

            Point2D thumbScreenPos = thumb.localToScreen(thumb.getBoundsInLocal().getMinX(), thumb.getBoundsInLocal().getMinY());

            Orientation orientation = getSkinnable().getOrientation();
            if (orientation.equals(Orientation.HORIZONTAL)) {
                popup.setX(thumbScreenPos.getX() + thumb.getWidth() / 2 - popup.getWidth() / 2);
            } else if (orientation.equals(Orientation.VERTICAL)) {
                popup.setY(thumbScreenPos.getY() + thumb.getHeight() / 2 - popup.getHeight() / 2);
            }
        }
    }

    private void hideValuePopup() {
        if (!isShowValueOnInteraction()) {
            return;
        }

        FadeTransition fadeOutTransition = new FadeTransition(POPUP_FADE_DURATION, popup.getScene().getRoot());
        fadeOutTransition.setFromValue(1);
        fadeOutTransition.setToValue(0);
        fadeOutTransition.setOnFinished(actionEvent -> popup.hide());

        fadeOutTransition.play();
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        Slider control = getSkinnable();

        boolean showTickMarks = control.isShowTickMarks() ||control.isShowTickLabels();

        double thumbWidth = snapSizeX(thumb.prefWidth(-1));
        double thumbHeight = snapSizeY(thumb.prefHeight(-1));

        // we are assuming the is common radius's for all corners on the track
        double trackRadius = track.getBackground() == null ? 0 : track.getBackground().getFills().size() > 0 ?
                track.getBackground().getFills().get(0).getRadii().getTopLeftHorizontalRadius() : 0;

        NumberAxis tickLine = (NumberAxis) control.lookup("NumberAxis");

        if (getSkinnable().getOrientation() == Orientation.HORIZONTAL) {
            double tickLineHeight =  (showTickMarks) ? tickLine.prefHeight(-1) : 0;
            double trackHeight = snapSizeY(track.prefHeight(-1));
            double trackAreaHeight = Math.max(trackHeight,thumbHeight);
            double totalHeightNeeded = trackAreaHeight  + ((showTickMarks) ? trackToTickGap+tickLineHeight : 0);
            double startY = y + ((h - totalHeightNeeded)/2); // center slider in available height vertically

            double trackStart = snapPositionX(x + (thumbWidth/2));
            double trackTop = (int)(startY + ((trackAreaHeight-trackHeight)/2));

            fill.resizeRelocate((int)(trackStart - trackRadius),
                    trackTop ,
                    ((int)trackStart - trackRadius) + thumb.getLayoutX(),
                    trackHeight);
        } else {  // VERTICAL
            double tickLineWidth = (showTickMarks) ? tickLine.prefWidth(-1) : 0;
            double trackWidth = snapSizeX(track.prefWidth(-1));
            double trackAreaWidth = Math.max(trackWidth,thumbWidth);
            double totalWidthNeeded = trackAreaWidth  + ((showTickMarks) ? trackToTickGap+tickLineWidth : 0) ;
            double startX = x + ((w - totalWidthNeeded)/2); // center slider in available width horizontally
            double trackLength = snapSizeY(h - thumbHeight);
            double trackStart = snapPositionY(y + (thumbHeight/2));
            double trackLeft = (int)(startX + ((trackAreaWidth-trackWidth)/2));

            fill.resizeRelocate(trackLeft,
                    ((int)trackStart - trackRadius) + thumb.getLayoutY(),
                    trackWidth,
                    trackLength - thumb.getLayoutY());
        }
    }

    /********** CSS Properties ****************/

    private static final CssMetaData<Slider, Boolean> SHOW_VALUE_ON_INTERACTION_META_DATA =
            new CssMetaData<>("-show-value-on-interaction",
                    BooleanConverter.getInstance(), true) {

                @Override
                public boolean isSettable(Slider slider) {
                    final SliderSkin skin = (SliderSkin) slider.getSkin();
                    return !skin.showValueOnInteraction.isBound();
                }

                @Override
                public StyleableProperty<Boolean> getStyleableProperty(Slider slider) {
                    final SliderSkin skin = (SliderSkin) slider.getSkin();
                    return (StyleableProperty<Boolean>) skin.showValueOnInteractionProperty();
                }
            };

    private BooleanProperty showValueOnInteraction = new SimpleStyleableBooleanProperty(SHOW_VALUE_ON_INTERACTION_META_DATA, true);

    private BooleanProperty showValueOnInteractionProperty() { return showValueOnInteraction; }

    private boolean isShowValueOnInteraction() { return showValueOnInteraction.get(); }


    /* Setup styleables for this Skin */
    private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    static {
        final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<>(SkinBase.getClassCssMetaData());
        styleables.add(SHOW_VALUE_ON_INTERACTION_META_DATA);
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
