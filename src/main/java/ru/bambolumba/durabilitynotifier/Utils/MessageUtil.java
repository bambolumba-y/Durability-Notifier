package ru.bambolumba.durabilitynotifier.Utils;

import it.unimi.dsi.fastutil.Pair;
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

    /*
    This method allows to use any number of replacements
     */
    public static Component build(String message, List<Pair<String, String>> replacements) {
        MiniMessage miniMessage = MiniMessage.miniMessage();
        Component component = miniMessage.deserialize(message);

        // Применение всех замен
        for (Pair<String, String> replacement : replacements) {
            component = component.replaceText(TextReplacementConfig.builder()
                    .match(replacement.left())
                    .replacement(replacement.right())
                    .build());
        }

        return component;
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
