package me.w4lkingt0aster.toastedclient.screen.widgets;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class IntegerFieldWidget extends TextFieldWidget {
    public IntegerFieldWidget(TextRenderer textRenderer, int x, int y, int width, int height, Text text) {
        super(textRenderer, x, y, width, height, text);
    }

    @Override
    public boolean charTyped(char chr, int modifiers) {
        if (!(chr == '1' || chr == '2' || chr == '3' || chr == '4' || chr == '5' || chr == '6' || chr == '7' || chr == '8' || chr == '9' || chr == '0')) {
            return false;
        }
        return super.charTyped(chr, modifiers);
    }
}
