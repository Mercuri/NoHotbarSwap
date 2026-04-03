package com.mercuri.nohotbarswap.neoforge;

import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_SWAP_PREVENTION = BUILDER
            .define("enableSwapPrevention", true);

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean getEnableSwapPrevention() {
        return NeoForgeConfig.ENABLE_SWAP_PREVENTION.get();
    }

    public static void setEnableSwapPrevention(boolean value) {
        ENABLE_SWAP_PREVENTION.set(value);
    }
}