package com.mercuri.nohotbarswap.fabric;

import com.mercuri.nohotbarswap.Config;
import me.shedaniel.clothconfig2.api.*;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigScreen {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Component.literal("No Hotbar Swap"));

        ConfigCategory general = builder.getOrCreateCategory(Component.literal("General"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        general.addEntry(entryBuilder.startBooleanToggle(
                Component.literal("Enable Swap Prevention"),
                Config.isEnabled()
        ).setSaveConsumer(Config::setEnableSwapPrevention).build());

        return builder.build();
    }
}