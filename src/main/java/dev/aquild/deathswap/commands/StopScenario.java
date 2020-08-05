package dev.aquild.deathswap.commands;

import dev.aquild.deathswap.Deathswap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StopScenario implements CommandExecutor {
    private final Deathswap plugin;

    public StopScenario(Deathswap plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        plugin.scenario = false;
        plugin.getServer().broadcastMessage("§4§lDeath Swap has been cancelled.§l§4");
        return true;
    }
}
