package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import org.controlsfx.control.SegmentedButton;

import java.util.Arrays;
import java.util.List;

public class SegmentedButtonInsideToolBarSample extends Application {
    private static final Style STYLE = Style.LIGHT;
    private static final boolean FOCUS_TRAVERSAL = false;

    private List<SegmentedButtonInsideToolBarSample.Employee> employees = Arrays.asList(
            new SegmentedButtonInsideToolBarSample.Employee("Jacob Smith", "Accounts Department"),
            new SegmentedButtonInsideToolBarSample.Employee("Isabella Johnson", "Accounts Department"),
            new SegmentedButtonInsideToolBarSample.Employee("Ethan Williams", "Sales Department"),
            new SegmentedButtonInsideToolBarSample.Employee("Emma Jones", "Sales Department"),
            new SegmentedButtonInsideToolBarSample.Employee("Michael Brown", "Sales Department"),
            new SegmentedButtonInsideToolBarSample.Employee("Anna Black", "Sales Department"),
            new SegmentedButtonInsideToolBarSample.Employee("Rodger York", "Sales Department"),
            new SegmentedButtonInsideToolBarSample.Employee("Susan Collins", "Sales Department"),
            new SegmentedButtonInsideToolBarSample.Employee("Mike Graham", "IT Support"),
            new SegmentedButtonInsideToolBarSample.Employee("Judy Mayer", "IT Support"),
            new SegmentedButtonInsideToolBarSample.Employee("Gregory Smith", "IT Support"));
    private TreeItem<String> rootNode;

    public static void main(String[] args) {
        launch(args);
    }

    public SegmentedButtonInsideToolBarSample() {
        this.rootNode = new TreeItem<>("MyCompany Human Resources");
    }

    @Override
    public void start(Stage stage) {
        rootNode.setExpanded(true);
        for (SegmentedButtonInsideToolBarSample.Employee employee : employees) {
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

        stage.setTitle("SegmentedButton Sample");

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(15, 0, 0, 0));
        vBox.getStyleClass().add("background");

        final Scene scene = new Scene(vBox, 400, 300);
        scene.setFill(Color.LIGHTGRAY);

        TreeView<String> treeView = new TreeView<>(rootNode);

        ToolBar toolBar = new ToolBar();
        Button copy = new Button();
        copy.setGraphic(new ImageView(ToolbarWithControlsSample.class.getResource("copy-16.png").toExternalForm()));
        copy.setFocusTraversable(FOCUS_TRAVERSAL);
        Button delete = new Button();
        delete.setGraphic(new ImageView(ToolbarWithControlsSample.class.getResource("trash-16.png").toExternalForm()));
        delete.setFocusTraversable(FOCUS_TRAVERSAL);

        HBox emptyBox = new HBox();
        emptyBox.setPadding(new Insets(0, 10, 0, 0));

        ToggleButton floorButton = new ToggleButton("Floor");
        ToggleButton ceilingButton = new ToggleButton("Ceiling");
        SegmentedButton segmentedButton = new SegmentedButton();
        segmentedButton.getButtons().addAll(floorButton, ceilingButton);
        floorButton.setSelected(true);

        toolBar.getItems().addAll(segmentedButton, emptyBox, copy, delete);

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
