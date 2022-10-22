package me.w4lkingt0aster.toastedclient;

import me.w4lkingt0aster.toastedclient.event.KeyInputHandler;
import me.w4lkingt0aster.toastedclient.hack.AutoFish;
import me.w4lkingt0aster.toastedclient.hack.Fly;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class ToastedClientClient implements ClientModInitializer {
    public static ToastedClientClient instance;
    public KeyInputHandler keyInputHandler;
    public AutoFish autoFish;
    public Fly fly;
    @Override
    public void onInitializeClient() {

        if (instance == null) instance = this;

        this.keyInputHandler = new KeyInputHandler();
        this.autoFish = new AutoFish();
        this.fly = new Fly();

        ClientTickEvents.END_CLIENT_TICK.register(this::tick);

    }

    public void tick(MinecraftClient client) { //To at the end of every client game tick
        autoFish.tick(client); // Run the AutoFish tick
        fly.tick(client);
        keyInputHandler.tick(client);
    }
}
