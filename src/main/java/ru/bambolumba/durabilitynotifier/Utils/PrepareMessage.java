package ru.bambolumba.durabilitynotifier.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextReplacementConfig;
import net.kyori.adventure.text.minimessage.MiniMessage;

import static ru.bambolumba.durabilitynotifier.Utils.ConfigManager.getMessageConfig;

public class PrepareMessage {

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

}
