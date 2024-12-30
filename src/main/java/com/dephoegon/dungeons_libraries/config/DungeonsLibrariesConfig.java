package com.dephoegon.dungeons_libraries.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class DungeonsLibrariesConfig {
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_AREA_OF_EFFECT_ON_OTHER_PLAYERS;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_KEEP_SOULS_ON_DEATH;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_DUAL_WIELDING;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_TWO_HANDED_WEAPON;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ENEMY_BLACKLIST;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_ELITE_MOBS;
    public static ForgeConfigSpec.ConfigValue<Double> ELITE_MOBS_BASE_CHANCE;
    public static ForgeConfigSpec.ConfigValue<List<? extends String>> ENEMY_WHITELIST;
    public static ForgeConfigSpec.ConfigValue<Boolean> ENABLE_TARGETS_BASED_ON_GOALS;

    public static class Common {

        public Common(ForgeConfigSpec.Builder builder) {

            builder.comment("Combat Configuration").push("combat_configuration");
            ENABLE_AREA_OF_EFFECT_ON_OTHER_PLAYERS = builder
                    .comment("Enable area of effects also being applied to players. \n" +
                            "If you do not want area of effects being applied to other players, disable this feature. [true / false]")
                    .define("enableAreaOfEffectOnOtherPlayers", false);
            ENABLE_TARGETS_BASED_ON_GOALS = builder
                    .comment("Enable limiting area of effects of mobs to only mobs they can normally target. \n" +
                            "Disabling this feature will cause mobs to hit eachother with AoE effects, but can fix unintended issues. [true / false]")
                    .define("enableTargetsBasedOnGoals", true);
            ENEMY_BLACKLIST = builder
                    .comment("Add entities that will never be targeted by aggressive Dungeons effects. \n"
                            + "To do so, enter their registry names.")
                    .defineList("effectTargetBlacklist", Lists.newArrayList(),
                            (itemRaw) -> itemRaw instanceof String);
            ENEMY_WHITELIST = builder
                    .comment("Add entities that should be targetted, but aren't by aggressive Dungeons effects. \n"
                            + "To do so, enter their registry names.")
                    .defineList("effectTargetWhitelist", Lists.newArrayList(),
                            (itemRaw) -> itemRaw instanceof String);
            builder.pop();

            //combat configuration
            builder.comment("Combat Configuration").push("combat_configuration");
            ENABLE_DUAL_WIELDING = builder
                    .comment("Enable dual wielding. \n" +
                            "If you do not want dual wielding, disable this feature. [true / false]")
                    .define("enableDualWielding", true);
            ENABLE_TWO_HANDED_WEAPON = builder
                    .comment("Enable two handed weapon. \n" +
                            "If you do not want two handed weapon, disable this feature. [true / false]")
                    .define("enableTwoHandedWeapon", true);
            builder.pop();

            builder.comment("Souls Configuration").push("souls_configuration");
            ENABLE_KEEP_SOULS_ON_DEATH = builder
                    .comment("Enables keeping of souls upon death, disabled by default. [true / false]")
                    .define("enableKeepSoulsOnDeath", false);
            builder.pop();

            builder.comment("Elite Mob Configuration").push("elite_mob_configuration");
            ENABLE_ELITE_MOBS = builder
                    .comment("Enables elite mobs, enabled by default. [true / false]")
                    .define("enableEliteMobs", true);
            ELITE_MOBS_BASE_CHANCE = builder
                    .comment("Base chance of an elite mob spawning. [0.0 - 1.0] \n" +
                            "Calculation: chance * difficulty.getSpecialMultiplier() \n" +
                            "Base chance for vanilla armor spawning is 0.15. Default is 0.15")
                    .defineInRange("eliteMobsBaseChance", 0.15, 0.0, 1.0);
            builder.pop();

        }
    }

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}