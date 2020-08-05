package dev.aquild.deathswap;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.Collections;

public class SwapTask extends BukkitRunnable {
    private final Deathswap plugin;
    private BukkitTask countdownTask;

    public SwapTask(Deathswap plugin) {
        this.plugin = plugin;
        this.countdownTask = new CountdownTask(plugin, plugin.warning / 20).runTaskTimer(plugin, plugin.delay - plugin.warning, 20);
    }

    @Override
    public void run() {
        if (!plugin.scenario) {
            cancel();
        }

        plugin.getLogger().info("Swapping players");
        plugin.getServer().broadcastMessage("§4§lSwapping now!§l§4");

        // Get players and their locations
        ArrayList<Player> players = new ArrayList<Player>();
        ArrayList<Location> playerLocations = new ArrayList<Location>();
        for (Player player : plugin.getServer().getOnlinePlayers()) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                players.add(player);
                playerLocations.add(player.getLocation());
            }
        }
        Collections.rotate(playerLocations, 1); // Shift list

        // Teleport players
        for (int i = 0; i < players.size(); i++) {
            players.get(i).teleport(playerLocations.get(i));
        }

        // Start Countdown
        countdownTask.cancel();
        countdownTask = new CountdownTask(plugin, plugin.warning / 20).runTaskTimer(plugin, plugin.delay - plugin.warning, 20);
    }
}
