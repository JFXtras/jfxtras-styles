
/**
 * Copyright (c) 2011, JFXtras
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jfxtras.styles.jmetro8;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

public class Main extends Application {
    static final String CHECK_BOX_RESOURCE = "JMetro CheckBox.fxml";
    static final String CONTEXT_MENU_RESOURCE = "JMetro ContextMenu.fxml";
    static final String PUSH_BUTTON_RESOURCE = "JMetro PushButton.fxml";
    static final String RADIO_BUTTON_RESOURCE = "JMetro RadioButton.fxml";
    static final String SCROLL_BAR_RESOURCE = "JMetro ScrollBar.fxml";
    static final String SCROLL_PANE_RESOURCE = "JMetro ScrollPane.fxml";
    static final String TOGGLE_BUTTON_RESOURCE = "JMetro ToggleButton.fxml";
    static final String COMBOBOX_RESOURCE = "JMetro ComboBox.fxml";
    static final String TOOLTIP_RESOURCE = "JMetro Tooltip.fxml";
    static final String RATING_RESOURCE = "JMetro Rating.fxml";
    static final String TEXTBOX_RESOURCE = "JMetro TextBox.fxml";
    static final String PASSWORDBOX_RESOURCE = "JMetro PasswordBox.fxml";
    static final String PROGRESSBAR_RESOURCE = "JMetro ProgressBar.fxml";
    static final String SLIDER_RESOURCE = "JMetro Slider.fxml";
    static final String TOGGLE_SWITCH_RESOURCE = "JMetro ToggleSwitch.fxml";
    static final String DATE_PICKER_RESOURCE = "JMetro DatePicker.fxml";
    static final String SPINNER_RESOURCE = "JMetro Spinner.fxml";
    static final String CHOICE_BOX_RESOURCE = "JMetro ChoiceBox.fxml";
    static final String LIST_BOX_RESOURCE = "JMetro ListBox.fxml";

    static final String RESOURCE = TOGGLE_SWITCH_RESOURCE;

    static final String LIGHT_STYLE_SHEET = "JMetroLightTheme.css";
    static final String DARK_STYLE_SHEET = "JMetroDarkTheme.css";

    static final String STYLE_SHEET = LIGHT_STYLE_SHEET;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource(RESOURCE));
        primaryStage.setTitle("JMetro");
//        root.getStylesheets().add(getClass().getResource("modena.css").toExternalForm());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

//        ScenicView.show(scene);

//        AnchorPane root2 = new AnchorPane();
//        Rating rating = new Rw
// dd(rating);
//        Scene scene2 = new Scene(root2);
//        primaryStage.setScene(scene2);
//        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
