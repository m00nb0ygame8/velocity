package me.moonboygamer.velocity;

import org.bukkit.plugin.java.JavaPlugin;

public class Velocity extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("Velocity has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("Velocity has been disabled!");
    }
}
