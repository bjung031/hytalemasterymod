package com.example. plugin;

import com.hypixel.hytale.server. core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation. Nonnull;

public class MasteryPlugin extends JavaPlugin {

    public MasteryPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();

        // Register commands
        this.getCommandRegistry().registerCommand(new MasteryCommand());

        // TODO: Register event listeners for sword hits
        // TODO: Add key binding for 'B' key
    }
}