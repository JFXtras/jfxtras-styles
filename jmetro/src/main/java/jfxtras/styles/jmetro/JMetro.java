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

package jfxtras.styles.jmetro;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * This class is used to apply the JMetro theme to a {@link Parent} or {@link Scene}.
 * It has various properties that tweak the theme.
 */
public class JMetro {
    private static final String BASE_STYLESHEET_URL = JMetro.class.getResource("base.css").toExternalForm();
    private static final String BASE_OTHER_LIBRARIES_STYLESHEET_URL = JMetro.class.getResource("base_other_libraries.css").toExternalForm();
    private static final String BASE_EXTRAS_STYLESHEET_URL = JMetro.class.getResource("base_extras.css").toExternalForm();

    /**
     * The {@link Scene} that JMetro will be applied to. Setting this property to a {@link Scene} instance will make
     * the {@link #parent parent} property null.
     */
    private ObjectProperty<Scene> scene = new SimpleObjectProperty<>() {
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
    private ObjectProperty<Parent> parent = new SimpleObjectProperty<>() {
        @Override
        protected void invalidated() {
            if (get() == null) {
                return;
            }
            scene.set(null);

            JMetroStyleClass.addIfNotPresent(get().getStyleClass(), "root");

            reApplyTheme();
        }
    };

    /**
     * The {@link Style} that should be applied
     */
    private ObjectProperty<Style> style = new SimpleObjectProperty<>(Style.LIGHT) {
        @Override
        protected void invalidated() {
            reApplyTheme();
        }
    };

    private ObservableList<String> overridingStylesheets = FXCollections.observableArrayList();

    /***************************************************************************
     *                                                                         *
     *                        Constructors                                     *
     *                                                                         *
     **************************************************************************/

    public JMetro() {
        overridingStylesheets.addListener(this::overridingStylesheetsChanged);
    }

    public JMetro(Style style) {
        this();
        this.style.set(style);
    }

    public JMetro(Scene scene, Style style) {
        this();
        this.style.set(style);
        this.scene.set(scene);
    }

    public JMetro(Parent parent, Style style) {
        this();
        this.style.set(style);
        this.parent.set(parent);
    }

    /***************************************************************************
     *                                                                         *
     *                           Public API                                    *
     *                                                                         *
     **************************************************************************/

    /**
     * Reapplies the theme in the specified parent or scene if the stylesheets don't exist in the stylesheets list of the
     * parent or scene.
     */
    public void reApplyTheme() {
        ObservableList<String> stylesheetsList = getAppliedStylesheetsList();

        /* Order:
           1 - Base stylesheets (base, extra stylesheets, other library stylesheets)
           2-  Style (Light or Dark) stylesheet
           3 - "Panes" Stylesheet
           4 - Overriding stylesheets */

        if (stylesheetsList != null) {
            // Remove existing JMetro style stylesheets that are configurable
            stylesheetsList.remove(Style.LIGHT.getStyleStylesheetURL());
            stylesheetsList.remove(Style.DARK.getStyleStylesheetURL());

            int baseStylesheetIndex = stylesheetsList.indexOf(BASE_STYLESHEET_URL);

            // Add BASE_STYLESHEET before all other JMetro stylesheets
            if (baseStylesheetIndex == -1) { // There are no base stylesheets added yet
                addBaseStylesheets(stylesheetsList);
                stylesheetsList.add(getStyle().getStyleStylesheetURL()); // This needs to be added after base stylesheet so that specific, overriding styles here are applied
                baseStylesheetIndex = stylesheetsList.indexOf(BASE_STYLESHEET_URL);
            } else { // base stylesheets were already added
                stylesheetsList.add(++baseStylesheetIndex, getStyle().getStyleStylesheetURL());
            }
        }
    }

    /***************************************************************************
     *                                                                         *
     *                          Private API                                    *
     *                                                                         *
     **************************************************************************/

    private void addBaseStylesheets(ObservableList<String> stylesheetsList) {
        stylesheetsList.add(BASE_STYLESHEET_URL);
        stylesheetsList.add(BASE_EXTRAS_STYLESHEET_URL);
        stylesheetsList.add(BASE_OTHER_LIBRARIES_STYLESHEET_URL);
    }

    private void overridingStylesheetsChanged(ListChangeListener.Change<? extends String> changed) {
        ObservableList<String> stylesheetsListBeingApplied = getAppliedStylesheetsList();

        // Currently this only supports adding and removing of stylesheets of the overriding stylesheets list
        while(changed.next()) {
            if (changed.wasRemoved()) {
                for (String stylesheetURL : changed.getRemoved()) {
                    stylesheetsListBeingApplied.remove(stylesheetURL);
                }
            }
            if (changed.wasAdded()) {
                // For now we just add at the bottom of the list
                stylesheetsListBeingApplied.addAll(changed.getAddedSubList());
            }
        }
    }


    /**
     * Gets the list of stylesheets that is being applied. If the {@link #scene} property is set it returns the scene's stylesheets list.
     * If the {@link #parent} property is set it returns the parent's stylesheet list.
     *
     * @return the list of stylesheets of the parent or scene
     */
    private ObservableList<String> getAppliedStylesheetsList() {
        ObservableList<String> stylesheetsList = null;
        if (getScene() != null) {
            stylesheetsList = getScene().getStylesheets();
        } else if (getParent() != null) {
            stylesheetsList = getParent().getStylesheets();
        }
        return stylesheetsList;
    }


    /***************************************************************************
     *                                                                         *
     *                            Properties                                   *
     *                                                                         *
     **************************************************************************/

    // --- overriding stylesheets

    /**
     * The list of stylesheet urls specified here will be present after JMetro stylesheets that make up this theme definition.
     * These stylesheets will be added to the specified {@link #parent} or {@link #scene}.
     *
     * @return the list of stylesheets that will be present after the JMetro stylesheets that compose this theme
     */
    public ObservableList<String> getOverridingStylesheets() { return overridingStylesheets; }

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

