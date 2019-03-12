/*
 * Copyright (c) 2011-2018 JFXtras
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *      * Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      * Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
 *      * Neither the name of the organization nor the
 *        names of its contributors may be used to endorse or promote products
 *        derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 *  DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jfxtras.styles.jmetro8;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
//import org.scenicview.ScenicView;

public class ControlsSample extends Application {
    private static final String CHECK_BOX_RESOURCE = "JMetro CheckBox.fxml";
    private static final String CONTEXT_MENU_RESOURCE = "JMetro ContextMenu.fxml";
    private static final String BUTTON_RESOURCE = "JMetro Button.fxml";
    private static final String RADIO_BUTTON_RESOURCE = "JMetro RadioButton.fxml";
    private static final String SCROLL_BAR_RESOURCE = "JMetro ScrollBar.fxml";
    private static final String SCROLL_PANE_RESOURCE = "JMetro ScrollPane.fxml";
    private static final String TOGGLE_BUTTON_RESOURCE = "JMetro ToggleButton.fxml";
    private static final String COMBOBOX_RESOURCE = "JMetro ComboBox.fxml";
    private static final String TOOLTIP_RESOURCE = "JMetro Tooltip.fxml";
    private static final String RATING_RESOURCE = "JMetro Rating.fxml";
    private static final String TEXT_FIELD_RESOURCE = "JMetro TextField.fxml";
    private static final String TEXT_AREA_RESOURCE = "JMetro TextArea.fxml";
    private static final String PASSWORD_FIELD_RESOURCE = "JMetro PasswordField.fxml";
    private static final String PROGRESS_BAR_RESOURCE = "JMetro ProgressBar.fxml";
    private static final String SLIDER_RESOURCE = "JMetro Slider.fxml";
    private static final String TOGGLE_SWITCH_RESOURCE = "JMetro ToggleSwitch.fxml";
    private static final String DATE_PICKER_RESOURCE = "JMetro DatePicker.fxml";
    private static final String SPINNER_RESOURCE = "JMetro Spinner.fxml";
    private static final String CHOICE_BOX_RESOURCE = "JMetro ChoiceBox.fxml";
    private static final String LIST_VIEW_RESOURCE = "JMetro ListView.fxml";
    private static final String TITLED_PANE_RESOURCE = "JMetro TitledPane.fxml";
    private static final String ACCORDION_RESOURCE = "JMetro Accordion.fxml";
    private static final String MENU_BUTTON_RESOURCE = "JMetro MenuButton.fxml";
    private static final String HYPERLINK_RESOURCE = "JMetro Hyperlink.fxml";

    static final private String RESOURCE = HYPERLINK_RESOURCE;
    static final private JMetro.Style STYLE = JMetro.Style.LIGHT;

    @Override
    public void start(Stage primaryStage) throws Exception{
        System.setProperty("prism.lcdtext", "false");

        Parent root = FXMLLoader.load(getClass().getResource(RESOURCE));
        primaryStage.setTitle("JMetro");

        new JMetro(STYLE).applyTheme(root);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        Platform.runLater(() -> {
            if (RESOURCE.equals(LIST_VIEW_RESOURCE)) {
                ListView listView = (ListView) scene.lookup(".list-view");
                listView.getSelectionModel().select(2);
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }

}
