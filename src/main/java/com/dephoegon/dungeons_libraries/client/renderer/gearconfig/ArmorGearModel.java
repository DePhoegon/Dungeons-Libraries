package com.dephoegon.dungeons_libraries.client.renderer.gearconfig;

import com.dephoegon.dungeons_libraries.items.gearconfig.ArmorGear;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ArmorGearModel<T extends ArmorGear> extends AnimatedGeoModel<T> {
    @Override
    public ResourceLocation getModelResource(ArmorGear object) {
        return object.getModelLocation();
    }

    @Override
    public ResourceLocation getTextureResource(ArmorGear object) {
        return object.getTextureLocation();
    }

    @Override
    public ResourceLocation getAnimationResource(ArmorGear object) {
        return object.getAnimationFileLocation();
    }
}
