package ru.bambolumba.durabilitynotifier.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    List<String> arguments = new ArrayList<>();
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, String[] args) {
        if (arguments.isEmpty()) {
            arguments.add("admin");
        }

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : arguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        }

        arguments.clear();

        if (args.length == 2) {
            if (args[1].equalsIgnoreCase("admin")) {
                arguments.add("reload"); arguments.add("set"); arguments.add("preview");
                for (String a : arguments) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            }
        }

        return null;
    }

}
