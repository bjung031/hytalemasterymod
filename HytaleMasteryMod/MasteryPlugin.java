package com.example.plugin;

import com.hypixel.hytale.component.ComponentKey;
import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;

import javax.annotation.Nonnull;

public class MasteryPlugin extends JavaPlugin {

    public MasteryPlugin(@Nonnull JavaPluginInit init) {
        super(init);
    }

    @Override
    protected void setup() {
        super.setup();
        // Register the SwordsmanData component
        this.getComponentRegistry().register(SwordsmanData.class, new ComponentKey<>("mastery:swordsman"));
    }

    @Override
    protected void start() {
        // Register the mastery command
        this.getCommandRegistry().registerCommand(new MasteryCommand("mastery", "Open sword mastery UI", false));
        
        // Register the sword hit listener
        this.getEventBus().registerListener(new SwordHitListener());
    }

    // Optional: add this for cleanup/debugging
    @Override
    protected void shutdown() {
    }
}