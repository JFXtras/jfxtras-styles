/*
 * Copyright (c) 2011-2019 JFXtras
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

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This class is used to apply the JMetro theme to a {@link Parent} or {@link Scene}.
 * It has various properties that tweak the theme.
 */
public class JMetro {
    private static final String BASE_STYLE_SHEET_URL = JMetro.class.getResource("JMetroBase.css").toExternalForm();
    private static final String PANES_STYLE_SHEET_URL = JMetro.class.getResource("JMetroPanes.css").toExternalForm();

    /**
     * The {@link Scene} that JMetro will be applied to. Setting this property to a {@link Scene} instance will make
     * the {@link #parent parent} property null.
     */
    private ObjectProperty<Scene> scene = new SimpleObjectProperty<Scene>() {
        @Override
        protected void invalidated() {
            if (get() == null) {
                return;
            }
            parent.set(null);

            reApplyTheme();
        }
    };

    /**
     * The {@link Parent} that JMetro will be applied to. Settings this property to a {@link Parent} instance will make
     * the {@link #scene scene} property null.
     */
    private ObjectProperty<Parent> parent = new SimpleObjectProperty<Parent>() {
        @Override
        protected void invalidated() {
            if (get() == null) {
                return;
            }
            scene.set(null);

            reApplyTheme();
        }
    };

    /**
     * The {@link Style} that should be applied
     */
    private ObjectProperty<Style> style = new SimpleObjectProperty<Style>(Style.LIGHT) {
        @Override
        protected void invalidated() {
            reApplyTheme();
        }
    };

    /**
     * If true, all Panes (e.g. BorderPane, AnchorPane, StackPane, Pane, etc) will automatically
     * have their background color set. If the style is {@link Style#DARK DARK} the background will be dark (like black),
     * if the style is {@link Style#LIGHT LIGHT} the background will be light (like white).
     * This has the disadvantage that if you have custom controls that have Panes as intermediate children, you'll
     * usually need to redefine their background to transparent or else you might get whitish/blackish background
     * patches in your custom controls.
     * Alternatively, if this property is set to false (the default), you can add the style class ".background" to the
     * Panes that are supposed to be in the background of your application. They will then automatically change their
     * background color according to the {@link #style} property value.
     */
    private BooleanProperty automaticallyColorPanes = new SimpleBooleanProperty(false) {
        @Override
        protected void invalidated() {
            reApplyTheme();
        }
    };

    public JMetro() {}

    public JMetro(Scene scene, Style style) {
        this.style.set(style);
        this.scene.set(scene);
    }

    public JMetro(Parent parent, Style style) {
        this.style.set(style);
        this.parent.set(parent);
    }

    /**
     * Reapplies the theme in the specified parent or scene if the stylesheets don't exist in the stylesheets list of the
     * parent or scene.
     */
    public void reApplyTheme() {
        ObservableList<String> stylesheetsList = null;
        if (getScene() != null) {
            stylesheetsList = getScene().getStylesheets();
        } else if (getParent() != null) {
            stylesheetsList = getParent().getStylesheets();
        }

        if (stylesheetsList != null) {
            // Remove existing JMetro style stylesheets
            stylesheetsList.remove(Style.LIGHT.getStyleStylesheetURL());
            stylesheetsList.remove(Style.DARK.getStyleStylesheetURL());
            stylesheetsList.remove(PANES_STYLE_SHEET_URL);

            int baseStylesheetIndex = stylesheetsList.indexOf(BASE_STYLE_SHEET_URL);

            if (baseStylesheetIndex == -1) {
                stylesheetsList.add(getStyle().getStyleStylesheetURL());
                stylesheetsList.add(BASE_STYLE_SHEET_URL);
                baseStylesheetIndex = stylesheetsList.indexOf(BASE_STYLE_SHEET_URL);
            } else {
                stylesheetsList.add(baseStylesheetIndex++, getStyle().getStyleStylesheetURL());
            }

            if (isAutomaticallyColorPanes()) {
                if (!stylesheetsList.contains(PANES_STYLE_SHEET_URL)) {
                    stylesheetsList.add(baseStylesheetIndex, PANES_STYLE_SHEET_URL);
                }
            }
        }
    }

    // --- style
    public Style getStyle() { return style.get(); }
    public ObjectProperty<Style> styleProperty() { return style; }
    public void setStyle(Style style) { this.style.set(style); }

    // --- scene
    public Scene getScene() { return scene.get(); }
    public ObjectProperty<Scene> sceneProperty() { return scene; }
    public void setScene(Scene scene) { this.scene.set(scene); }

    // --- parent
    public Parent getParent() { return parent.get(); }
    public ObjectProperty<Parent> parentProperty() { return parent; }
    public void setParent(Parent parent) { this.parent.set(parent); }

    // --- automatically color panes
    public boolean isAutomaticallyColorPanes() { return automaticallyColorPanes.get(); }
    public BooleanProperty automaticallyColorPanesProperty() { return automaticallyColorPanes; }
    public void setAutomaticallyColorPanes(boolean automaticallyColorPanes) { this.automaticallyColorPanes.set(automaticallyColorPanes); }
}

