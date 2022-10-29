package me.mike3132.pearlrider.EventHandler;

import me.mike3132.pearlrider.ItemManager.PearlItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class LoginEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent pje) {
        Player player = pje.getPlayer();
        if (player.hasPermission("PearlRider.use")) {
            PearlItem pearlItem = new PearlItem();
            ItemStack pearl = pearlItem.getPearl();
            if (!player.getInventory().contains(pearl)) {
                player.getInventory().addItem(pearl);
            }
        }
    }
}
