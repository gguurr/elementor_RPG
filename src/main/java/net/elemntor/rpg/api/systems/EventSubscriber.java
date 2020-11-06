package net.elemntor.rpg.api.systems;

public interface EventSubscriber<T extends Event> {

    void execute(T event);
}

