package ru.bambolumba.durabilitynotifier.Utils;

import it.unimi.dsi.fastutil.Pair;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.bambolumba.durabilitynotifier.Utils.ConfigManager.getMessageConfig;

public class MessageUtil {

    public static String build(String configPath) {
        String message = getMessageConfig().getString(configPath);
        return format(message);
    }

    /*
    This method allows to use any number of replacements
     */
    public static String build(String message, List<Pair<String, String>> replacements) {

        // Применение всех замен
        for (Pair<String, String> replacement : replacements) {
            message = message.replaceAll(replacement.left(), replacement.right());
        }

        return format(message);
    }

    public static String build(String message, boolean isFinal) {
        return format(message);
    }

    public static String build(String message, String messageToAdd) {
        String finalMessage = message + messageToAdd;
        return format(message + messageToAdd);
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

    private static String format(String message) {

        // Use pattern to find HEX codes in message
        Pattern pattern = Pattern.compile("&#([A-Fa-f0-9]{6})");
        Matcher matcher = pattern.matcher(message);
        StringBuffer buffer = new StringBuffer();

        while (matcher.find()) {
            matcher.appendReplacement(buffer, ChatColor.of("#" + matcher.group(1)).toString());
        }
        matcher.appendTail(buffer);

        return ChatColor.translateAlternateColorCodes('&', buffer.toString());
    }

}
