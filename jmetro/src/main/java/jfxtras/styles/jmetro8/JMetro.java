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
     * and parent apparently have null reference.
     */
    public void setStyle(Style style) {
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
        }
    }

    /**
     * @return theme style in current class instance.
     */
    public Style getStyle() { return style; }

    /**
     * It method change accent color on scene or parent
     * what initialized in applyTheme method.
     *
     * @param accent accent color from Accent enum.
     * and parent apparently have null reference.
     */
    public void setKnownAccent(Accent accent) {
        if (scene != null) {
            scene.getRoot().setStyle(String.format("accent_color: %s", accent.getColorName()));
            this.accent = accent;
        } else if (parent != null) {
            parent.setStyle(String.format("accent_color: %s", accent.getColorName()));
            this.accent = accent;
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
     * and parent apparently have null reference.
     */
    public void setAccent(Color accent) {
        String accentColorFormatted =
                accent.toString().split("x")[1];

        if (!accentColorFormatted.contains("#")) {
            accentColorFormatted = "#" + accentColorFormatted;
        }

        if (scene != null) {
            scene.getRoot().setStyle(String.format("accent_color: %s", accentColorFormatted));
            accentColor = accent;
        } else if (parent != null) {
            parent.setStyle(String.format("accent_color: %s", accentColorFormatted));
            accentColor = accent;
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

