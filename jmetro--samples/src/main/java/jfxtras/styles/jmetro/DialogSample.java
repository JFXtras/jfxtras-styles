package jfxtras.styles.jmetro;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DialogSample extends Application {

    private static final Style STYLE = Style.DARK;

    public static void main(String[] args) {
        launch(args);
    }

    public DialogSample() {
    }

    @Override
    public void start(Stage stage) {
        VBox vBox = new VBox();
        vBox.getStyleClass().add("background");
        vBox.setSpacing(15);
        vBox.setAlignment(Pos.CENTER);

        HBox jmetroDialogs = new HBox();
        jmetroDialogs.setSpacing(4);
        Button jMetroDialog = new Button("JMetro Dialog");
        jMetroDialog.setOnAction(actionEvent -> showJMetroDialog());

        Button jMetroAlert = new Button("JMetro Alert");
        jMetroAlert.setOnAction(actionEvent -> showJMetroAlert());

        Button jMetroAlertWithoutHeader = new Button("JMetro Alert Without Header");
        jMetroAlertWithoutHeader.setOnAction(actionEvent -> showJMetroAlertWithoutHeader());

        jmetroDialogs.getChildren().addAll(jMetroDialog, jMetroAlert, jMetroAlertWithoutHeader);
        jmetroDialogs.getStyleClass().add("background");
        jmetroDialogs.setAlignment(Pos.CENTER);


        HBox simpleAlerts = new HBox();
        simpleAlerts.setSpacing(4);
        Button informationDialog = new Button("Information Dialog");
        informationDialog.setOnAction(actionEvent -> showInformationDialog());
        Button warningDialog = new Button("Warning Dialog");
        warningDialog.setOnAction(actionEvent -> showWarningDialog());
        Button errorDialog = new Button("Error Dialog");
        errorDialog.setOnAction(actionEvent -> showErrorDialog());
        simpleAlerts.getChildren().addAll(informationDialog, warningDialog, errorDialog);
        simpleAlerts.getStyleClass().add("background");
        simpleAlerts.setAlignment(Pos.CENTER);


        HBox complexAlerts = new HBox();
        complexAlerts.setSpacing(4);
        Button confirmationDialog = new Button("Confirmation Dialog");
        confirmationDialog.setOnAction(actionEvent -> showConfirmationDialog());
        Button textInputDialog = new Button("Text Input Dialog");
        textInputDialog.setOnAction(actionEvent -> showTextInputDialog());
        Button choiceDialog = new Button("Choice Dialog");
        choiceDialog.setOnAction(actionEvent -> showChoiceDialog());
        complexAlerts.getChildren().addAll(confirmationDialog, textInputDialog, choiceDialog);
        complexAlerts.getStyleClass().add("background");
        complexAlerts.setAlignment(Pos.CENTER);

        HBox dialogsWithoutHeaderText = new HBox();
        dialogsWithoutHeaderText.setSpacing(4);
        Button alertWithoutHeaderText = new Button("Alert Without Header Text");
        alertWithoutHeaderText.setOnAction(actionEvent -> showAlertWithoutHeaderText());
        dialogsWithoutHeaderText.getChildren().add(alertWithoutHeaderText);
        dialogsWithoutHeaderText.setAlignment(Pos.CENTER);

        HBox regularDialogHBox = new HBox();
        regularDialogHBox.setSpacing(4);
        Button regularDialog = new Button("Regular Dialog");
        regularDialog.setOnAction(actionEvent -> showRegularDialog());
        regularDialogHBox.getChildren().add(regularDialog);
        regularDialogHBox.setAlignment(Pos.CENTER);

        vBox.getChildren().addAll(jmetroDialogs, simpleAlerts, complexAlerts, dialogsWithoutHeaderText, regularDialogHBox);

        Scene scene = new Scene(vBox, 500, 250);

        new JMetro(scene, STYLE);

//        ScenicView.show(scene);

        stage.setTitle("Dialog Sample");
        stage.setScene(scene);
        stage.show();
    }

    private void showJMetroDialog() {
        JMetro jMetro = new JMetro(STYLE);
        Dialog<?> dialog = JMetro.newDialog("Save your work?", "Lorem ipsum dolor sit amet, adispisicing elit.", jMetro);

        Button closeButton = new Button();
        closeButton.setOnAction(event -> dialog.close());

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);

        dialog.showAndWait();
    }

    private void showJMetroAlert() {
        JMetro jMetro = new JMetro(STYLE);
        Alert.AlertType alertType = Alert.AlertType.CONFIRMATION;
        Alert alert = JMetro.newAlert("Save your work?", "Lorem ipsum dolor sit amet, adispisicing elit.", alertType, jMetro);
        alert.showAndWait();
    }

    private void showJMetroAlertWithoutHeader() {
        JMetro jMetro = new JMetro(STYLE);
        Alert.AlertType alertType = Alert.AlertType.CONFIRMATION;
        Alert alert = JMetro.newAlert("","Save your work before quitting this application?", alertType, jMetro);
        alert.showAndWait();
    }

    private void showInformationDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Save your work?");
        alert.setContentText("Lorem ipsum dolor sit amet, adispisicing elit.");

        new JMetro(alert.getDialogPane().getScene(), STYLE);

        alert.showAndWait();
    }

    private void showWarningDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setHeaderText("Look, a Warning Dialog");
        alert.setContentText("Careful with the next step!");
        alert.setTitle("");

        new JMetro(alert.getDialogPane().getScene(), STYLE);

        alert.showAndWait();
    }

    private void showErrorDialog() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Dialog");
        alert.setHeaderText("Look, an Error Dialog");
        alert.setContentText("Ooops, there was an error!");
        alert.setTitle("");

        new JMetro(alert.getDialogPane().getScene(), STYLE);

        alert.showAndWait();
    }

    private void showConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");
        alert.setTitle("");

        new JMetro(alert.getDialogPane().getScene(), STYLE);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    private void showTextInputDialog() {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Text Input Dialog");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Please enter your name:");
        dialog.setTitle("");

        new JMetro(dialog.getDialogPane().getScene(), STYLE);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your name: " + result.get());
        }

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(name -> System.out.println("Your name: " + name));
    }

    private void showChoiceDialog() {
        List<String> choices = new ArrayList<>();
        choices.add("a");
        choices.add("b");
        choices.add("c");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("b", choices);
        dialog.setTitle("Choice Dialog");
        dialog.setHeaderText("Look, a Choice Dialog");
        dialog.setContentText("Choose your letter:");
        dialog.setTitle("");

        new JMetro(dialog.getDialogPane().getScene(), STYLE);

        // Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()){
            System.out.println("Your choice: " + result.get());
        }

        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(letter -> System.out.println("Your choice: " + letter));
    }

    private void showAlertWithoutHeaderText() {
        Alert alert = new Alert(Alert.AlertType.WARNING);

        alert.setContentText("Careful with the next step!");
        alert.setHeaderText("");
        alert.setTitle("");

        new JMetro(alert.getDialogPane().getScene(), STYLE);

        alert.showAndWait();
    }

    private void showRegularDialog() {
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setHeaderText("Login Credentials");
        dialog.setTitle("");

        // Set the icon (must be included in the project).
//        dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

        // Set the button types.
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        new JMetro(dialog.getDialogPane().getScene(), STYLE);

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