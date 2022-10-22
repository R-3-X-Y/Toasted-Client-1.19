package me.w4lkingt0aster.toastedclient.mixin;

import me.w4lkingt0aster.toastedclient.ToastedClientClient;
import me.w4lkingt0aster.toastedclient.config.ToastedClientConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {

    @Shadow private boolean caughtFish;

    @Inject(method = "onTrackedDataSet", at = @At("TAIL"))
    public void onTrackedDataSet(TrackedData<?> data, CallbackInfo ci) {

        MinecraftClient client = MinecraftClient.getInstance();

        if (caughtFish && (boolean)ToastedClientConfig.get("AUTO_FISH_ENABLE").getValue() && client.interactionManager != null) {
            client.interactionManager.interactItem(client.player, Hand.MAIN_HAND); // Reel in bobber
            ToastedClientClient.instance.autoFish.setRecastRod((int)ToastedClientConfig.get("AUTO_FISH_RECAST_DELAY").getValue()); // Set recast delay
        }
    }
}
