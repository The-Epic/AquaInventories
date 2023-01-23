package sh.miles.aquainvs.slot;

import org.bukkit.inventory.ItemStack;

import lombok.Data;

/**
 * @author Miles
 * @version 1.0.0
 * @since 1.0.0
 * 
 *        This class represents a slot in an inventory. It holds an
 *        {@link ItemStack} and a {@link SlotEvent}. The SlotEvent is executed
 *        on inventory click.
 */
@Data
public class Slot {

    private final ItemStack item;
    private final SlotEvent event;

}
