package net.elemntor.rpg.api.gui;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

public interface UiElement {
    ItemStack toItemStack(HumanEntity viewer, long frame);
    boolean checkItemsStack(ItemStack itemStack);
}
