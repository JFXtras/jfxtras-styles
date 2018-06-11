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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class SpinnerController implements Initializable
{
    @FXML private Spinner spinner1;
    @FXML private Spinner spinner2;
    @FXML private Spinner spinner3;
    @FXML private Spinner spinner4;
    @FXML private Spinner spinner5;
    @FXML private Spinner spinner6;
    @FXML private Spinner disabledSpinner;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        spinner1.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        spinner2.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        spinner3.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        spinner4.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        spinner5.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        spinner6.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));

        spinner6.getEditor().setAlignment(Pos.CENTER);
        disabledSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
    }
}
