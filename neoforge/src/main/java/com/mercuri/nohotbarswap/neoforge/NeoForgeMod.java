package com.mercuri.nohotbarswap.neoforge;

import com.mercuri.nohotbarswap.Config;
import com.mercuri.nohotbarswap.NoHotbarSwap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.minecraft.client.KeyMapping;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.lwjgl.glfw.GLFW;

@Mod(NoHotbarSwap.MOD_ID)
@EventBusSubscriber(modid = NoHotbarSwap.MOD_ID, value = Dist.CLIENT)
public class NeoForgeMod {

    private static final KeyMapping TOGGLE = new KeyMapping(
            "key.nohotbarswap.toggle",
            GLFW.GLFW_KEY_UNKNOWN,
            "key.categories.nohotbarswap"
    );

    private static final KeyMapping HOLD_ALLOW = new KeyMapping(
            "key.nohotbarswap.allow_swap",
            GLFW.GLFW_KEY_LEFT_CONTROL,
            "key.categories.nohotbarswap"
    );

    public NeoForgeMod(ModContainer container) {
        NoHotbarSwap.init();

        // Register config
        container.registerConfig(ModConfig.Type.CLIENT, NeoForgeConfig.SPEC);
        // Register config screen
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE);
        event.register(HOLD_ALLOW);
    }

    @SubscribeEvent
    public static void onKey(InputEvent.Key event) {
        if (event.getKey() == HOLD_ALLOW.getKey().getValue()) {
            NoHotbarSwap.setOverrideHeld(event.getAction() != GLFW.GLFW_RELEASE);
        }

        if (TOGGLE.consumeClick()) {
            Config.toggle();
            boolean newValue = Config.isEnabled();

            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.displayClientMessage(
                        Component.literal("Hotbar Swap " + (newValue ? "Disabled" : "Enabled")),
                        true
                );
            }
        }
    }

    @SubscribeEvent
    public static void onScreen(ScreenEvent.KeyPressed.Pre event) {
        if (!(event.getScreen() instanceof AbstractContainerScreen<?> screen)) return;

        int key = event.getKeyCode();

        if (key >= GLFW.GLFW_KEY_1 && key <= GLFW.GLFW_KEY_9) {
            Slot hovered = screen.getSlotUnderMouse();

            if (NoHotbarSwap.shouldCancel(hovered != null, true)) {
                event.setCanceled(true);
            }
        }
    }
}