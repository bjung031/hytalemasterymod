package com.example.plugin;

import com.hypixel.hytale.component.BuilderCodec;
import com.hypixel.hytale.component.Codec;
import com.hypixel.hytale.component.Component;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.core.util.KeyedCodec;
import org.checkerframework.checker.nullness.compatqual.NonNullDecl;

import javax.annotation.Nonnull;

public class SwordsmanData implements Component<EntityStore> {
    private int totalExp = 0;

    // Serialization codec (required for persistence)
    @Nonnull
    public static final BuilderCodec<SwordsmanData> CODEC = BuilderCodec.builder(SwordsmanData.class, SwordsmanData::new)
            .append(new KeyedCodec<>("totalExp", Codec.INT), SwordsmanData::setTotalExp, SwordsmanData::getTotalExp)
            .build();

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

    @Override
    @Nonnull
    public Component<EntityStore> clone() {
        SwordsmanData copy = new SwordsmanData();
        copy.totalExp = this.totalExp;
        return copy;
    }
}