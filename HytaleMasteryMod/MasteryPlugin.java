package com.example.plugin;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.server.core.entity.entities.player.events.EntityDamageEvent;
import com.hypixel.hytale.server.core.inventory.items.ItemType;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

public class MasteryPlugin extends JavaPlugin {

    public MasteryPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        // Components are automatically managed by Hytale's component system
        // No manual registration needed for SwordsmanData
    }

    @Override
    protected void start() {
        // Register the mastery command
        this.getCommandRegistry().registerCommand(new MasteryCommand("mastery", "Open sword mastery UI", false));
        
        // Register the sword hit listener using EventRegistry
        this.getEventRegistry().register(EntityDamageEvent.class, this::onEntityDamage);
    }

    /**
     * Handles entity damage events to grant sword mastery experience
     * and apply damage bonuses
     */
    private void onEntityDamage(@Nonnull EntityDamageEvent event) {
        // Check if the damage source is a player
        if (!(event.getDamageSource().getAttacker() instanceof PlayerRef)) {
            return;
        }

        PlayerRef attacker = (PlayerRef) event.getDamageSource().getAttacker();
        
        // Check if the weapon is a sword
        ItemType weapon = attacker.getInventory().getMainHandItem().getType();
        if (weapon == null || !isSword(weapon)) {
            return;
        }

        // Get the player's entity store
        Store<EntityStore> store = attacker.getWorld().getEntityStore();
        
        // Get or create the player's sword mastery data
        SwordsmanData data = store.getOrCreate(attacker.getEntityRef(), SwordsmanData.class);
        
        // Grant 1 experience point
        data.addExp(1);
        
        // Apply damage bonus if the player has reached level 2
        // Note: The bonus applies to the hit that levels up the player,
        // providing immediate feedback when reaching a new level
        int damageBonus = data.getDamageBonus();
        if (damageBonus > 0) {
            event.setDamage(event.getDamage() + damageBonus);
        }
    }

    /**
     * Check if the item is a sword based on its type name
     * Checks for common sword naming patterns while avoiding false positives
     */
    private boolean isSword(@Nonnull ItemType type) {
        String typeName = type.getName().toLowerCase();
        // Check for sword but avoid matching items like "swordfish" or "crossword"
        // by ensuring "sword" appears as a complete word or at the start/end
        return typeName.equals("sword") || 
               typeName.startsWith("sword_") || 
               typeName.endsWith("_sword") ||
               typeName.matches(".*\\bsword\\b.*");
    }

    // Optional: add this for cleanup/debugging
    @Override
    protected void shutdown() {
    }
}