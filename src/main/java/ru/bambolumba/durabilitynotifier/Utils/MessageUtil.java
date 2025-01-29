package ru.bambolumba.durabilitynotifier.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

import java.util.List;

import static ru.bambolumba.durabilitynotifier.Utils.ConfigManager.getMessageConfig;

public class MessageUtil {

    public static Component build(String configPath) {
        String message = getMessageConfig().getString(configPath);
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(message);
    }

    public static Component build(String configPath, String match, String replacement) {
        String message = getMessageConfig().getString(configPath);
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(message).replaceText(TextReplacementConfig.builder()
                .match(match)
                .replacement(replacement)
                .build());
    }

    public static Component build(String message, boolean isFinal) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(message);
    }

    public static Component build(String message, String messageToAdd) {
        String finalMessage = message + messageToAdd;
        MiniMessage miniMessage = MiniMessage.miniMessage();
        return miniMessage.deserialize(finalMessage);
    }

    public static void sendUsage(Player player) {
        List<String> messageList = getMessageConfig().getStringList("usage");
        for (String message : messageList) {
            player.sendMessage(build(message, true));
        }
    }

    public static String removeBrackets(String text) {
        return text.replace("[", "").replace("]", "");
    }

}
