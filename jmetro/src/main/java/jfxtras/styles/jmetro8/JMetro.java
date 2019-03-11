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
import javafx.scene.paint.Color;

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
    private Color accentColor;
    private Scene scene;
    private Parent parent;

    /**
     * JMetro class constructor.
     * It constructor call JMetro constructor but with
     * default color blue.
     *
     * @param style theme style.
     */
    public JMetro(Style style) {
        this(style, Accent.BLUE);
    }

    /**
     * JMetro class constructor.
     *
     * @param style theme style.
     * @param accent accent color from Accent enum.
     */
    public JMetro(Style style, Accent accent) {
        this.style = style;
        this.accent = accent;
        validateJavaVersion();
    }

    /**
     * JMetro class constructor.
     * With supporting custom color.
     * <p>
     * Callback value blue color from accent enum.
     *
     * @param style theme style.
     * @param accent custom accent color.
     */
    public JMetro(Style style, Color accent) {
        this.style = style;
        this.accent = Accent.BLUE;
        this.accentColor = accent;
        validateJavaVersion();
    }

    /**
     * It method apply theme and accent colors on your scene.
     *
     * @param scene your reference on window scene.
     */
    public void applyTheme(Scene scene) {
        scene.getStylesheets().add(getClass().getResource(style.getStyleSheetFileName()).toExternalForm());
        scene.getStylesheets().add(getClass().getResource("JMetroColors.css").toExternalForm());
        scene.getRoot().setStyle(String.format("accent_color: %s", accent.getColorName()));

        if (accentColor != null) {
            //String accentColorFormatted = 0xd2691eff;
            String accentColorFormatted =
                    accentColor.toString().split("x")[1];
            scene.getRoot().setStyle(String.format("accent_color: %s", accentColorFormatted));
        }

        this.scene = scene;
    }

    /**
     * It method apply theme and accent colors on your parent.
     *
     * @param parent your reference on window parent.
     */
    public void applyTheme(Parent parent) {
        parent.getStylesheets().add(getClass().getResource(style.getStyleSheetFileName()).toExternalForm());
        parent.getStylesheets().add(getClass().getResource("JMetroColors.css").toExternalForm());
        parent.setStyle(String.format("accent_color: %s", accent.getColorName()));

        if (accentColor != null) {
            String accentColorFormatted =
                    accentColor.toString().split("x")[1];
            parent.setStyle(String.format("accent_color: %s", accentColorFormatted));
        }

        this.parent = parent;
    }

    /**
     * It method change theme style on scene or parent
     * what initialized in applyTheme method.
     *
     * @param style theme style.
     * @throws NullPointerException Initial reference to scene
     * and parent apparently have null reference.
     */
    public void setTheme(Style style) {
        scene.getStylesheets().removeIf(css -> {
            String[] cssFile = css.split("/");
            return cssFile[cssFile.length - 1].equals("JMetroLightTheme.css");
        });

        scene.getStylesheets().removeIf(css -> {
            String[] cssFile = css.split("/");
            return cssFile[cssFile.length - 1].equals("JMetroDarkTheme.css");
        });

        if (scene != null) {
            scene.getStylesheets().add(getClass().getResource(style.getStyleSheetFileName()).toExternalForm());
            this.style = style;
        } else if (parent != null) {
            parent.getStylesheets().add(getClass().getResource(style.getStyleSheetFileName()).toExternalForm());
            this.style = style;
        } else {
            throw new NullPointerException("Initial reference to scene and parent apparently have null reference.");
        }
    }

    /**
     * @return theme style in current class instance.
     */
    public Style getTheme() { return style; }

    /**
     * It method change accent color on scene or parent
     * what initialized in applyTheme method.
     *
     * @param accent accent color from Accent enum.
     * @throws NullPointerException Initial reference to scene
     * and parent apparently have null reference.
     */
    public void setAccent(Accent accent) {
        if (scene != null) {
            scene.getRoot().setStyle(String.format("accent_color: %s", accent.getColorName()));
            this.accent = accent;
        } else if (parent != null) {
            parent.setStyle(String.format("accent_color: %s", accent.getColorName()));
            this.accent = accent;
        } else {
            throw new NullPointerException("Initial reference to scene and parent apparently have null reference.");
        }
    }

    /**
     * @return accent enum color name in current class instance.
     */
    public Accent getAccent() { return accent; }

    /**
     * It method change accent color on scene or parent
     * what initialized in applyTheme method.
     *
     * @param accent custom accent color.
     * @throws NullPointerException Initial reference to scene
     * and parent apparently have null reference.
     */
    public void setAccent(Color accent) {
        String accentColorFormatted =
                accent.toString().split("x")[1];

        if (scene != null) {
            scene.getRoot().setStyle(String.format("accent_color: %s", accentColorFormatted));
            accentColor = accent;
        } else if (parent != null) {
            parent.setStyle(String.format("accent_color: %s", accentColorFormatted));
            accentColor = accent;
        } else {
            throw new NullPointerException("Initial reference to scene and parent apparently have null reference.");
        }
    }

    /**
     * @return accent color in current class instance.
     */
    public Color getAccentAsColor() { return accentColor; }

    /**
     * It method just validate java version.
     * If java version not equals 1.8 then it
     * method print warning in log.
     */
    private void validateJavaVersion() {
        if (!System.getProperty("java.specification.version").equals("1.8")) {
            Logger log = Logger.getLogger(getClass().getName());
            log.log(Level.WARNING, "for now JMetro only supports Java 8 (with JavaFX 8). You are using Java " + System.getProperty("java.specification.version"));
        }
    }
}

