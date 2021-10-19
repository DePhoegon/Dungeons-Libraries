package com.infamous.dungeons_libraries.capabilities.enchantable;


import com.infamous.dungeons_libraries.mobenchantments.MobEnchantment;
import com.infamous.dungeons_libraries.network.MobEnchantmentMessage;
import com.infamous.dungeons_libraries.network.NetworkHandler;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.List;

public class Enchantable implements IEnchantable{
    private ArrayList<MobEnchantment> enchantments = new ArrayList<>();
    private boolean isSpawned = false;

    @Override
    public boolean addEnchantment(MobEnchantment enchantment) {
        enchantments.add(enchantment);
        return true;
    }

    @Override
    public boolean removeEnchantment(MobEnchantment enchantment) {
        enchantments.remove(enchantment);
        return true;
    }

    @Override
    public boolean clearAllEnchantments() {
        enchantments.clear();
        return true;
    }

    @Override
    public List<MobEnchantment> getEnchantments() {
        return enchantments;
    }

    public boolean hasEnchantment() {
        return !this.enchantments.isEmpty();
    }

    public boolean hasEnchantment(MobEnchantment mobEnchantment) {
        return this.enchantments.contains(mobEnchantment);
    }

    @Override
    public boolean isSpawned() {
        return isSpawned;
    }

    @Override
    public void setSpawned(boolean spawned) {
        isSpawned = spawned;
    }
}
