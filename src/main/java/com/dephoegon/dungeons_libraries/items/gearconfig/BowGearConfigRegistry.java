package com.dephoegon.dungeons_libraries.items.gearconfig;

import com.dephoegon.dungeons_libraries.data.util.CodecJsonDataManager;
import com.dephoegon.dungeons_libraries.network.gearconfig.BowGearConfigSyncPacket;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class BowGearConfigRegistry {

    public static final CodecJsonDataManager<BowGearConfig> BOW_GEAR_CONFIGS = new CodecJsonDataManager<>("gearconfig/bow", BowGearConfig.CODEC);


    public static BowGearConfig getConfig(ResourceLocation resourceLocation) {
        return BOW_GEAR_CONFIGS.getData().getOrDefault(resourceLocation, BowGearConfig.DEFAULT);
    }

    public static boolean gearConfigExists(ResourceLocation resourceLocation) {
        return BOW_GEAR_CONFIGS.getData().containsKey(resourceLocation);
    }

    public static BowGearConfigSyncPacket toPacket(Map<ResourceLocation, BowGearConfig> map) {
        return new BowGearConfigSyncPacket(map);
    }
}