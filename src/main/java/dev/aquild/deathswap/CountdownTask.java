package dev.aquild.deathswap;

import org.bukkit.scheduler.BukkitRunnable;

public class CountdownTask extends BukkitRunnable {
    private final Deathswap plugin;
    public int length;

    public CountdownTask(Deathswap plugin, int length) {
        this.plugin = plugin;
        this.length = length;
    }

    @Override
    public void run() {
        plugin.getServer().broadcastMessage(String.format("§4§lSwapping in %s seconds!§l§4", length));
        if (length == 1) {
            cancel();
        };
        length--;
    }
}
