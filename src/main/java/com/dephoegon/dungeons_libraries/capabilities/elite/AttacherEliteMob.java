package com.dephoegon.dungeons_libraries.capabilities.elite;

import com.dephoegon.dungeons_libraries.capabilities.ModCapabilities;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;

import static com.dephoegon.dungeons_libraries.DungeonsLibraries.MODID;

public class AttacherEliteMob {

    private static class EliteMobProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

        public static final ResourceLocation IDENTIFIER = new ResourceLocation(MODID, "elite_mob");
        private final EliteMob backend = new EliteMob();
        private final LazyOptional<EliteMob> optionalData = LazyOptional.of(() -> backend);

        @Override
        public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction side) {
            return ModCapabilities.ELITE_MOB_CAPABILITY.orEmpty(cap, this.optionalData);
        }

        @Override
        public CompoundTag serializeNBT() {
            return this.backend.serializeNBT();
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.backend.deserializeNBT(nbt);
        }
    }

    public static void attach(final AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Mob) {
            final EliteMobProvider provider = new EliteMobProvider();
            event.addCapability(EliteMobProvider.IDENTIFIER, provider);
        }
    }
}
