package ru.bambolumba.durabilitynotifier.Notifications;

import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;

public class Message {

    private boolean enabled;
    private String text;

    public Message () {
        this.enabled = ConfigManager.getConfig().getBoolean("notifications.types.message.enabled");
        this.text = ConfigManager.getMessageConfig().getString("notifications.types.message.text");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getText() {
        return text;
    }

}
