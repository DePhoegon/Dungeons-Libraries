package com.dephoegon.dungeons_libraries.combat;

import com.dephoegon.dungeons_libraries.DungeonsLibraries;
import com.dephoegon.dungeons_libraries.config.DungeonsLibrariesConfig;
import com.dephoegon.dungeons_libraries.items.gearconfig.MeleeGearConfig;
import com.dephoegon.dungeons_libraries.items.gearconfig.MeleeGearConfigRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = DungeonsLibraries.MODID)
public class DualWieldHandler {
    public static void switchHand(ServerPlayer player) {
        if (!DungeonsLibrariesConfig.ENABLE_DUAL_WIELDING.get()) return;
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offHandItem = player.getOffhandItem();
        MeleeGearConfig config = MeleeGearConfigRegistry.getConfig(ForgeRegistries.ITEMS.getKey(mainHandItem.getItem()));
        if (config.isLight() && !offHandItem.isEmpty() && offHandItem.getItem() instanceof TieredItem) {
            player.setItemInHand(InteractionHand.MAIN_HAND, player.getOffhandItem());
            player.setItemInHand(InteractionHand.OFF_HAND, mainHandItem);
        }
    }
}
