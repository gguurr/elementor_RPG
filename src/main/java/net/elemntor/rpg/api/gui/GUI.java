package net.elemntor.rpg.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class GUI implements Listener {
    private HashMap<Integer, UiElement> UIElements;
    private int size;
    private long frame;
    private boolean needUpdates;
    public GUI(int size,boolean needUpdates){
        this.needUpdates = needUpdates;
        frame = 0;
        UIElements = new HashMap<>();
        this.size = size;
    }

    public void setUIElement(int loc,UiElement element) {
        UIElements.put(loc,element);
    }

    public Inventory Render(String name, HumanEntity viewer){
        Inventory inventory = Bukkit.createInventory(null, size, name);
        for (Map.Entry<Integer, UiElement> entry : UIElements.entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().toItemStack(viewer, frame));
        }
        return inventory;
    }

    public void register(JavaPlugin plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
        if (needUpdates) {
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (isThis(player.getOpenInventory().getTopInventory())) {
                        frame++;
                        update(player);
                    }
                }
            }, 0, 5);
        }
    }

    public void update(HumanEntity player){
        for (Map.Entry<Integer,UiElement> entry : UIElements.entrySet()) {
            if(entry.getValue() != null){
                player.getOpenInventory().getTopInventory().setItem(entry.getKey(),entry.getValue().toItemStack(player, frame));
            }
        }
    }

    private boolean isThis(Inventory inv){
        if (inv == null){
            return false;
        }
        if (inv.getSize() != size) {
            return false;
        }
        for (Map.Entry<Integer, UiElement> entry : UIElements.entrySet()) {
            if (entry.getValue().checkItemsStack(inv.getItem(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if (isThis(event.getInventory())){
            int slot = event.getSlot();
            if (UIElements.containsKey(slot)) {
                event.setCancelled(true);
                UiElement element = UIElements.get(slot);
                if (element instanceof Button){
                    ((Button)element).onClick(event.getWhoClicked(), event.getClick());
                    if(isThis(event.getWhoClicked().getOpenInventory().getTopInventory())){
                        update(event.getWhoClicked());
                    }
                }
            }
        }
    }

}
