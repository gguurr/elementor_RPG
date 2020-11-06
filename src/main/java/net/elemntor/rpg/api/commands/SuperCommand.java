package net.elemntor.rpg.api.commands;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class SuperCommand {
    private String name;
    private String perm;
    public SuperCommand(String name, String perm){
        this.name = name;
        this.perm = perm;
    }

    public String getName() {
        return name;
    }

    public abstract SubCommand[] getSubs();

    private static String[] removeFirst(String[] in){
        String[] r = new String[in.length - 1];
        for (int i = 0; i < r.length; i++) {
            r[i] = in[i+1];
        }
        return r;
    }

    public void register(JavaPlugin plugin){
        plugin.getCommand(name).setTabCompleter((sender, command, alias, args) -> {
            List<String> r = new ArrayList<>();
            if(sender.hasPermission(perm)) {
                for (SubCommand sub : getSubs()) {
                    if (sender.hasPermission(sub.getPrem())) {
                        if (args.length == 0 || args.length == 1) {
                            r.add(sub.getCommandName());
                        } else if (sub.getCommandName().equalsIgnoreCase(args[0])) {
                            r.addAll(sub.tabOptions(removeFirst(args.clone())));
                        }
                    }
                }
            }
            return r;
        });
        plugin.getCommand(name).setExecutor((sender, command, label, args) -> {
            if(sender.hasPermission(perm)) {
                if (args.length != 0) {
                    for (SubCommand sub : getSubs()) {
                        if (sender.hasPermission(sub.getPrem())) {
                            if (sub.getCommandName().equalsIgnoreCase(args[0])) {
                                return sub.runCommand(sender, removeFirst(args.clone()));
                            }
                        }
                    }
                } else {
                    sender.sendMessage(ChatColor.RED + "Sub command isn't given");
                }
            }
            return false;
        });
    }
}
