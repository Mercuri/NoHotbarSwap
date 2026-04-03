package com.mercuri.nohotbarswap.fabric.mixin;

import com.mercuri.nohotbarswap.NoHotbarSwap;
import com.mercuri.nohotbarswap.fabric.client.FabricClientMod;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.Slot;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractContainerScreen.class)
public abstract class HandledScreenMixin {

    @Shadow
    protected Slot hoveredSlot;

    @Inject(method = "keyPressed", at = @At("HEAD"), cancellable = true)
    private void noHotbarSwap(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {

        //Check for modifier
        var key = KeyBindingHelper.getBoundKeyOf(FabricClientMod.HOLD_ALLOW);
        long window = net.minecraft.client.Minecraft.getInstance().getWindow().getWindow();
        boolean overrideHeld = InputConstants.isKeyDown(window, key.getValue());
        //Update common logic
        NoHotbarSwap.setOverrideHeld(overrideHeld);

        // Only care about number keys 1–9
        if (keyCode < GLFW.GLFW_KEY_1 || keyCode > GLFW.GLFW_KEY_9) {
            return;
        }

        if (this.hoveredSlot == null) {
            return;
        }

        if (NoHotbarSwap.shouldCancel()) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}