package net.elemntor.rpg.api.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class Board {
    private Line[] lines = new Line[15];
    private Objective mainO;
    private Scoreboard mainSB;
    private long frame;
    public Board(String title, String id){
        mainSB = Bukkit.getScoreboardManager().getNewScoreboard();
        mainO = mainSB.registerNewObjective(id, "dummy", title);
        mainO.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    public void setLine(Line line, int index){
        lines[index] = line;
    }

    public void display(Player player){
        for (int i = lines.length-1; i >= 0; i--) {
            Line line = lines[i];
            if(line == null){
                line = new EmptyLine();
            }
            mainO.getScore(line.render(player,frame,i)).setScore(i);
        }
        player.setScoreboard(mainSB);
    }
}
