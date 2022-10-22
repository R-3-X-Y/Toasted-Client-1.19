package me.w4lkingt0aster.toastedclient.utils;

import me.w4lkingt0aster.toastedclient.ToastedClient;
import me.w4lkingt0aster.toastedclient.screen.widgets.DoubleFieldWidget;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.text.Text;

public class DoubleConfigValue extends ConfigValue{
    private double value;
    private final double defaultValue;

    public DoubleConfigValue(String configKey, String displayName, Category category, double value) {
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
        this.value = (double)value;
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
        DoubleFieldWidget widget = new DoubleFieldWidget(textRenderer, x, y, width, height, getFieldText());
        widget.setText(String.valueOf(value));
        widget.setChangedListener(s -> {
            try {
                value = Double.parseDouble(s);
            }
            catch (NumberFormatException e) {
                ToastedClient.LOGGER.error("Unable to convert string into double.");
                e.printStackTrace();
            }
        });
        return widget;
    }
}
