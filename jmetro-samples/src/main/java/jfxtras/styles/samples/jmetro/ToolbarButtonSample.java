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
public class ToolbarButtonSample extends Application {
    private static final Style STYLE = Style.LIGHT;
    private static final boolean FOCUS_TRAVERSAL = false;

    private List<ToolbarButtonSample.Employee> employees = Arrays.asList(
            new ToolbarButtonSample.Employee("Jacob Smith", "Accounts Department"),
            new ToolbarButtonSample.Employee("Isabella Johnson", "Accounts Department"),
            new ToolbarButtonSample.Employee("Ethan Williams", "Sales Department"),
            new ToolbarButtonSample.Employee("Emma Jones", "Sales Department"),
            new ToolbarButtonSample.Employee("Michael Brown", "Sales Department"),
            new ToolbarButtonSample.Employee("Anna Black", "Sales Department"),
            new ToolbarButtonSample.Employee("Rodger York", "Sales Department"),
            new ToolbarButtonSample.Employee("Susan Collins", "Sales Department"),
            new ToolbarButtonSample.Employee("Mike Graham", "IT Support"),
            new ToolbarButtonSample.Employee("Judy Mayer", "IT Support"),
            new ToolbarButtonSample.Employee("Gregory Smith", "IT Support"));
    private TreeItem<String> rootNode;

    public static void main(String[] args) {
        launch(args);
    }

    public ToolbarButtonSample() {
        this.rootNode = new TreeItem<>("MyCompany Human Resources");
    }

    @Override
    public void start(Stage stage) {
        rootNode.setExpanded(true);
        for (ToolbarButtonSample.Employee employee : employees) {
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
        VBox box = new VBox();
        box.setPadding(new Insets(30, 0, 0, 0));

        final Scene scene = new Scene(box, 400, 300);
        scene.setFill(Color.LIGHTGRAY);

        TreeView<String> treeView = new TreeView<>(rootNode);

        ToolBar toolBar = new ToolBar();
        Button copy = new Button();
        copy.setGraphic(new ImageView(ToolbarButtonSample.class.getResource("copy-16.png").toExternalForm()));
        copy.setFocusTraversable(FOCUS_TRAVERSAL);
        Button delete = new Button();
        delete.setGraphic(new ImageView(ToolbarButtonSample.class.getResource("trash-16.png").toExternalForm()));
        delete.setFocusTraversable(FOCUS_TRAVERSAL);
        ToggleButton selectAll = new ToggleButton();
        selectAll.setGraphic(new ImageView(ToolbarButtonSample.class.getResource("tick-box-16.png").toExternalForm()));
        selectAll.setFocusTraversable(FOCUS_TRAVERSAL);
        ToggleButton unselectAll = new ToggleButton();
        unselectAll.setGraphic(new ImageView(ToolbarButtonSample.class.getResource("unchecked-checkbox-16.png").toExternalForm()));
        unselectAll.setFocusTraversable(FOCUS_TRAVERSAL);
        toolBar.getItems().addAll(copy, delete, new Separator(), selectAll, unselectAll);

        new JMetro(scene, STYLE);

        box.getChildren().addAll(toolBar, treeView);

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
