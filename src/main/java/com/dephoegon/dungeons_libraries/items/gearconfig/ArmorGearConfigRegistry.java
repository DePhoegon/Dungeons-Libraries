package com.dephoegon.dungeons_libraries.items.gearconfig;

import com.dephoegon.dungeons_libraries.data.util.CodecJsonDataManager;
import com.dephoegon.dungeons_libraries.network.gearconfig.ArmorGearConfigSyncPacket;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

import static com.dephoegon.dungeons_libraries.DungeonsLibraries.MODID;

public class ArmorGearConfigRegistry {
    public static final ResourceLocation GEAR_CONFIG_BUILTIN_RESOURCELOCATION = new ResourceLocation(MODID, "gear_config");

    public static final CodecJsonDataManager<ArmorGearConfig> ARMOR_GEAR_CONFIGS = new CodecJsonDataManager<>("gearconfig/armor", ArmorGearConfig.CODEC);


    public static ArmorGearConfig getConfig(ResourceLocation resourceLocation) {
        return ARMOR_GEAR_CONFIGS.getData().getOrDefault(resourceLocation, ArmorGearConfig.DEFAULT);
    }

    public static boolean gearConfigExists(ResourceLocation resourceLocation) {
        return ARMOR_GEAR_CONFIGS.getData().containsKey(resourceLocation);
    }

    public static ArmorGearConfigSyncPacket toPacket(Map<ResourceLocation, ArmorGearConfig> map) {
        return new ArmorGearConfigSyncPacket(map);
    }
}