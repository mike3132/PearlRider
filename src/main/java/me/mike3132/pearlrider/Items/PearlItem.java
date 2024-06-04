package me.mike3132.pearlrider.Items;

import me.mike3132.pearlrider.Main;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class PearlItem {

    private ItemStack pearl;

    public PearlItem() {
        this.createPearl();
    }

    public ItemStack getPearl() {
        return this.pearl;
    }

    private void createPearl() {
        ItemStack item = new ItemStack(Material.ENDER_PEARL, 1);
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        for (String realLore : Main.plugin.getConfig().getStringList("Pearl-Lore")) {
            lore.add(Main.chatColor("" + realLore));
        }
        meta.setDisplayName(Main.chatColor("" + Main.plugin.getConfig().getString("Pearl-Name")));
        meta.setLore(lore);
        item.setItemMeta(meta);
        pearl = item;
    }
}
