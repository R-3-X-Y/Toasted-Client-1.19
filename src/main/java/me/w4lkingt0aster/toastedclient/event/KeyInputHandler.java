package me.w4lkingt0aster.toastedclient.event;

import me.w4lkingt0aster.toastedclient.screen.AllHacksScreen;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public KeyBinding menuKey;

    public void tick(MinecraftClient client) {
        if (menuKey.isPressed()) {
            client.setScreen(new AllHacksScreen(null, client.options));
        }
    }

    public KeyInputHandler() {
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.toastedclient.menu",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_RIGHT_SHIFT,
                "category.toastedclient.all"
        ));
    }
}
