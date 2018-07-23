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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.input.*;

public class MenuSample extends Application {

    final PageData[] pages = new PageData[] {
            new PageData("Apple",
                    "The apple is the pomaceous fruit of the apple tree, species Malus "
                            + "domestica in the rose family (Rosaceae). It is one of the most "
                            + "widely cultivated tree fruits, and the most widely known of "
                            + "the many members of genus Malus that are used by humans. "
                            + "The tree originated in Western Asia, where its wild ancestor, "
                            + "the Alma, is still found today.",
                    "Malus domestica"),
            new PageData("Hawthorn",
                    "The hawthorn is a large genus of shrubs and trees in the rose family,"
                            + "Rosaceae, native to temperate regions of the Northern Hemisphere "
                            + "in Europe, Asia and North America. The name hawthorn was "
                            + "originally applied to the species native to northern Europe, "
                            + "especially the Common Hawthorn C. monogyna, and the unmodified "
                            + "name is often so used in Britain and Ireland.",
                    "Crataegus monogyna"),
            new PageData("Ivy",
                    "The ivy is a flowering plant in the grape family (Vitaceae) native to "
                            + " eastern Asia in Japan, Korea, and northern and eastern China. "
                            + "It is a deciduous woody vine growing to 30 m tall or more given "
                            + "suitable support,  attaching itself by means of numerous small "
                            + "branched tendrils tipped with sticky disks.",
                    "Parthenocissus tricuspidata"),
            new PageData("Quince",
                    "The quince is the sole member of the genus Cydonia and is native to "
                            + "warm-temperate southwest Asia in the Caucasus region. The "
                            + "immature fruit is green with dense grey-white pubescence, most "
                            + "of which rubs off before maturity in late autumn when the fruit "
                            + "changes color to yellow with hard, strongly perfumed flesh.",
                    "Cydonia oblonga")
    };

    final String[] viewOptions = new String[] {
            "Title",
            "Binomial name",
            "Picture",
            "Description"
    };

    final private Entry[] effects = new Entry[] {
            new SimpleEntry<>("Sepia Tone", new SepiaTone()),
            new SimpleEntry<>("Glow", new Glow()),
            new SimpleEntry<>("Shadow", new DropShadow())
    };

    final private ImageView pic = new ImageView();
    final private Label name = new Label();
    final private Label binName = new Label();
    final private Label description = new Label();
    private int currentIndex = -1;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Menu Sample");
        Scene scene = new Scene(new VBox(), 400, 350);
        scene.setFill(Color.OLDLACE);

        name.setFont(new Font("Verdana Bold", 22));
        binName.setFont(new Font("Arial Italic", 10));
        pic.setFitHeight(150);
        pic.setPreserveRatio(true);
        description.setWrapText(true);
        description.setTextAlignment(TextAlignment.JUSTIFY);

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

        groupEffect.selectedToggleProperty().addListener(
                (ObservableValue<? extends Toggle> ov, Toggle old_toggle,
                 Toggle new_toggle) -> {
                    if (groupEffect.getSelectedToggle() != null) {
                        Effect effect =
                                (Effect) groupEffect.getSelectedToggle().getUserData();
                        pic.setEffect(effect);
                        noEffects.setDisable(false);
                    } else {
                        noEffects.setDisable(true);
                    }
                });

        menuEdit.getItems().addAll(menuEffect, noEffects);

        // --- Menu View
        Menu menuView = new Menu("View");
        CheckMenuItem titleView = createMenuItem ("Title", name);
        CheckMenuItem binNameView = createMenuItem ("Binomial name", binName);
        CheckMenuItem picView = createMenuItem ("Picture", pic);
        CheckMenuItem descriptionView = createMenuItem (
                "Decsription", description);

        menuView.getItems().addAll(titleView, binNameView, picView,
                descriptionView);
        menuBar.getMenus().addAll(menuFile, menuEdit, menuView);


        // --- Context Menu
        final ContextMenu cm = new ContextMenu();
        MenuItem cmItem1 = new MenuItem("Copy Image");

        cm.getItems().add(cmItem1);

        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);


        new JMetro(JMetro.Style.LIGHT).applyTheme(scene);
//        ScenicView.show(scene);


        stage.setScene(scene);
        stage.show();
    }

    private static CheckMenuItem createMenuItem (String title, final Node node){
        CheckMenuItem cmi = new CheckMenuItem(title);
        cmi.setSelected(true);
        cmi.selectedProperty().addListener(
                (ObservableValue<? extends Boolean> ov, Boolean old_val,
                 Boolean new_val) -> node.setVisible(new_val));
        return cmi;
    }

    private class PageData {
        String name;
        String description;
        String binNames;
        Image image;
        PageData(String name, String description, String binNames) {
            this.name = name;
            this.description = description;
            this.binNames = binNames;
            image = new Image(getClass().getResourceAsStream(name + ".jpg"));
        }
    }
}
