package com.dephoegon.dungeons_libraries;

import com.dephoegon.dungeons_libraries.attribute.AttributeRegistry;
import com.dephoegon.dungeons_libraries.capabilities.ModCapabilities;
import com.dephoegon.dungeons_libraries.client.gui.elementconfig.GuiElementConfigRegistry;
import com.dephoegon.dungeons_libraries.config.DungeonsLibrariesConfig;
import com.dephoegon.dungeons_libraries.items.ItemTagWrappers;
import com.dephoegon.dungeons_libraries.items.RangedItemModelProperties;
import com.dephoegon.dungeons_libraries.items.artifacts.config.ArtifactGearConfigRegistry;
import com.dephoegon.dungeons_libraries.items.gearconfig.ArmorGearConfigRegistry;
import com.dephoegon.dungeons_libraries.items.gearconfig.BowGearConfigRegistry;
import com.dephoegon.dungeons_libraries.items.gearconfig.CrossbowGearConfigRegistry;
import com.dephoegon.dungeons_libraries.items.gearconfig.MeleeGearConfigRegistry;
import com.dephoegon.dungeons_libraries.items.materials.armor.DungeonsArmorMaterials;
import com.dephoegon.dungeons_libraries.items.materials.weapon.WeaponMaterials;
import com.dephoegon.dungeons_libraries.network.NetworkHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.dephoegon.dungeons_libraries.entities.ModEntityTypes.ENTITY_TYPES;
import static com.dephoegon.dungeons_libraries.items.artifacts.config.ArtifactGearConfigRegistry.ARTIFACT_GEAR_CONFIGS;
import static com.dephoegon.dungeons_libraries.items.gearconfig.ArmorGearConfigRegistry.ARMOR_GEAR_CONFIGS;
import static com.dephoegon.dungeons_libraries.items.gearconfig.BowGearConfigRegistry.BOW_GEAR_CONFIGS;
import static com.dephoegon.dungeons_libraries.items.gearconfig.CrossbowGearConfigRegistry.CROSSBOW_GEAR_CONFIGS;
import static com.dephoegon.dungeons_libraries.items.gearconfig.MeleeGearConfigRegistry.MELEE_GEAR_CONFIGS;
import static com.dephoegon.dungeons_libraries.items.materials.armor.DungeonsArmorMaterials.ARMOR_MATERIALS;
import static com.dephoegon.dungeons_libraries.items.materials.weapon.WeaponMaterials.WEAPON_MATERIALS;


@Mod("dungeons_libraries")
public class DungeonsLibraries {
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "dungeons_libraries";

    public DungeonsLibraries() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> {
            return GuiElementConfigRegistry::initGuiElementConfigs;
        });
        // Register the setup method for modloading
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, DungeonsLibrariesConfig.COMMON_SPEC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ItemTagWrappers.init();
        AttributeRegistry.ATTRIBUTES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        DungeonsArmorMaterials.setupVanillaMaterials();
        WeaponMaterials.setupVanillaMaterials();

        ARMOR_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, ArmorGearConfigRegistry::toPacket);
        MELEE_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, MeleeGearConfigRegistry::toPacket);
        BOW_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, BowGearConfigRegistry::toPacket);
        CROSSBOW_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, CrossbowGearConfigRegistry::toPacket);
        WEAPON_MATERIALS.subscribeAsSyncable(NetworkHandler.INSTANCE, WeaponMaterials::toPacket);
        ARMOR_MATERIALS.subscribeAsSyncable(NetworkHandler.INSTANCE, DungeonsArmorMaterials::toPacket);
        ARTIFACT_GEAR_CONFIGS.subscribeAsSyncable(NetworkHandler.INSTANCE, ArtifactGearConfigRegistry::toPacket);

        ModCapabilities.setupCapabilities();
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(NetworkHandler::init);
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        event.enqueueWork(RangedItemModelProperties::init);
    }

}
