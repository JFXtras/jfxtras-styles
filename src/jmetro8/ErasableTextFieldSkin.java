package jfxtras.styles.jmetro;

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
 * Created by pedro_000 on 12/5/13.
 */
public class ErasableTextFieldSkin extends TextFieldSkin{
    private StackPane clearButton;
    private Region clearGraphic;

    public ErasableTextFieldSkin(TextField textField) {
        super(textField);

        clearButton = new StackPane();
        clearButton.getStyleClass().setAll("clear-button");
        clearButton.setFocusTraversable(false);

        clearGraphic = new Region();
        clearGraphic.getStyleClass().setAll("clear-graphic");
        clearGraphic.setFocusTraversable(false);

        clearGraphic.setMaxWidth(Region.USE_PREF_SIZE);
        clearGraphic.setMaxHeight(Region.USE_PREF_SIZE);

        clearButton.setVisible(false);
        clearGraphic.setVisible(false);

        clearButton.getChildren().add(clearGraphic);
        getChildren().add(clearButton);

        setupListeners();
    }

    private void setupListeners() {

        final TextField textField = getSkinnable();
        clearButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textField.setText("");
            }
        });
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                clearButton.setVisible(!textField.getText().isEmpty());
                clearGraphic.setVisible(!textField.getText().isEmpty());
            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                clearButton.setVisible(textField.isFocused() && !textField.getText().isEmpty());
                clearGraphic.setVisible(textField.isFocused() && !textField.getText().isEmpty());
            }
        });
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        final double clearGraphicWidth = snapSize(clearGraphic.prefWidth(-1));
        final double clearButtonWidth = clearButton.snappedLeftInset() + clearGraphicWidth + clearButton.snappedRightInset();

        clearButton.resize(clearButtonWidth, h);
        positionInArea(clearButton,
                (x+w) - clearButtonWidth, y,
                clearButtonWidth, h, 0, HPos.CENTER, VPos.CENTER);
    }
}
