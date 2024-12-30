package com.dephoegon.dungeons_libraries.capabilities.playerrewards;

import net.minecraft.world.entity.player.Player;

import static com.dephoegon.dungeons_libraries.capabilities.ModCapabilities.PLAYER_REWARDS_CAPABILITY;


public class PlayerRewardsHelper {

    public static PlayerRewards getPlayerRewardsCapability(Player playerEntity) {
        return playerEntity.getCapability(PLAYER_REWARDS_CAPABILITY).orElse(new PlayerRewards());
    }

}
