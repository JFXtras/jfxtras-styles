//package jfxtras.styles.jmetro;
//
//import com.sun.javafx.css.StyleManager;
//import com.sun.javafx.css.converters.*;
//import javafx.beans.property.*;
//import javafx.css.*;
//import javafx.geometry.Insets;
//import javafx.geometry.Orientation;
//import javafx.geometry.Pos;
//import javafx.scene.Node;
//import javafx.scene.control.ContentDisplay;
//import javafx.scene.control.Control;
//import javafx.scene.control.OverrunStyle;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import javafx.scene.paint.Color;
//import javafx.scene.paint.Paint;
//import javafx.scene.text.Font;
//import javafx.scene.text.TextAlignment;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
///**
// * Created by pedro_000 on 12/5/13.
// */
//public class ToggleSwitchBase extends Control {
//
//    /***************************************************************************
//     *                                                                         *
//     * Constructors                                                            *
//     *                                                                         *
//     **************************************************************************/
//
//
//
//    public ToggleSwitchBase() {
////        setText(text);
//    }
//
//
//    /***************************************************************************
//     *                                                                         *
//     * Properties                                                              *
//     *                                                                         *
//     **************************************************************************/
//    /**
//     * The text to display in the label. The text may be null.
//     */
//    protected final StringProperty textProperty() {
//        if (text == null) {
//            text = new SimpleStringProperty(this, "text", "");
//        }
//        return text;
//    }
//    private StringProperty text;
//    protected final void setText(String value) { textProperty().setValue(value); }
//    protected final String getText() { return text == null ? "" : text.getValue(); }
//
//    /**
//     * Specifies how the text and graphic within the Labeled should be
//     * aligned when there is empty space within the Labeled.
//     */
//    public final ObjectProperty<Pos> alignmentProperty() {
//        if (alignment == null) {
//            alignment = new StyleableObjectProperty<Pos>(Pos.CENTER_LEFT) {
//
//                @Override public CssMetaData<ToggleSwitchBase,Pos> getCssMetaData() {
//                    return StyleableProperties.ALIGNMENT;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "alignment";
//                }
//            };
//        }
//        return alignment;
//    }
//    private ObjectProperty<Pos> alignment;
//    protected final void setAlignment(Pos value) { alignmentProperty().set(value); }
//    protected final Pos getAlignment() { return alignment == null ? Pos.CENTER_LEFT : alignment.get(); }
//
//
//    /**
//     * Specifies the behavior for lines of text <em>when text is multiline</em>
//     * Unlike {@link #contentDisplayProperty} which affects the graphic and text, this setting
//     * only affects multiple lines of text relative to the text bounds.
//     */
//    public final ObjectProperty<TextAlignment> textAlignmentProperty() {
//        if (textAlignment == null) {
//            textAlignment = new StyleableObjectProperty<TextAlignment>(TextAlignment.LEFT) {
//
//                @Override
//                public CssMetaData<Labeled,TextAlignment> getCssMetaData() {
//                    return StyleableProperties.TEXT_ALIGNMENT;
//                }
//
//                @Override
//                public Object getBean() {
//                    return Labeled.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "textAlignment";
//                }
//            };
//        }
//        return textAlignment;
//    }
//    private ObjectProperty<TextAlignment> textAlignment;
//    public final void setTextAlignment(TextAlignment value) { textAlignmentProperty().setValue(value); }
//    public final TextAlignment getTextAlignment() { return textAlignment == null ? TextAlignment.LEFT : textAlignment.getValue(); }
//
//
//    /**
//     * The default font to use for text in the Labeled. If the Label's text is
//     * rich text then this font may or may not be used depending on the font
//     * information embedded in the rich text, but in any case where a default
//     * font is required, this font will be used.
//     */
//    public final ObjectProperty<Font> fontProperty() {
//
//        if (font == null) {
//            font = new StyleableObjectProperty<Font>(Font.getDefault()) {
//
//                private boolean fontSetByCss = false;
//
//                @Override
//                public void applyStyle(StyleOrigin newOrigin, Font value) {
//
//                    //
//                    // RT-20727 - if CSS is setting the font, then make sure invalidate doesn't call impl_reapplyCSS
//                    //
//                    try {
//                        // super.applyStyle calls set which might throw if value is bound.
//                        // Have to make sure fontSetByCss is reset.
//                        fontSetByCss = true;
//                        super.applyStyle(newOrigin, value);
//                    } catch(Exception e) {
//                        throw e;
//                    } finally {
//                        fontSetByCss = false;
//                    }
//                }
//
//                @Override
//                public void set(Font value) {
//
//                    final Font oldValue = get();
//                    if (value != null ? !value.equals(oldValue) : oldValue != null) {
//                        super.set(value);
//                    }
//
//                }
//
//                @Override
//                protected void invalidated() {
//                    // RT-20727 - if font is changed by calling setFont, then
//                    // css might need to be reapplied since font size affects
//                    // calculated values for styles with relative values
//                    if(fontSetByCss == false) {
//                        ToggleSwitchBase.this.impl_reapplyCSS();
//                    }
//                }
//
//                @Override
//                public CssMetaData<ToggleSwitchBase,Font> getCssMetaData() {
//                    return StyleableProperties.FONT;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "font";
//                }
//            };
//        }
//        return font;
//    }
//    private ObjectProperty<Font> font;
//    public final void setFont(Font value) { fontProperty().setValue(value); }
//    public final Font getFont() { return font == null ? Font.getDefault() : font.getValue(); }
//
//
//    /**
//     * An optional icon for the Labeled. This can be positioned relative to the
//     * text by using {@link #setContentDisplay}.  The node specified for this
//     * variable cannot appear elsewhere in the scene graph, otherwise
//     * the {@code IllegalArgumentException} is thrown.  See the class
//     * description of {@link javafx.scene.Node Node} for more detail.
//     */
//    public final ObjectProperty<Node> graphicProperty() {
//        if (graphic == null) {
//            graphic = new StyleableObjectProperty<Node>() {
//
//                // The graphic is styleable by css, but it is the
//                // imageUrlProperty that handles the style value.
//                @Override
//                public CssMetaData getCssMetaData() {
//                    return StyleableProperties.GRAPHIC;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "graphic";
//                }
//            };
//        }
//        return graphic;
//    }
//    private ObjectProperty<Node> graphic;
//    protected final void setGraphic(Node value) {
//        graphicProperty().setValue(value);
//    }
//    protected final Node getGraphic() { return graphic == null ? null : graphic.getValue(); }
//
//    private StyleableStringProperty imageUrl = null;
//    /**
//     * The imageUrl property is set from CSS and then the graphic property is
//     * set from the invalidated method. This ensures that the same image isn't
//     * reloaded.
//     */
//    private StyleableStringProperty imageUrlProperty() {
//        if (imageUrl == null) {
//            imageUrl = new StyleableStringProperty() {
//
//                //
//                // If imageUrlProperty is invalidated, this is the origin of the style that
//                // triggered the invalidation. This is used in the invaildated() method where the
//                // value of super.getStyleOrigin() is not valid until after the call to set(v) returns,
//                // by which time invalidated will have been called.
//                // This value is initialized to USER in case someone calls set on the imageUrlProperty, which
//                // is possible:
//                //     CssMetaData metaData = ((StyleableProperty)labeled.graphicProperty()).getCssMetaData();
//                //     StyleableProperty prop = metaData.getStyleableProperty(labeled);
//                //     prop.set(someUrl);
//                //
//                // TODO: Note that prop != labeled, which violates the contract between StyleableProperty and CssMetaData.
//                //
//                StyleOrigin origin = StyleOrigin.USER;
//
//                @Override
//                public void applyStyle(StyleOrigin origin, String v) {
//
//                    this.origin = origin;
//
//                    // Don't want applyStyle to throw an exception which would leave this.origin set to the wrong value
//                    if (graphic == null || graphic.isBound() == false) super.applyStyle(origin, v);
//
//                    // Origin is only valid for this invocation of applyStyle, so reset it to USER in case someone calls set.
//                    this.origin = StyleOrigin.USER;
//                }
//
//                @Override
//                protected void invalidated() {
//
//                    // need to call super.get() here since get() is overridden to return the graphicProperty's value
//                    String url = super.get();
//
//                    if (url == null) {
//                        ((StyleableProperty)graphicProperty()).applyStyle(origin, null);
//                    } else {
//
//                        final Image img = StyleManager.getInstance().getCachedImage(url);
//
//                        if (img != null) {
//                            //
//                            // Note that it is tempting to try to re-use existing ImageView simply by setting
//                            // the image on the current ImageView, if there is one. This would effectively change
//                            // the image, but not the ImageView which means that no graphicProperty listeners would
//                            // be notified. This is probably not what we want.
//                            //
//
//                            //
//                            // Have to call applyStyle on graphicProperty so that the graphicProperty's
//                            // origin matches the imageUrlProperty's origin.
//                            //
//                            ((StyleableProperty)graphicProperty()).applyStyle(origin, new ImageView(img));
//                        }
//                    }
//                }
//
//                @Override
//                public String get() {
//
//                    //
//                    // The value of the imageUrlProperty is that of the graphicProperty.
//                    // Return the value in a way that doesn't expand the graphicProperty.
//                    //
//                    final Node graphic = getGraphic();
//                    if (graphic instanceof ImageView) {
//                        final Image image = ((ImageView)graphic).getImage();
//                        if (image != null) {
//                            return image.impl_getUrl();
//                        }
//                    }
//                    return null;
//                }
//
//                @Override
//                public StyleOrigin getStyleOrigin() {
//
//                    //
//                    // The origin of the imageUrlProperty is that of the graphicProperty.
//                    // Return the origin in a way that doesn't expand the graphicProperty.
//                    //
//                    return graphic != null ? ((StyleableProperty)graphic).getStyleOrigin() : null;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "imageUrl";
//                }
//
//                @Override
//                public CssMetaData<ToggleSwitchBase,String> getCssMetaData() {
//                    return StyleableProperties.GRAPHIC;
//                }
//
//            };
//        }
//        return imageUrl;
//    }
//
//    /**
//     * Whether all text should be underlined.
//     */
//    public final BooleanProperty underlineProperty() {
//        if (underline == null) {
//            underline = new StyleableBooleanProperty(false) {
//
//                @Override
//                public CssMetaData<ToggleSwitchBase, Boolean> getCssMetaData() {
//                    return StyleableProperties.UNDERLINE;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "underline";
//                }
//            };
//        }
//        return underline;
//    }
//    private BooleanProperty underline;
//    public final void setUnderline(boolean value) { underlineProperty().setValue(value); }
//    public final boolean isUnderline() { return underline == null ? false : underline.getValue(); }
//
//    /**
//     * Specifies the positioning of the graphic relative to the text.
//     */
//    public final ObjectProperty<ContentDisplay> contentDisplayProperty() {
//        if (contentDisplay == null) {
//            contentDisplay = new StyleableObjectProperty<ContentDisplay>(ContentDisplay.LEFT) {
//
//                @Override
//                public CssMetaData<ToggleSwitchBase,ContentDisplay> getCssMetaData() {
//                    return StyleableProperties.CONTENT_DISPLAY;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "contentDisplay";
//                }
//            };
//        }
//        return contentDisplay;
//    }
//    private ObjectProperty<ContentDisplay> contentDisplay;
//    public final void setContentDisplay(ContentDisplay value) { contentDisplayProperty().setValue(value); }
//    public final ContentDisplay getContentDisplay() { return contentDisplay == null ? ContentDisplay.LEFT : contentDisplay.getValue(); }
//
//    /**
//     * The padding around the Labeled's text and graphic content.
//     * By default labelPadding is Insets.EMPTY and cannot be set to null.
//     * Subclasses may add nodes outside this padding and inside the Labeled's padding.
//     *
//     * This property can only be set from CSS.
//     */
//    public final ReadOnlyObjectProperty<Insets> labelPaddingProperty() {
//        return labelPaddingPropertyImpl();
//    }
//    private ObjectProperty<Insets> labelPaddingPropertyImpl() {
//        if (labelPadding == null) {
//            labelPadding = new StyleableObjectProperty<Insets>(Insets.EMPTY) {
//                private Insets lastValidValue = Insets.EMPTY;
//
//                @Override
//                public void invalidated() {
//                    final Insets newValue = get();
//                    if (newValue == null) {
//                        set(lastValidValue);
//                        throw new NullPointerException("cannot set labelPadding to null");
//                    }
//                    lastValidValue = newValue;
//                    requestLayout();
//                }
//
//                @Override
//                public CssMetaData<ToggleSwitchBase,Insets> getCssMetaData() {
//                    return StyleableProperties.LABEL_PADDING;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "labelPadding";
//                }
//            };
//        }
//        return labelPadding;
//    }
//    private ObjectProperty<Insets> labelPadding;
//    private void setLabelPadding(Insets value) { labelPaddingPropertyImpl().set(value); }
//    public final Insets getLabelPadding() { return labelPadding == null ? Insets.EMPTY : labelPadding.get(); }
//
//    /**
//     * The amount of space between the graphic and text
//     */
//    public final DoubleProperty graphicTextGapProperty() {
//        if (graphicTextGap == null) {
//            graphicTextGap = new StyleableDoubleProperty(4) {
//
//                @Override
//                public CssMetaData getCssMetaData() {
//                    return StyleableProperties.GRAPHIC_TEXT_GAP;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "graphicTextGap";
//                }
//            };
//        }
//        return graphicTextGap;
//    }
//    private DoubleProperty graphicTextGap;
//    public final void setGraphicTextGap(double value) { graphicTextGapProperty().setValue(value); }
//    public final double getGraphicTextGap() { return graphicTextGap == null ? 4 : graphicTextGap.getValue(); }
//
//
//    /**
//     * The {@link javafx.scene.paint.Paint} used to fill the text.
//     */
//    private ObjectProperty<Paint> textFill; // TODO for now change this
//
//    public final void setTextFill(Paint value) {
//        textFillProperty().set(value);
//    }
//
//    public final Paint getTextFill() {
//        return textFill == null ? Color.BLACK : textFill.get();
//    }
//
//    public final ObjectProperty<Paint> textFillProperty() {
//        if (textFill == null) {
//            textFill = new StyleableObjectProperty<Paint>(Color.BLACK) {
//
//                @Override
//                public CssMetaData<ToggleSwitchBase,Paint> getCssMetaData() {
//                    return StyleableProperties.TEXT_FILL;
//                }
//
//                @Override
//                public Object getBean() {
//                    return ToggleSwitchBase.this;
//                }
//
//                @Override
//                public String getName() {
//                    return "textFill";
//                }
//            };
//        }
//        return textFill;
//    }
//
//    @Override public String toString() {
//        StringBuilder builder =
//                new StringBuilder(super.toString())
//                        .append("'").append(getText()).append("'");
//        return builder.toString();
//    }
//
//    /***************************************************************************
//     *                                                                         *
//     * Stylesheet Handling                                                     *
//     *                                                                         *
//     **************************************************************************/
//
//    /**
//     * Not everything uses the default value of false for alignment.
//     * This method provides a way to have them return the correct initial value.
//     * @treatAsPrivate implementation detail
//     */
//    @Deprecated
//    protected Pos impl_cssGetAlignmentInitialValue() {
//        return Pos.CENTER_LEFT;
//    }
//
//    /**
//     * @treatAsPrivate implementation detail
//     */
//    private static class StyleableProperties {
//        private static final FontCssMetaData<ToggleSwitchBase> FONT =
//                new FontCssMetaData<ToggleSwitchBase>("-fx-font", Font.getDefault()) {
//
//                    @Override
//                    public boolean isSettable(ToggleSwitchBase n) {
//                        return n.font == null || !n.font.isBound();
//                    }
//
//                    @Override
//                    public StyleableProperty<Font> getStyleableProperty(ToggleSwitchBase n) {
//                        return (StyleableProperty)n.fontProperty();
//                    }
//                };
//
//        private static final CssMetaData<ToggleSwitchBase,Paint> TEXT_FILL =
//                new CssMetaData<ToggleSwitchBase,Paint>("-fx-text-fill",
//                        PaintConverter.getInstance(), Color.BLACK) {
//
//                    @Override
//                    public boolean isSettable(ToggleSwitchBase n) {
//                        return n.textFill == null || !n.textFill.isBound();
//                    }
//
//                    @Override
//                    public StyleableProperty<Paint> getStyleableProperty(ToggleSwitchBase n) {
//                        return (StyleableProperty)n.textFillProperty();
//                    }
//                };
//
//        private static final CssMetaData<ToggleSwitchBase,String> GRAPHIC =
//                new CssMetaData<ToggleSwitchBase,String>("-fx-graphic",
//                        StringConverter.getInstance()) {
//
//                    @Override
//                    public boolean isSettable(ToggleSwitchBase n) {
//                        // Note that we care about the graphic, not imageUrl
//                        return n.graphic == null || !n.graphic.isBound();
//                    }
//
//                    @Override
//                    public StyleableProperty<String> getStyleableProperty(ToggleSwitchBase n) {
//                        return n.imageUrlProperty();
//                    }
//                };
//
//        private static final CssMetaData<ToggleSwitchBase,Boolean> UNDERLINE =
//                new CssMetaData<ToggleSwitchBase,Boolean>("-fx-underline",
//                        BooleanConverter.getInstance(), Boolean.FALSE) {
//
//                    @Override
//                    public boolean isSettable(ToggleSwitchBase n) {
//                        return n.underline == null || !n.underline.isBound();
//                    }
//
//                    @Override
//                    public StyleableProperty<Boolean> getStyleableProperty(ToggleSwitchBase n) {
//                        return (StyleableProperty)n.underlineProperty();
//                    }
//                };
//
//        private static final CssMetaData<Labeled,Insets> LABEL_PADDING =
//                new CssMetaData<Labeled,Insets>("-fx-label-padding",
//                        InsetsConverter.getInstance(), Insets.EMPTY) {
//
//                    @Override
//                    public boolean isSettable(Labeled n) {
//                        return n.labelPadding == null || !n.labelPadding.isBound();
//                    }
//
//                    @Override
//                    public StyleableProperty<Insets> getStyleableProperty(Labeled n) {
//                        return (StyleableProperty)n.labelPaddingPropertyImpl();
//                    }
//                };
//
//        private static final List<CssMetaData<? extends Styleable, ?>> STYLEABLES;
//        static {
//            final List<CssMetaData<? extends Styleable, ?>> styleables =
//                    new ArrayList<CssMetaData<? extends Styleable, ?>>(Control.getClassCssMetaData());
//            Collections.addAll(styleables,
//                    FONT,
//                    ALIGNMENT,
//                    TEXT_ALIGNMENT,
//                    TEXT_FILL,
//                    GRAPHIC,
//                    UNDERLINE,
//                    CONTENT_DISPLAY,
//                    LABEL_PADDING,
//                    GRAPHIC_TEXT_GAP
//            );
//            STYLEABLES = Collections.unmodifiableList(styleables);
//        }
//    }
//
//    /**
//     * @return The CssMetaData associated with this class, which may include the
//     * CssMetaData of its super classes.
//     * @since JavaFX 8.0
//     */
//    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
//        return StyleableProperties.STYLEABLES;
//    }
//
//    /**
//     * {@inheritDoc}
//     * @since JavaFX 8.0
//     */
//    @Override
//    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
//        return getClassCssMetaData();
//    }
//
//}
//
