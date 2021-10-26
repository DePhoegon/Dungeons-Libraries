package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.mobenchantments.MobEnchantment;
import com.infamous.dungeons_libraries.mobenchantments.MobEnchantmentsRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static com.infamous.dungeons_libraries.capabilities.enchantable.EnchantableHelper.getEnchantableCapabilityLazy;

public class MobEnchantmentMessage {
    private int entityId;
    private List<MobEnchantment> mobEnchantments;

    public MobEnchantmentMessage(int entityId, List<MobEnchantment> mobEnchantments) {
        this.entityId = entityId;
        this.mobEnchantments = mobEnchantments;
    }

    public void encode(PacketBuffer buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeVarInt(mobEnchantments.size());
        this.mobEnchantments.forEach(mobEnchantment -> buffer.writeResourceLocation(mobEnchantment.getRegistryName()));
    }

    public static MobEnchantmentMessage decode(PacketBuffer buffer) {
        int entityId = buffer.readInt();
        List<MobEnchantment> mobEnchantments = new ArrayList<>();
        int length = buffer.readVarInt();
        for (int x = 0; x < length; x++) {
            mobEnchantments.add(MobEnchantmentsRegistry.MOB_ENCHANTMENTS.getValue(buffer.readResourceLocation()));
        }

        return new MobEnchantmentMessage(entityId, mobEnchantments);
    }

    public static boolean onPacketReceived(MobEnchantmentMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
            context.enqueueWork(() -> {
                Entity entity = Minecraft.getInstance().player.level.getEntity(message.entityId);
                if (entity instanceof LivingEntity) {
                    getEnchantableCapabilityLazy(entity).ifPresent(iEnchantable -> {
                        iEnchantable.clearAllEnchantments();
                        message.mobEnchantments.forEach(iEnchantable::addEnchantment);
                        if(!message.mobEnchantments.isEmpty()) {
                            entity.refreshDimensions();
                        }
                    });
                }
            });
        }
        return true;
    }
}
