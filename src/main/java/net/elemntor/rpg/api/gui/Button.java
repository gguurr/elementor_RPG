package net.elemntor.rpg.api.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.ClickType;

public interface Button extends UiElement {
    void onClick(HumanEntity clicker, ClickType clickType);
}
