package com.infamous.dungeons_libraries.capabilities.elite;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

import static com.infamous.dungeons_libraries.capabilities.ModCapabilities.ELITE_MOB_CAPABILITY;

public class EliteMobHelper {

    @Nullable
    public static EliteMob getEliteMobCapability(Entity entity)
    {
        LazyOptional<EliteMob> lazyCap = entity.getCapability(ELITE_MOB_CAPABILITY);
        if (lazyCap.isPresent()) {
            return lazyCap.orElseThrow(() -> new IllegalStateException("Couldn't get the combo capability from the Entity!"));
        }
        return null;
    }
}