package com.mercuri.nohotbarswap;

public class NoHotbarSwap {
    public static final String MOD_ID = "nohotbarswap";
    private static boolean allowSwapOverrideActive = false;

    public static boolean shouldCancel(boolean hoveredSlot, boolean hotbarKey) {
        if (!Config.isEnabled()) return false;
        if (!hotbarKey) return false;

        return hoveredSlot && !allowSwapOverrideActive;
    }
    // Overload for “we already know it's a hotbar key + hovered slot”
    public static boolean shouldCancel() {
        boolean enabled = Config.isEnabled();
        boolean override = allowSwapOverrideActive;

        //System.out.println("[NoHotbarSwap] shouldCancel -> enabled=" + enabled + ", override=" + override);

        if (!enabled) return false;
        return !override;
    }


    public static void setOverrideHeld(boolean isActive) {
        //System.out.println("[NoHotbarSwap] overrideHeld set to: " + isActive);
        allowSwapOverrideActive = isActive;
    }

    public static void init() {}

    public static boolean isOverrideActive() {
        return allowSwapOverrideActive;
    }
}