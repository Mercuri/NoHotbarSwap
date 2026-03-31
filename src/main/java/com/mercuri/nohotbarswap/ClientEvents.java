package com.mercuri.nohotbarswap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.bus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = NoHotbarSwapMod.MODID, value = Dist.CLIENT)
public class ClientEvents {
    private static boolean allowSwapHeld = false;

    @SubscribeEvent
    public static void onKeyPress(ScreenEvent.KeyPressed.Pre event) {
        if (!Config.ENABLE_SWAP_PREVENTION.get()) return;

        if (!(event.getScreen() instanceof AbstractContainerScreen<?> screen)) return;

        int key = event.getKeyCode();

        // Number keys 1–9
        if (key >= GLFW.GLFW_KEY_1 && key <= GLFW.GLFW_KEY_9) {
            Slot hovered = screen.getSlotUnderMouse();

            if (hovered != null) {
                if (!allowSwapHeld) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (event.getKey() == KeyBindings.ALLOW_SWAP.getKey().getValue()) {
            allowSwapHeld = event.getAction() != GLFW.GLFW_RELEASE;
        }

        if (KeyBindings.TOGGLE_MOD.consumeClick()) {
            boolean newValue = !Config.ENABLE_SWAP_PREVENTION.get();
            Config.ENABLE_SWAP_PREVENTION.set(newValue);
            Config.SPEC.save();

            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.displayClientMessage(
                        Component.literal("Hotbar Swap " + (newValue ? "Disabled" : "Enabled")),
                        true // <- action bar (above hotbar)
                );
            }

            NoHotbarSwapMod.LOGGER.debug("Hotbar swap prevention: {}", newValue ? "ENABLED" : "DISABLED");
        }
    }
}
