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
        BLUE_COLOR,
        BLUE_GRAY_COLOR,
        BRICK_RED_COLOR,
        CAMOUFLAGE_COLOR,
        CAMOUFLAGE_DESERT_COLOR,
        COOL_BLUE_BRIGHT_COLOR,
        COOL_BLUE_COLOR,
        GOLD_COLOR,
        GRAY_BROWN_COLOR,
        GRAY_COLOR,
        GRAY_DARK_COLOR,
        GREEN_COLOR,
        IRIS_PASTEL_COLOR,
        IRIS_SPRING_COLOR,
        LIDDY_GREEN_COLOR,
        MEADOW_GREEN_COLOR,
        METAL_BLUE_COLOR,
        MINT_DARK_COLOR,
        MINT_LIGHT_COLOR,
        MOD_RED_COLOR,
        MOSS_COLOR,
        NAVY_BLUE_COLOR,
        ORANGE_BRIGHT_COLOR,
        ORANGE_DARK_COLOR,
        ORCHID_COLOR,
        ORCHID_LIGHT_COLOR,
        OVERCAST_COLOR,
        PALE_MOSS_COLOR,
        PALE_RED_COLOR,
        PALE_RUST_COLOR,
        PLUM_COLOR,
        PLUM_LIGHT_COLOR,
        PURPLE_SHADOW_COLOR,
        PURPLE_SHADOW_DARK_COLOR,
        RED_COLOR,
        ROSE_BRIGHT_COLOR,
        ROSE_COLOR,
        RUST_COLOR,
        SAGE_COLOR,
        SEAFOAM_COLOR,
        SEAFOAM_TEAL_COLOR,
        SPORT_GREEN_COLOR,
        STEEL_BLUE_COLOR,
        STORM_COLOR,
        TURF_GREEN_COLOR,
        VIOLET_RED_COLOR,
        VIOLET_RED_LIGHT_COLOR,
        YELLOW_GOLD_COLOR;

        private String getColorName() {
            String color = "JMETRO_BLUE";

            switch (this) {
                case YELLOW_GOLD_COLOR:
                    color = "JMETRO_YELLOW_GOLD";
                    break;
                case GOLD_COLOR:
                    color = "JMETRO_GOLD";
                    break;
                case ORANGE_BRIGHT_COLOR:
                    color = "JMETRO_ORANGE_BRIGHT";
                    break;
                case ORANGE_DARK_COLOR:
                    color = "JMETRO_ORANGE_DARK";
                    break;
                case RUST_COLOR:
                    color = "JMETRO_RUST";
                    break;
                case PALE_RUST_COLOR:
                    color = "JMETRO_PALE_RUST";
                    break;
                case BRICK_RED_COLOR:
                    color = "JMETRO_BRICK_RED";
                    break;
                case MOD_RED_COLOR:
                    color = "JMETRO_MOD_RED";
                    break;
                case PALE_RED_COLOR:
                    color = "JMETRO_PALE_RED";
                    break;
                case RED_COLOR:
                    color = "JMETRO_RED";
                    break;
                case ROSE_BRIGHT_COLOR:
                    color = "JMETRO_ROSE_BRIGHT";
                    break;
                case ROSE_COLOR:
                    color = "JMETRO_ROSE";
                    break;
                case PLUM_LIGHT_COLOR:
                    color = "JMETRO_PLUM_LIGHT";
                    break;
                case PLUM_COLOR:
                    color = "JMETRO_PLUM";
                    break;
                case ORCHID_LIGHT_COLOR:
                    color = "JMETRO_ORCHID_LIGHT";
                    break;
                case ORCHID_COLOR:
                    color = "JMETRO_ORCHID";
                    break;
                case BLUE_COLOR:
                    color = "JMETRO_BLUE";
                    break;
                case NAVY_BLUE_COLOR:
                    color = "JMETRO_NAVY_BLUE";
                    break;
                case PURPLE_SHADOW_COLOR:
                    color = "JMETRO_PURPLE_SHADOW";
                    break;
                case PURPLE_SHADOW_DARK_COLOR:
                    color = "JMETRO_PURPLE_SHADOW_DARK";
                    break;
                case IRIS_PASTEL_COLOR:
                    color = "JMETRO_IRIS_PASTEL";
                    break;
                case IRIS_SPRING_COLOR:
                    color = "JMETRO_IRIS_SPRING";
                    break;
                case VIOLET_RED_LIGHT_COLOR:
                    color = "JMETRO_VIOLET_RED_LIGHT";
                    break;
                case VIOLET_RED_COLOR:
                    color = "JMETRO_VIOLET_RED";
                    break;
                case COOL_BLUE_BRIGHT_COLOR:
                    color = "JMETRO_COOL_BLUE_BRIGHT";
                    break;
                case COOL_BLUE_COLOR:
                    color = "JMETRO_COOL_BLUE";
                    break;
                case SEAFOAM_COLOR:
                    color = "JMETRO_SEAFOAM";
                    break;
                case SEAFOAM_TEAL_COLOR:
                    color = "JMETRO_SEAFOAM_TEAL";
                    break;
                case MINT_LIGHT_COLOR:
                    color = "JMETRO_MINT_LIGHT";
                    break;
                case MINT_DARK_COLOR:
                    color = "JMETRO_MINT_DARK";
                    break;
                case TURF_GREEN_COLOR:
                    color = "JMETRO_TURF_GREEN";
                    break;
                case SPORT_GREEN_COLOR:
                    color = "JMETRO_SPORT_GREEN";
                    break;
                case GRAY_COLOR:
                    color = "JMETRO_GRAY";
                    break;
                case GRAY_BROWN_COLOR:
                    color = "JMETRO_GRAY_BROWN";
                    break;
                case STEEL_BLUE_COLOR:
                    color = "JMETRO_STEEL_BLUE";
                    break;
                case METAL_BLUE_COLOR:
                    color = "JMETRO_METAL_BLUE";
                    break;
                case PALE_MOSS_COLOR:
                    color = "JMETRO_PALE_MOSS";
                    break;
                case MOSS_COLOR:
                    color = "JMETRO_MOSS";
                    break;
                case MEADOW_GREEN_COLOR:
                    color = "JMETRO_MEADOW_GREEN";
                    break;
                case GREEN_COLOR:
                    color = "JMETRO_GREEN";
                    break;
                case OVERCAST_COLOR:
                    color = "JMETRO_OVERCAST";
                    break;
                case STORM_COLOR:
                    color = "JMETRO_STORM";
                    break;
                case BLUE_GRAY_COLOR:
                    color = "JMETRO_BLUE_GRAY";
                    break;
                case GRAY_DARK_COLOR:
                    color = "JMETRO_GRAY_DARK";
                    break;
                case LIDDY_GREEN_COLOR:
                    color = "JMETRO_LIDDY_GREEN";
                    break;
                case SAGE_COLOR:
                    color = "JMETRO_SAGE";
                    break;
                case CAMOUFLAGE_DESERT_COLOR:
                    color = "JMETRO_CAMOUFLAGE_DESERT";
                    break;
                case CAMOUFLAGE_COLOR:
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
     * Base JMetro constructor. With default accent color argument.
     *
     * @param style base style.
     */
    public JMetro(Style style) {
        this.style = style;
        this.accent = Accent.BLUE_COLOR;
        validateJavaVersion();
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

