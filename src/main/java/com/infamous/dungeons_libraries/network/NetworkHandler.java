package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.DungeonsLibraries;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class NetworkHandler {
    public static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
            new ResourceLocation(DungeonsLibraries.MODID, "network"))
            .clientAcceptedVersions("1"::equals)
            .serverAcceptedVersions("1"::equals)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();

    protected static int PACKET_COUNTER = 0;

    public NetworkHandler() {
    }

    public static void init() {
        INSTANCE.messageBuilder(MobEnchantmentMessage.class, incrementAndGetPacketCounter())
                .encoder(MobEnchantmentMessage::encode).decoder(MobEnchantmentMessage::decode)
                .consumer(MobEnchantmentMessage::onPacketReceived)
                .add();
        INSTANCE.messageBuilder(UpdateSoulsMessage.class, incrementAndGetPacketCounter())
                .encoder(UpdateSoulsMessage::encode).decoder(UpdateSoulsMessage::decode)
                .consumer(UpdateSoulsMessage.UpdateSoulsHandler::handle)
                .add();
    }

    public static int incrementAndGetPacketCounter() {
        return PACKET_COUNTER++;
    }
}