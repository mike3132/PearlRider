package me.mike3132.pearlrider.EventHandler;

import me.mike3132.pearlrider.Items.PearlItem;
import me.mike3132.pearlrider.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class LoginEvent implements Listener {

    private final boolean pearlGiveOnJoin = Main.plugin.getConfig().getBoolean("Pearl-Given-On-Join");

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent pje) {
        if (!pearlGiveOnJoin) return;
        Player player = pje.getPlayer();
        PearlItem pearlItem = new PearlItem();
        ItemStack pearl = pearlItem.getPearl();
        if (!player.getInventory().contains(pearl)) {
            player.getInventory().addItem(pearl);
        }

    }
}
