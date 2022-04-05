package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.capabilities.soulcaster.ISoulCaster;
import com.infamous.dungeons_libraries.capabilities.soulcaster.SoulCasterHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.DistExecutor.SafeRunnable;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateSoulsMessage {
    private final float newAmount;

    public UpdateSoulsMessage(float souls) {
        this.newAmount = souls;
    }

    public static void encode(UpdateSoulsMessage packet, PacketBuffer buf) {
        buf.writeFloat(packet.newAmount);
    }

    public static UpdateSoulsMessage decode(PacketBuffer buf) {
        return new UpdateSoulsMessage(buf.readFloat());
    }

    public static class UpdateSoulsHandler {
        public static void handle(UpdateSoulsMessage packet, Supplier<NetworkEvent.Context> ctx) {
            if (packet != null) {
                ctx.get().setPacketHandled(true);
                ctx.get().enqueueWork(() ->
                DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> new SafeRunnable() {
                    private static final long serialVersionUID = 1;

                    @Override
                    public void run() {
                        ClientPlayerEntity player = Minecraft.getInstance().player;
                        if (player != null) {
                            ISoulCaster soulCasterCap = SoulCasterHelper.getSoulCasterCapability(player);
                            if (soulCasterCap != null) {
                                soulCasterCap.setSouls(packet.newAmount, player);
                            }
                        }
                    }
                }));
            }
        }
    }
}