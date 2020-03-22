package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.MDL2IconCollection;
import jfxtras.styles.jmetro.MDL2IconFont;
import jfxtras.styles.jmetro.Style;

import static jfxtras.styles.jmetro.JMetroStyleClass.*;

public class MDL2IconFontSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Style startingStyle = Style.LIGHT;
        JMetro jMetro = new JMetro(startingStyle);

        System.setProperty("prism.lcdtext", "false");

        BorderPane borderPane = new BorderPane();
        Scene scene = new Scene(borderPane);
        stage.setTitle("MDL2 Icon Font Sample");
        stage.setWidth(650);
        stage.setHeight(600);

        // Header
        final Label header = new Label("MDL2 Icons");
        header.setPadding(new Insets(0, 0, 0, 5));
        header.getStyleClass().add("header");

        // Content
        VBox iconsContainer = new VBox();
        iconsContainer.setSpacing(50);
        iconsContainer.setPadding(new Insets(60, 0, 0, 10));

        // -- icons configured through code
        HBox iconsParameterizedThroughCode = new HBox();
        iconsParameterizedThroughCode.setSpacing(15);
        VBox iconsParameterizedThroughCodeContainer = createSection("Icons configured through code", iconsParameterizedThroughCode);

        int pixelSize = 30;
        MDL2IconFont iconFont1 = new MDL2IconFont("\uE701");
        iconFont1.setSize(pixelSize);
        MDL2IconFont iconFont2 = new MDL2IconFont("\uE703");
        iconFont2.setSize(pixelSize);
        MDL2IconFont iconFont3 = new MDL2IconFont("\uE707");
        iconFont3.setSize(pixelSize);
        MDL2IconFont iconFont4 = new MDL2IconFont("\uE709");
        iconFont4.setSize(pixelSize);

        iconsParameterizedThroughCode.getChildren().addAll(iconFont1, iconFont2, iconFont3, iconFont4);

        // -- icons configured through CSS
        HBox iconsParameterizedThroughCSS = new HBox();
        iconsParameterizedThroughCSS.getStyleClass().add("CSS");
        iconsParameterizedThroughCSS.setSpacing(15);

        VBox iconsParameterizedThroughCSSContainer = createSection("Icons configured through CSS", iconsParameterizedThroughCSS);

        MDL2IconFont iconFont5 = new MDL2IconFont("\uE701");
        iconFont1.setSize(pixelSize);
        MDL2IconFont iconFont6 = new MDL2IconFont("\uE703");
        iconFont2.setSize(pixelSize);
        MDL2IconFont iconFont7 = new MDL2IconFont("\uE707");
        iconFont3.setSize(pixelSize);
        MDL2IconFont iconFont8 = new MDL2IconFont("\uE709");

        iconsParameterizedThroughCSS.getChildren().addAll(iconFont5, iconFont6, iconFont7, iconFont8);

        // -- icons stacked together
        HBox iconsCollections = createIconsContainer();
        VBox iconsCollectionsContainer = createSection("Icons combined", iconsCollections);

        MDL2IconFont starFill = new MDL2IconFont("\uE735");
        starFill.getStyleClass().add("star-fill");
        MDL2IconFont star = new MDL2IconFont("\uE734");
        MDL2IconCollection collection1 = new MDL2IconCollection(starFill, star);
        collection1.getStyleClass().add("star-icons");

        MDL2IconFont statusWarning = new MDL2IconFont("\uEC1E");
        MDL2IconFont statusVPN = new MDL2IconFont("\uE889");
        String mobWifi4Code = "\uEC3F";
        MDL2IconFont mobWifi4 = new MDL2IconFont(mobWifi4Code);
        MDL2IconFont mobWifi4_2 = new MDL2IconFont(mobWifi4Code);
        MDL2IconCollection collection2 = new MDL2IconCollection(mobWifi4, statusWarning);
        collection2.setStyle("-fx-font-size: 30px;");
        MDL2IconCollection collection3 = new MDL2IconCollection(mobWifi4_2, statusVPN);
        collection3.setStyle("-fx-font-size: 30px;");

        iconsCollections.getChildren().addAll(collection1, collection2, collection3);
        
        // Footer
        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(startingStyle);
        jmetroStyleComboBox.valueProperty().addListener(observable -> jMetro.setStyle(jmetroStyleComboBox.getValue()));

        BorderPane footer = new BorderPane();
        footer.setRight(jmetroStyleComboBox);

        // Etc
        iconsContainer.getChildren().addAll(iconsParameterizedThroughCodeContainer, iconsParameterizedThroughCSSContainer, iconsCollectionsContainer);

        borderPane.setPadding(new Insets(10, 10, 10, 10));

        addIfNotPresent(borderPane.getStyleClass(), BACKGROUND);

        borderPane.setTop(header);
        borderPane.setCenter(iconsContainer);
        borderPane.setBottom(footer);

        jMetro.setScene(scene);
        scene.getStylesheets().add(MDL2IconFontSample.class.getResource("mdl2-icon-font-sample.css").toExternalForm());

        stage.setScene(scene);
        stage.show();
    }

    private VBox createSection(String title, HBox content) {
        Label label = new Label(title);

        VBox container = new VBox();
        container.setSpacing(15);
        container.getChildren().addAll(label, content);

        return container;
    }

    private HBox createIconsContainer() {
        HBox hBox = new HBox();
        hBox.setSpacing(15);
        return hBox;
    }
}

