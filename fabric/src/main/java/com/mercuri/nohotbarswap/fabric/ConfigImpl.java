package com.mercuri.nohotbarswap.fabric;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigImpl {
    private static final Path PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("nohotbarswap.json");

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static boolean enableSwapPrevention = true;

    public static boolean getEnableSwapPrevention() {
        return enableSwapPrevention;
    }

    public static void setEnableSwapPrevention(boolean value) {
        enableSwapPrevention = value;
        save();
    }

    public static void load() {
        if (Files.exists(PATH)) {
            try {
                var json = Files.readString(PATH);
                var data = GSON.fromJson(json, Data.class);
                enableSwapPrevention = data.enableSwapPrevention;
            } catch (Exception ignored) {}
        } else {
            save();
        }
    }

    private static void save() {
        try {
            var data = new Data();
            data.enableSwapPrevention = enableSwapPrevention;
            Files.writeString(PATH, GSON.toJson(data));
        } catch (IOException ignored) {}
    }

    private static class Data {
        boolean enableSwapPrevention = true;
    }
}