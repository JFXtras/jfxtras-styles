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

import static javafx.geometry.Orientation.HORIZONTAL;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.Styleable;
import javafx.css.StyleableProperty;
import javafx.css.converter.BooleanConverter;
import javafx.geometry.Orientation;
import javafx.geometry.Point2D;
import javafx.scene.control.SkinBase;
import javafx.scene.control.Slider;
import javafx.scene.layout.Region;
import javafx.util.Duration;

public class SliderSkin extends javafx.scene.control.skin.SliderSkin {
	
	private Region thumb;
	private Region track;

	private SliderPopup popup = new SliderPopup();
	private static final int POPUP_DISTANCE_FROM_THUMB = 50;
	private static final Duration POPUP_FADE_DURATION = Duration.millis(200);

	public SliderSkin(Slider slider) {
		super(slider);

		thumb = (Region) slider.lookup(".thumb");
		track = (Region) slider.lookup(".track");
		changeTrackBackground(null, slider);
		
		slider.setBlockIncrement(POPUP_DISTANCE_FROM_THUMB);
		slider.setShowTickMarks((slider.isShowTickLabels() || slider.isShowTickLabels()));
		
		slider.valueProperty().addListener(listener -> changeTrackBackground(listener, slider));
		slider.setOnMouseDragged(event -> this.displaceValuePopup());
		slider.setOnMouseEntered(event -> this.showValuePopup());
		slider.setOnMouseExited(event -> this.hideValuePopup());
	}
	
	/*
	 * Changes the track background with a linear-gradient and the orientation of slider.
	 */
	private void changeTrackBackground(Observable listener, Slider slider) {
		String trackBackgroundColor = 
				"-fx-background-color: linear-gradient({0}, {1} 0%, {1} {2}%, {3} {2}%, {3} 100%)";
		String orientation = slider.getOrientation() == HORIZONTAL ? "to right" : "to top";
		String accentColor = "#0078d7";
		String trackBackground = "#c6c6c6";
		int sliderValue = (int) (slider.getMax() * slider.getValue() / 100);

		String backgroundColor = MessageFormat.format(trackBackgroundColor, 
				orientation, accentColor, sliderValue, trackBackground);
		track.setStyle(backgroundColor);
	}

	private void showValuePopup() {
		if (!isShowValueOnInteraction()) {
			return;
		}

		Slider slider = getSkinnable();
		popup.setValue(slider.getValue());

		Point2D thumbScreenPos = thumb.localToScreen(thumb.getBoundsInLocal().getMinX(),
				thumb.getBoundsInLocal().getMinY());

		Orientation orientation = getSkinnable().getOrientation();
		if (orientation.equals(Orientation.HORIZONTAL)) {
			popup.show(thumb, thumbScreenPos.getX() + thumb.getWidth() / 2,
					thumbScreenPos.getY() - POPUP_DISTANCE_FROM_THUMB);
			popup.setX(popup.getX() - popup.getWidth() / 2); /* We only know the Popup's bounds after we show it */
		} else if (orientation.equals(Orientation.VERTICAL)) {
			popup.show(thumb, thumbScreenPos.getX() - POPUP_DISTANCE_FROM_THUMB,
					thumbScreenPos.getY() + thumb.getHeight() / 2);
			popup.setY(popup.getY() - popup.getHeight() / 2); /* We only know the Popup's bounds after we show it */
		}

		FadeTransition fadeInTransition = new FadeTransition(POPUP_FADE_DURATION, popup.getScene().getRoot());
		fadeInTransition.setFromValue(0);
		fadeInTransition.setToValue(1);
		fadeInTransition.play();
	}

	private void displaceValuePopup() {
		if (!isShowValueOnInteraction()) {
			return;
		}

		Slider slider = getSkinnable();
		if (popup.isShowing()) {
			popup.setValue(slider.getValue());

			Point2D thumbScreenPos = thumb.localToScreen(thumb.getBoundsInLocal().getMinX(),
					thumb.getBoundsInLocal().getMinY());			
			
			Orientation orientation = getSkinnable().getOrientation();
			if (orientation.equals(Orientation.HORIZONTAL)) {
				popup.setX(thumbScreenPos.getX() + thumb.getWidth() / 2 - popup.getWidth() / 2);
			} else if (orientation.equals(Orientation.VERTICAL)) {
				popup.setY(thumbScreenPos.getY() + thumb.getHeight() / 2 - popup.getHeight() / 2);
			}
		}
	}

	private void hideValuePopup() {
		if (!isShowValueOnInteraction()) {
			return;
		}

		FadeTransition fadeOutTransition = new FadeTransition(POPUP_FADE_DURATION, popup.getScene().getRoot());
		fadeOutTransition.setFromValue(1);
		fadeOutTransition.setToValue(0);
		fadeOutTransition.setOnFinished(actionEvent -> popup.hide());

		fadeOutTransition.play();
	}

	/********** CSS Properties ****************/

	private static final CssMetaData<Slider, Boolean> SHOW_VALUE_ON_INTERACTION_META_DATA = new CssMetaData<Slider, Boolean>(
			"-show-value-on-interaction", BooleanConverter.getInstance(), true) {

		@Override
		public boolean isSettable(Slider slider) {
			final SliderSkin skin = (SliderSkin) slider.getSkin();
			return !skin.showValueOnInteraction.isBound();
		}

		@Override
		@SuppressWarnings("unchecked")
		public StyleableProperty<Boolean> getStyleableProperty(Slider slider) {
			final SliderSkin skin = (SliderSkin) slider.getSkin();
			return (StyleableProperty<Boolean>) skin.showValueOnInteractionProperty();
		}
	};

	private BooleanProperty showValueOnInteraction = new SimpleStyleableBooleanProperty(
			SHOW_VALUE_ON_INTERACTION_META_DATA, true);

	private BooleanProperty showValueOnInteractionProperty() {
		return showValueOnInteraction;
	}

	private boolean isShowValueOnInteraction() {
		return showValueOnInteraction.get();
	}

	/* Setup styleables for this Skin */
	private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

	static {
		final List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(SkinBase.getClassCssMetaData());
		styleables.add(SHOW_VALUE_ON_INTERACTION_META_DATA);
		STYLEABLES = Collections.unmodifiableList(styleables);
	}

	/**
	 * @return The CssMetaData associated with this class, which may include the
	 *         CssMetaData of its super classes.
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

}
