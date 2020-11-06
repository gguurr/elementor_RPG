package net.elemntor.rpg.api.systems;

public class Event {

    private boolean cancelled;

    public Event() {
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
