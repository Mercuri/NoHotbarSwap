package com.mercuri.nohotbarswap.fabric;

import com.mercuri.nohotbarswap.NoHotbarSwap;
import net.fabricmc.api.ModInitializer;

public class FabricMod implements ModInitializer {
    @Override
    public void onInitialize() {
        NoHotbarSwap.init();
        ConfigImpl.load();
    }
}