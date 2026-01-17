package com.example.plugin;

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
        // Usually configs, systems, chunk registries, etc. go here — not commands

    }

    @Override
    protected void start() {
        // This is the right place for command registration
        this.getCommandRegistry().registerCommand(new MasteryCommand("hello", "An example command", false));
    }

    // Optional: add this for cleanup/debugging
    @Override
    protected void shutdown() {
    }
}