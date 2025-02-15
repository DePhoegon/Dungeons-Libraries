package com.dephoegon.dungeons_libraries.mixinhandler;

import com.dephoegon.dungeons_libraries.capabilities.builtinenchants.BuiltInEnchantments;
import com.dephoegon.dungeons_libraries.capabilities.builtinenchants.BuiltInEnchantmentsHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.stream.Collectors;

public class EnchantmentHelperMixinHandler {
    public static void handler(EnchantmentHelper.EnchantmentVisitor visitor, ItemStack itemStack) {
        if (!itemStack.isEmpty()) {
            List<String> itemStackEnchantments = itemStack.getEnchantmentTags().stream().map(inbt -> ((CompoundTag) inbt).getString("id")).collect(Collectors.toList());
            BuiltInEnchantments cap = BuiltInEnchantmentsHelper.getBuiltInEnchantmentsCapability(itemStack);
            cap.getAllBuiltInEnchantmentInstances().stream()
                    .filter(enchantmentInstance -> !itemStackEnchantments.contains(ForgeRegistries.ENCHANTMENTS.getKey(enchantmentInstance.enchantment).toString()))
                    .collect(Collectors.groupingBy(enchantmentInstance -> enchantmentInstance.enchantment, Collectors.summingInt(value -> value.level)))
                    .forEach(visitor::accept);
        }
    }
}
