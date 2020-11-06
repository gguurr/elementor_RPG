package net.elemntor.rpg.api.systems;

import java.util.*;

public abstract class GameSystem {

    private GameSystemRegistry registry;
    private Map<Class, List<EventSubscriber>> registeredEvents;

    public GameSystem() {
        registeredEvents = new HashMap<>();
    }

    public final <T extends Event> void registerEvent(Class<T> eventType, EventSubscriber<T> method) {
        registeredEvents.computeIfAbsent(eventType, e -> new ArrayList<>()).add(method);
    }

    public final List<EventSubscriber> getEvents(Event event) {
        return registeredEvents.getOrDefault(event.getClass(), Collections.emptyList());
    }

    protected final void send(Event event) {
        registry.sendEvent(event);
    }

    public abstract void onLoad();
    public abstract void onUnload();
    public void initialize(GameSystemRegistry registry){
        this.registry = registry;
    }

}
