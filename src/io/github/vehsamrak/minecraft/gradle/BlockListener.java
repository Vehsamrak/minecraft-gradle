package io.github.vehsamrak.minecraft.gradle;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Random;

import static org.bukkit.Bukkit.getLogger;

public class BlockListener implements Listener {

    @EventHandler
    public void blockBreakHandler(BlockBreakEvent event) {
        getLogger().info("Block broken");

        Material[] availableOres = {
                Material.COAL_ORE,
                Material.DIAMOND_ORE,
                Material.EMERALD_ORE,
                Material.REDSTONE_ORE,
                Material.GOLD_ORE,
                Material.IRON_ORE,
                Material.LAPIS_ORE,
                Material.COBBLESTONE,
                Material.DIRT,
                Material.AIR,
        };

        Block block = event.getBlock();

        if (block.getType() == Material.STONE) {
            getLogger().info("Stone block broken");

            int idx = new Random().nextInt(availableOres.length);
            Material ore = (availableOres[idx]);
            block.setType(ore);

            Player player = event.getPlayer();

            PlayerInventory inventory = player.getInventory();
            ItemStack itemInMainHand = inventory.getItemInMainHand();
            getLogger().info(String.valueOf(itemInMainHand.getDurability()));
            itemInMainHand.setDurability((short) (itemInMainHand.getDurability() + 1));
            block.breakNaturally();

            event.setCancelled(true);
        }
    }
}
