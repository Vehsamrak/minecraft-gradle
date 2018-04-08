package io.github.vehsamrak.minecraft.gradle;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

import static org.bukkit.Bukkit.getLogger;

public class BlockListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        getLogger().info("Block broken");

        Material[] availableOres = {
                Material.COAL_ORE,
                Material.DIAMOND_ORE,
                Material.EMERALD_ORE,
                Material.REDSTONE_ORE,
                Material.GOLD_ORE,
                Material.IRON_ORE,
                Material.LAPIS_ORE,
                Material.QUARTZ_ORE,
                Material.COBBLESTONE,
                Material.DIRT,
        };

        Block block = event.getBlock();

        if (block.getType() == Material.STONE) {
            getLogger().info("Stone block broken");

            int idx = new Random().nextInt(availableOres.length);
            Material ore = (availableOres[idx]);
            block.setType(ore);
            block.breakNaturally();

            event.setCancelled(true);
        }
    }
}
