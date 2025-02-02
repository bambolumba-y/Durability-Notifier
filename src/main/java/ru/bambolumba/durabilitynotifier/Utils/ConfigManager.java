package ru.bambolumba.durabilitynotifier.Utils;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;

import java.io.File;

import static org.bukkit.Bukkit.getLogger;
import static org.bukkit.Effect.Type.SOUND;

public class ConfigManager {

    static DurabilityNotifier plugin = DurabilityNotifier.getPlugin(DurabilityNotifier.class);
    private static FileConfiguration messageConfig;

    public static void createConfigFiles() {

        plugin.saveDefaultConfig();
        plugin.reloadConfig();
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

    /*
    Sound should be in format minecraft:example.example.example. If there would be _ instead of . , so this method will return null
     */
    public static Sound getSoundFromConfig(String path, Sound defaultSound) {
        String soundName = plugin.getConfig().getString(path);
        if (soundName != null) {
            soundName = "minecraft:" + soundName;
            try {
                NamespacedKey soundKey = NamespacedKey.fromString(soundName.toLowerCase().replace("_", "."));
                if (soundKey != null) {
                    return Registry.SOUNDS.get(soundKey);
                }
            } catch (IllegalArgumentException e) {
                plugin.getLogger().severe("Something went wrong when loading sound from config.");
            }
        }
        return defaultSound;
    }

}
