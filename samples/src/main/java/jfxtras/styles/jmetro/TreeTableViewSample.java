package jfxtras.styles.jmetro;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro8.JMetro;
import jfxtras.styles.jmetro8.Style;

import java.util.Arrays;
import java.util.List;

public class TreeTableViewSample extends Application {

    private static final Style STYLE = Style.DARK;

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
        HBox hBox = new HBox();
        CheckBox cellSelection = new CheckBox("Cell Selection");
        cellSelection.setOnAction(event -> {
            treeTableView.getSelectionModel().setCellSelectionEnabled(cellSelection.isSelected());
        });
        hBox.getChildren().add(cellSelection);
        sceneRoot.setBottom(hBox);

        stage.setScene(scene);

        new JMetro(scene, STYLE);

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

