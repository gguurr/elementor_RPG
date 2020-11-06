package net.elemntor.rpg.api.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public abstract class SubCommand {

    private String command;
    private String prem;

    public SubCommand(String command, String prem){
        this.command = command;
        this.prem = prem;
    }

    public abstract boolean runCommand(CommandSender sender, String[] args);
    public abstract List<String> tabOptions(String[] args);
    public abstract String usage();
    public abstract String description();

    public String getCommandName() {
        return command;
    }
    public String getPrem() {
        return prem;
    }

}
