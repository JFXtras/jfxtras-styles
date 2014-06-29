package jfxtras.styles.jmetro8;

import com.sun.javafx.scene.control.skin.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.SkinBase;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * Created by pedro_000 on 12/4/13.
 */
public class ToggleSwitchSkin extends SkinBase<ToggleSwitch>{
    StackPane thumb;
    StackPane thumbArea;
    LabeledText label;
    StackPane labelContainer;


    /**
     * Constructor for all SkinBase instances.
     *
     * @param control The control for which this Skin should attach to.
     */
    protected ToggleSwitchSkin(ToggleSwitch control) {
        super(control);

        thumb = new StackPane();
        thumbArea = new StackPane();
        label = new LabeledText(control);
        labelContainer = new StackPane();

        updateLabel(control);
        getChildren().addAll(labelContainer, thumbArea, thumb);
        labelContainer.getChildren().addAll(label);
        StackPane.setAlignment(label, Pos.CENTER_LEFT);

        thumb.getStyleClass().setAll("thumb");
        thumbArea.getStyleClass().setAll("thumb-area");

        thumbArea.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mousePressedOnToggleSwitch(control);
            }
        });
        control.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updateLabel(control);
            }
        });
    }

    private void mousePressedOnToggleSwitch(ToggleSwitch toggleSwitch) {

        toggleSwitch.setSelected(!toggleSwitch.isSelected());
        toggleSwitch.requestLayout();
        // TODO: instead of requesting a relayout, animate the thumb transition
    }

    private void updateLabel(ToggleSwitch skinnable) {
        label.setText(skinnable.isSelected() ? skinnable.getTurnOnText() : skinnable.getTurnOffText());
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        ToggleSwitch toggleSwitch = getSkinnable();

        double thumbWidth = snapSize(thumb.prefWidth(-1));
        double thumbHeight = snapSize(thumb.prefHeight(-1));
        thumb.resize(thumbWidth, thumbHeight);

        double thumbAreaY = snapPosition(contentY);
        double thumbAreaWidth = snapSize(thumbArea.prefWidth(-1));
        double thumbAreaHeight = snapSize(thumbArea.prefHeight(-1));

        thumbArea.resize(thumbAreaWidth, thumbAreaHeight);
        thumbArea.setLayoutX(contentWidth - thumbAreaWidth);
        thumbArea.setLayoutY(thumbAreaY);

        labelContainer.resize(contentWidth - thumbAreaWidth, thumbAreaHeight);
        labelContainer.setLayoutY(thumbAreaY);

        if (!toggleSwitch.isSelected())
        {
            thumb.setLayoutX(thumbArea.getLayoutX());
            thumb.setLayoutY(thumbAreaY);
        } else
        {
            thumb.setLayoutX(thumbArea.getLayoutX() + thumbAreaWidth - thumbWidth);
            thumb.setLayoutY(thumbAreaY);
        }
    }


    @Override protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final String labelText = label.getText();
        final Font font = label.getFont();
        double textWidth = Utils.computeTextWidth(font, labelText, 0);

        return leftInset + textWidth + thumbArea.prefWidth(-1) + rightInset;
    }

    @Override protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        final Font font = label.getFont();
        final String labelText = label.getText();
        final double textHeight = Utils.computeTextHeight(font, labelText, 0, label.getLineSpacing(), label.getBoundsType());

        return topInset + Math.max(minThumbAreaHeight(), textHeight) + bottomInset;
    }

    @Override protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        final String labelText = label.getText();
        final Font font = label.getFont();
        double textWidth = Utils.computeTextWidth(font, labelText, 0);

        return leftInset + textWidth + 20 + thumbArea.prefWidth(-1) + rightInset;
    }

    @Override protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset)
    {
        final Font font = label.getFont();
        final String labelText = label.getText();
        final double textHeight = Utils.computeTextHeight(font, labelText, 0, label.getLineSpacing(), label.getBoundsType());

        return topInset + Math.max(minThumbAreaHeight(), textHeight) + bottomInset;
    }

    private double minThumbAreaHeight()
    {
        return thumb.prefHeight(-1);
    }
}
