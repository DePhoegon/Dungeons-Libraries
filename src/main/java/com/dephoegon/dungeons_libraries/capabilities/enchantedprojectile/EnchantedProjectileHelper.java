package com.dephoegon.dungeons_libraries.capabilities.enchantedprojectile;

import com.dephoegon.dungeons_libraries.capabilities.ModCapabilities;
import net.minecraft.world.entity.Entity;

public class EnchantedProjectileHelper {

    public static EnchantedProjectile getEnchantedProjectileCapability(Entity entity) {
        return entity.getCapability(ModCapabilities.ENCHANTED_PROJECTILE_CAPABILITY).orElse(new EnchantedProjectile());
    }
}
