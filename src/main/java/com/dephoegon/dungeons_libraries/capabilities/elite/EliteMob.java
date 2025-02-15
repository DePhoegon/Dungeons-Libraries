package com.dephoegon.dungeons_libraries.capabilities.elite;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

import static com.dephoegon.dungeons_libraries.entities.elite.EliteMobConfig.EMPTY_TEXTURE;

public class EliteMob {
    private boolean isElite = false;
    private boolean hasSpawned = false;
    private ResourceLocation texture = null;

    public boolean isElite() {
        return isElite;
    }

    public void setElite(boolean elite) {
        isElite = elite;
    }

    public boolean hasSpawned() {
        return hasSpawned;
    }

    public void setHasSpawned(boolean hasSpawned) {
        this.hasSpawned = hasSpawned;
    }

    public ResourceLocation getTexture() {
        return texture;
    }

    public void setTexture(ResourceLocation texture) {
        this.texture = texture;
    }

    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        nbt.putBoolean("isElite", isElite);
        nbt.putBoolean("hasSpawned", hasSpawned);
        if (texture != null) {
            nbt.putString("texture", texture.toString());
        }
        return nbt;
    }

    public void deserializeNBT(CompoundTag tag) {
        isElite = tag.getBoolean("isElite");
        hasSpawned = tag.getBoolean("hasSpawned");
        if (tag.contains("texture")) {
            texture = new ResourceLocation(tag.getString("texture"));
        } else {
            texture = EMPTY_TEXTURE;
        }
    }

}
