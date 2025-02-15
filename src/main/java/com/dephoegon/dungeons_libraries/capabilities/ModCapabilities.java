package com.dephoegon.dungeons_libraries.capabilities;

import com.dephoegon.dungeons_libraries.capabilities.artifact.ArtifactUsage;
import com.dephoegon.dungeons_libraries.capabilities.artifact.AttacherArtifactUsage;
import com.dephoegon.dungeons_libraries.capabilities.builtinenchants.AttacherBuiltInEnchantments;
import com.dephoegon.dungeons_libraries.capabilities.builtinenchants.BuiltInEnchantments;
import com.dephoegon.dungeons_libraries.capabilities.elite.AttacherEliteMob;
import com.dephoegon.dungeons_libraries.capabilities.elite.EliteMob;
import com.dephoegon.dungeons_libraries.capabilities.enchantedprojectile.AttacherEnchantedProjectile;
import com.dephoegon.dungeons_libraries.capabilities.enchantedprojectile.EnchantedProjectile;
import com.dephoegon.dungeons_libraries.capabilities.minionmaster.AttacherLeader;
import com.dephoegon.dungeons_libraries.capabilities.minionmaster.AttacherFollower;
import com.dephoegon.dungeons_libraries.capabilities.minionmaster.Leader;
import com.dephoegon.dungeons_libraries.capabilities.minionmaster.Follower;
import com.dephoegon.dungeons_libraries.capabilities.playerrewards.AttacherPlayerRewards;
import com.dephoegon.dungeons_libraries.capabilities.playerrewards.PlayerRewards;
import com.dephoegon.dungeons_libraries.capabilities.soulcaster.AttacherSoulCaster;
import com.dephoegon.dungeons_libraries.capabilities.soulcaster.SoulCaster;
import com.dephoegon.dungeons_libraries.capabilities.timers.AttacherTimers;
import com.dephoegon.dungeons_libraries.capabilities.timers.Timers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.dephoegon.dungeons_libraries.DungeonsLibraries.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities {

    public static final Capability<Timers> TIMERS_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<Follower> FOLLOWER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<Leader> LEADER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<SoulCaster> SOUL_CASTER_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<EnchantedProjectile> ENCHANTED_PROJECTILE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<PlayerRewards> PLAYER_REWARDS_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<BuiltInEnchantments> BUILT_IN_ENCHANTMENTS_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<ArtifactUsage> ARTIFACT_USAGE_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    public static final Capability<EliteMob> ELITE_MOB_CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });


    public static void setupCapabilities() {
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addGenericListener(Entity.class, AttacherTimers::attach);
        forgeBus.addGenericListener(Entity.class, AttacherFollower::attach);
        forgeBus.addGenericListener(Entity.class, AttacherLeader::attach);
        forgeBus.addGenericListener(Entity.class, AttacherSoulCaster::attach);
        forgeBus.addGenericListener(Entity.class, AttacherEnchantedProjectile::attach);
        forgeBus.addGenericListener(Entity.class, AttacherPlayerRewards::attach);
        forgeBus.addGenericListener(ItemStack.class, AttacherBuiltInEnchantments::attach);
        forgeBus.addGenericListener(Entity.class, AttacherArtifactUsage::attach);
        forgeBus.addGenericListener(Entity.class, AttacherEliteMob::attach);
    }

    @SubscribeEvent
    public static void registerCaps(RegisterCapabilitiesEvent event) {
        event.register(Timers.class);
        event.register(Follower.class);
        event.register(Leader.class);
        event.register(SoulCaster.class);
        event.register(EnchantedProjectile.class);
        event.register(PlayerRewards.class);
        event.register(BuiltInEnchantments.class);
        event.register(ArtifactUsage.class);
        event.register(EliteMob.class);
    }
}
