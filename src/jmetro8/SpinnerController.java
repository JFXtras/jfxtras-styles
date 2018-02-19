package jfxtras.styles.jmetro8;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by pedro_000 on 8/5/2015.
 */
public class SpinnerController implements Initializable
{
    @FXML private Spinner spinner;
    @FXML private Spinner disabledSpinner;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
//        spinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);
//        disabledSpinner.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_HORIZONTAL);

        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
    }
}
