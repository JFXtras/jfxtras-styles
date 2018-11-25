package jfxtras.styles.jmetro8;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.SepiaTone;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.input.*;

public class MenuSample extends Application {

    private static final JMetro.Style STYLE = JMetro.Style.LIGHT;

    private final Entry[] effects = new Entry[] {
            new SimpleEntry<>("Sepia Tone", new SepiaTone()),
            new SimpleEntry<>("Glow", new Glow()),
            new SimpleEntry<>("Shadow", new DropShadow())
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 400, 350);

        MenuBar menuBar = new MenuBar();

        // --- Menu File
        Menu menuFile = new Menu("File");
        MenuItem add = new MenuItem("Shuffle",
                new ImageView(new Image(MenuSample.class.getResource("new.png").toExternalForm())));


        MenuItem clear = new MenuItem("Clear");
        clear.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));


        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction((ActionEvent t) -> System.exit(0));

        menuFile.getItems().addAll(add, clear, new SeparatorMenuItem(), exit);

        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
        Menu menuEffect = new Menu("Picture Effect");

        final ToggleGroup groupEffect = new ToggleGroup();
        for (Entry<String, Effect> effect : effects) {
            RadioMenuItem itemEffect = new RadioMenuItem(effect.getKey());
            itemEffect.setUserData(effect.getValue());
            itemEffect.setToggleGroup(groupEffect);
            menuEffect.getItems().add(itemEffect);
        }

        final MenuItem noEffects = new MenuItem("No Effects");
        noEffects.setDisable(true);

        menuEdit.getItems().addAll(menuEffect, noEffects);

        // --- Menu View
        Menu menuView = new Menu("View");
        CheckMenuItem titleView = createMenuItem ("Title");
        CheckMenuItem binNameView = createMenuItem ("Binomial name");
        CheckMenuItem picView = createMenuItem ("Picture");
        CheckMenuItem descriptionView = createMenuItem (
                "Decsription");

        menuView.getItems().addAll(titleView, binNameView, picView,
                descriptionView);
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);


        if (STYLE.equals(JMetro.Style.LIGHT)) {
            scene.getRoot().setStyle("-fx-background-color: #F9F9F9;");
        } else {
            scene.getRoot().setStyle("-fx-background-color: #333333;");
        }

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);


        new JMetro(STYLE).applyTheme(scene);
//        ScenicView.show(scene);


        stage.setScene(scene);
        stage.show();
    }

    private static CheckMenuItem createMenuItem (String title){
        CheckMenuItem cmi = new CheckMenuItem(title);
        cmi.setSelected(true);
        return cmi;
    }
}
