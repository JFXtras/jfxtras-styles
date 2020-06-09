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

package impl.jfxtras.styles.jmetro;

import javafx.beans.InvalidationListener;
import javafx.beans.property.BooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.converter.BooleanConverter;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.SkinBase;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TextFieldWithButtonSkin extends TextFieldSkin{
    private static final String RIGHT_BUTTON_VISIBLE_PROPERTY_NAME = "-right-button-visible";

    private InvalidationListener textChanged = observable -> onTextChanged();
    private InvalidationListener focusChanged = observable -> onFocusChanged();
    private InvalidationListener rightButtonVisibleChanged = observable -> onRightButtonVisibilityChanged();

    private StackPane rightButton;
    private Region rightButtonGraphic;

    protected TextField textField;

    /**************************************************************************
     *                                                                        *
     *  Constructor                                                           *
     *                                                                        *
     *************************************************************************/

    public TextFieldWithButtonSkin(TextField textField) {
        super(textField);

        this.textField = textField;

        rightButton = new StackPane();
        rightButton.setManaged(false);
        rightButton.getStyleClass().setAll("right-button");
        rightButton.setFocusTraversable(false);

        rightButtonGraphic = new Region();
        rightButtonGraphic.getStyleClass().setAll("right-button-graphic");
        rightButtonGraphic.setFocusTraversable(false);

        rightButtonGraphic.setMaxWidth(Region.USE_PREF_SIZE);
        rightButtonGraphic.setMaxHeight(Region.USE_PREF_SIZE);

        rightButton.setVisible(false);
        rightButtonGraphic.setVisible(false);

        rightButton.getChildren().add(rightButtonGraphic);
        getChildren().add(rightButton);

        setupListeners();
    }

    /**************************************************************************
     *                                                                        *
     *  CSS                                                                   *
     *                                                                        *
     *************************************************************************/

    /*====================== Right Button Visible ===========================*/

    private static final CssMetaData<TextField, Boolean> RIGHT_BUTTON_VISIBLE_META_DATA =
            new CssMetaData<TextField, Boolean>(RIGHT_BUTTON_VISIBLE_PROPERTY_NAME,
                    BooleanConverter.getInstance(), true) {

                @Override
                public boolean isSettable(TextField textField) {
                    final TextFieldWithButtonSkin skin = (TextFieldWithButtonSkin) textField.getSkin();
                    return !skin.rightButtonVisible.isBound();
                }

                @Override
                public StyleableProperty<Boolean> getStyleableProperty(TextField textField) {
                    final TextFieldWithButtonSkin skin = (TextFieldWithButtonSkin) textField.getSkin();
                    return (StyleableProperty<Boolean>) skin.rightButtonVisibleProperty();
                }
            };

    private BooleanProperty rightButtonVisible = new SimpleStyleableBooleanProperty(RIGHT_BUTTON_VISIBLE_META_DATA, true);

    private BooleanProperty rightButtonVisibleProperty() { return rightButtonVisible; }

    private boolean getRightButtonVisible() { return rightButtonVisible.get(); }


    /*=================== General handling of CSS ===========================*/

    /* Setup styleables for this Skin */
    private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    static {
        final List<CssMetaData<? extends Styleable, ?>> styleables =
                new ArrayList<>(SkinBase.getClassCssMetaData());
        styleables.add(RIGHT_BUTTON_VISIBLE_META_DATA);
        styleables.addAll(TextFieldSkin.getClassCssMetaData());
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

    /**************************************************************************
     *                                                                        *
     *  Other methods                                                         *
     *                                                                        *
     *************************************************************************/

    private void setupListeners() {
        final TextField textField = getSkinnable();
        rightButton.setOnMousePressed(event -> onRightButtonPressed());
        rightButton.setOnMouseReleased(event -> onRightButtonReleased());

        textField.textProperty().addListener(textChanged);
        textField.focusedProperty().addListener(focusChanged);
        rightButtonVisible.addListener(rightButtonVisibleChanged);
    }

    protected void onTextChanged()
    {
        updateRightButtonVisibility();
    }

    protected void onFocusChanged()
    {
        updateRightButtonVisibility();
    }

    protected void onRightButtonVisibilityChanged() {
        updateRightButtonVisibility();
    }

    private void updateRightButtonVisibility() {
        if (textField.getText() == null) {
            return;
        }

        boolean hasFocus = textField.isFocused();
        boolean isEmpty = textField.getText().isEmpty();
        boolean isRightButtonVisible = rightButtonVisible.get();

        boolean shouldBeVisible = isRightButtonVisible && hasFocus && !isEmpty;

        rightButton.setVisible(shouldBeVisible);
        rightButtonGraphic.setVisible(shouldBeVisible);
    }

    protected void onRightButtonPressed()
    {
    }

    protected void onRightButtonReleased()
    {

    }

    @Override
    protected void layoutChildren(double x, double y, double w, double h) {
        super.layoutChildren(x, y, w, h);

        final double clearGraphicWidth = snapSizeX(rightButtonGraphic.prefWidth(-1));
        final double clearButtonWidth = rightButton.snappedLeftInset() + clearGraphicWidth + rightButton.snappedRightInset();

        rightButton.resize(clearButtonWidth, h);
        positionInArea(rightButton,
                (x+w) - clearButtonWidth, y,
                clearButtonWidth, h, 0, HPos.CENTER, VPos.CENTER);
    }

    @Override
    public void dispose() {
        textField.textProperty().removeListener(textChanged);
        textField.focusedProperty().removeListener(focusChanged);
        rightButtonVisible.removeListener(rightButtonVisibleChanged);

        super.dispose();
    }
}
