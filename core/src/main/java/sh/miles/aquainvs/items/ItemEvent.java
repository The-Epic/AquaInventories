package sh.miles.aquainvs.items;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * ItemEvent is an interface that is triggered when an item is clicked inside of
 * a {@link sh.miles.aquainvs.inventory.chest.ChestInventory}
 */
public interface ItemEvent {

    /**
     * Called when an item is clicked
     * 
     * @param player the player who clicked which may be null under the
     *               circumstances a HumanEntity clicked even though this case is
     *               unlikely it is reccomended you check if player is null before
     *               using it
     * @param event  The InventoryClickEvent provided by Bukkit
     */
    void click(@Nullable final Player clicker, InventoryClickEvent event);

}
