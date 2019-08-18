package impl.jfxtras.styles.jmetro;

import javafx.geometry.Orientation;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class SliderSkin extends javafx.scene.control.skin.SliderSkin {
    private StackPane fill = new StackPane();

    private StackPane thumb, track;

    private double trackToTickGap = 2;

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
    }

    private void mouseDraggedOnTrack(MouseEvent mouseEvent) {

    }

    private void mousePressedOnTrack(MouseEvent mouseEvent) {

    }

    private void mouseReleasedFromTrack(MouseEvent mouseEvent) {

    }

    private void mouseDraggedOnThumb(MouseEvent mouseEvent) {

    }

    private void mousePressedOnThumb(MouseEvent mouseEvent) {

    }

    private void mouseReleasedFromThumb(MouseEvent mouseEvent) {

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
}
