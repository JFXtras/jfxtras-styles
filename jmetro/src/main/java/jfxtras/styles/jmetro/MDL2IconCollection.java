package jfxtras.styles.jmetro;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class MDL2IconCollection extends Region {

    private StackPane stackPane = new StackPane();
    private ObservableList<MDL2IconFont> icons = FXCollections.observableArrayList();

    public MDL2IconCollection() {
        getChildren().add(stackPane);

        Bindings.bindContent(stackPane.getChildren(), icons);

        getStyleClass().add("mdl2-collection");
    }

    public MDL2IconCollection(MDL2IconFont... icons) {
        this();
        this.icons.addAll(icons);
    }

    public ObservableList<MDL2IconFont> getIcons() {
        return icons;
    }

}
