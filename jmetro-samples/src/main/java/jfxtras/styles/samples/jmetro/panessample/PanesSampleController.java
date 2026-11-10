package jfxtras.styles.samples.jmetro.panessample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.net.URL;
import java.util.ResourceBundle;

public class PanesSampleController implements Initializable {
    @FXML
    private ComboBox<Style> styleComboBox;

    private JMetro jMetro;


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        styleComboBox.getItems().addAll(Style.values());
    }

    public void init(JMetro jmetro) {
        this.jMetro = jmetro;

        styleComboBox.setValue(jmetro.getStyle());
        styleComboBox.valueProperty().addListener(observable -> jmetro.setStyle(styleComboBox.getValue()));
    }
}
