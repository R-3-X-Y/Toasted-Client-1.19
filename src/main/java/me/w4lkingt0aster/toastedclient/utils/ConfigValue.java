package me.w4lkingt0aster.toastedclient.utils;

import net.minecraft.client.font.TextRenderer;

public abstract class ConfigValue {
    public enum Category {
        Utilities,
        Movement,
        Combat
    }
    private final String configKey;
    public final String displayName;
    private final Category category;
    public String getConfigKey() { return configKey; }
    public Category getCategory() { return category; }
    public abstract Object getValue();
    public abstract void setValue(Object value);
    public abstract void resetValue();
    public abstract Object getWidget(int x, int y, int width, int height, TextRenderer textRenderer);
    public ConfigValue(String configKey, String displayName, Category category) {
        this.configKey = configKey;
        this.displayName = displayName;
        this.category = category;
    }
}
