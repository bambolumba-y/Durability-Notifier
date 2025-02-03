package ru.bambolumba.durabilitynotifier.Handlers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import ru.bambolumba.durabilitynotifier.Commands.Admin;
import ru.bambolumba.durabilitynotifier.Utils.MessageUtil;

public class CommandHandler implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender commandSender, @NonNull Command command, @NonNull String s, @NonNull String @NonNull [] args) {

        if (commandSender instanceof Player player) {

            if (args.length == 0) {
                MessageUtil.sendUsage(player);
                return true;
            }

            switch (args[0]) {
                case "admin":
                    return new Admin().execute(player, args);
                default:
                    MessageUtil.sendUsage(player);
                    return true;
            }

        }

        return false;
    }

}
