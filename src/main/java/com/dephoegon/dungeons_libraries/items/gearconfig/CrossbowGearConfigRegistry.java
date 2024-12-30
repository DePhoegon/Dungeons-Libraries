package com.dephoegon.dungeons_libraries.items.gearconfig;

import com.dephoegon.dungeons_libraries.data.util.CodecJsonDataManager;
import com.dephoegon.dungeons_libraries.network.gearconfig.CrossbowGearConfigSyncPacket;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class CrossbowGearConfigRegistry {

    public static final CodecJsonDataManager<BowGearConfig> CROSSBOW_GEAR_CONFIGS = new CodecJsonDataManager<>("gearconfig/crossbow", BowGearConfig.CODEC);


    public static BowGearConfig getConfig(ResourceLocation resourceLocation) {
        return CROSSBOW_GEAR_CONFIGS.getData().getOrDefault(resourceLocation, BowGearConfig.DEFAULT);
    }

    public static boolean gearConfigExists(ResourceLocation resourceLocation) {
        return CROSSBOW_GEAR_CONFIGS.getData().containsKey(resourceLocation);
    }

    public static CrossbowGearConfigSyncPacket toPacket(Map<ResourceLocation, BowGearConfig> map) {
        return new CrossbowGearConfigSyncPacket(map);
    }
}