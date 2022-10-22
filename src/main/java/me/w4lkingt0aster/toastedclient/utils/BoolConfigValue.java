package me.w4lkingt0aster.toastedclient.utils;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class BoolConfigValue extends ConfigValue {

    private boolean value;
    private final boolean defaultValue;

    public BoolConfigValue(String configKey, String displayName, Category category, boolean value) {
        super(configKey, displayName, category);
        this.value = value;
        this.defaultValue = value;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public void setValue(Object value) {
        this.value = (boolean)value;
    }

    private Text getButtonText() {
        if (value) {
            return Text.of("\2472" + value);
        }
        else {
            return Text.of("\2474" + value);
        }

    }

    @Override
    public void resetValue() {
        value = defaultValue;
    }

    @Override
    public Object getWidget(int x, int y, int width, int height, TextRenderer textRenderer) {
        return new ButtonWidget(x, y, width, height, getButtonText(), (button) -> {
            value = !value;
            button.setMessage(getButtonText());
        });
    }
}
