package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.util.Arrays;
import java.util.List;

public class SplitPaneSample extends Application {

    private static final Style STYLE = Style.LIGHT;

    private TableView<TableViewSample.Person> table = new TableView<>();
    private final ObservableList<TableViewSample.Person> data =
            FXCollections.observableArrayList(
                    new TableViewSample.Person("Jacob", "Smith", "jacob.smith@example.com"),
                    new TableViewSample.Person("Isabella", "Johnson", "isabella.johnson@example.com"),
                    new TableViewSample.Person("Ethan", "Williams", "ethan.williams@example.com"),
                    new TableViewSample.Person("Emma", "Jones", "emma.jones@example.com"),
                    new TableViewSample.Person("Michael", "Brown", "michael.brown@example.com")
            );

    private List<TreeViewSample.Employee> employees = Arrays.asList(
            new TreeViewSample.Employee("Jacob Smith", "Accounts Department"),
            new TreeViewSample.Employee("Isabella Johnson", "Accounts Department"),
            new TreeViewSample.Employee("Ethan Williams", "Sales Department"),
            new TreeViewSample.Employee("Emma Jones", "Sales Department"),
            new TreeViewSample.Employee("Michael Brown", "Sales Department"),
            new TreeViewSample.Employee("Anna Black", "Sales Department"),
            new TreeViewSample.Employee("Rodger York", "Sales Department"),
            new TreeViewSample.Employee("Susan Collins", "Sales Department"),
            new TreeViewSample.Employee("Mike Graham", "IT Support"),
            new TreeViewSample.Employee("Judy Mayer", "IT Support"),
            new TreeViewSample.Employee("Gregory Smith", "IT Support"));
    private TreeItem<String> rootNode;

    public static void main(String[] args) {
        launch(args);
    }

    public SplitPaneSample() {
    }

    @Override
    public void start(Stage stage) {
        System.setProperty("prism.lcdtext", "false");

        SplitPane mainSplitPane = new SplitPane();
        mainSplitPane.setOrientation(Orientation.VERTICAL);
        mainSplitPane.setDividerPosition(0, 0.7);

        SplitPane horizontalSplitPane = new SplitPane();
        horizontalSplitPane.setDividerPosition(0, 0.3);

        TableView tableView = createTableView();
        ScrollPane scrollPane = new ScrollPane();

        TreeView<String> treeView = createTreeView();

        horizontalSplitPane.getItems().addAll(treeView, scrollPane);
        mainSplitPane.getItems().addAll(horizontalSplitPane, tableView);


        BorderPane root = new BorderPane(mainSplitPane);
        Scene scene = new Scene(root, 800, 600);

        new JMetro(scene, STYLE);

        stage.setTitle("SplitPane Sample");
        stage.setScene(scene);
        stage.show();
    }

    private TableView createTableView() {
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setMinWidth(150);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<TableViewSample.Person, String>("firstName"));

        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setMinWidth(150);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<TableViewSample.Person, String>("lastName"));

        TableColumn emailCol = new TableColumn("Email");
        emailCol.setMinWidth(250);
        emailCol.setCellValueFactory(new PropertyValueFactory<TableViewSample.Person, String>("email"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        return table;
    }

    private TreeView<String> createTreeView() {
        this.rootNode = new TreeItem<>("MyCompany Human Resources");
        rootNode.setExpanded(true);
        for (TreeViewSample.Employee employee : employees) {
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

        return new TreeView<String>(rootNode);
    }
}
