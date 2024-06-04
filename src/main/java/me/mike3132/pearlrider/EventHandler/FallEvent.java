package me.mike3132.pearlrider.EventHandler;

import me.mike3132.pearlrider.Items.PearlItem;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class FallEvent implements Listener {

    @EventHandler
    public void onPlayerFall(EntityDamageEvent ede) {
        if (ede.getEntity() instanceof Player) {
            if (ede.getCause() != EntityDamageEvent.DamageCause.FALL) return;
            Player player = (Player) ede.getEntity();

            if (player.hasPermission("PearlRider.use")) {
                PearlItem pearlItem = new PearlItem();
                ItemStack pearl = pearlItem.getPearl();
                if (player.getInventory().getItemInMainHand().isSimilar(pearl)) {
                    ede.setCancelled(true);
                }
            }
        }
    }

}
