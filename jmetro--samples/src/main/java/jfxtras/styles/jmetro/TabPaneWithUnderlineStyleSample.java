package jfxtras.styles.jmetro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static jfxtras.styles.jmetro.JMetroStyleClass.*;

public class TabPaneWithUnderlineStyleSample extends Application {

    private static final Style STYLE = Style.DARK;

    public static void main(String[] args) {
        launch(args);
    }

    public TabPaneWithUnderlineStyleSample() {
    }

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();

        addIfNotPresent(tabPane.getStyleClass(), UNDERLINE_TAB_PANE);

        Tab fileTab = new Tab();
        fileTab.setClosable(false);
        fileTab.setText("File");

        Tab homeTab = new Tab();
        homeTab.setClosable(false);
        homeTab.setText("Home");

        Tab insertTab = new Tab();
        insertTab.setClosable(false);
        insertTab.setText("Insert");

        Tab tableTab = new Tab();
        tableTab.setClosable(false);
        tableTab.setText("Table");

        Tab optionsTab = new Tab();
        optionsTab.setClosable(false);
        optionsTab.setText("Options");

        tabPane.getTabs().addAll(fileTab, homeTab, insertTab, tableTab, optionsTab);

        BorderPane root = new BorderPane(tabPane);

        addIfNotPresent(root.getStyleClass(), BACKGROUND);

        Scene scene = new Scene(root, 500, 200);

        new JMetro(scene, STYLE);

//        ScenicView.show(scene);


        stage.setTitle("TabPane With Underline Sample");
        stage.setScene(scene);
        stage.show();
    }
}
