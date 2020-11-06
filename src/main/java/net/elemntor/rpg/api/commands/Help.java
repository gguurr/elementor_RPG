package net.elemntor.rpg.api.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class Help extends SubCommand {

    private static final int COMMANDS_IN_PAGE = 10;

    private final SuperCommand parent;
    public Help(SuperCommand parent) {
        super("help","");
        this.parent = parent;
    }

    @Override
    public boolean runCommand(CommandSender sender, String[] args) {
        int page = 1;
        try{
            page = Integer.parseInt(args[0]);
        }catch (Exception ignored){
        }
        if(page < 0 || page > numberOfPages()){
            page = 1;
        }
        sender.sendMessage(renderHelpHeader(page));
        int pageStart = (page - 1) * COMMANDS_IN_PAGE;
        for (int i = 0; i < getLeft(page); i++) {
            SubCommand sub = parent.getSubs()[pageStart + i];
            if(sender.hasPermission(sub.getPrem())) {
                sender.sendMessage(renderHelp(sub));
            }
        }
        sender.sendMessage(renderHelpHeader(page));
        return true;
    }

    @Override
    public List<String> tabOptions(String[] args) {
        List<String> r = new ArrayList<>();
        for (int i = 1; i < numberOfPages() + 1; i++) {
            r.add(i + "");
        }
        return r;
    }

    @Override
    public String usage() {
        return "[page]";
    }

    @Override
    public String description() {
        return "Display this message";
    }

    //utils
    private int numberOfPages(){
        return parent.getSubs().length / COMMANDS_IN_PAGE;
    }

    private int getLeft(int page){
        int left = parent.getSubs().length - ((page - 1) * COMMANDS_IN_PAGE);
        return Math.min(left, COMMANDS_IN_PAGE);

    }
    private String renderHelp(SubCommand command){
        return ChatColor.DARK_AQUA + "/" + parent.getName() + " " + command.getCommandName() + ChatColor.AQUA  + " " + command.usage() + ChatColor.RESET + " - " + command.description();
    }
    private String renderHelpHeader(int pageNum){
        return ChatColor.YELLOW + "<===========[" + pageNum + "/" + (numberOfPages() + 1) + "]===========>";
    }
}
