package jfxtras.styles.samples.jmetro.themetester;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThemeTester extends Application {
    public static final String TEST = "test";
    public static final String SKINBASE = "com/sun/javafx/scene/control/skin/";

    static {
        System.getProperties().put("javafx.pseudoClassOverrideEnabled", "true");
    }

    private static final String TEST_APP_CSS_URL = ThemeTester.class.getResource("TestApp.css").toExternalForm();

//    private static String MODENA_STYLESHEET_URL;
//    private static String MODENA_EMBEDDED_STYLESHEET_URL;
//    private static String MODENA_STYLESHEET_BASE;

    static {
//        try {
            File modenaCssFile = new File("../../../modules/controls/src/main/resources/com/sun/javafx/scene/control/skin/modena/modena.css");
            if (!modenaCssFile.exists()) {
                modenaCssFile = new File("rt/modules/controls/src/main/resources/com/sun/javafx/scene/control/skin/modena/modena.css");
                System.out.println("modenaCssFile = " + modenaCssFile);
                System.out.println("modenaCssFile = " + modenaCssFile.getAbsolutePath());
            }
//            MODENA_STYLESHEET_URL = modenaCssFile.exists() ?
//                    modenaCssFile.toURI().toURL().toExternalForm() :
//                    com.sun.javafx.scene.control.skin.ButtonSkin.class.getResource("modena/modena.css").toExternalForm();
//            MODENA_STYLESHEET_BASE = MODENA_STYLESHEET_URL.substring(0,MODENA_STYLESHEET_URL.lastIndexOf('/')+1);
//            MODENA_EMBEDDED_STYLESHEET_URL = MODENA_STYLESHEET_BASE + "modena-embedded-performance.css";
//            System.out.println("MODENA_EMBEDDED_STYLESHEET_URL = " + MODENA_EMBEDDED_STYLESHEET_URL);
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(ThemeTester.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private BorderPane outerRoot;
    private BorderPane root;
    private SamplePageNavigation samplePageNavigation;
    private SamplePage samplePage;
    private Node mosaic;
    private Node heightTest;
    private Node combinationsTest;
    private Node customerTest;
    private Stage mainStage;
    private Color backgroundColor;
    private Color baseColor;
    private Color accentColor;
    private String fontName = null;
    private int fontSize = 13;
    private String styleSheetContent = "";
    private String styleSheetBase = "";
    private ToggleButton modenaButton,retinaButton,rtlButton,embeddedPerformanceButton;
    private TabPane contentTabs;
    private ComboBox<String> styleComboBox;

    private JMetro jMetro = new JMetro();

    private Scene scene;

    private boolean test = false;
    private boolean embeddedPerformanceMode = false;

    private final EventHandler<ActionEvent> rebuild = event -> Platform.runLater(() -> {
        updateTheme();
        rebuildUI(modenaButton.isSelected(), retinaButton.isSelected(),
                contentTabs.getSelectionModel().getSelectedIndex(),
                samplePageNavigation.getCurrentSection());
    });

    private static ThemeTester instance;

    public static ThemeTester getInstance() {
        return instance;
    }

    public Map<String, Node> getContent() {
        return samplePage.getContent();
    }

    public void setRetinaMode(boolean retinaMode) {
        if (retinaMode) {
            contentTabs.getTransforms().setAll(new Scale(2,2));
        } else {
            contentTabs.getTransforms().setAll(new Scale(1,1));
        }
        contentTabs.requestLayout();
    }

    public void restart() {
        mainStage.close();
        root = null;
        accentColor = null;
        baseColor = null;
        backgroundColor = null;
        fontName = null;
        fontSize = 13;
        try {
            start(new Stage());
        } catch (Exception ex) {
            throw new RuntimeException("Failed to start another Modena window", ex);
        }
    }

    @Override public void start(Stage stage) {
        if (getParameters().getRaw().contains(TEST)) {
            test = true;
        }
        mainStage = stage;
        // build Menu Bar
        outerRoot = new BorderPane();
        outerRoot.getStyleClass().add("background");
        outerRoot.setTop(buildMenuBar());
        outerRoot.setCenter(root);
        // show UI
        double sceneWidth = 1600;
        double sceneHeight = 768;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (screenSize.getWidth() < sceneWidth) {
            sceneWidth = screenSize.getWidth();
        }
        if (screenSize.getHeight() < sceneHeight) {
            sceneHeight = screenSize.getHeight();
        }
        scene = new Scene(outerRoot, sceneWidth, sceneHeight);

        jMetro.setScene(scene);

        // set user agent stylesheet
        updateTheme(Theme.JMETRO, Style.LIGHT);
        // build UI
        rebuildUI(false,false,0, null);


        scene.getStylesheets().add(TEST_APP_CSS_URL);

        stage.setScene(scene);

        stage.setTitle("Theme Tester");
//        stage.setIconified(test); // TODO: Blocked by http://javafx-jira.kenai.com/browse/JMY-203
        stage.show(); // see SamplePage.java:110 comment on how test fails without having stage shown
        instance = this;
    }

    private MenuBar buildMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);
        Menu fontSizeMenu = new Menu("Font");
        ToggleGroup tg = new ToggleGroup();
        fontSizeMenu.getItems().addAll(
            buildFontRadioMenuItem("System Default", null, 0, tg),
            buildFontRadioMenuItem("Mac (13px)", "Lucida Grande", 13, tg),
            buildFontRadioMenuItem("Windows 100% (12px)", "Segoe UI", 12, tg),
            buildFontRadioMenuItem("Windows 125% (15px)", "Segoe UI", 15, tg),
            buildFontRadioMenuItem("Windows 150% (18px)", "Segoe UI", 18, tg),
            buildFontRadioMenuItem("Linux (13px)", "Lucida Sans", 13, tg),
            buildFontRadioMenuItem("Embedded Touch (22px)", "Arial", 22, tg),
            buildFontRadioMenuItem("Embedded Small (9px)", "Arial", 9, tg)
        );
        menuBar.getMenus().add(fontSizeMenu);
        return menuBar;
    }

    private void updateTheme() {
        updateTheme(modenaButton.isSelected() ? Theme.MODENA : Theme.JMETRO, styleComboBox.getValue().equals("Light") ? Style.LIGHT : Style.DARK);
    }

    private void updateTheme(Theme theme, Style style) {
        final SamplePage.Section scrolledSection = samplePageNavigation == null ? null : samplePageNavigation.getCurrentSection();

//        styleSheetContent = loadUrl(MODENA_STYLESHEET_URL);
        scene.setUserAgentStylesheet(SKINBASE + "modena/modena.css");


//        if ((theme == Theme.MODENA) && embeddedPerformanceMode) {
//            styleSheetContent += loadUrl(MODENA_EMBEDDED_STYLESHEET_URL);
//        }

//        styleSheetBase = theme == Theme.MODENA ? MODENA_STYLESHEET_BASE : null;

        styleSheetContent += "\n.root {\n";
        System.out.println("baseColor = "+baseColor);
        System.out.println("accentColor = " + accentColor);
        System.out.println("backgroundColor = " + backgroundColor);
        if (baseColor != null && baseColor != Color.TRANSPARENT) {
            styleSheetContent += "    -fx-base:" + colorToRGBA(baseColor) + ";\n";
        }
        if (backgroundColor != null && backgroundColor != Color.TRANSPARENT) {
            styleSheetContent += "    -fx-background:" + colorToRGBA(backgroundColor) + ";\n";
        }
        if (accentColor != null && accentColor != Color.TRANSPARENT) {
            styleSheetContent += "    -fx-accent:" + colorToRGBA(accentColor) + ";\n";
        }
        if (fontName != null) {
            styleSheetContent += "    -fx-font:"+fontSize+"px \""+fontName+"\";\n";
        }
        styleSheetContent += "}\n";

        if (scene != null) {
            scene.getStylesheets().clear();
        }

        // load theme
        setUserAgentStylesheet("internal:stylesheet"+Math.random()+".css");

        if (theme == Theme.JMETRO) {
            new JMetro(scene, style);
        }

        if (root != null) root.requestLayout();

        // restore scrolled section
        Platform.runLater(() -> samplePageNavigation.setCurrentSection(scrolledSection));
    }

    private void rebuildUI(boolean modena, boolean retina, int selectedTab, final SamplePage.Section scrolledSection) {
        try {
            if (root == null) {
                root = new BorderPane();
                outerRoot.setCenter(root);
            } else {
                // clear out old UI
                root.setTop(null);
                root.setCenter(null);
            }
            // Create sample page and nav
            samplePageNavigation = new SamplePageNavigation();
            samplePage = samplePageNavigation.getSamplePage();
            // Create Content Area
            contentTabs = new TabPane();
            Tab tab1 = new Tab("All Controls");
            tab1.setContent(samplePageNavigation);
            Tab tab2 = new Tab("UI Mosaic");
            tab2.setContent(new ScrollPane(mosaic = (Node)FXMLLoader.load(ThemeTester.class.getResource("ui-mosaic.fxml"))));

            Tab tab3 = new Tab("Alignment Test");
            tab3.setContent(new ScrollPane(heightTest =
                    (Node)FXMLLoader.load(ThemeTester.class.getResource("AlignmentTest.fxml"))));

//            Tab tab4 = new Tab("Simple Windows");
//            tab4.setContent(new ScrollPane(simpleWindows = new SimpleWindowPage()));

            Tab tab5 = new Tab("Combinations");
            tab5.setContent(new ScrollPane(combinationsTest =
                    (Node)FXMLLoader.load(ThemeTester.class.getResource("CombinationTest.fxml"))));

            // Customer example from bug report http://javafx-jira.kenai.com/browse/DTL-5561
            Tab tab6 = new Tab("Customer Example");
            tab6.setContent(new ScrollPane(customerTest =
                    (Node)FXMLLoader.load(ThemeTester.class.getResource("ScottSelvia.fxml"))));

            contentTabs.getTabs().addAll(tab1, tab2, tab3/*, tab4*/, tab5, tab6);
            contentTabs.getSelectionModel().select(selectedTab);
            samplePage.setMouseTransparent(test);
            // height test set selection for
            Platform.runLater(() -> {
                for (Node n: heightTest.lookupAll(".choice-box")) {
                    ((ChoiceBox)n).getSelectionModel().selectFirst();
                }
                for (Node n: heightTest.lookupAll(".combo-box")) {
                    ((ComboBox)n).getSelectionModel().selectFirst();
                }
            });
            // Create Toolbar
            retinaButton = new ToggleButton("@2x");
            retinaButton.setSelected(retina);
            retinaButton.setOnAction(event -> {
                ToggleButton btn = (ToggleButton)event.getSource();
                setRetinaMode(btn.isSelected());
            });
            ToggleGroup themesToggleGroup = new ToggleGroup();
            modenaButton = new ToggleButton("Modena");
            modenaButton.setToggleGroup(themesToggleGroup);
            modenaButton.setSelected(modena);
            modenaButton.setOnAction(rebuild);
            modenaButton.getStyleClass().add("left-pill:");
            ToggleButton jMetroButton = new ToggleButton("JMetro");
            jMetroButton.setToggleGroup(themesToggleGroup);
            jMetroButton.setSelected(!modena);
            jMetroButton.setOnAction(rebuild);
            jMetroButton.getStyleClass().add("right-pill");
            Button reloadButton = new Button("", new ImageView(new Image(ThemeTester.class.getResource("reload_12x14.png").toString())));
            reloadButton.setOnAction(rebuild);

            rtlButton = new ToggleButton("RTL");
            rtlButton.setOnAction(event -> root.setNodeOrientation(rtlButton.isSelected() ?
                    NodeOrientation.RIGHT_TO_LEFT : NodeOrientation.LEFT_TO_RIGHT));

        /*  embeddedPerformanceButton = new ToggleButton("EP");
            embeddedPerformanceButton.setSelected(embeddedPerformanceMode);
            embeddedPerformanceButton.setTooltip(new Tooltip("Apply Embedded Performance extra stylesheet"));
            embeddedPerformanceButton.setOnAction(event -> {
                embeddedPerformanceMode = embeddedPerformanceButton.isSelected();
                rebuild.handle(event);
            }); */

            Button saveButton = new Button("Save...");
            saveButton.setOnAction(saveBtnHandler);

            Button restartButton = new Button("Restart");
            restartButton.setOnAction(event -> restart());

            ToolBar toolBar = new ToolBar(new HBox(modenaButton, jMetroButton), reloadButton, rtlButton,
                    /* embeddedPerformanceButton, new Separator(), */ retinaButton,
                    new Label("Base:"),
                    createBaseColorPicker(),
                    new Label("Background:"),
                    createBackgroundColorPicker(),
                    new Label("Accent:"),
                    createAccentColorPicker(),
                    new Label ("Style:"),
                    styleComboBox = createStyleComboBox(),
                    new Separator(), saveButton, restartButton
                    );
            toolBar.setId("TestAppToolbar");
            // Create content group used for scaleing @2x
            final Pane contentGroup = new Pane() {
                @Override protected void layoutChildren() {
                    double scale = contentTabs.getTransforms().isEmpty() ? 1 : ((Scale)contentTabs.getTransforms().get(0)).getX();
                    contentTabs.resizeRelocate(0,0,getWidth()/scale, getHeight()/scale);
                }
            };
            contentGroup.getChildren().add(contentTabs);
            // populate root
            root.setTop(toolBar);
            root.setCenter(contentGroup);

            samplePage.getStyleClass().add("needs-background");
            mosaic.getStyleClass().add("needs-background");
            heightTest.getStyleClass().add("needs-background");
            combinationsTest.getStyleClass().add("needs-background");
            customerTest.getStyleClass().add("needs-background");
            // apply retina scale
            if (retina) {
                contentTabs.getTransforms().setAll(new Scale(2,2));
            }
            root.applyCss();
            // update state
            Platform.runLater(() -> {
                // move focus out of the way
                modenaButton.requestFocus();
                samplePageNavigation.setCurrentSection(scrolledSection);
            });
        } catch (IOException ex) {
            Logger.getLogger(ThemeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public RadioMenuItem buildFontRadioMenuItem(String name, final String in_fontName, final int in_fontSize, ToggleGroup tg) {
        RadioMenuItem rmItem = new RadioMenuItem(name);
        rmItem.setOnAction(event -> setFont(in_fontName, in_fontSize));
        rmItem.setStyle("-fx-font: " + in_fontSize + "px \"" + in_fontName + "\";");
        rmItem.setToggleGroup(tg);
        return rmItem;
    }

    public void setFont(String in_fontName, int in_fontSize) {
        System.out.println("===================================================================");
        System.out.println("==   SETTING FONT TO "+in_fontName+" "+in_fontSize+"px");
        System.out.println("===================================================================");
        fontName = in_fontName;
        fontSize = in_fontSize;
        updateTheme();
    }

    private ComboBox<String> createStyleComboBox() {
        ComboBox<String> styleComboBox = new ComboBox<>();
        styleComboBox.getItems().addAll("Light", "Dark");
        styleComboBox.setValue("Light");
        styleComboBox.valueProperty().addListener(((observable, oldValue, newValue) -> updateTheme(Theme.JMETRO, styleComboBox.getValue().equals("Light") ? Style.LIGHT : Style.DARK)));
        return styleComboBox;
    }


    private ColorPicker createBaseColorPicker() {
        ColorPicker colorPicker = new ColorPicker(Color.TRANSPARENT);
        colorPicker.getCustomColors().addAll(
                Color.TRANSPARENT,
                Color.web("#f3622d"),
                Color.web("#fba71b"),
                Color.web("#57b757"),
                Color.web("#41a9c9"),
                Color.web("#888"),
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.CYAN,
                Color.BLUE,
                Color.PURPLE,
                Color.MAGENTA,
                Color.BLACK
        );
        colorPicker.valueProperty().addListener((observable, oldValue, c) -> setBaseColor(c));
        return colorPicker;
    }

    public void setBaseColor(Color c) {
        if (c == null) {
            baseColor = null;
        } else {
            baseColor = c;
        }
        updateTheme();
    }

    private ColorPicker createBackgroundColorPicker() {
        ColorPicker colorPicker = new ColorPicker(Color.TRANSPARENT);
        colorPicker.getCustomColors().addAll(
                Color.TRANSPARENT,
                Color.web("#f3622d"),
                Color.web("#fba71b"),
                Color.web("#57b757"),
                Color.web("#41a9c9"),
                Color.web("#888"),
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.CYAN,
                Color.BLUE,
                Color.PURPLE,
                Color.MAGENTA,
                Color.BLACK
        );
        colorPicker.valueProperty().addListener((observable, oldValue, c) -> {
            if (c == null) {
                backgroundColor = null;
            } else {
                backgroundColor = c;
            }
            updateTheme();
        });
        return colorPicker;
    }

    private ColorPicker createAccentColorPicker() {
        ColorPicker colorPicker = new ColorPicker(Color.web("#0096C9"));
        colorPicker.getCustomColors().addAll(
                Color.TRANSPARENT,
                Color.web("#0096C9"),
                Color.web("#4fb6d6"),
                Color.web("#f3622d"),
                Color.web("#fba71b"),
                Color.web("#57b757"),
                Color.web("#41a9c9"),
                Color.web("#888"),
                Color.RED,
                Color.ORANGE,
                Color.YELLOW,
                Color.GREEN,
                Color.CYAN,
                Color.BLUE,
                Color.PURPLE,
                Color.MAGENTA,
                Color.BLACK
        );
        colorPicker.valueProperty().addListener((observable, oldValue, c) -> setAccentColor(c));
        return colorPicker;
    }

    public void setAccentColor(Color c) {
        if (c == null) {
            accentColor = null;
        } else {
            accentColor = c;
        }
        updateTheme();
    }

    private EventHandler<ActionEvent> saveBtnHandler = event -> {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG", "*.png"));
        File file = fc.showSaveDialog(mainStage);
        if (file != null) {
            try {
                samplePage.getStyleClass().add("root");
                int width = (int)(samplePage.getLayoutBounds().getWidth()+0.5d);
                int height = (int)(samplePage.getLayoutBounds().getHeight()+0.5d);
                BufferedImage imgBuffer = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2 = imgBuffer.createGraphics();
                for (int y=0; y<height; y+=2048) {
                    SnapshotParameters snapshotParameters = new SnapshotParameters();
                    int remainingHeight = Math.min(2048, height - y);
                    snapshotParameters.setViewport(new Rectangle2D(0,y,width,remainingHeight));
                    WritableImage img = samplePage.snapshot(snapshotParameters, null);
                    g2.drawImage(SwingFXUtils.fromFXImage(img,null),0,y,null);
                }
                g2.dispose();
                ImageIO.write(imgBuffer, "PNG", file);
                System.out.println("Written image: "+file.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(ThemeTester.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public static void main(String[] args) {
        launch(args);
    }

    /** Utility method to load a URL into a string */
    private static String loadUrl(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                sb.append('\n');
            }
        } catch (IOException ex) {
            Logger.getLogger(ThemeTester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sb.toString();
    }

    // =========================================================================
    // URL Handler to create magic "internal:stylesheet.css" url for our css string buffer
    {
        URL.setURLStreamHandlerFactory(new StringURLStreamHandlerFactory());
    }

    private String colorToRGBA(Color color) {
        return String.format((Locale) null, "rgba(%d, %d, %d, %f)",
            (int) Math.round(color.getRed() * 255),
            (int) Math.round(color.getGreen() * 255),
            (int) Math.round(color.getBlue() * 255),
            color.getOpacity());
    }

    /**
     * Simple URLConnection that always returns the content of the cssBuffer
     */
    private class StringURLConnection extends URLConnection {
        public StringURLConnection(URL url){
            super(url);
        }

        @Override public void connect() {}

        @Override public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(styleSheetContent.getBytes("UTF-8"));
        }
    }

    private class StringURLStreamHandlerFactory implements URLStreamHandlerFactory {
        URLStreamHandler streamHandler = new URLStreamHandler(){
            @Override protected URLConnection openConnection(URL url) throws IOException {
                if (url.toString().toLowerCase().endsWith(".css")) {
                    return new StringURLConnection(url);
                } else {
                    return new URL(styleSheetBase+url.getFile()).openConnection();
                }
            }
        };
        @Override public URLStreamHandler createURLStreamHandler(String protocol) {
            if ("internal".equals(protocol)) {
                return streamHandler;
            }
            return null;
        }
    }

    private enum Theme {
        MODENA("Modena"),
        JMETRO("JMetro");

        private String themeName;

        Theme(String themeName) { this.themeName = themeName; }

        public String getThemeName() { return themeName; }
    }
}
