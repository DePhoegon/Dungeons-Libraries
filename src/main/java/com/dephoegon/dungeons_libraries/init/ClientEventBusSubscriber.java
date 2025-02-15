package com.dephoegon.dungeons_libraries.init;

import com.dephoegon.dungeons_libraries.client.renderer.SoulOrbRenderer;
import com.dephoegon.dungeons_libraries.client.renderer.gearconfig.ArmorGearRenderer;
import com.dephoegon.dungeons_libraries.entities.ModEntityTypes;
import com.dephoegon.dungeons_libraries.items.gearconfig.ArmorGear;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

import static com.dephoegon.dungeons_libraries.DungeonsLibraries.MODID;
import static net.minecraftforge.api.distmarker.Dist.CLIENT;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = CLIENT)
public class ClientEventBusSubscriber {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntityTypes.SOUL_ORB.get(), SoulOrbRenderer::new);
    }

    @SubscribeEvent
    public static void registerArmorRenderers(final EntityRenderersEvent.AddLayers event) {
        GeoArmorRenderer.registerArmorRenderer(ArmorGear.class, ArmorGearRenderer::new);
    }
}
