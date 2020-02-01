package jfxtras.styles.samples.jmetro.panessample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.net.URL;
import java.util.ResourceBundle;

public class PanesWithBackgroundStyleClassController implements Initializable {
    @FXML
    private ComboBox<String> styleComboBox;

    private JMetro jMetro;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        styleComboBox.getItems().addAll("Light", "Dark");
    }

    public void init(JMetro jmetro) {
        this.jMetro = jmetro;

        styleComboBox.setValue(jmetro.getStyle().equals(Style.LIGHT) ? "Light" : "Dark");
        styleComboBox.valueProperty().addListener(observable -> {
            if (styleComboBox.getValue().equals("Light")) {
                jmetro.setStyle(Style.LIGHT);
            } else {
                jmetro.setStyle(Style.DARK);
            }
        });
    }
}