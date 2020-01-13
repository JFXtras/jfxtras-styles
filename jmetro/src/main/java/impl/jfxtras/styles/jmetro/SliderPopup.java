/*
 * Copyright (c) 2011-2020 JFXtras
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

package impl.jfxtras.styles.jmetro;

import javafx.beans.binding.StringBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PopupControl;
import javafx.scene.control.Skin;

class SliderPopup extends PopupControl {
    private static final String DEFAULT_STYLE_CLASS = "slider-popup";



    SliderPopup() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);

    }


    private DoubleProperty value = new SimpleDoubleProperty();

    DoubleProperty valueProperty() { return value; }

    double getValue() { return value.get(); }

    void setValue(double value) {
        this.value.set(value);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new SliderPopupSkin(this);
    }

    private class SliderPopupSkin implements Skin<SliderPopup> {

        SliderPopup skinnable;
        Label valueText;

        /**
         * Constructor for all SkinBase instances.
         *
         * @param control The control for which this Skin should attach to.
         */
        SliderPopupSkin(SliderPopup control) {
            skinnable = control;
            valueText = new Label();

            valueText.textProperty().bind(new StringBinding() {
                {
                    super.bind(skinnable.valueProperty());
                }

                @Override
                protected String computeValue() {
                    return String.valueOf(Math.round(skinnable.getValue()));
                }
            });
        }

        @Override
        public SliderPopup getSkinnable() {
            return skinnable;
        }

        @Override
        public Node getNode() {
            return valueText;
        }

        @Override
        public void dispose() {

        }
    }
}
