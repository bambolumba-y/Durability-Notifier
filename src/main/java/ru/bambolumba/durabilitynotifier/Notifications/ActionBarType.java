package ru.bambolumba.durabilitynotifier.Notifications;

import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;

public class ActionBarType {

    private final boolean enabled;
    private final String text;

    public ActionBarType() {
        this.enabled = ConfigManager.getConfig().getBoolean("notifications.types.action-bar.enabled");
        this.text = ConfigManager.getMessageConfig().getString("notifications.types.action-bar.text");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getText() {
        return text;
    }

}
