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

import com.sun.javafx.css.converters.BooleanConverter;
import com.sun.javafx.scene.control.skin.ToggleButtonSkin;
import javafx.beans.property.BooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.scene.control.SkinBase;
import javafx.scene.control.ToggleButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FluentToggleButtonSkin extends ToggleButtonSkin {
    private ButtonAnimationHelper buttonAnimationHelper;

    public FluentToggleButtonSkin(ToggleButton button) {
        super(button);

        buttonAnimationHelper = ButtonAnimationHelper.setupButton(button, shrinkAnimateOnPressProperty());
    }

    /********** CSS Properties ****************/

    private static final CssMetaData<ToggleButton, Boolean> SHRINK_ANIMATE_ON_PRESS_META_DATA =
            new CssMetaData<ToggleButton, Boolean>(ButtonAnimationHelper.SHRINK_ANIMATE_ON_PRESS_PROPERTY_NAME,
                    BooleanConverter.getInstance(), true) {

                @Override
                public boolean isSettable(ToggleButton button) {
                    final FluentToggleButtonSkin skin = (FluentToggleButtonSkin) button.getSkin();
                    return !skin.shrinkAnimateOnPress.isBound();
                }

                @Override
                public StyleableProperty<Boolean> getStyleableProperty(ToggleButton button) {
                    final FluentToggleButtonSkin skin = (FluentToggleButtonSkin) button.getSkin();
                    return (StyleableProperty<Boolean>) skin.shrinkAnimateOnPressProperty();
                }
            };

    private BooleanProperty shrinkAnimateOnPress = new SimpleStyleableBooleanProperty(SHRINK_ANIMATE_ON_PRESS_META_DATA, true);

    private BooleanProperty shrinkAnimateOnPressProperty() { return shrinkAnimateOnPress; }

    private boolean isShrinkAnimateOnPress() { return shrinkAnimateOnPress.get(); }


    /* Setup styleables for this Skin */
    private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    static {
        final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<>(SkinBase.getClassCssMetaData());
        styleables.add(SHRINK_ANIMATE_ON_PRESS_META_DATA);
        STYLEABLES = Collections.unmodifiableList(styleables);
    }

    /**
     * @return The CssMetaData associated with this class, which may include the
     * CssMetaData of its super classes.
     */
    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return STYLEABLES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CssMetaData<? extends Styleable, ?>> getCssMetaData() {
        return getClassCssMetaData();
    }

    @Override
    public void dispose() {
        buttonAnimationHelper.dispose();

        super.dispose();
    }
}
