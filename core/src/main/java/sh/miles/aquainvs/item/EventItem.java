package sh.miles.aquainvs.item;

import java.util.function.BiConsumer;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import lombok.Data;

/**
 * EventItem is a class that represents an item in an inventory that has
 * that can trigger an event when clicked.
 * 
 * @author Miles
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
@Data
public class EventItem {

    public static final BiConsumer<HumanEntity, InventoryClickEvent> EMPTY_EVENT = (HumanEntity ent,
            InventoryClickEvent e) -> {
    };
    public static final EventItem EMPTY = new EventItem(new ItemStack(Material.AIR), EMPTY_EVENT);

    private final ItemStack item;
    private final BiConsumer<HumanEntity, InventoryClickEvent> event;

    public static EventItem of(ItemStack item, BiConsumer<HumanEntity, InventoryClickEvent> event) {
        return new EventItem(item, event);
    }

    public static EventItem of(ItemStack item) {
        return new EventItem(item, EMPTY_EVENT);
    }

    public static EventItem of(Material material, BiConsumer<HumanEntity, InventoryClickEvent> event) {
        return new EventItem(new ItemStack(material), event);
    }

    public static EventItem of(Material material) {
        return new EventItem(new ItemStack(material), EMPTY_EVENT);
    }
}
