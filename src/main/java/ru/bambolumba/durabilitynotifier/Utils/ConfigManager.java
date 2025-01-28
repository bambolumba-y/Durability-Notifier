package ru.bambolumba.durabilitynotifier.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.bambolumba.durabilitynotifier.ProjectDurability;

import java.io.File;

public class ConfigManager {

    static ProjectDurability instance = ProjectDurability.getPlugin(ProjectDurability.class);
    private static FileConfiguration messageConfig;

    public static void createConfigFiles() {

        instance.saveDefaultConfig();
        createMessageConfig();

    }

    public static FileConfiguration getMessageConfig() {
        return messageConfig;
    }

    public static FileConfiguration getConfig() {
        return instance.getConfig();
    }

    private static void createMessageConfig() {

        File messageConfigFile = new File(instance.getDataFolder(), "messages.yml");
        if (!messageConfigFile.exists()) {
            messageConfigFile.getParentFile().mkdirs();
            instance.saveResource("messages.yml", false);
        }

        messageConfig = YamlConfiguration.loadConfiguration(messageConfigFile);

    }

    public static Component buildMessage(String configPath) {
        String message = getMessageConfig().getString(configPath);
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(message);
    }

    public static Component buildMessage(String configPath, String match, String replacement) {
        String message = getMessageConfig().getString(configPath);
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(message).replaceText(TextReplacementConfig.builder()
                .match(match)
                .replacement(replacement)
                .build());
    }

    public static Component buildMessage(String message, boolean isFinal) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(message);
    }

    public static Component buildMessage(String message, String messageToAdd) {
        String finalMessage = message + messageToAdd;
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(finalMessage);
    }

    public static void reloadConfigFiles() {
        instance.reloadConfig();
        createMessageConfig();
    }

}
