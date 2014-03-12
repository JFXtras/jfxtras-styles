package jfxtras.styles.jmetro8;

import com.sun.javafx.scene.control.behavior.TextFieldBehavior;
import com.sun.javafx.scene.control.skin.TextFieldSkin;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * Created by pedro_000 on 12/15/13.
 */
public class TextFieldWithButtonSkin extends TextFieldSkin{
    private StackPane rightButton;
    private Region rightButtonGraphic;

    public TextFieldWithButtonSkin(TextField textField) {
        super(textField);

        rightButton = new StackPane();
        rightButton.getStyleClass().setAll("right-button");
        rightButton.setFocusTraversable(false);

        rightButtonGraphic = new Region();
        rightButtonGraphic.getStyleClass().setAll("right-button-graphic");
        rightButtonGraphic.setFocusTraversable(false);

        rightButtonGraphic.setMaxWidth(Region.USE_PREF_SIZE);
        rightButtonGraphic.setMaxHeight(Region.USE_PREF_SIZE);

        rightButton.setVisible(false);
        rightButtonGraphic.setVisible(false);

        rightButton.getChildren().add(rightButtonGraphic);
        getChildren().add(rightButton);

        setupListeners();
    }

    private void setupListeners() {

        final TextField textField = getSkinnable();
        rightButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rightButtonPressed();
            }
        });
        rightButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rightButtonReleased();
            }
        });

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textChanged(textField);
            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                rightButton.setVisible(textField.isFocused() && !textField.getText().isEmpty());
                rightButtonGraphic.setVisible(textField.isFocused() && !textField.getText().isEmpty());
            }
        });
    }

    private void textChanged(TextField textField) {
        
        rightButton.setVisible(!textField.getText().isEmpty());
        rightButtonGraphic.setVisible(!textField.getText().isEmpty());
    }

    protected void textChanged()
    {
        if (textField.getText() == null || textField.getText().isEmpty())
            return;

        rightButton.setVisible(true);
        rightButtonGraphic.setVisible(true);
    }

    protected void focusChanged()
    {
        if (textField.getText() == null || textField.getText().isEmpty())
            return;

        rightButton.setVisible(textField.isFocused());
        rightButtonGraphic.setVisible(textField.isFocused());
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        final double clearGraphicWidth = snapSize(rightButtonGraphic.prefWidth(-1));
        final double clearButtonWidth = rightButton.snappedLeftInset() + clearGraphicWidth + rightButton.snappedRightInset();

        rightButton.resize(clearButtonWidth, h);
        positionInArea(rightButton,
                (x+w) - clearButtonWidth, y,
                clearButtonWidth, h, 0, HPos.CENTER, VPos.CENTER);
    }

    protected void rightButtonPressed()
    {
    }

    protected void rightButtonReleased()
    {

    }

}
