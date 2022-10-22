package me.w4lkingt0aster.toastedclient.mixin;

import me.w4lkingt0aster.toastedclient.screen.AllHacksScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin extends Screen {
    protected TitleScreenMixin(Text text) { super(text); }

    @Inject(at = @At("HEAD"), method = "initWidgetsNormal")
    private void initWidgetsNormal(CallbackInfo ci) {
        this.addDrawableChild(new ButtonWidget(10, 10, 90, 20, Text.of("Toasted Client"), (button) -> {
            if (client != null) this.client.setScreen(new AllHacksScreen(this, this.client.options));
        }));
    }
}
