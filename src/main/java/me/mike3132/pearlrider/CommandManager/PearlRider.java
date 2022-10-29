package me.mike3132.pearlrider.CommandManager;

import me.mike3132.pearlrider.ChatManager.ChatMessages;
import me.mike3132.pearlrider.ItemManager.PearlItem;
import me.mike3132.pearlrider.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PearlRider implements CommandExecutor {

    public PearlRider() {
        Main.plugin.getCommand("PearlRider").setExecutor(this);
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(Main.chatColor("&d[&5Pearl &bRider&d] " + "&4&lYou cannot use PearlRider commands from console"));
        }
        Player player = (Player) sender;

        if (args.length != 0) {
            if (!player.hasPermission("pearlRider.Admin")) {
                ChatMessages.sendMessage(player, "No-Command-Permission");
                return true;
            }
            if (args[0].equalsIgnoreCase("Reload")) {
                player.sendMessage(Main.chatColor("&d[&5Pearl &bRider&d] " + "&6Config reloaded in &2" + String.valueOf(System.currentTimeMillis() - 1 + " &6ms")));
                Main.plugin.reloadConfig();
                return true;
            }
            if (!player.hasPermission("pearlRider.Give")) {
                ChatMessages.sendMessage(player, "No-Admin-Permission");
                return false;
            }
            if (!args[0].equalsIgnoreCase("Give")) {
                ChatMessages.sendMessage(player, "Not-Give-Command-Argument");
                return false;
            }
            if (player.getInventory().firstEmpty() != -1) {
                PearlItem pearlItem = new PearlItem();
                ItemStack pearl = pearlItem.getPearl();
                player.getInventory().addItem(pearl);
                ChatMessages.sendMessage(player, "Pearl-Given-Message");
            } else {
                ChatMessages.sendMessage(player, "Inventory-Full-Message");
            }
        } else {
            ChatMessages.sendMessage(player, "Help-Trigger");
        }

        return true;
    }
}
