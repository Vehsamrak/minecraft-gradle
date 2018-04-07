package io.github.vehsamrak.minecraft.gradle;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable() {
        getLogger().info("))) PLUGIN ENABLED (((");
        getLogger().warning("This plugin is experimental!");
    }
}
