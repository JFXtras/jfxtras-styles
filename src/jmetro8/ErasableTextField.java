package jfxtras.styles.jmetro;


import com.sun.javafx.scene.control.skin.ToggleButtonSkin;
import javafx.scene.control.Skin;
import javafx.scene.control.TextField;

/**
 * Created by pedro_000 on 12/5/13.
 */
public class ErasableTextField extends TextField {

    @Override protected Skin<?> createDefaultSkin() {
        return new ErasableTextFieldSkin(this);
    }
}
