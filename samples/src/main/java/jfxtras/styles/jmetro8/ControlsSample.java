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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControlsSample extends Application {
    static final private String CHECK_BOX_RESOURCE = "JMetro CheckBox.fxml";
    static final private String CONTEXT_MENU_RESOURCE = "JMetro ContextMenu.fxml";
    static final private String BUTTON_RESOURCE = "JMetro Button.fxml";
    static final private String RADIO_BUTTON_RESOURCE = "JMetro RadioButton.fxml";
    static final private String SCROLL_BAR_RESOURCE = "JMetro ScrollBar.fxml";
    static final private String SCROLL_PANE_RESOURCE = "JMetro ScrollPane.fxml";
    static final private String TOGGLE_BUTTON_RESOURCE = "JMetro ToggleButton.fxml";
    static final private String COMBOBOX_RESOURCE = "JMetro ComboBox.fxml";
    static final private String TOOLTIP_RESOURCE = "JMetro Tooltip.fxml";
    static final private String RATING_RESOURCE = "JMetro Rating.fxml";
    static final private String TEXT_FIELD_RESOURCE = "JMetro TextField.fxml";
    static final private String PASSWORD_FIELD_RESOURCE = "JMetro PasswordField.fxml";
    static final private String PROGRESS_BAR_RESOURCE = "JMetro ProgressBar.fxml";
    static final private String SLIDER_RESOURCE = "JMetro Slider.fxml";
    static final private String TOGGLE_SWITCH_RESOURCE = "JMetro ToggleSwitch.fxml";
    static final private String DATE_PICKER_RESOURCE = "JMetro DatePicker.fxml";
    static final private String SPINNER_RESOURCE = "JMetro Spinner.fxml";
    static final private String CHOICE_BOX_RESOURCE = "JMetro ChoiceBox.fxml";
    static final private String LIST_VIEW_RESOURCE = "JMetro ListView.fxml";

    static final private String RESOURCE = PROGRESS_BAR_RESOURCE;

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

//        ScenicView.show(scene);
    }


    public static void main(String[] args) {
        launch(args);
    }

}
