package jfxtras.styles.jmetro8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class TabPaneSample extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    public TabPaneSample() {
    }

    @Override
    public void start(Stage stage) {
        TabPane tabPane = new TabPane();

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

        Scene scene = new Scene(tabPane, 500, 400);


        new JMetro(JMetro.Style.DARK).applyTheme(scene);


        stage.setTitle("TabePane Sample");
        stage.setScene(scene);
        stage.show();
    }
}