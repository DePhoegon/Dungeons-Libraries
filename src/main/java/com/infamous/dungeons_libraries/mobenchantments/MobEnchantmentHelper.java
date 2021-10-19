package com.infamous.dungeons_libraries.mobenchantments;

import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.ShulkerEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.infamous.dungeons_libraries.capabilities.enchantable.EnchantableHelper.getEnchantableCapabilityLazy;
import static com.infamous.dungeons_libraries.mobenchantments.MobEnchantment.Type.ANY;
import static com.infamous.dungeons_libraries.mobenchantments.MobEnchantment.Type.RANGED;

public class MobEnchantmentHelper {

    public static void executeIfPresent(LivingEntity entity, MobEnchantment mobEnchantment, Runnable runnable){
        if (entity != null) {
            getEnchantableCapabilityLazy(entity).ifPresent(cap -> {
                if(cap.hasEnchantment(mobEnchantment)) {
                    runnable.run();
                }
            });
        }
    }

    public static MobEnchantment getRandomMobEnchantment(Random random, List<MobEnchantment> mobEnchantments){
        double totalWeight = 0.0;

        // Compute the total weight of all items together.
        for (MobEnchantment mobEnchantment : mobEnchantments) {
            totalWeight += mobEnchantment.getRarity().getWeight();
        }

        // Now choose a random item.
        int index = 0;
        for (double randomWeightPicked = random.nextFloat() * totalWeight; index < mobEnchantments.size() - 1; ++index) {
            randomWeightPicked -= mobEnchantments.get(index).getRarity().getWeight();
            if (randomWeightPicked <= 0.0) break;
        }

        return mobEnchantments.get(index);
    }

    public static ArrayList<MobEnchantment.Type> getPossibleTypes(Entity entity) {
        if(entity instanceof IRangedAttackMob || entity instanceof ShulkerEntity){
            return Lists.newArrayList(ANY, RANGED);
        }else {
            return Lists.newArrayList(ANY);
        }
    }

    public static boolean hasType(MobEnchantment mobEnchantment, ArrayList<MobEnchantment.Type> possibleTypes) {
        return possibleTypes.contains(mobEnchantment.getType());
    }

}
