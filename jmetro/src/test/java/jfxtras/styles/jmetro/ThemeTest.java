package jfxtras.styles.jmetro;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.css.PseudoClass;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.controlsfx.control.ToggleSwitch;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/** Desktop regression checks, run by Gradle's test/check tasks. */
public final class ThemeTest {
    @Test
    public void themes() throws Exception {
        List<String> cssWarnings = new CopyOnWriteArrayList<>();
        Logger logger = Logger.getLogger("javafx.css");
        Handler handler = new Handler() {
            @Override
            public void publish(LogRecord record) {
                if (record.getLevel().intValue() >= Level.WARNING.intValue()) {
                    cssWarnings.add(record.getMessage());
                }
            }

            @Override
            public void flush() {}

            @Override
            public void close() {}
        };
        logger.addHandler(handler);
        FutureTask<Void> checks = new FutureTask<>(() -> {
            Platform.setImplicitExit(false);
            verifyThemes();
            return null;
        });
        Platform.startup(checks);
        try {
            checks.get(90, TimeUnit.SECONDS);
            require(cssWarnings.isEmpty(), "CSS warnings: " + cssWarnings);
            System.out.println("All four styles, 16 transitions, parent/scene targets and popup checks passed.");
        } finally {
            Platform.exit();
            logger.removeHandler(handler);
        }
    }

    private static void verifyThemes() throws IOException {
        require(Style.values().length == 4, "Expected exactly four styles");
        require(new JMetro().getStyle() == Style.WIN10_LIGHT, "Default style changed");
        Button button = new Button("Normal");
        Button primary = new Button("Apply");
        primary.setDefaultButton(true);
        Button disabled = new Button("Disabled");
        disabled.setDisable(true);
        ToggleButton toggle = new ToggleButton("Selected");
        toggle.setSelected(true);
        CheckBox check = new CheckBox("Check");
        check.setSelected(true);
        RadioButton radio = new RadioButton("Radio");
        radio.setSelected(true);
        TextField text = new TextField("Text");
        PasswordField password = new PasswordField();
        password.setText("password");
        TextArea area = new TextArea("Notes");
        area.setPrefRowCount(2);
        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll("One", "Two");
        combo.getSelectionModel().selectFirst();
        ComboBox<String> editableCombo = new ComboBox<>();
        editableCombo.setEditable(true);
        ChoiceBox<String> choice = new ChoiceBox<>();
        choice.getItems().addAll("One", "Two");
        choice.getSelectionModel().selectFirst();
        DatePicker date = new DatePicker();
        Spinner<Integer> spinner = new Spinner<>(0, 10, 5);
        ListView<String> list = new ListView<>();
        list.getItems().addAll("One", "Two");
        list.getSelectionModel().selectFirst();
        list.setPrefHeight(80);
        TreeView<String> tree = new TreeView<>(new TreeItem<>("Root"));
        tree.setPrefHeight(80);
        TableView<String> table = new TableView<>();
        table.getColumns().add(new TableColumn<>("Column"));
        table.getItems().add("One");
        table.setPrefHeight(80);
        TreeTableView<String> treeTable = new TreeTableView<>(new TreeItem<>("Root"));
        treeTable.getColumns().add(new TreeTableColumn<>("Column"));
        treeTable.setPrefHeight(80);
        MenuButton menu = new MenuButton("Menu", null, new MenuItem("Action"));
        SplitMenuButton split = new SplitMenuButton(new MenuItem("Action"));
        split.setText("Split");
        ToggleSwitch toggleSwitch = new ToggleSwitch("Sync");
        Slider slider = new Slider();
        TabPane tabs = new TabPane(new Tab("Tab", new Label("Content")));
        TitledPane titled = new TitledPane("Details", new Label("Content"));
        ColorPicker colorPicker = new ColorPicker();
        ContextMenu contextMenu = new ContextMenu(new MenuItem("Action"));
        button.setContextMenu(contextMenu);
        VBox root = new VBox(8,
                new HBox(8, button, primary, disabled, toggle, check, radio),
                new HBox(8, text, password), area,
                new HBox(8, combo, editableCombo, choice),
                new HBox(8, date, spinner, menu, split, colorPicker),
                new HBox(8, slider, toggleSwitch, new ProgressBar(0.5)),
                new HBox(8, list, tree, table, treeTable), tabs, titled);
        root.setPadding(new Insets(16));
        root.getStyleClass().add("background");
        Scene scene = new Scene(root, 1100, 740);
        JMetro metro = new JMetro(scene, Style.WIN10_LIGHT);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
        try {
            for (Style from : Style.values()) {
                for (Style to : Style.values()) {
                    metro.setStyle(from);
                    metro.setStyle(to);
                    metro.reApplyTheme();
                    metro.reApplyTheme();
                    root.applyCss();
                    root.layout();
                    verifyStylesheets(scene.getStylesheets(), to);
                    boolean win11 = to == Style.WIN11_LIGHT || to == Style.WIN11_DARK;
                    double radius = button.getBackground().getFills().getFirst()
                            .getRadii().getTopLeftHorizontalRadius();
                    require(radius == (win11 ? 4 : 0), "Wrong button radius for " + to + ": " + radius);
                    Color expectedBackground = Color.web(to == Style.WIN11_DARK ? "#202020"
                            : to == Style.WIN10_DARK ? "#252525" : "#f3f3f3");
                    require(fill(root).equals(expectedBackground), "Wrong background for " + to);
                    if (win11) {
                        verifyWin11(root, primary, text, check, slider, to);
                        require(combo.getBackground().getFills().size() == 1,
                                "Combo must not retain Windows 10 border layers");
                        require(combo.getHeight() == 32 && choice.getHeight() == 32
                                        && date.getHeight() == 32,
                                "Windows 11 selectors must use the 32px control height");
                        require(fill((Region) radio.lookup(".radio")).equals(
                                Color.web(to == Style.WIN11_DARK ? "#60cdff" : "#005fb8")),
                                "Selected radio must have an accent-filled ring");
                    }
                    for (String state : List.of("hover", "pressed", "focused")) {
                        PseudoClass pseudo = PseudoClass.getPseudoClass(state);
                        button.pseudoClassStateChanged(pseudo, true);
                        text.pseudoClassStateChanged(pseudo, true);
                        root.applyCss();
                        root.snapshot(null, null);
                        button.pseudoClassStateChanged(pseudo, false);
                        text.pseudoClassStateChanged(pseudo, false);
                    }
                    combo.show();
                    verifyPopup(combo.isShowing(), "combo");
                    combo.hide();
                    choice.show();
                    verifyPopup(choice.isShowing(), "choice");
                    choice.hide();
                    date.show();
                    verifyPopup(date.isShowing(), "date");
                    date.hide();
                    menu.show();
                    verifyPopup(menu.isShowing(), "menu");
                    menu.hide();
                    colorPicker.show();
                    verifyPopup(colorPicker.isShowing(), "color");
                    colorPicker.hide();
                    contextMenu.show(button, javafx.geometry.Side.BOTTOM, 0, 0);
                    contextMenu.getSkin().getNode().applyCss();
                    if (win11) {
                        require(fill((Region) contextMenu.getSkin().getNode()).equals(
                                Color.web(to == Style.WIN11_DARK ? "#2b2b2b" : "#ffffff")),
                                "Popup palette did not follow " + to);
                    }
                    contextMenu.hide();
                    if (from == Style.WIN10_LIGHT) {
                        root.applyCss();
                        saveSnapshot(root, to);
                    }
                }
            }
            verifyOverrides(metro, root, button);
            verifyParentTarget();
        } finally {
            stage.close();
        }
    }

    private static void verifyWin11(VBox root, Button primary, TextField text,
                                    CheckBox check, Slider slider, Style style) {
        Color accent = Color.web(style == Style.WIN11_DARK ? "#60cdff" : "#005fb8");
        require(fill(primary).equals(accent), "Wrong accent for " + style);
        require(primary.getTextFill().equals(Color.web(
                style == Style.WIN11_DARK ? "#003e5c" : "#ffffff")), "Wrong accent text");
        require(((Region) check.lookup(".box")).getBackground().getFills().getFirst()
                .getRadii().getTopLeftHorizontalRadius() == 4, "Checkbox is not rounded");
        Region thumb = (Region) slider.lookup(".thumb");
        require(thumb.getBackground().getFills().size() == 2, "Slider thumb must have two layers");
        text.pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), true);
        root.applyCss();
        require(text.getBorder().getStrokes().getFirst().getBottomStroke().equals(accent),
                "Input focus underline must use accent");
        text.pseudoClassStateChanged(PseudoClass.getPseudoClass("focused"), false);
    }

    private static void verifyOverrides(JMetro metro, VBox root, Button button) {
        String override = ThemeTest.class.getResource("theme_override.css").toExternalForm();
        metro.getOverridingStylesheets().add(override);
        for (Style style : Style.values()) {
            metro.setStyle(style);
            root.applyCss();
            require(fill(button).equals(Color.MAGENTA), "Override lost priority for " + style);
            require(metro.getScene().getStylesheets().getLast().equals(override),
                    "Override must follow the selected style");
        }
        metro.getOverridingStylesheets().remove(override);
    }

    private static void verifyParentTarget() {
        VBox parent = new VBox(new Button("Parent target"));
        new Scene(parent);
        JMetro metro = new JMetro(parent, Style.WIN11_DARK);
        SimpleObjectProperty<Style> selectedStyle = new SimpleObjectProperty<>(Style.WIN11_DARK);
        metro.styleProperty().bind(selectedStyle);
        require(parent.getStyleClass().contains("root"), "Parent must have root style class");
        for (Style style : Style.values()) {
            selectedStyle.set(style);
            parent.applyCss();
            verifyStylesheets(parent.getStylesheets(), style);
        }
    }

    private static void verifyStylesheets(List<String> stylesheets, Style expected) {
        List<String> selected = new ArrayList<>();
        for (Style style : Style.values()) {
            for (String stylesheet : stylesheets) {
                if (stylesheet.equals(style.getStyleStylesheetURL())) {
                    selected.add(stylesheet);
                }
            }
        }
        require(selected.equals(List.of(expected.getStyleStylesheetURL())),
                "Stale or duplicate stylesheets: " + selected);
        int selectedIndex = stylesheets.indexOf(expected.getStyleStylesheetURL());
        require(selectedIndex > stylesheets.indexOf(JMetro.class.getResource("base_other_libraries.css")
                .toExternalForm()), "Theme must follow all base stylesheets");
    }

    private static Paint fill(Region region) {
        List<BackgroundFill> fills = region.getBackground().getFills();
        return fills.getLast().getFill();
    }

    private static void verifyPopup(boolean condition, String popup) {
        require(condition, "Popup content missing: " + popup);
        for (Window window : Window.getWindows()) {
            if (window.isShowing() && window.getScene() != null) {
                window.getScene().getRoot().applyCss();
                window.getScene().getRoot().layout();
            }
        }
    }

    private static void saveSnapshot(VBox root, Style style) throws IOException {
        var image = root.snapshot(null, null);
        int width = (int) image.getWidth();
        int height = (int) image.getHeight();
        BufferedImage png = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                png.setRGB(x, y, image.getPixelReader().getArgb(x, y));
            }
        }
        Path directory = Path.of("build", "theme-snapshots");
        Files.createDirectories(directory);
        require(ImageIO.write(png, "png", directory.resolve(style.name() + ".png").toFile()),
                "PNG writer unavailable");
    }

    private static void require(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
