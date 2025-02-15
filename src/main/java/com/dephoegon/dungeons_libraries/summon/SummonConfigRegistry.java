package com.dephoegon.dungeons_libraries.summon;

import com.dephoegon.dungeons_libraries.data.util.CodecJsonDataManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.dephoegon.dungeons_libraries.DungeonsLibraries.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SummonConfigRegistry {
    public static final ResourceLocation SUMMON_RESOURCELOCATION = new ResourceLocation(MODID, "summon");

    public static final CodecJsonDataManager<SummonConfig> SUMMON_CONFIGS = new CodecJsonDataManager<>("summon", SummonConfig.CODEC);


    public static SummonConfig getConfig(ResourceLocation resourceLocation) {
        return SUMMON_CONFIGS.getData().getOrDefault(resourceLocation, SummonConfig.DEFAULT);
    }

    public static boolean gearConfigExists(ResourceLocation resourceLocation) {
        return SUMMON_CONFIGS.getData().containsKey(resourceLocation);
    }

    @SubscribeEvent
    public static void onAddReloadListeners(AddReloadListenerEvent event) {
        event.addListener(SUMMON_CONFIGS);
    }
}