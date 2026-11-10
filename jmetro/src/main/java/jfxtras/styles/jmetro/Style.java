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

/**
 * The Windows design generation and color scheme used by JMetro.
 */
public enum Style {
    /** Windows 10-inspired light color scheme. */
    WIN10_LIGHT("light_theme.css"),
    /** Windows 10-inspired dark color scheme. */
    WIN10_DARK("dark_theme.css"),
    /** Windows 11 / WinUI 3-inspired light color scheme. */
    WIN11_LIGHT("win11_light.css"),
    /** Windows 11 / WinUI 3-inspired dark color scheme. */
    WIN11_DARK("win11_dark.css");

    private final String stylesheetURL;

    Style(String stylesheet) {
        stylesheetURL = Style.class.getResource(stylesheet).toExternalForm();
    }

    /**
     * Returns the URL of the stylesheet defining this style's palette and overrides.
     *
     * @return the stylesheet URL as an external-form string
     */
    public String getStyleStylesheetURL() {
        return stylesheetURL;
    }
}
