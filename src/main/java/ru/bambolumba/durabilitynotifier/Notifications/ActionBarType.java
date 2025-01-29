package ru.bambolumba.durabilitynotifier.Notifications;

import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;

public class ActionBarType {

    private final boolean enabled;
    private final String damageText;
    private final String breakText;

    public ActionBarType() {
        this.enabled = ConfigManager.getConfig().getBoolean("notifications.types.action-bar.enabled");
        this.damageText = ConfigManager.getConfig().getString("notifications.types.action-bar.damage-text");
        this.breakText = ConfigManager.getConfig().getString("notifications.types.action-bar.break-text");
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
