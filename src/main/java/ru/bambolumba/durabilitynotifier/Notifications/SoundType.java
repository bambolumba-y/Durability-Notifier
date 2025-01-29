package ru.bambolumba.durabilitynotifier.Notifications;

import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.Sound;
import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;

public class SoundType {

    private final boolean enabled;
    private final Sound damageSound;
    private final Sound breakSound;
    private final String damageText;
    private final String breakText;

    public SoundType() {
        String damageSoundName = ConfigManager.getConfig().getString("notifications.types.sound.damage-sound");
        NamespacedKey key = NamespacedKey.fromString(damageSoundName);
        if (key != null) {
            this.damageSound = Registry.SOUNDS.get(key);
        } else {
            this.damageSound = Sound.ITEM_CROSSBOW_HIT;
        }
        String breakSoundName = ConfigManager.getConfig().getString("notifications.types.sound.break-sound");
        key = NamespacedKey.fromString(breakSoundName);
        if (key != null) {
            this.breakSound = Registry.SOUNDS.get(key);
        } else {
            this.breakSound = Sound.ENTITY_VEX_DEATH;
        }
        this.enabled = ConfigManager.getConfig().getBoolean("notifications.types.sound.enabled");
        this.damageText = ConfigManager.getConfig().getString("notifications.types.sound.damage-text");
        this.breakText = ConfigManager.getConfig().getString("notifications.types.sound.break-text");
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Sound getDamageSound() {
        return damageSound;
    }

    public String getDamageText() {
        return damageText;
    }

    public Sound getBreakSound() {
        return breakSound;
    }

    public String getBreakText() {
        return breakText;
    }

}
