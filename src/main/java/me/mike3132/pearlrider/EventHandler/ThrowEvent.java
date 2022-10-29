package me.mike3132.pearlrider.EventHandler;

import me.mike3132.pearlrider.ChatManager.ChatMessages;
import me.mike3132.pearlrider.ItemManager.PearlItem;
import me.mike3132.pearlrider.Main;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ThrowEvent implements Listener {


    @EventHandler
    public void ride(ProjectileLaunchEvent ple) {
        Projectile projectile = ple.getEntity();

        if (projectile.getShooter() instanceof Player) {
            Player player = (Player) ple.getEntity().getShooter();
            if (player.hasPermission("PearlRider.use")) {
                PearlItem pearlItem = new PearlItem();
                ItemStack pearl = pearlItem.getPearl();
                if (projectile.getType().equals(EntityType.ENDER_PEARL)) {
                    if (!player.isInsideVehicle()) {
                        projectile.addPassenger(player);new BukkitRunnable() {
                            @Override
                            public void run() {
                                if (!player.getInventory().getItemInMainHand().isSimilar(pearl)) {
                                    player.getInventory().addItem(pearl);
                                }
                            }
                        }.runTaskLater(Main.plugin, 20L);
                    } else {
                        ChatMessages.sendMessage(player, "Already-Riding-Pearl");
                        ple.setCancelled(true);
                    }
                }
            } else {
                ChatMessages.sendMessage(player, "No-Ride-Permission");
                ple.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent pte) {
        if (pte.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)) {
            pte.setCancelled(true);
        }
    }
}
