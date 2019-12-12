package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Arrays;
import java.util.List;

/**
 * This sample uses icons from icons8 - https://icons8.com.
 */
public class ControlsInsideToolbarSample extends Application {
    private static final Style STYLE = Style.LIGHT;
    private static final boolean FOCUS_TRAVERSAL = false;

    private List<ControlsInsideToolbarSample.Employee> employees = Arrays.asList(
            new ControlsInsideToolbarSample.Employee("Jacob Smith", "Accounts Department"),
            new ControlsInsideToolbarSample.Employee("Isabella Johnson", "Accounts Department"),
            new ControlsInsideToolbarSample.Employee("Ethan Williams", "Sales Department"),
            new ControlsInsideToolbarSample.Employee("Emma Jones", "Sales Department"),
            new ControlsInsideToolbarSample.Employee("Michael Brown", "Sales Department"),
            new ControlsInsideToolbarSample.Employee("Anna Black", "Sales Department"),
            new ControlsInsideToolbarSample.Employee("Rodger York", "Sales Department"),
            new ControlsInsideToolbarSample.Employee("Susan Collins", "Sales Department"),
            new ControlsInsideToolbarSample.Employee("Mike Graham", "IT Support"),
            new ControlsInsideToolbarSample.Employee("Judy Mayer", "IT Support"),
            new ControlsInsideToolbarSample.Employee("Gregory Smith", "IT Support"));
    private TreeItem<String> rootNode;

    public static void main(String[] args) {
        launch(args);
    }

    public ControlsInsideToolbarSample() {
        this.rootNode = new TreeItem<>("MyCompany Human Resources");
    }

    @Override
    public void start(Stage stage) {
        rootNode.setExpanded(true);
        for (ControlsInsideToolbarSample.Employee employee : employees) {
            TreeItem<String> empLeaf = new TreeItem<>(employee.getName());
            boolean found = false;
            for (TreeItem<String> depNode : rootNode.getChildren()) {
                if (depNode.getValue().contentEquals(employee.getDepartment())){
                    depNode.getChildren().add(empLeaf);
                    found = true;
                    break;
                }
            }
            if (!found) {
                TreeItem<String> depNode = new TreeItem<>(
                        employee.getDepartment()
                );
                rootNode.getChildren().add(depNode);
                depNode.getChildren().add(empLeaf);
            }
        }

        stage.setTitle("Tree View Sample");

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 0, 0, 0));
        vBox.getStyleClass().add("background");

        final Scene scene = new Scene(vBox, 400, 300);
        scene.setFill(Color.LIGHTGRAY);

        TreeView<String> treeView = new TreeView<>(rootNode);

        ToolBar toolBar = new ToolBar();
        Button copy = new Button();
        copy.setGraphic(new ImageView(ControlsInsideToolbarSample.class.getResource("copy-16.png").toExternalForm()));
        copy.setFocusTraversable(FOCUS_TRAVERSAL);
        Button delete = new Button();
        delete.setGraphic(new ImageView(ControlsInsideToolbarSample.class.getResource("trash-16.png").toExternalForm()));
        delete.setFocusTraversable(FOCUS_TRAVERSAL);
        ToggleButton selectAll = new ToggleButton();
        selectAll.setGraphic(new ImageView(ControlsInsideToolbarSample.class.getResource("tick-box-16.png").toExternalForm()));
        selectAll.setFocusTraversable(FOCUS_TRAVERSAL);
        ToggleButton unselectAll = new ToggleButton();
        unselectAll.setGraphic(new ImageView(ControlsInsideToolbarSample.class.getResource("unchecked-checkbox-16.png").toExternalForm()));
        unselectAll.setFocusTraversable(FOCUS_TRAVERSAL);

        // Menu Button
        MenuItem copyMenuItem = new MenuItem("Copy");
        MenuItem cutMenuItem = new MenuItem("Cut");
        MenuItem pasteMenuItem = new MenuItem("Paste");
        MenuItem duplicateMenuItem = new MenuItem("Duplicate");
        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        MenuItem deleteMenuItem = new MenuItem("Delete");
        MenuButton settingsButton = new MenuButton();
        settingsButton.setGraphic(new ImageView(ControlsInsideToolbarSample.class.getResource("settings-16.png").toExternalForm()));
        settingsButton.getItems().addAll(copyMenuItem, cutMenuItem, pasteMenuItem, duplicateMenuItem, separatorMenuItem, deleteMenuItem);

        // Split Menu Button
        MenuItem runMenuItem = new MenuItem("Run Single");
        MenuItem runAllMenuItem = new MenuItem("Run All");
        MenuItem runAllSelected = new MenuItem("Run All Selected");
        SplitMenuButton splitMenuButton = new SplitMenuButton();
        splitMenuButton.setGraphic(new ImageView(ControlsInsideToolbarSample.class.getResource("play-16.png").toExternalForm()));
        splitMenuButton.getItems().addAll(runMenuItem, runAllMenuItem, runAllSelected);

        toolBar.getItems().addAll(copy, delete, new Separator(), selectAll, unselectAll, settingsButton,splitMenuButton);

        new JMetro(scene, STYLE);

        vBox.getChildren().addAll(toolBar, treeView);

        stage.setScene(scene);
        stage.show();
    }

    public static class Employee {

        private final SimpleStringProperty name;
        private final SimpleStringProperty department;

        private Employee(String name, String department) {
            this.name = new SimpleStringProperty(name);
            this.department = new SimpleStringProperty(department);
        }

        public String getName() {
            return name.get();
        }

        public void setName(String fName) {
            name.set(fName);
        }

        String getDepartment() {
            return department.get();
        }

        public void setDepartment(String fName) {
            department.set(fName);
        }
    }
}
