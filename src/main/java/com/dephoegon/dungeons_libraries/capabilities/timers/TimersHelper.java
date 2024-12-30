package com.dephoegon.dungeons_libraries.capabilities.timers;

import net.minecraft.world.entity.Entity;

import static com.dephoegon.dungeons_libraries.capabilities.ModCapabilities.TIMERS_CAPABILITY;


public class TimersHelper {

    public static Timers getTimersCapability(Entity entity) {
        return entity.getCapability(TIMERS_CAPABILITY).orElse(new Timers());
    }
}
