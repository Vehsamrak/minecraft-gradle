package io.github.vehsamrak.minecraft.gradle;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import static org.bukkit.Bukkit.getLogger;

public class WeatherListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        getLogger().info("Weather changed");

        boolean isRaining = event.toWeatherState();

        if (!isRaining) {
            event.setCancelled(true);
            getLogger().info("setting rain");
        }
    }
}
