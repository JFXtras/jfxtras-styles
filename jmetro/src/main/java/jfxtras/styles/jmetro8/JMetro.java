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

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This class is used to apply the JMetro theme to a {@link Parent} or {@link Scene}.
 * It has various properties that tweak the theme.
 */
public class JMetro {
    private static final String BASE_STYLE_SHEET_URL = JMetro.class.getResource("JMetroBase.css").toExternalForm();

    /**
     * The {@link Scene} that JMetro will be applied to. Setting this property to a {@link Scene} instance will make
     * the {@Link #parent parent} property null.
     */
    private ObjectProperty<Scene> scene = new SimpleObjectProperty<Scene>() {
        @Override
        protected void invalidated() {
            if (get() == null) {
                return;
            }
            parent.set(null);

            get().getStylesheets().add(BASE_STYLE_SHEET_URL);
            get().getStylesheets().add(getStyle().getStyleStylesheetURL());
        }
    };

    /**
     * The {@Link Parent} that JMetro will be applied to. Settings this property to a {@link Parent} instance will make
     * the {@Link #scene scene} property null.
     */
    private ObjectProperty<Parent> parent = new SimpleObjectProperty<Parent>() {
        @Override
        protected void invalidated() {
            if (get() == null) {
                return;
            }
            scene.set(null);

            get().getStylesheets().add(BASE_STYLE_SHEET_URL);
            get().getStylesheets().add(getStyle().getStyleStylesheetURL());
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
        if (getScene() != null) {
            // Remove existing style stylesheets
            getScene().getStylesheets().remove(Style.LIGHT.getStyleStylesheetURL());
            getScene().getStylesheets().remove(Style.DARK.getStyleStylesheetURL());

            int baseStylesheetIndex = getScene().getStylesheets().indexOf(BASE_STYLE_SHEET_URL);
            if (baseStylesheetIndex == -1) {
                getScene().getStylesheets().add(BASE_STYLE_SHEET_URL);
                baseStylesheetIndex = getScene().getStylesheets().indexOf(BASE_STYLE_SHEET_URL);
            }

            getScene().getStylesheets().add(baseStylesheetIndex + 1, getStyle().getStyleStylesheetURL());
        } else if (getParent() != null) {
            // Remove existing style stylesheets
            getParent().getStylesheets().remove(Style.LIGHT.getStyleStylesheetURL());
            getParent().getStylesheets().remove(Style.DARK.getStyleStylesheetURL());

            int baseStylesheetIndex = getParent().getStylesheets().indexOf(BASE_STYLE_SHEET_URL);
            if (baseStylesheetIndex == -1) {
                getParent().getStylesheets().add(BASE_STYLE_SHEET_URL);
                baseStylesheetIndex =  getParent().getStylesheets().indexOf(BASE_STYLE_SHEET_URL);
            }

            getParent().getStylesheets().add(baseStylesheetIndex + 1, getStyle().getStyleStylesheetURL());
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
}

