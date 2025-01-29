package ru.bambolumba.durabilitynotifier.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;

import java.io.File;

public class ConfigManager {

    static DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
    private static FileConfiguration messageConfig;

    public static void createConfigFiles() {

        plugin.saveDefaultConfig();
        createMessageConfig();

    }

    public static FileConfiguration getMessageConfig() {
        return messageConfig;
    }

    public static FileConfiguration getConfig() {
        return plugin.getConfig();
    }

    private static void createMessageConfig() {

        File messageConfigFile = new File(plugin.getDataFolder(), "messages.yml");
        if (!messageConfigFile.exists()) {
            messageConfigFile.getParentFile().mkdirs();
            plugin.saveResource("messages.yml", false);
        }

        messageConfig = YamlConfiguration.loadConfiguration(messageConfigFile);

    }

    public static void reloadConfigFiles() {
        plugin.reloadConfig();
        createMessageConfig();
        plugin.applyNotificationsUpdate();
    }

}
