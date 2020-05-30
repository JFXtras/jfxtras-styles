package jfxtras.styles.samples.jmetro;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;
import jfxtras.styles.jmetro.*;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DialogSample extends Application {
    private ToggleButton withOwner;
    private ComboBox<Style> styleComboBox;

    private static final Style STYLE = Style.DARK;

    public static void main(String[] args) {
        launch(args);
    }

    public DialogSample() {
    }

    @Override
    public void start(Stage stage) {
        JMetro mainStageJMetro = new JMetro(Style.LIGHT);

        BorderPane borderPane = new BorderPane();
        borderPane.getStyleClass().add("background");
        ToolBar topLeftToolbar = new ToolBar();

        withOwner = new ToggleButton("With Owner");

        styleComboBox = new ComboBox<>();
        styleComboBox.getItems().addAll(Style.LIGHT, Style.DARK);
        styleComboBox.setValue(Style.LIGHT);

        topLeftToolbar.getItems().addAll(withOwner, styleComboBox);

        ToolBar topRightToolBar = new ToolBar();
        ComboBox<Style> mainStageStyleComboBox = new ComboBox<>();
        mainStageStyleComboBox.getItems().addAll(Style.LIGHT, Style.DARK);
        mainStageStyleComboBox.setValue(mainStageJMetro.getStyle());
        mainStageStyleComboBox.valueProperty().addListener(observable -> {
            mainStageJMetro.setStyle(mainStageStyleComboBox.getValue());
        });
        topRightToolBar.getItems().add(mainStageStyleComboBox);

        BorderPane topToolBarBorderPane = new BorderPane();
        topToolBarBorderPane.getStyleClass().add("background");
        topToolBarBorderPane.setLeft(topLeftToolbar);
        topToolBarBorderPane.setRight(topRightToolBar);

        borderPane.setTop(topToolBarBorderPane);

        VBox vBox = new VBox();
        vBox.getStyleClass().add("background");
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);

        HBox jmetroDialogs = new HBox();
        jmetroDialogs.setSpacing(4);
        Button jMetroDialog = new Button("JMetro Dialog");
        jMetroDialog.setOnAction(actionEvent -> showJMetroDialog(stage));

        Button jMetroAlert = new Button("JMetro Alert");
        jMetroAlert.setOnAction(actionEvent -> showJMetroAlert(stage));

        Button jMetroAlertWithoutHeader = new Button("JMetro Alert Without Header");
        jMetroAlertWithoutHeader.setOnAction(actionEvent -> showJMetroAlertWithoutHeader(stage));

        Button jMetroTextInputDialog = new Button("JMetro TextInputDialog");
        jMetroTextInputDialog.setOnAction(actionEvent -> showJMetroTextInputDialog(stage));

        Button jMetroChoiceDialog = new Button("JMetro ChoiceDialog");
        jMetroChoiceDialog.setOnAction(actionEvent -> showJMetroChoiceDialog(stage));

        Button jMetroAlertWithExpandableContent = new Button("JMetro Alert With Expandable Content");
        jMetroAlertWithExpandableContent.setOnAction(actionEvent -> showJMetroAlertWithExpandableContent(stage));

        jmetroDialogs.getChildren().addAll(jMetroDialog, jMetroAlert, jMetroAlertWithoutHeader, jMetroTextInputDialog,
                jMetroChoiceDialog, jMetroAlertWithExpandableContent);
        jmetroDialogs.getStyleClass().add("background");
        jmetroDialogs.setAlignment(Pos.CENTER);


        HBox simpleAlerts = new HBox();
        simpleAlerts.setSpacing(4);
        Button informationDialog = new Button("Information Dialog");
        informationDialog.setOnAction(actionEvent -> showInformationDialog(stage));
        Button warningDialog = new Button("Warning Dialog");
        warningDialog.setOnAction(actionEvent -> showWarningDialog(stage));
        Button errorDialog = new Button("Error Dialog");
        errorDialog.setOnAction(actionEvent -> showErrorDialog(stage));
        simpleAlerts.getChildren().addAll(informationDialog, warningDialog, errorDialog);
        simpleAlerts.getStyleClass().add("background");
        simpleAlerts.setAlignment(Pos.CENTER);


        HBox complexAlerts = new HBox();
        complexAlerts.setSpacing(4);
        Button confirmationDialog = new Button("Confirmation Dialog");
        confirmationDialog.setOnAction(actionEvent -> showConfirmationDialog(stage));
        Button textInputDialog = new Button("Text Input Dialog");
        textInputDialog.setOnAction(actionEvent -> showTextInputDialog(stage));
        Button choiceDialog = new Button("Choice Dialog");
        choiceDialog.setOnAction(actionEvent -> showChoiceDialog(stage));
        complexAlerts.getChildren().addAll(confirmationDialog, textInputDialog, choiceDialog);
        complexAlerts.getStyleClass().add("background");
        complexAlerts.setAlignment(Pos.CENTER);

        HBox dialogsWithoutHeaderText = new HBox();
        dialogsWithoutHeaderText.setSpacing(4);
        Button alertWithoutHeaderText = new Button("Alert Without Header Text");
        alertWithoutHeaderText.setOnAction(actionEvent -> showAlertWithoutHeaderText(stage));
        dialogsWithoutHeaderText.getChildren().add(alertWithoutHeaderText);
        dialogsWithoutHeaderText.setAlignment(Pos.CENTER);

        HBox regularDialogHBox = new HBox();
        regularDialogHBox.setSpacing(4);
        Button regularDialog = new Button("Regular Dialog");
        regularDialog.setOnAction(actionEvent -> showRegularDialog(stage));
        regularDialogHBox.getChildren().add(regularDialog);
        regularDialogHBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(jmetroDialogs,
                                  new Separator(Orientation.HORIZONTAL),
                                  simpleAlerts, complexAlerts, dialogsWithoutHeaderText, regularDialogHBox);
        VBox.setMargin(jmetroDialogs, new Insets(30, 0, 0, 0));

        borderPane.setCenter(vBox);

        Scene scene = new Scene(borderPane, 900, 250);

        mainStageJMetro.setScene(scene);

//        ScenicView.show(scene);

        stage.setTitle("Dialog Sample");
        stage.setScene(scene);
        stage.show();
    }

    private void showJMetroDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());
        FlatDialog<ButtonType> dialog = new FlatDialog<>();
        dialog.setHeaderText("Save your work?");
        dialog.setContentText("Lorem ipsum dolor sit amet, adispisicing elit.");

        if (withOwner.isSelected()) {
            dialog.initOwner(owner);
        } else {
            jMetro.setScene(dialog.getDialogPane().getScene());
        }

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        result.ifPresent(buttonType -> System.out.println("Result is = " + buttonType));
    }

    private void showJMetroAlert(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        FlatAlert alert = new FlatAlert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Save your work?");
        alert.setContentText("Lorem ipsum dolor sit amet, adispisicing elit.");

        if (withOwner.isSelected()) {
            alert.initOwner(owner);
        } else {
            jMetro.setScene(alert.getDialogPane().getScene());
        }

        alert.showAndWait();
    }

    private void showJMetroAlertWithoutHeader(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        FlatAlert alert = new FlatAlert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("Save your work before quitting this application?");

        if (withOwner.isSelected()) {
            alert.initOwner(owner);
        } else {
            jMetro.setScene(alert.getDialogPane().getScene());
        }

        alert.showAndWait();
    }

    private void showJMetroTextInputDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        FlatTextInputDialog textInputDialog = new FlatTextInputDialog();
        textInputDialog.setHeaderText("Look, a Text Input Dialog");
        textInputDialog.setContentText("Please enter your name:");

        if (withOwner.isSelected()) {
            textInputDialog.initOwner(owner);
        } else {
            jMetro.setScene(textInputDialog.getDialogPane().getScene());
        }

        // Traditional way to get the response value.
        Optional<String> result = textInputDialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your name: " + name));

    }

    private void showJMetroChoiceDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        List<String> choices = new ArrayList<>();
        choices.add("a");
        choices.add("b");
        choices.add("c");

        FlatChoiceDialog<String> dialog = new FlatChoiceDialog<>("b", choices);
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Look, a Choice Dialog");

        if (withOwner.isSelected()) {
            dialog.initOwner(owner);
        } else {
            jMetro.setScene(dialog.getDialogPane().getScene());
        }

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your choice: " + name));
    }

    private void showJMetroAlertWithExpandableContent(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        FlatAlert alert = new FlatAlert(Alert.AlertType.ERROR);
        alert.setHeaderText("Look, an Exception Dialog");
        alert.setContentText("Could not find file blabla.txt!");

        Exception ex = new FileNotFoundException("Could not find file blabla.txt");

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

//        textArea.setMaxWidth(Double.MAX_VALUE);
//        textArea.setMaxHeight(Double.MAX_VALUE);
//        GridPane.setVgrow(textArea, Priority.ALWAYS);
//        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

// Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        if (withOwner.isSelected()) {
            alert.initOwner(owner);
        } else {
            jMetro.setScene(alert.getDialogPane().getScene());
        }

        alert.showAndWait();
    }

    private void showInformationDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Save your work?");
        alert.setContentText("Lorem ipsum dolor sit amet, adispisicing elit.");

        if (withOwner.isSelected()) {
            alert.initOwner(owner);
        } else {
            jMetro.setScene(alert.getDialogPane().getScene());
        }

        alert.showAndWait();
    }

    private void showWarningDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Careful with the next step!");
        alert.setTitle("");

        if (withOwner.isSelected()) {
            alert.initOwner(owner);
        } else {
            jMetro.setScene(alert.getDialogPane().getScene());
        }

        alert.showAndWait();
    }

    private void showErrorDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("Ooops, there was an error!");
        alert.setTitle("");

        if (withOwner.isSelected()) {
            alert.initOwner(owner);
        } else {
            jMetro.setScene(alert.getDialogPane().getScene());
        }

        alert.showAndWait();
    }

    private void showConfirmationDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        alert.setTitle("");

        if (withOwner.isSelected()) {
            alert.initOwner(owner);
        } else {
            jMetro.setScene(alert.getDialogPane().getScene());
        }

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private void showTextInputDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");
        dialog.setTitle("");

        if (withOwner.isSelected()) {
            dialog.initOwner(owner);
        } else {
            jMetro.setScene(dialog.getDialogPane().getScene());
        }

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    private void showChoiceDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        List<String> choices = new ArrayList<>();
        choices.add("a");
        choices.add("b");
        choices.add("c");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your letter:");
        dialog.setTitle("");

        if (withOwner.isSelected()) {
            dialog.initOwner(owner);
        } else {
            jMetro.setScene(dialog.getDialogPane().getScene());
        }

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
        }

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(letter -> System.out.println("Your choice: " + letter));
    }

    private void showAlertWithoutHeaderText(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setContentText("Careful with the next step!");
        alert.setHeaderText("");
        alert.setTitle("");

        if (withOwner.isSelected()) {
            alert.initOwner(owner);
        } else {
            jMetro.setScene(alert.getDialogPane().getScene());
        }

        alert.showAndWait();
    }

    private void showRegularDialog(Stage owner) {
        JMetro jMetro = new JMetro(styleComboBox.getValue());

        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setHeaderText("Login Credentials");
        dialog.setTitle("");

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        if (withOwner.isSelected()) {
            dialog.initOwner(owner);
        } else {
            jMetro.setScene(dialog.getDialogPane().getScene());
        }


        // Create the username and password labels and fields.
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        grid.add(usernameLabel, 0, 1);
        grid.add(username, 1, 1);
        grid.add(passwordLabel, 0, 2);
        grid.add(password, 1, 2);

        // Enable/Disable login button depending on whether a username was entered.
        Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
        loginButton.setDisable(true);

        // Do some validation (using the Java 8 lambda syntax).
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });

        dialog.getDialogPane().setContent(grid);

        // Request focus on the username field by default.
        Platform.runLater(() -> username.requestFocus());

        // Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return new Pair<>(username.getText(), password.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(usernamePassword -> {
            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
    }
}