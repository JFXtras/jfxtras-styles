package jfxtras.styles.jmetro8;

import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//import org.scenicview.ScenicView;

public class TableViewSample extends Application {

    private static final JMetro.Style STYLE = JMetro.Style.DARK;

    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data =
            FXCollections.observableArrayList(
                    new Person("Jacob", "Smith", "jacob.smith@example.com"),
                    new Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                    new Person("Ethan", "Williams", "ethan.williams@example.com"),
                    new Person("Emma", "Jones", "emma.jones@example.com"),
                    new Person("Michael", "Brown", "michael.brown@example.com")
            );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);
        stage.setTitle("Table View Sample");
        stage.setWidth(650);
        stage.setHeight(600);

        final Label label = new Label("Table View");
        label.setPadding(new Insets(0, 0, 0, 5));
        label.setFont(new Font("Segoe UI", 36));

        table.setEditable(true);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("firstName"));

        TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("lastName"));

        TableColumn<Person, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<Person, String>("email"));

        table.setItems(data);
        table.getColumns().addAll(List.of(firstNameCol, lastNameCol, emailCol));

        vbox.setSpacing(40);
        vbox.setPadding(new Insets(10, 10, 0, 10));
        vbox.getChildren().addAll(label, table);


        if (STYLE.equals(JMetro.Style.LIGHT)) {
            vbox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            table.setStyle("-fx-border-color: #E5E5E5; -fx-border-width: 1px; -fx-border-style: solid;");
            label.setStyle("-fx-text-fill: #111;");
        }
        else if (STYLE.equals(JMetro.Style.DARK)){
            vbox.setBackground(new Background(new BackgroundFill(Paint.valueOf("#111"), CornerRadii.EMPTY, Insets.EMPTY)));
            table.setStyle("-fx-border-color: #1D1D1D; -fx-border-width: 1px; -fx-border-style: solid;");
            label.setStyle("-fx-text-fill: white;");
        }

        new JMetro(STYLE).applyTheme(scene);

//        ScenicView.show(scene);

        stage.setScene(scene);
        stage.show();
    }

    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty email;

        private Person(String fName, String lName, String email) {
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
