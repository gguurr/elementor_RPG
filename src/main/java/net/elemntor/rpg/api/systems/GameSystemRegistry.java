package net.elemntor.rpg.api.systems;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GameSystemRegistry {

    private List<GameSystem> gameSystemList;

    public GameSystemRegistry() {
        gameSystemList = new ArrayList<>();
    }

    public void registerGameSystem(GameSystem gameSystem) {
        gameSystemList.add(gameSystem);
    }

    public void sendEvent(Event event) {
        List<EventSubscriber> methods = gameSystemList.stream().map(gameSystem -> gameSystem.getEvents(event)).flatMap(Collection::stream).collect(Collectors.toList());
        for (EventSubscriber eventMethod : methods) {
            eventMethod.execute(event);
            if (event.isCancelled()) {
                break;
            }
        }
    }

    public void load() {
        gameSystemList.forEach(gameSystem -> gameSystem.initialize(this));
        gameSystemList.forEach(GameSystem::onLoad);
    }

    public void unload() {
        gameSystemList.forEach(GameSystem::onUnload);
    }

}
