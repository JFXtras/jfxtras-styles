package jfxtras.styles.samples.jmetro;

import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

public class TreeViewSample extends Application {
    private static final Style STYLE = Style.DARK;

    private List<Employee> employees = Arrays.asList(
            new Employee("Jacob Smith", "Accounts Department"),
            new Employee("Isabella Johnson", "Accounts Department"),
            new Employee("Ethan Williams", "Sales Department"),
            new Employee("Emma Jones", "Sales Department"),
            new Employee("Michael Brown", "Sales Department"),
            new Employee("Anna Black", "Sales Department"),
            new Employee("Rodger York", "Sales Department"),
            new Employee("Susan Collins", "Sales Department"),
            new Employee("Mike Graham", "IT Support"),
            new Employee("Judy Mayer", "IT Support"),
            new Employee("Gregory Smith", "IT Support"));
    private TreeItem<String> rootNode;

    public static void main(String[] args) {
        launch(args);
    }

    public TreeViewSample() {
        this.rootNode = new TreeItem<>("MyCompany Human Resources");
    }

    @Override
    public void start(Stage stage) {
        Style startingStyle = Style.LIGHT;
        JMetro jMetro = new JMetro(startingStyle);

        rootNode.setExpanded(true);
        for (Employee employee : employees) {
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
        box.getStyleClass().add("background");

        final Scene scene = new Scene(box, 400, 300);
        scene.setFill(Color.LIGHTGRAY);


        TreeView<String> treeView = new TreeView<>(rootNode);

        jMetro.setScene(scene);

        HBox controlsContainer = new HBox();

        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(startingStyle);
        jmetroStyleComboBox.valueProperty().addListener(observable -> jMetro.setStyle(jmetroStyleComboBox.getValue()));

        CheckBox alternatingRowColors = new CheckBox("Alternating Row Colors");
        alternatingRowColors.setOnAction(event -> {
            String alternatingRowColorsStyleClass = "alternating-row-colors";
            boolean isSelected = alternatingRowColors.isSelected();
            if (isSelected) {
                if (!treeView.getStyleClass().contains(alternatingRowColorsStyleClass)) {
                    treeView.getStyleClass().add(alternatingRowColorsStyleClass);
                }
            } else {
                treeView.getStyleClass().remove(alternatingRowColorsStyleClass);
            }
        });

        controlsContainer.getChildren().addAll(jmetroStyleComboBox, alternatingRowColors);

        box.getChildren().addAll(treeView, controlsContainer);
        stage.setScene(scene);
        stage.show();
    }

    public static class Employee {

        private final SimpleStringProperty name;
        private final SimpleStringProperty department;

        public  Employee(String name, String department) {
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