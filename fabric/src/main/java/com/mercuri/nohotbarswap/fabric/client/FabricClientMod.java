package com.mercuri.nohotbarswap.fabric.client;

import com.mercuri.nohotbarswap.Config;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.platform.InputConstants.Type;
import org.lwjgl.glfw.GLFW;

public class FabricClientMod implements ClientModInitializer {

    public static KeyMapping TOGGLE;
    public static KeyMapping HOLD_ALLOW;

    @Override
    public void onInitializeClient() {

        TOGGLE = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.nohotbarswap.toggle",
                GLFW.GLFW_KEY_UNKNOWN,
                "key.categories.nohotbarswap"
        ));

        HOLD_ALLOW = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.nohotbarswap.allow_swap",
                Type.KEYSYM,
                GLFW.GLFW_KEY_LEFT_CONTROL,
                "key.categories.nohotbarswap"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // ✅ Toggle handling
            while (TOGGLE.consumeClick()) {
                Config.toggle(); // persists + flips value

                if (client.player != null) {
                    client.player.displayClientMessage(
                            Component.literal("Hotbar Swap " + (Config.isEnabled() ? "Disabled" : "Enabled")),
                            true
                    );
                }
            }
        });
    }
}