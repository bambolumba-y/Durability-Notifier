package ru.bambolumba.durabilitynotifier.Notifications;

import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;

public class MessageType {

    private final boolean enabled;
    private final String damageText;
    private final String breakText;

    public MessageType() {
        this.enabled = ConfigManager.getConfig().getBoolean("notifications.types.message.enabled");
        this.damageText = ConfigManager.getConfig().getString("notifications.types.message.damage-text");
        this.breakText = ConfigManager.getConfig().getString("notifications.types.message.break-text");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getDamageText() {
        return damageText;
    }

    public String getBreakText() {
        return breakText;
    }
}
