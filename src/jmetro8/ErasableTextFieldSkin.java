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

        // Layout
//        getSkinnable().widthProperty().addListener(observable -> {
//            double size = loupe.getMaxWidth() < 0 ? 20.8 : loupe.getWidth();
//            loupe.setTranslateX(-getSkinnable().getWidth() * 0.5 + size * 0.7);
//            crossButton.setTranslateX(getSkinnable().getWidth() * 0.5 - size * 0.7);
//        });
//        getSkinnable().heightProperty().addListener(observable -> {
//            crossButton.setMaxSize(getSkinnable().getHeight() * 0.8,
//                    getSkinnable().getHeight() * 0.8);
//            loupe.setMaxSize(getSkinnable().getHeight() * 0.8,
//                    getSkinnable().getHeight() * 0.8);
//        });
//        getSkinnable().sceneProperty().addListener(observable -> {
//            loupe.setTranslateX(-getSkinnable().getWidth() * 0.5 + crossButton.getWidth() * 0.7);
//            crossButton.setTranslateX(getSkinnable().getWidth() * 0.5 - loupe.getWidth() * 0.7);
//        });
//        getSkinnable().widthProperty().addListener(observable -> {
//            double clearButtonWidth = clearButton.getWidth();
//            clearButton.setLayoutX(textField.getWidth() * 0.5 - clearButtonWidth * 0.7);
//        });
//        getSkinnable().sceneProperty().addListener(observable -> {
//            clearButton.setLayoutX(textField.getWidth() * 0.5 - clearButton.getWidth() * 0.7);
//        });
//        getSkinnable().heightProperty().addListener(observable -> {
//            clearButton.setMaxSize(getSkinnable().getHeight() * 0.8,
//                    getSkinnable().getHeight() * 0.8);
//        });
    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

//        final double arrowWidth = snapSize(arrow.prefWidth(-1));
//        final double arrowButtonWidth = (isButton()) ? 0 :
//                arrowButton.snappedLeftInset() + arrowWidth +
//                        arrowButton.snappedRightInset();
//
//        if (displayNode != null) {
//            displayNode.resizeRelocate(x, y, w - arrowButtonWidth, h);
//        }
//
//        if (isButton()) return;
//
//        arrowButton.resize(arrowButtonWidth, h);
//        positionInArea(arrowButton,
//                (x+w) - arrowButtonWidth, y,
//                arrowButtonWidth, h, 0, HPos.CENTER, VPos.CENTER);

        // TODO: layout clear button
        final double clearGraphicWidth = snapSize(clearGraphic.prefWidth(-1));
        final double clearButtonWidth = clearButton.snappedLeftInset() + clearGraphicWidth + clearButton.snappedRightInset();

        clearButton.resize(clearButtonWidth, h);
        positionInArea(clearButton,
                (x+w) - clearButtonWidth, y,
                clearButtonWidth, h, 0, HPos.CENTER, VPos.CENTER);
    }
}
