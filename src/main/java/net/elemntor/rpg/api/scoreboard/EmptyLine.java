package net.elemntor.rpg.api.scoreboard;

import org.bukkit.entity.Player;

public class EmptyLine implements Line{

    @Override
    public String render(Player viewer, long frame, int index) {
        String r = "";
        for (int i = 0; i < index; i++) {
            r += " ";
        }
        return r;
    }
}
