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

import javafx.scene.Parent;
import javafx.scene.Scene;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JMetro {
    public enum Style {
        LIGHT,
        DARK;

        private String getStyleSheetFileName() {
            String stylesheet = null;

            switch (this) {
                case LIGHT:
                    stylesheet = "JMetroLightTheme.css";
                    break;
                case DARK:
                    stylesheet = "JMetroDarkTheme.css";
                    break;
            }

            return stylesheet;
        }
    }

    public enum Accent {
        BLUE,
        BLUE_GRAY,
        BRICK_RED,
        CAMOUFLAGE,
        CAMOUFLAGE_DESERT,
        COOL_BLUE_BRIGHT,
        COOL_BLUE,
        GOLD,
        GRAY_BROWN,
        GRAY,
        GRAY_DARK,
        GREEN,
        IRIS_PASTEL,
        IRIS_SPRING,
        LIDDY_GREEN,
        MEADOW_GREEN,
        METAL_BLUE,
        MINT_DARK,
        MINT_LIGHT,
        MOD_RED,
        MOSS,
        NAVY_BLUE,
        ORANGE_BRIGHT,
        ORANGE_DARK,
        ORCHID,
        ORCHID_LIGHT,
        OVERCAST,
        PALE_MOSS,
        PALE_RED,
        PALE_RUST,
        PLUM,
        PLUM_LIGHT,
        PURPLE_SHADOW,
        PURPLE_SHADOW_DARK,
        RED,
        ROSE_BRIGHT,
        ROSE,
        RUST,
        SAGE,
        SEAFOAM,
        SEAFOAM_TEAL,
        SPORT_GREEN,
        STEEL_BLUE,
        STORM,
        TURF_GREEN,
        VIOLET_RED,
        VIOLET_RED_LIGHT,
        YELLOW_GOLD;

        private String getColorName() {
            String color = "JMETRO_BLUE";

            switch (this) {
                case YELLOW_GOLD:
                    color = "JMETRO_YELLOW_GOLD";
                    break;
                case GOLD:
                    color = "JMETRO_GOLD";
                    break;
                case ORANGE_BRIGHT:
                    color = "JMETRO_ORANGE_BRIGHT";
                    break;
                case ORANGE_DARK:
                    color = "JMETRO_ORANGE_DARK";
                    break;
                case RUST:
                    color = "JMETRO_RUST";
                    break;
                case PALE_RUST:
                    color = "JMETRO_PALE_RUST";
                    break;
                case BRICK_RED:
                    color = "JMETRO_BRICK_RED";
                    break;
                case MOD_RED:
                    color = "JMETRO_MOD_RED";
                    break;
                case PALE_RED:
                    color = "JMETRO_PALE_RED";
                    break;
                case RED:
                    color = "JMETRO_RED";
                    break;
                case ROSE_BRIGHT:
                    color = "JMETRO_ROSE_BRIGHT";
                    break;
                case ROSE:
                    color = "JMETRO_ROSE";
                    break;
                case PLUM_LIGHT:
                    color = "JMETRO_PLUM_LIGHT";
                    break;
                case PLUM:
                    color = "JMETRO_PLUM";
                    break;
                case ORCHID_LIGHT:
                    color = "JMETRO_ORCHID_LIGHT";
                    break;
                case ORCHID:
                    color = "JMETRO_ORCHID";
                    break;
                case BLUE:
                    color = "JMETRO_BLUE";
                    break;
                case NAVY_BLUE:
                    color = "JMETRO_NAVY_BLUE";
                    break;
                case PURPLE_SHADOW:
                    color = "JMETRO_PURPLE_SHADOW";
                    break;
                case PURPLE_SHADOW_DARK:
                    color = "JMETRO_PURPLE_SHADOW_DARK";
                    break;
                case IRIS_PASTEL:
                    color = "JMETRO_IRIS_PASTEL";
                    break;
                case IRIS_SPRING:
                    color = "JMETRO_IRIS_SPRING";
                    break;
                case VIOLET_RED_LIGHT:
                    color = "JMETRO_VIOLET_RED_LIGHT";
                    break;
                case VIOLET_RED:
                    color = "JMETRO_VIOLET_RED";
                    break;
                case COOL_BLUE_BRIGHT:
                    color = "JMETRO_COOL_BLUE_BRIGHT";
                    break;
                case COOL_BLUE:
                    color = "JMETRO_COOL_BLUE";
                    break;
                case SEAFOAM:
                    color = "JMETRO_SEAFOAM";
                    break;
                case SEAFOAM_TEAL:
                    color = "JMETRO_SEAFOAM_TEAL";
                    break;
                case MINT_LIGHT:
                    color = "JMETRO_MINT_LIGHT";
                    break;
                case MINT_DARK:
                    color = "JMETRO_MINT_DARK";
                    break;
                case TURF_GREEN:
                    color = "JMETRO_TURF_GREEN";
                    break;
                case SPORT_GREEN:
                    color = "JMETRO_SPORT_GREEN";
                    break;
                case GRAY:
                    color = "JMETRO_GRAY";
                    break;
                case GRAY_BROWN:
                    color = "JMETRO_GRAY_BROWN";
                    break;
                case STEEL_BLUE:
                    color = "JMETRO_STEEL_BLUE";
                    break;
                case METAL_BLUE:
                    color = "JMETRO_METAL_BLUE";
                    break;
                case PALE_MOSS:
                    color = "JMETRO_PALE_MOSS";
                    break;
                case MOSS:
                    color = "JMETRO_MOSS";
                    break;
                case MEADOW_GREEN:
                    color = "JMETRO_MEADOW_GREEN";
                    break;
                case GREEN:
                    color = "JMETRO_GREEN";
                    break;
                case OVERCAST:
                    color = "JMETRO_OVERCAST";
                    break;
                case STORM:
                    color = "JMETRO_STORM";
                    break;
                case BLUE_GRAY:
                    color = "JMETRO_BLUE_GRAY";
                    break;
                case GRAY_DARK:
                    color = "JMETRO_GRAY_DARK";
                    break;
                case LIDDY_GREEN:
                    color = "JMETRO_LIDDY_GREEN";
                    break;
                case SAGE:
                    color = "JMETRO_SAGE";
                    break;
                case CAMOUFLAGE_DESERT:
                    color = "JMETRO_CAMOUFLAGE_DESERT";
                    break;
                case CAMOUFLAGE:
                    color = "JMETRO_CAMOUFLAGE";
                    break;
            }

            return color;
        }
    }

    private Style style;
    private Accent accent;
    private Scene scene;
    private Parent parent;
    private boolean isDark = false;

    /**
     * Base JMetro constructor.
     * With default accent color BLUE.
     *
     * @param style base style.
     */
    public JMetro(Style style) {
        this(style, Accent.BLUE);
    }

    /**
     * Base JMetro constructor.
     *
     * @param style  base style.
     * @param accent base accent color.
     */
    public JMetro(Style style, Accent accent) {
        this.style = style;
        this.accent = accent;
        validateJavaVersion();
    }

    /**
     * It method apply theme and accent colors on your scene.
     *
     * @param scene your reference on window scene.
     */
    public void applyTheme(Scene scene) {
        scene.getStylesheets().add(getClass().getResource(style.getStyleSheetFileName()).toExternalForm());
        scene.getRoot().setStyle(String.format("accent_color: %s", accent.getColorName()));
        this.isDark = this.style == Style.DARK;
        this.scene = scene;
    }

    /**
     * It method apply theme and accent colors on your parent.
     *
     * @param parent your reference on window parent.
     */
    public void applyTheme(Parent parent) {
        parent.getStylesheets().add(getClass().getResource(style.getStyleSheetFileName()).toExternalForm());
        parent.setStyle(String.format("accent_color: %s", accent.getColorName()));
        this.isDark = this.style == Style.DARK;
        this.parent = parent;
    }

    /**
     * It method change theme and accent colors on scene or parent what initialized in applyTheme method.
     *
     * @param style  base style.
     * @param accent base accent color.
     * @throws Exception Initial reference to scene and parent apparently have null reference.
     */
    public void changeTheme(Style style, Accent accent) throws Exception {
        if (this.scene != null) {
            this.scene.getStylesheets().clear();
            this.scene.getStylesheets().add(getClass().getResource(style.getStyleSheetFileName()).toExternalForm());
            this.scene.getRoot().setStyle(String.format("accent_color: %s", accent.getColorName()));
            this.isDark = style == Style.DARK;
        } else if (this.parent != null) {
            this.parent.getStylesheets().clear();
            this.parent.getStylesheets().add(getClass().getResource(style.getStyleSheetFileName()).toExternalForm());
            this.scene.getRoot().setStyle(String.format("accent_color: %s", accent.getColorName()));
            this.isDark = style == Style.DARK;
        } else {
            throw new Exception("Initial reference to scene and parent apparently have null reference.");
        }
    }

    /**
     * It method just get isDark base theme value.
     * If your theme dark - return true, else return false.
     *
     * @return isDark theme value, boolean type.
     */
    public boolean isDark() {
        return this.isDark;
    }

    /**
     * It method just validate java version.
     * If java version not equals 1.8 then it method print warning in log.
     */
    private void validateJavaVersion() {
        if (!System.getProperty("java.specification.version").equals("1.8")) {
            Logger log = Logger.getLogger(getClass().getName());
            log.log(Level.WARNING, "for now JMetro only supports Java 8 (with JavaFX 8). You are using Java " + System.getProperty("java.specification.version"));
        }
    }
}

