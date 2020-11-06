package net.elemntor.rpg.api.scoreboard;

import org.bukkit.entity.Player;

public interface Line {
    String render(Player viewer,long frame, int index);
}
