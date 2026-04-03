package com.mercuri.nohotbarswap;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class Config {

    public static boolean isEnabled() {
        return getEnableSwapPrevention();
    }

    @ExpectPlatform
    public static boolean getEnableSwapPrevention() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void setEnableSwapPrevention(boolean value) {
        throw new AssertionError();
    }

    public static void toggle() {
        setEnableSwapPrevention(!isEnabled());
    }
}
