package dev.aquild.deathswap;

import dev.aquild.deathswap.commands.StartScenario;
import dev.aquild.deathswap.commands.StopScenario;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class Deathswap extends JavaPlugin implements Listener {
    public boolean scenario = false;
    public int delay;
    public int warning;

    @Override
    public void onEnable() {
        // Config
        saveDefaultConfig();

        // Assign Commands
        getCommand("startscenario").setExecutor(new StartScenario(this));
        getCommand("stopscenario").setExecutor(new StopScenario(this));

        // Spectator mode on death if hardcore is enabled
        if (getConfig().getBoolean("hardcore")) {
            getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        }

        getLogger().info("Death Swap by Aquild Loaded!");
        getLogger().info("Scenario: " + scenario);
    }
}