package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Arrays;
import java.util.List;

import static jfxtras.styles.jmetro.JMetroStyleClass.TABLE_GRID_LINES;
import static jfxtras.styles.jmetro.JMetroStyleClass.addIfNotPresent;

public class TreeTableViewSample extends Application {

    private static final Style STARTING_STYLE = Style.DARK;

    List<Employee> employees = Arrays.asList(
            new Employee("Ethan Williams", "ethan.williams@example.com", "25", "Manager", "San Francisco"),
            new Employee("Emma Jones", "emma.jones@example.com", "30", "Sales Rep", "San Francisco"),
            new Employee("Michael Brown", "michael.brown@example.com", "50", "Consultant", "New York"),
            new Employee("Anna Black", "anna.black@example.com", "45", "Consultant", "Miami"),
            new Employee("Rodger York", "roger.york@example.com", "56", "Manager", "Washington"),
            new Employee("Susan Collins", "susan.collins@example.com", "32", "Director", "Florida"));

    List<Employee> itEmployees = Arrays.asList(
            new Employee("Jonathan James", "ethan.williams@example.com", "25", "Manager", "California"),
            new Employee("Pedro Duque Vieira", "emma.jones@example.com", "30", "Sales Rep", "California"),
            new Employee("Pavel Erokhin", "michael.brown@example.com", "50", "Consultant", "Florida"),
            new Employee("James Brown", "anna.black@example.com", "45", "Consultant", "Boston"),
            new Employee("Linda Gates", "roger.york@example.com", "56", "Manager", "Washington"));

    final TreeItem<Employee> root =
            new TreeItem<>(new Employee("Employees", "", "", "", ""));
    final TreeItem<Employee> salesRoot =
            new TreeItem<>(new Employee("Sales Department", "", "", "", ""));
    final TreeItem<Employee> itRoot =
            new TreeItem<>(new Employee("IT Department", "", "", "", ""));

    public static void main(String[] args) {
        Application.launch(TreeTableViewSample.class, args);
    }

    @Override
    public void start(Stage stage) {
        JMetro jMetro = new JMetro(STARTING_STYLE);

        System.setProperty("prism.lcdtext", "false");

        root.getChildren().add(salesRoot);
        root.getChildren().add(itRoot);

        employees.forEach((employee) -> salesRoot.getChildren().add(new TreeItem<>(employee)));
        itEmployees.forEach((employee) -> itRoot.getChildren().add(new TreeItem<>(employee)));

        stage.setTitle("Tree Table View Sample");
        final Scene scene = new Scene(new BorderPane());
        BorderPane sceneRoot = (BorderPane) scene.getRoot();


        TreeTableColumn<Employee, String> empColumn = new TreeTableColumn<>("Employee");
        empColumn.setPrefWidth(170);
        empColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getName())
        );

        TreeTableColumn<Employee, String> emailColumn = new TreeTableColumn<>("Email");
        emailColumn.setPrefWidth(220);
        emailColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getEmail())
        );

        TreeTableColumn<Employee, String> ageColumn = new TreeTableColumn<>("Age");
        ageColumn.setPrefWidth(60);
        ageColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getAge())
        );

        TreeTableColumn<Employee, String> positionColumn = new TreeTableColumn<>("Position");
        positionColumn.setPrefWidth(130);
        positionColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getPosition())
        );

        TreeTableColumn<Employee, String> locationColumn = new TreeTableColumn<>("Location");
        locationColumn.setPrefWidth(130);
        locationColumn.setCellValueFactory(
                (TreeTableColumn.CellDataFeatures<Employee, String> param) ->
                        new ReadOnlyStringWrapper(param.getValue().getValue().getLocation())
        );

        TreeTableView<Employee> treeTableView = new TreeTableView<>(root);
        treeTableView.getColumns().setAll(empColumn, emailColumn, ageColumn, positionColumn, locationColumn);

        salesRoot.setExpanded(true);
        treeTableView.setShowRoot(false);

        sceneRoot.setCenter(treeTableView);

        // Customization controls
        HBox controlsHBox = new HBox();
        controlsHBox.setSpacing(10);

        CheckBox cellSelectionCheckBox = new CheckBox("Cell Selection");
        cellSelectionCheckBox.setOnAction(event -> treeTableView.getSelectionModel().setCellSelectionEnabled(cellSelectionCheckBox.isSelected()));
        cellSelectionCheckBox.setSelected(treeTableView.getSelectionModel().isCellSelectionEnabled());

        CheckBox tableButtonCheckBox = new CheckBox("Table Menu Button");
        tableButtonCheckBox.setOnAction(event -> treeTableView.setTableMenuButtonVisible(tableButtonCheckBox.isSelected()));
        tableButtonCheckBox.setSelected(treeTableView.isTableMenuButtonVisible());

        CheckBox alternatingRowColors = new CheckBox("Alternating Row Colors");
        alternatingRowColors.setOnAction(event -> {
            String alternatingRowColorsStyleClass = "alternating-row-colors";
            boolean isSelected = alternatingRowColors.isSelected();
            if (isSelected) {
                if (!treeTableView.getStyleClass().contains(alternatingRowColorsStyleClass)) {
                    treeTableView.getStyleClass().add(alternatingRowColorsStyleClass);
                }
            } else {
                treeTableView.getStyleClass().remove(alternatingRowColorsStyleClass);
            }
        });

        CheckBox columnGridLines = new CheckBox("Grid Lines");
        columnGridLines.setOnAction(event -> {
            boolean isSelected = columnGridLines.isSelected();
            if (isSelected) {
                addIfNotPresent(treeTableView.getStyleClass(), TABLE_GRID_LINES);
            } else {
                treeTableView.getStyleClass().remove(TABLE_GRID_LINES);
            }
        });

        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(STARTING_STYLE);
        jmetroStyleComboBox.valueProperty().addListener(observable -> jMetro.setStyle(jmetroStyleComboBox.getValue()));

        controlsHBox.setAlignment(Pos.CENTER_LEFT);
        controlsHBox.getChildren().addAll(jmetroStyleComboBox, cellSelectionCheckBox, tableButtonCheckBox, alternatingRowColors, columnGridLines);
        sceneRoot.setBottom(controlsHBox);

        controlsHBox.getStyleClass().add("background");

        stage.setScene(scene);

        jMetro.setScene(scene);

        stage.show();
    }

    public class Employee {

        private SimpleStringProperty name;
        private SimpleStringProperty email;
        private SimpleStringProperty age;
        private SimpleStringProperty position;
        private SimpleStringProperty location;

        public SimpleStringProperty nameProperty() {
            if (name == null) {
                name = new SimpleStringProperty(this, "name");
            }
            return name;
        }
        public SimpleStringProperty emailProperty() {
            if (email == null) {
                email = new SimpleStringProperty(this, "email");
            }
            return email;
        }

        public SimpleStringProperty ageProperty() {
            return age;
        }

        public SimpleStringProperty positionProperty() {
            return position;
        }

        public SimpleStringProperty locationProperty() {
            return location;
        }

        private Employee(String name, String email, String age, String position, String location) {
            this.name = new SimpleStringProperty(name);
            this.email = new SimpleStringProperty(email);
            this.age = new SimpleStringProperty(age);
            this.position = new SimpleStringProperty(position);
            this.location = new SimpleStringProperty(location);
        }

        public String getName() { return name.get(); }
        public void setName(String fName) { name.set(fName); }

        public String getEmail() { return email.get(); }
        public void setEmail(String fName) { email.set(fName); }

        public String getAge() { return age.get(); }
        public void setAge(String age) { this.age.set(age); }

        public String getPosition() { return position.get(); }
        public void setPosition(String position) { this.position.set(position); }

        public String getLocation() { return location.get(); }
        public void setLocation(String location) { this.location.set(location); }
    }
}

