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
import java.util.Random;

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
        BONDI_BLUE_COLOR,
        BURNT_SIENNA_COLOR,
        CRIMSON_COLOR,
        CUTTY_SARK_COLOR,
        DARK_BLUE_COLOR,
        DARK_CYAN_COLOR,
        DARK_GRAY_COLOR,
        DARK_MAGENTA_COLOR,
        DARK_MINT_COLOR,
        DARK_ORANGE_COLOR,
        DEEP_LILAC_COLOR,
        FLAMINGO_COLOR,
        FROWNING_COLOR,
        FUCHSIA_COLOR,
        GOLDEN_ORANGE_COLOR,
        GOLDEN_YELLOW_COLOR,
        GRAY_BROWN_COLOR,
        GRAY_COLOR,
        GREEN_COLOR,
        GREEN_GRAY_COLOR,
        GREEN_LIDDY_COLOR,
        GREEN_SHADOW_COLOR,
        HURRICANE_COLOR,
        IRIS_BLUE_COLOR,
        JADE_COLOR,
        LIGHT_GREEN_COLOR,
        LIGHT_MINT_COLOR,
        LIGHT_ORANGE_COLOR,
        LIGHT_PALE_VIOLET_COLOR,
        LIGHT_PINK_COLOR,
        LILAC_BUSH_COLOR,
        MINERAL_GREEN_COLOR,
        NAVY_BLUE_COLOR,
        NEVADA_COLOR,
        PALE_VIOLET_COLOR,
        PERSIAN_RED_COLOR,
        PINK_COLOR,
        PORTAGE_COLOR,
        RED_COLOR,
        RUST_ORANGE_COLOR,
        SALEM_COLOR,
        SCOOTER_COLOR,
        SEANCE_COLOR,
        SHUTTLE_GRAY_COLOR,
        SLATE_BLUE_COLOR,
        SLATE_GRAY_COLOR,
        STONEWALL_COLOR,
        STUDIO_COLOR;

        private String getStyleSheetFileName() {
            String stylesheet = null;

            switch (this) {
                case GOLDEN_YELLOW_COLOR:
                    stylesheet = "JMetroGoldenYellow.css";
                    break;
                case GOLDEN_ORANGE_COLOR:
                    stylesheet = "JMetroGoldenOrange.css";
                    break;
                case LIGHT_ORANGE_COLOR:
                    stylesheet = "JMetroLightOrange.css";
                    break;
                case DARK_ORANGE_COLOR:
                    stylesheet = "JMetroDarkOrange.css";
                    break;
                case RUST_ORANGE_COLOR:
                    stylesheet = "JMetroRustOrange.css";
                    break;
                case BURNT_SIENNA_COLOR:
                    stylesheet = "JMetroBurntSienna.css";
                    break;
                case PERSIAN_RED_COLOR:
                    stylesheet = "JMetroPersianRed.css";
                    break;
                case RED_COLOR:
                    stylesheet = "JMetroRed.css";
                    break;
                case FLAMINGO_COLOR:
                    stylesheet = "JMetroFlamingo.css";
                    break;
                case CRIMSON_COLOR:
                    stylesheet = "JMetroCrimson.css";
                    break;
                case LIGHT_PINK_COLOR:
                    stylesheet = "JMetroLightPink.css";
                    break;
                case PINK_COLOR:
                    stylesheet = "JMetroPink.css";
                    break;
                case LIGHT_PALE_VIOLET_COLOR:
                    stylesheet = "JMetroLightPaleViolet.css";
                    break;
                case PALE_VIOLET_COLOR:
                    stylesheet = "JMetroPaleViolet.css";
                    break;
                case FUCHSIA_COLOR:
                    stylesheet = "JMetroFuchsia.css";
                    break;
                case DARK_MAGENTA_COLOR:
                    stylesheet = "JMetroDarkMagenta.css";
                    break;
                case NAVY_BLUE_COLOR:
                    stylesheet = "JMetroNavyBlue.css";
                    break;
                case DARK_BLUE_COLOR:
                    stylesheet = "JMetroDarkBlue.css";
                    break;
                case PORTAGE_COLOR:
                    stylesheet = "JMetroPortage.css";
                    break;
                case SLATE_BLUE_COLOR:
                    stylesheet = "JMetroSlateBlue.css";
                    break;
                case LILAC_BUSH_COLOR:
                    stylesheet = "JMetroLilacBush.css";
                    break;
                case STUDIO_COLOR:
                    stylesheet = "JMetroStudio.css";
                    break;
                case DEEP_LILAC_COLOR:
                    stylesheet = "JMetroDeepLilac.css";
                    break;
                case SEANCE_COLOR:
                    stylesheet = "JMetroSeance.css";
                    break;
                case BONDI_BLUE_COLOR:
                    stylesheet = "JMetroBondiBlue.css";
                    break;
                case SCOOTER_COLOR:
                    stylesheet = "JMetroScooter.css";
                    break;
                case IRIS_BLUE_COLOR:
                    stylesheet = "JMetroIrisBlue.css";
                    break;
                case DARK_CYAN_COLOR:
                    stylesheet = "JMetroDarkCyan.css";
                    break;
                case LIGHT_MINT_COLOR:
                    stylesheet = "JMetroLightMint.css";
                    break;
                case DARK_MINT_COLOR:
                    stylesheet = "JMetroDarkMint.css";
                    break;
                case JADE_COLOR:
                    stylesheet = "JMetroJade.css";
                    break;
                case SALEM_COLOR:
                    stylesheet = "JMetroSalem.css";
                    break;
                case GRAY_COLOR:
                    stylesheet = "JMetroGray.css";
                    break;
                case GRAY_BROWN_COLOR:
                    stylesheet = "JMetroGrayBrown.css";
                    break;
                case SLATE_GRAY_COLOR:
                    stylesheet = "JMetroSlateGray.css";
                    break;
                case SHUTTLE_GRAY_COLOR:
                    stylesheet = "JMetroShuttleGray.css";
                    break;
                case CUTTY_SARK_COLOR:
                    stylesheet = "JMetroCuttySark.css";
                    break;
                case MINERAL_GREEN_COLOR:
                    stylesheet = "JMetroMineralGreen.css";
                    break;
                case LIGHT_GREEN_COLOR:
                    stylesheet = "JMetroLightGreen.css";
                    break;
                case GREEN_COLOR:
                    stylesheet = "JMetroGreen.css";
                    break;
                case FROWNING_COLOR:
                    stylesheet = "JMetroFrowning.css";
                    break;
                case HURRICANE_COLOR:
                    stylesheet = "JMetroHurricane.css";
                    break;
                case NEVADA_COLOR:
                    stylesheet = "JMetroNevada.css";
                    break;
                case DARK_GRAY_COLOR:
                    stylesheet = "JMetroDarkGray.css";
                    break;
                case GREEN_LIDDY_COLOR:
                    stylesheet = "JMetroGreenLiddy.css";
                    break;
                case GREEN_GRAY_COLOR:
                    stylesheet = "JMetroGreenGray.css";
                    break;
                case GREEN_SHADOW_COLOR:
                    stylesheet = "JMetroGreenShadow.css";
                    break;
                case STONEWALL_COLOR:
                    stylesheet = "JMetroStonewall.css";
                    break;
            }

            return stylesheet;
        }
    }

    private Style style;
    private Accent accent;
    private Scene scene;
    private Parent parent;
    private Boolean isDark = false;
    private Random random = new Random();

    /**
     * Base JMetro constructor. With default accent color argument.
     *
     * @param style base style.
     */
    public JMetro(Style style) {
        this.style = style;
        this.accent = Accent.NAVY_BLUE_COLOR;
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
        scene.getStylesheets().add(JMetro.class.getResource(style.getStyleSheetFileName()).toExternalForm());
        scene.getStylesheets().add(JMetro.class.getResource("themes/" + accent.getStyleSheetFileName()).toExternalForm());
        this.isDark = style.getStyleSheetFileName().equals("JMetroDarkTheme.css");
        this.scene = scene;
    }

    /**
     * It method apply theme and accent colors on your parent.
     *
     * @param parent your reference on window parent.
     */
    public void applyTheme(Parent parent) {
        parent.getStylesheets().add(JMetro.class.getResource(style.getStyleSheetFileName()).toExternalForm());
        parent.getStylesheets().add(JMetro.class.getResource("themes/" + accent.getStyleSheetFileName()).toExternalForm());
        this.isDark = style.getStyleSheetFileName().equals("JMetroDarkTheme.css");
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
            this.scene.getStylesheets().add(JMetro.class.getResource(style.getStyleSheetFileName()).toExternalForm());
            this.scene.getStylesheets().add(JMetro.class.getResource("themes/" + accent.getStyleSheetFileName()).toExternalForm());
            this.isDark = style.getStyleSheetFileName().equals("JMetroDarkTheme.css");
        } else if (this.parent != null) {
            this.parent.getStylesheets().clear();
            this.parent.getStylesheets().add(JMetro.class.getResource(style.getStyleSheetFileName()).toExternalForm());
            this.parent.getStylesheets().add(JMetro.class.getResource("themes/" + accent.getStyleSheetFileName()).toExternalForm());
            this.isDark = style.getStyleSheetFileName().equals("JMetroDarkTheme.css");
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
     * If java version not equals 1.8 then it method print warning in console.
     */
    private void validateJavaVersion() {
        if (!System.getProperty("java.specification.version").equals("1.8")) {
            System.out.println("WARNING! For work JMetro lib needed JRE version 1.8.X, you have " + System.getProperty("java.specification.version"));
        }
    }
}

