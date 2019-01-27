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

    private Style style;
    private Boolean isDark = false;

    /**
     * Base JMetro constructor. With default accent color argument.
     *
     * @param style base style.
     */
    public JMetro(Style style) {
        this.style = style;
        validateJavaVersion();
    }

    /**
     * It method apply theme and accent colors on your scene.
     *
     * @param scene your reference on window scene.
     */
    public void applyTheme(Scene scene) {
        scene.getStylesheets().add(JMetro.class.getResource(style.getStyleSheetFileName()).toExternalForm());
        this.isDark = style.getStyleSheetFileName().equals("JMetroDarkTheme.css");
    }

    /**
     * It method apply theme and accent colors on your parent.
     *
     * @param parent your reference on window parent.
     */
    public void applyTheme(Parent parent) {
        parent.getStylesheets().add(JMetro.class.getResource(style.getStyleSheetFileName()).toExternalForm());
        this.isDark = style.getStyleSheetFileName().equals("JMetroDarkTheme.css");
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

