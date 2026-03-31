package com.mercuri.nohotbarswap;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLE_SWAP_PREVENTION = BUILDER
            .comment("Enable/Disable Hotbar Swap Prevention")
            .define("enableSwapPrevention", true);

    public static final ModConfigSpec SPEC = BUILDER.build();
}
