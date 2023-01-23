package sh.miles.aquainvs.slot;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * @author Miles
 * @version 1.0.0
 * @since 1.0.0
 * 
 *        This interface is used to handle events that occur when a player
 *        clicks a specific slot. It is used in the {@link Slot} class.
 */
@FunctionalInterface
public interface SlotEvent {

    void onEvent(Player whoClicked, InventoryClickEvent event);

}
