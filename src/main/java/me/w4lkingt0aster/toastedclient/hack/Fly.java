package me.w4lkingt0aster.toastedclient.hack;

import me.w4lkingt0aster.toastedclient.ToastedClient;
import me.w4lkingt0aster.toastedclient.config.ToastedClientConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

public class Fly {
    int doubleSpaceCheck = 0;
    boolean spaceBefore = false;
    boolean isFlying = false;
    int toggle = 0;
    int MAX_SPEED = 3;
    double FALL_SPEED = -0.04;
    double acceleration = 0.1;

    public void tick(MinecraftClient client) {
        if (client.player != null && (boolean)ToastedClientConfig.get("FLY_HACK_ENABLE").getValue()) {

            boolean jumpPressed = client.options.jumpKey.isPressed();
            boolean forwardPressed = client.options.forwardKey.isPressed();
            boolean leftPressed = client.options.leftKey.isPressed();
            boolean rightPressed = client.options.rightKey.isPressed();
            boolean backPressed = client.options.backKey.isPressed();
            boolean sneakPressed = client.options.sneakKey.isPressed();
            boolean sprinting = client.player.isSprinting();

            if (!spaceBefore && jumpPressed) {
                if (doubleSpaceCheck > 0) {
                    isFlying = !isFlying;
                    doubleSpaceCheck = 0;
                }
                else {
                    doubleSpaceCheck = 5;
                }
            }
            spaceBefore = jumpPressed;
            if (doubleSpaceCheck > 0) {
                doubleSpaceCheck--;
            }

            if (client.player.hasVehicle() || !isFlying || client.player.isCreative() || client.player.isSpectator()) {
                return;
            }

            Vec3d newVelocity = new Vec3d(0, 0, 0);
            double yaw = Math.toRadians(-client.player.getYaw());
            double deltaX = 0;
            double deltaZ = 0;

            if (jumpPressed && !sneakPressed) {
                newVelocity = new Vec3d(newVelocity.x, 7.5d / 20d, newVelocity.z);
            }
            if (sneakPressed && !jumpPressed) {
                newVelocity = new Vec3d(newVelocity.x, -7.5d / 20d, newVelocity.z);
                if (client.player.isOnGround()) {
                    isFlying = false;
                }
            }

            if (forwardPressed) {
                deltaZ += 1;
            }
            if (leftPressed) {
                deltaX += 1;
            }
            if (rightPressed) {
                deltaX -= 1;
            }
            if (backPressed) {
                deltaZ -= 1;
            }
            double totalYaw = yaw + Math.atan2(deltaX, deltaZ);
            if (forwardPressed || leftPressed || rightPressed || backPressed) {
                newVelocity = new Vec3d(Math.sin(totalYaw), newVelocity.y, Math.cos(totalYaw));
            }
            else {
                newVelocity = new Vec3d(0, newVelocity.y, 0);
            }

            double y = newVelocity.y;
            newVelocity = newVelocity.multiply((((sprinting) ? 21.6d : 10.92d) / 20) * (double)ToastedClientConfig.get("FLY_HACK_SPEED_MULTIPLIER").getValue());
            if (toggle == 0) {
                y = FALL_SPEED;
                ToastedClient.LOGGER.info("Fall");
            }
            else if (toggle == 1 && y == 0) {
                y = -FALL_SPEED;
                ToastedClient.LOGGER.info("Up");
            }
            newVelocity = new Vec3d(newVelocity.x, y, newVelocity.z);

            client.player.setVelocity(newVelocity);

            if (forwardPressed || leftPressed || rightPressed || backPressed) {
                if (acceleration < MAX_SPEED) {
                    acceleration += 0.1;
                }
            }
            else if (acceleration > 0.2) {
                acceleration -= 0.2;
            }

            if (toggle == 0 || newVelocity.y <= -0.04) {
                toggle = 40;
            }
            toggle--;
        }
    }
}
