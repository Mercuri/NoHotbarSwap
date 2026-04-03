package com.mercuri.nohotbarswap.neoforge;

public class ConfigImpl {

    public static boolean getEnableSwapPrevention() {
        return NeoForgeConfig.getEnableSwapPrevention();
    }

    public static void setEnableSwapPrevention(boolean value) {
        NeoForgeConfig.setEnableSwapPrevention(value);
    }
}