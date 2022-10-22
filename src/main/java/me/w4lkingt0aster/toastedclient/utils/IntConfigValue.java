package me.w4lkingt0aster.toastedclient.utils;

import me.w4lkingt0aster.toastedclient.ToastedClient;
import me.w4lkingt0aster.toastedclient.screen.widgets.IntegerFieldWidget;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

public class IntConfigValue extends ConfigValue{
    private int value;
    private final int defaultValue;

    public IntConfigValue(String configKey, String displayName, Category category,int value) {
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
        this.value = (int)value;
    }

    @Override
    public void resetValue() {
        value = defaultValue;
    }

    private Text getFieldText() {
        return Text.of(String.valueOf(value));
    }

    @Override
    public Object getWidget(int x, int y, int width, int height, TextRenderer textRenderer) {
        IntegerFieldWidget widget = new IntegerFieldWidget(textRenderer, x, y, width, height, getFieldText());
        widget.setText(String.valueOf(value));
        widget.setChangedListener(s -> {
            try {
                if (!s.isBlank()) value = Integer.parseInt(s);
            }
            catch (NumberFormatException e) {
                ToastedClient.LOGGER.error("Unable to convert string into int.");
                e.printStackTrace();
            }
        });
        return widget;
    }
}
