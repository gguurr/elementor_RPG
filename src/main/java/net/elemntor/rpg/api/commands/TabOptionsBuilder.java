package net.elemntor.rpg.api.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class TabOptionsBuilder {
    private List<Callable<List<String>>> argsF = new ArrayList<>();
    public TabOptionsBuilder range(int min, int max, int jump){
        argsF.add(() -> {
            List<String> r = new ArrayList<>();
            for (int i = min; i < max; i += jump) {
                r.add(i + "");
            }
            return r;
        });
        return this;
    }
    public TabOptionsBuilder player(){
        argsF.add(() -> Bukkit.getOnlinePlayers().stream().map(HumanEntity::getName).collect(Collectors.toList()));
        return this;
    }

    public TabOptionsBuilder list(String... option){
        argsF.add(() -> Arrays.asList(option));
        return this;
    }


    public List<String> build(String[] args)  {
        int id = args.length;
        if(id > 0){
            id -= 1;
        }
        if(id < argsF.size()){
            try {
                return argsF.get(id).call();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }
}
