package io.github.vehsamrak.minecraft.gradle;

import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public void onEnable() {
        getLogger().info("))) PLUGIN ENABLED (((");
        getLogger().warning("This plugin is experimental!");

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new WeatherListener(), this);
        pluginManager.registerEvents(new BlockListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        World world = player.getWorld();

        world.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 5, 1);
        world.setWeatherDuration(0);
        world.setStorm(true);
        world.setThundering(true);

        return true;
    }
}
