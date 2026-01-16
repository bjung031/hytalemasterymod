package com.example.plugin;

import com.hypixel.hytale.protocol.packets.interface_. CustomPageLifetime;
import com.hypixel.hytale.server.core.entity.entities.player.pages.BasicCustomUIPage;
import com.hypixel.hytale.server. core.ui.builder.UICommandBuilder;
import com.hypixel.hytale.server. core.universe.PlayerRef;

import javax.annotation. Nonnull;

/**
 * Mastery UI Page - displays when player presses 'B'
 * Shows sword mastery level, exp progress, and damage bonus
 */
public class MasteryPage extends BasicCustomUIPage {

    private final SwordsmanData data;

    public MasteryPage(@Nonnull PlayerRef playerRef, @Nonnull SwordsmanData data) {
        super(playerRef, CustomPageLifetime. CanDismiss);
        this.data = data;
    }

    @Override
    public void build(@Nonnull UICommandBuilder cmd) {
        // Load the UI layout file
        cmd.append("Pages/MasteryPage.ui");

        // Set dynamic values
        int level = data.getLevel();
        int progress = data.getProgress();
        int damageBonus = data.getDamageBonus();

        // Update UI elements with current values
        cmd.set("#LevelText. Text", "Level: " + level);
        cmd.set("#ExpText.Text", "Exp: " + progress + " / 100");
        cmd.set("#DamageText.Text", "Sword Damage: +" + damageBonus);

        // Set bonus status text
        String bonusStatus = level >= 2
                ? "Active:  +5 Flat Damage"
                : "Level 2:  +5 Flat Damage";
        cmd.set("#BonusStatus.Text", bonusStatus);
    }
}