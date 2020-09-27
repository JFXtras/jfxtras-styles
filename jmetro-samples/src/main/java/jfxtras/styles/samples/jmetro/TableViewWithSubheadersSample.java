package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import static jfxtras.styles.jmetro.JMetroStyleClass.*;
import static jfxtras.styles.jmetro.JMetroStyleClass.BACKGROUND;

public class TableViewWithSubheadersSample extends Application {

    private static final Style STYLE = Style.LIGHT;

    private TableView<TableViewWithSubheadersSample.Person> table = new TableView<>();
    private final ObservableList<TableViewWithSubheadersSample.Person> data =
            FXCollections.observableArrayList(
                    new TableViewWithSubheadersSample.Person("Jacob", "Smith", "jacob.smith@example.com"),
                    new TableViewWithSubheadersSample.Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                    new TableViewWithSubheadersSample.Person("Ethan", "Williams", "ethan.williams@example.com"),
                    new TableViewWithSubheadersSample.Person("Emma", "Jones", "emma.jones@example.com"),
                    new TableViewWithSubheadersSample.Person("Michael", "Brown", "michael.brown@example.com")
            );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Style startingStyle = Style.LIGHT;
        JMetro jMetro = new JMetro(startingStyle);

        System.setProperty("prism.lcdtext", "false");

        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        stage.setTitle("Table View  With Subheaders Sample");
        stage.setWidth(650);
        stage.setHeight(800);

        final Label header = new Label("Table View");
        header.setPadding(new Insets(0, 0, 0, 5));
        header.getStyleClass().add("header");

        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<TableViewSample.Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<TableViewSample.Person, String>("lastName"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(new PropertyValueFactory<TableViewSample.Person, String>("email"));

        TableColumn fullNameCol = new TableColumn("Full Name");
        fullNameCol.getColumns().addAll(firstNameCol, lastNameCol);

        table.setItems(data);
        table.getColumns().addAll(fullNameCol, emailCol);

        table.getSelectionModel().setCellSelectionEnabled(false);
        CheckBox cellSelection = new CheckBox("Cell Selection");
        cellSelection.setOnAction(event -> {
            table.getSelectionModel().setCellSelectionEnabled(cellSelection.isSelected());
        });
        cellSelection.setSelected(table.getSelectionModel().isCellSelectionEnabled());

        CheckBox tableButtonCheckBox = new CheckBox("Table Menu Button");
        tableButtonCheckBox.setOnAction(event -> {
            table.setTableMenuButtonVisible(tableButtonCheckBox.isSelected());
        });
        tableButtonCheckBox.setSelected(table.isTableMenuButtonVisible());

        CheckBox alternatingRowColors = new CheckBox("Alternating Row Colors");
        alternatingRowColors.setOnAction(event -> {
            boolean isSelected = alternatingRowColors.isSelected();
            if (isSelected) {
                addIfNotPresent(table.getStyleClass(), ALTERNATING_ROW_COLORS);
            } else {
                table.getStyleClass().remove(ALTERNATING_ROW_COLORS);
            }
        });

        CheckBox columnGridLines = new CheckBox("Grid Lines");
        columnGridLines.setOnAction(event -> {
            boolean isSelected = columnGridLines.isSelected();
            if (isSelected) {
                addIfNotPresent(table.getStyleClass(), TABLE_GRID_LINES);
            } else {
                table.getStyleClass().remove(TABLE_GRID_LINES);
            }
        });

        vbox.setSpacing(40);
        vbox.setPadding(new Insets(10, 10, 10, 10));

        addIfNotPresent(vbox.getStyleClass(), BACKGROUND);

        BorderPane controlsBorderPane = new BorderPane();
        VBox controlsVBox = new VBox();
        controlsVBox.getChildren().addAll(cellSelection, tableButtonCheckBox, alternatingRowColors, columnGridLines);
        controlsVBox.setSpacing(10);

        ComboBox<Style> jmetroStyleComboBox = new ComboBox<>();
        jmetroStyleComboBox.getItems().addAll(Style.DARK, Style.LIGHT);
        jmetroStyleComboBox.setValue(startingStyle);
        jmetroStyleComboBox.valueProperty().addListener(observable -> jMetro.setStyle(jmetroStyleComboBox.getValue()));

        controlsBorderPane.setLeft(controlsVBox);
        controlsBorderPane.setRight(jmetroStyleComboBox);

        vbox.getChildren().addAll(header, table, controlsBorderPane);

        jMetro.setScene(scene);

        stage.setScene(scene);
        stage.show();
    }

    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        public Person(String fName, String lName, String email) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.email = new SimpleStringProperty(email);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String fName) {
            lastName.set(fName);
        }

        public String getEmail() {
            return email.get();
        }

        public void setEmail(String fName) {
            email.set(fName);
        }
    }
}