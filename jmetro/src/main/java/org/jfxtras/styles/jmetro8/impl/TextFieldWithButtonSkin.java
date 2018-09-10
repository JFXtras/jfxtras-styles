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

package org.jfxtras.styles.jmetro8.impl;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.TextFieldSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class TextFieldWithButtonSkin extends TextFieldSkin {
    private StackPane rightButton;
    private Region rightButtonGraphic;

    protected TextField textField;

    public TextFieldWithButtonSkin(TextField textField) {
        super(textField);

        this.textField = textField;

        rightButton = new StackPane();
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

    private void setupListeners() {

        final TextField textField = getSkinnable();
        rightButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rightButtonPressed();
            }
        });
        rightButton.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                rightButtonReleased();
            }
        });

        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textChanged();
            }
        });
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                focusChanged();
            }
        });
    }

    protected void textChanged()
    {
        if (textField.getText() == null)
            return;

        rightButton.setVisible(!textField.getText().isEmpty());
        rightButtonGraphic.setVisible(!textField.getText().isEmpty());
    }

    protected void focusChanged()
    {
        if (textField.getText() == null)
            return;

        rightButton.setVisible(textField.isFocused() && !textField.getText().isEmpty());
        rightButtonGraphic.setVisible(textField.isFocused() && !textField.getText().isEmpty());
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

	protected void rightButtonPressed() {
	}

    protected void rightButtonReleased() {
    	textField.setText("");
    }
}
