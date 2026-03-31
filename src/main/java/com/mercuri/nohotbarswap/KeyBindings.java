package com.mercuri.nohotbarswap;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {
    public static final String CATEGORY = "key.categories.nohotbarswap";

    public static final KeyMapping TOGGLE_MOD = new KeyMapping(
            "key.nohotbarswap.toggle",
            GLFW.GLFW_KEY_UNKNOWN, // unbound by default
            CATEGORY
    );
    
    public static final KeyMapping ALLOW_SWAP = new KeyMapping(
            "key.nohotbarswap.allow_swap",
            GLFW.GLFW_KEY_LEFT_CONTROL, // default key
            CATEGORY
    );
}