package com.mercuri.nohotbarswap;

import com.mojang.logging.LogUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import org.slf4j.Logger;

@Mod(value = NoHotbarSwapMod.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = NoHotbarSwapMod.MODID, value = Dist.CLIENT)
public class NoHotbarSwapMod {
    public static final String MODID = "nohotbarswap";
    public static final Logger LOGGER = LogUtils.getLogger();

    public NoHotbarSwapMod(ModContainer container) {
        // Register config
        container.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);

        // Register config screen
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        LOGGER.info("NoHotbarSwap Loaded");
    }

    @SubscribeEvent
    public static void onRegisterKeybinds(RegisterKeyMappingsEvent event) {
        event.register(KeyBindings.ALLOW_SWAP);
        event.register(KeyBindings.TOGGLE_MOD);
    }
}