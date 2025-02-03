package ru.bambolumba.durabilitynotifier.Utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.List;

public class TabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NonNull CommandSender sender, @NonNull Command command, @NonNull String alias, String[] args) {

        List<String> firstArguments = new ArrayList<>();
        List<String> secondArguments = new ArrayList<>();
        List<String> thirdArguments = new ArrayList<>();

        firstArguments.add("admin");

        List<String> result = new ArrayList<>();
        if (args.length == 1) {
            for (String a : firstArguments) {
                if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                    result.add(a);
                }
            }
            return result;
        }

        secondArguments.add("reload"); secondArguments.add("set"); secondArguments.add("preview");

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("admin")) {
                for (String a : secondArguments) {
                    if (a.toLowerCase().startsWith(args[1].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            }
        }

        thirdArguments.add("damage"); thirdArguments.add("break");

        if (args.length == 3) {
            if (args[1].equalsIgnoreCase("preview")) {
                for (String a : thirdArguments) {
                    if (a.toLowerCase().startsWith(args[2].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            }
        }

        return null;
    }

}
