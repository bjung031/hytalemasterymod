package com.example.plugin;

import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

/**
 * Stores sword mastery data for a player
 * - Gains +1 exp per sword hit
 * - Level 2+ grants +5 flat sword damage
 *
 * NOTE: Data will NOT persist between server restarts yet
 * (Codec removed until we find the correct integer codec)
 */
public class SwordsmanData implements Component<EntityStore> {
    private int totalExp = 0;

    public int getTotalExp() {
        return totalExp;
    }

    public void setTotalExp(int totalExp) {
        this.totalExp = totalExp;
    }

    public void addExp(int amount) {
        totalExp += amount;
    }

    public int getLevel() {
        return totalExp / 100 + 1;
    }

    public int getProgress() {
        return totalExp % 100;
    }

    /**
     * Get the flat damage bonus for sword attacks
     * Level 2+ grants +5 flat sword damage
     */
    public int getDamageBonus() {
        return getLevel() >= 2 ? 5 :  0;
    }

    @Override
    @Nonnull
    public Component<EntityStore> clone() {
        SwordsmanData copy = new SwordsmanData();
        copy.totalExp = this.totalExp;
        return copy;
    }
}