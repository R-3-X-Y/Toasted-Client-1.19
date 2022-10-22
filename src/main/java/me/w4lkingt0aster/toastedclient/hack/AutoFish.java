package me.w4lkingt0aster.toastedclient.hack;

import me.w4lkingt0aster.toastedclient.config.ToastedClientConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;

public class AutoFish {
    private int recastRod = -1;
    public void tick(MinecraftClient client) {
        if (!(boolean)ToastedClientConfig.get("AUTO_FISH_ENABLE").getValue()) {
            recastRod = -1;
        }
        if (recastRod > 0) {
            if (client.player.fishHook != null) {
                recastRod = -1;
            }
            recastRod--;
        }
        else if (recastRod == 0) {
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND);
            recastRod = -1;
        }
    }

    public void setRecastRod(int countdown) {
        recastRod = countdown;
    }
}
