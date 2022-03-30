package com.infamous.dungeons_libraries.items.interfaces;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IArmor<T extends Item> {

    boolean isUnique();

    default boolean doHealthPotionsHealNearbyAllies(){
        return false;
    }
    default boolean doGivesYouAPetBat(){
        return false;
    }
    default boolean doBriefInvulnerabilityWhenJumping(){
        return false;
    }

    default double getMagicDamage(){
        return 0;
    }
    default double getRangedDamage(){
        return 0;
    }
    default double getArtifactCooldown(){
        return 0;
    }
    default double getHealthPotionBoost(){
        return 0;
    }
    default double getSoulsGathered(){
        return 0;
    }
    default double getLifeSteal(){
        return 0;
    }
    default int getArrowsPerBundle(){
        return 0;
    }
    default double getChanceToTeleportAwayWhenHit(){
        return 0;
    }
    default double getLongerRolls(){
        return 0;
    }
    default double getChanceToNegateHits(){
        return 0;
    }
    default double getLongerRollCooldown(){
        return 0;
    }
    default double getFreezingResistance(){
        return 0;
    }
    default float getKnockbackResistance(){
        return 0;
    }

    // Enchantment abilities
    default boolean hasBurningBuiltIn(ItemStack stack){ return false;}
    default boolean hasSnowballBuiltIn(ItemStack stack){ return false;}
    default boolean hasPotionBarrierBuiltIn(ItemStack stack){return false;}
    default boolean hasSwiftfootedBuiltIn(ItemStack stack){return false;}
    default boolean hasChillingBuiltIn(ItemStack stack){return false;}
    default boolean hasArrowHoarderBuiltIn(ItemStack stack){return false;}
    default boolean hasMultiRollBuiltIn(ItemStack stack){return false;}
    default boolean hasEnvironmentalProtectionBuiltIn(ItemStack stack){return false;}
    default boolean hasLuckyExplorerBuiltIn(ItemStack stack){return false;}
    default boolean hasDeathBarterBuiltIn(ItemStack stack){return false;}
    default boolean hasOpulentShieldBuiltIn(ItemStack stack){return false;}
}
