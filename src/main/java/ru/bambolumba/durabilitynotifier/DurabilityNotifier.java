package ru.bambolumba.durabilitynotifier;

import org.bukkit.plugin.java.JavaPlugin;
import ru.bambolumba.durabilitynotifier.Handlers.CommandHandler;
import ru.bambolumba.durabilitynotifier.Listeners.ItemDamageListener;
import ru.bambolumba.durabilitynotifier.Notifications.ActionBarType;
import ru.bambolumba.durabilitynotifier.Notifications.MessageType;
import ru.bambolumba.durabilitynotifier.Notifications.SoundType;
import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;
import ru.bambolumba.durabilitynotifier.Utils.DurabilityUtil;
import ru.bambolumba.durabilitynotifier.Utils.Metrics;
import ru.bambolumba.durabilitynotifier.Utils.TabComplete;

public final class DurabilityNotifier extends JavaPlugin {

    private MessageType messageType;
    private ActionBarType actionBarType;
    private SoundType soundType;
    private DurabilityUtil durabilityUtil;

    @Override
    public void onEnable() {

        int pluginId = 24605; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        messageType = new MessageType();
        actionBarType = new ActionBarType();
        soundType = new SoundType();
        durabilityUtil = new DurabilityUtil();

        ConfigManager.createConfigFiles();

        getServer().getPluginManager().registerEvents(new ItemDamageListener(), this);

        getCommand("durability").setExecutor(new CommandHandler());
        getCommand("durability").setTabCompleter(new TabComplete());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public MessageType getMessage() {
        return messageType;
    }

    public ActionBarType getActionBar() {
        return actionBarType;
    }

    public SoundType getSound() {
        return soundType;
    }

    public DurabilityUtil getDurabilityUtil() {
        return durabilityUtil;
    }

    public void applyNotificationsUpdate() {
        messageType = new MessageType();
        actionBarType = new ActionBarType();
        soundType = new SoundType();
        durabilityUtil = new DurabilityUtil();
    }

}
