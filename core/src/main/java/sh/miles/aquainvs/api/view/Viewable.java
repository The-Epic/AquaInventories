package sh.miles.aquainvs.api.view;

import java.util.Set;

import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;

/**
 * Viewable interface is used to indicate that something can be viewed by a
 * {@link org.bukkit.entity.HumanEntity} (e.g.
 * {@link org.bukkit.entity.Player}).
 * 
 * @author Miles
 * @version 1.0.0
 * @since 1.0.0
 */
public interface Viewable {

    default void onOpen(HumanEntity entity, InventoryOpenEvent event) {
    }

    default void onClose(HumanEntity entity, InventoryCloseEvent event) {
    }

    /**
     * Used to open the view for the specified {@link HumanEntity}.
     */
    void open(final HumanEntity human);

    /**
     * Used to close the view for the specified {@link HumanEntity}.
     */
    void close(final HumanEntity human);

    /**
     * Used to update the view for the specified {@link HumanEntity}.
     */
    void update(final HumanEntity human);

    /**
     * Used to get all the {@link HumanEntity} that are viewing this viewable.
     * 
     * @return a {@link Set} of {@link HumanEntity} that are viewing this viewable.
     */
    Set<HumanEntity> viewers();
}
