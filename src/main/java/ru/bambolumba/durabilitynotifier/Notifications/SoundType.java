package ru.bambolumba.durabilitynotifier.Notifications;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import ru.bambolumba.durabilitynotifier.DurabilityNotifier;
import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;

public class SoundType {

    private final boolean enabled;
    private final Sound damageSound;
    private final Sound breakSound;

    public SoundType() {
        this.damageSound = ConfigManager.getSoundFromConfig("notifications.types.sound.damage-sound", Sound.ENTITY_ARROW_HIT);
        this.breakSound = ConfigManager.getSoundFromConfig("notifications.types.sound.break-sound", Sound.ENTITY_VEX_DEATH);
        this.enabled = ConfigManager.getConfig().getBoolean("notifications.types.sound.enabled");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Sound getDamageSound() {
        return damageSound;
    }

    public Sound getBreakSound() {
        return breakSound;
    }

}
