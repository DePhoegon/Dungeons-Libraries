package com.dephoegon.dungeons_libraries.capabilities.timers;

import com.dephoegon.dungeons_libraries.DungeonsLibraries;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DungeonsLibraries.MODID)
public class TimerEvents {

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        Timers timersCapability = TimersHelper.getTimersCapability(event.getEntity());
        timersCapability.tickTimers();
    }

    @SubscribeEvent
    public static void onPlayerUpdate(TickEvent.PlayerTickEvent event) {
        Timers timersCapability = TimersHelper.getTimersCapability(event.player);
        if (event.phase == TickEvent.Phase.START && !event.player.isSpectator() && !event.player.level.isClientSide()) {
            timersCapability.tickTimers();
        }
    }
}
