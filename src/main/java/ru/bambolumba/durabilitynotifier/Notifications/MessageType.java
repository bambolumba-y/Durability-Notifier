package ru.bambolumba.durabilitynotifier.Notifications;

import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;

public class MessageType {

    private boolean enabled;
    private String text;

    public MessageType() {
        this.enabled = ConfigManager.getConfig().getBoolean("notifications.types.message.enabled");
        this.text = ConfigManager.getConfig().getString("notifications.types.message.text");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getText() {
        return text;
    }

    public void update() {
        this.enabled = ConfigManager.getConfig().getBoolean("notifications.types.message.enabled");
        this.text = ConfigManager.getConfig().getString("notifications.types.message.text");
    }

}
