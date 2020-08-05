package dev.aquild.deathswap;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    private final Deathswap plugin;
    private int players = 0;

    public DeathListener(Deathswap plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent event) {
        if (plugin.scenario) {
            event.getEntity().setGameMode(GameMode.SPECTATOR);
        }
    }
}
