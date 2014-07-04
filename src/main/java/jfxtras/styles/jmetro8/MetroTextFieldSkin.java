package jfxtras.styles.jmetro8;

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
public class MetroTextFieldSkin extends TextFieldWithButtonSkin{
    public MetroTextFieldSkin(TextField textField) {
        super(textField);
    }

    protected void rightButtonPressed()
    {
        getSkinnable().setText("");
    }

}