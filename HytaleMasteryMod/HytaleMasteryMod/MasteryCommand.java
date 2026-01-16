package com.example.plugin;

import com.hypixel.hytale.component.Ref;
import com.hypixel. hytale.component.Store;
import com.hypixel. hytale.server.core. command.system.CommandContext;
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe. world.World;
import com. hypixel.hytale. server.core.universe.world. storage.EntityStore;

import javax.annotation.Nonnull;

/**
 * Command to open the mastery UI
 * Usage: /mastery
 */
public class MasteryCommand extends AbstractPlayerCommand {

    public MasteryCommand() {
        super("mastery", "Opens the sword mastery UI");
    }

    @Override
    protected void execute(
            @Nonnull CommandContext context,
            @Nonnull Store<EntityStore> store,
            @Nonnull Ref<EntityStore> ref,
            @Nonnull PlayerRef playerRef,
            @Nonnull World world
    ) {
        // Get the player component using Player.getComponentType()
        Player player = store.getComponent(ref, Player.getComponentType());

        if (player == null) {
            return;
        }

        // For now, just create a new SwordsmanData each time
        // (until we figure out how to properly register and retrieve custom components)
        SwordsmanData data = new SwordsmanData();

        // Open the mastery UI
        MasteryPage page = new MasteryPage(playerRef, data);
        player.getPageManager().openCustomPage(ref, store, page);
    }
}