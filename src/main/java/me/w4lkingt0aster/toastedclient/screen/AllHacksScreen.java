package me.w4lkingt0aster.toastedclient.screen;

import me.w4lkingt0aster.toastedclient.config.ToastedClientConfig;
import me.w4lkingt0aster.toastedclient.screen.widgets.DoubleFieldWidget;
import me.w4lkingt0aster.toastedclient.screen.widgets.IntegerFieldWidget;
import me.w4lkingt0aster.toastedclient.utils.BoolConfigValue;
import me.w4lkingt0aster.toastedclient.utils.ConfigValue;
import me.w4lkingt0aster.toastedclient.utils.DoubleConfigValue;
import me.w4lkingt0aster.toastedclient.utils.IntConfigValue;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class AllHacksScreen extends Screen {

    private final Screen parent;
    private final GameOptions settings;

    public AllHacksScreen(Screen parent, GameOptions gameOptions) {
        super(Text.of("Toasted Client"));
        this.parent = parent;
        this.settings = gameOptions;
    }

    protected void init() {
        int i = 0;
        int xZero = 0;
        int xMax = this.width / 2;
        int height = 20;
        int spacing = 10;
        for (ConfigValue value : ToastedClientConfig.getValues()) {
            if (i * (height + spacing) + spacing > this.height - 60) {
                xZero = this.width / 2;
                xMax = this.width;
                i = 0;
            }
            if (value instanceof BoolConfigValue) {
                this.addDrawableChild((ButtonWidget)value.getWidget(xMax - 120, i * (height + spacing) + spacing, 100, 20, textRenderer));
            }
            else if (value instanceof IntConfigValue) {
                this.addDrawableChild((IntegerFieldWidget)value.getWidget(xMax - 120, i * (height + spacing) + spacing, 100, 20, textRenderer));
            }
            else if (value instanceof DoubleConfigValue) {
                this.addDrawableChild((DoubleFieldWidget)value.getWidget(xMax - 120, i * (height + spacing) + spacing, 100, 20, textRenderer));
            }
            i++;
        }
        this.addDrawableChild(new ButtonWidget(this.width / 2 - 100, this.height - 40, 200, 20, ScreenTexts.DONE, (button) -> {
            if (this.client != null) this.client.setScreen(this.parent);
            ToastedClientConfig.writeConfigFile();
        }));

    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackgroundTexture(1);
        int i = 0;
        int xZero = 0;
        int xMax = this.width / 2;
        int height = 20;
        int spacing = 10;
        for (ConfigValue value : ToastedClientConfig.getValues()) {
            if (i * (height + spacing) + spacing > this.height - 60) {
                xZero = this.width / 2;
                xMax = this.width;
                i = 0;
            }
            textRenderer.draw(new MatrixStack(), value.displayName, xZero + 20, i * (height + spacing) + spacing + 10, 0xffffffff);
            i++;
        }

        super.render(matrices, mouseX, mouseY, delta);
    }
}
