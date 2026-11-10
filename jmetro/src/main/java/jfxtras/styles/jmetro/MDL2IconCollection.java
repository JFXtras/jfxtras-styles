package jfxtras.styles.jmetro;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/** A region that stacks multiple {@link MDL2IconFont} glyphs. */
public class MDL2IconCollection extends Region {

    private StackPane stackPane = new StackPane();
    private ObservableList<MDL2IconFont> icons = FXCollections.observableArrayList();

    /** Creates an empty icon collection. */
    public MDL2IconCollection() {
        getChildren().add(stackPane);

        Bindings.bindContent(stackPane.getChildren(), icons);

        getStyleClass().add("mdl2-collection");
    }

    /**
     * Creates a collection containing the supplied icons.
     *
     * @param icons icons to stack in the collection
     */
    public MDL2IconCollection(MDL2IconFont... icons) {
        this();
        this.icons.addAll(icons);
    }

    /**
     * Returns the mutable list of icons displayed by this collection.
     *
     * @return the observable icon list
     */
    public ObservableList<MDL2IconFont> getIcons() {
        return icons;
    }

}
