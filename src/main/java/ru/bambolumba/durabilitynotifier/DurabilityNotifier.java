package ru.bambolumba.durabilitynotifier;

import org.bukkit.plugin.java.JavaPlugin;
import ru.bambolumba.durabilitynotifier.Handlers.CommandHandler;
import ru.bambolumba.durabilitynotifier.Listeners.BlockBreakListener;
import ru.bambolumba.durabilitynotifier.Notifications.ActionBarType;
import ru.bambolumba.durabilitynotifier.Notifications.MessageType;
import ru.bambolumba.durabilitynotifier.Utils.ConfigManager;

public final class DurabilityNotifier extends JavaPlugin {

    private MessageType messageType;
    private ActionBarType actionBarType;

    @Override
    public void onEnable() {

        messageType = new MessageType();
        actionBarType = new ActionBarType();

        ConfigManager.createConfigFiles();

        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        getCommand("durability").setExecutor(new CommandHandler());

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

    public void applyNotificationsUpdate() {
        actionBarType.update();
        messageType.update();
    }

}
