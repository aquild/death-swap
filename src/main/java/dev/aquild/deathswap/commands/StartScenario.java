package dev.aquild.deathswap.commands;

import dev.aquild.deathswap.Deathswap;
import dev.aquild.deathswap.SwapTask;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class StartScenario implements CommandExecutor {
    private final Deathswap plugin;

    public StartScenario(Deathswap plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        try {
            plugin.delay = Integer.parseInt(args[0]) * 20;
            plugin.warning = Integer.parseInt(args[1]) * 20;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            sender.sendMessage("Please specify the Swap Delay and Swap Warning in seconds.");
            return false;
        }

        if (plugin.scenario) {
            sender.sendMessage("Death Swap is already running. Cancel it using /stopscenario");
            return true;
        }

        plugin.getServer().broadcastMessage(String.format("§4§lStarting Death Swap scenario. In a few seconds you'll be teleported to a random part of the world. Every %s seconds you will be swapped with another player.§l§4",  plugin.delay/20));

        // Spread Players
        plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "spreadplayers 0 0 1000 10000 false @a");

        // Schedule Repeating Task
        plugin.scenario = true;
        new SwapTask(plugin).runTaskTimer(plugin, plugin.delay, plugin.delay);

        return true;
    }
}
