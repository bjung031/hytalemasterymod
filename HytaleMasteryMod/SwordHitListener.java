package com.example.plugin;

import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.event.Subscribe;
import com.hypixel.hytale.server.core.entity.entities.player.events.EntityDamageEvent;
import com.hypixel.hytale.server.core.inventory.items.ItemType;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;

import javax.annotation.Nonnull;

/**
 * Listens for entity damage events to grant sword mastery experience
 * and apply damage bonuses
 */
public class SwordHitListener {

    @Subscribe
    public void onEntityDamage(@Nonnull EntityDamageEvent event) {
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
        int damageBonus = data.getDamageBonus();
        if (damageBonus > 0) {
            event.setDamage(event.getDamage() + damageBonus);
        }
    }

    /**
     * Check if the item is a sword based on its type name
     */
    private boolean isSword(@Nonnull ItemType type) {
        String typeName = type.getName().toLowerCase();
        return typeName.contains("sword");
    }
}
