package me.mike3132.pearlrider;

import me.mike3132.pearlrider.Commands.PearlRider;
import me.mike3132.pearlrider.EventHandler.FallEvent;
import me.mike3132.pearlrider.EventHandler.LoginEvent;
import me.mike3132.pearlrider.EventHandler.ThrowEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    public static Main plugin;

    public static String chatColor(String chatColor) {
        return ChatColor.translateAlternateColorCodes('&', chatColor);
    }

    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(chatColor("&d[&5Pearl &bRider&7]" + "&2&lENABLED"));

        // Event Listener
        Bukkit.getPluginManager().registerEvents(new ThrowEvent(), this);
        Bukkit.getPluginManager().registerEvents(new FallEvent(), this);
        Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);

        // Config Loaders
        saveDefaultConfig();
        getConfig();
        createFiles();

        // Command Loaders
        registerPearlRider();

    }

    // Command Register
    public void registerPearlRider() {
        new PearlRider();
    }

    // Messages.yml file creation
    private File messages;
    private FileConfiguration config;

    private void createFiles() {
        messages = new File(getDataFolder(), "messages.yml");
        if (!messages.exists()) {
            messages.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        config = new YamlConfiguration();
        try {
            config.load(messages);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(chatColor("&d[&5Pearl &bRider&7]" + "&4&lDISABLED"));
    }
}
