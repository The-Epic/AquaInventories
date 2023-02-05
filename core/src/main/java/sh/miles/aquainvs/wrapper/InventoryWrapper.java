package sh.miles.aquainvs.wrapper;

import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Sets;

import lombok.Getter;
import lombok.NonNull;

/**
 * Inventory Wrapper wraps the bukkit inventory and provides more
 * functionality. It also provides a way to create immutable inventories
 * 
 * @author Miles
 * @version 1.0.0
 * @since 1.0.0
 * 
 */
public class InventoryWrapper {

    public static final int ROW_SIZE = 9;

    @Getter
    private final String title;
    @Getter
    private final int size;
    @Getter
    private final Inventory inventory;

    public InventoryWrapper(int size, @NonNull String title) {

        if (size % ROW_SIZE != 0 || size <= 0 || size > 54) {
            throw new IllegalArgumentException("Inventory size must be a multiple of 9");
        }

        this.title = title;
        this.size = size;

        this.inventory = Bukkit.getServer().createInventory(null, size, title);
    }

    public InventoryWrapper(int size) {
        this(size, "");
    }

    public InventoryWrapper(@NonNull String title) {
        this(ROW_SIZE, title);
    }

    public InventoryWrapper(@NonNull InventoryWrapper inventory) {
        this(inventory.getSize(), inventory.getTitle());
        for (int i = 0; i < inventory.getSize(); i++) {
            this.inventory.setItem(i, inventory.getItem(i));
        }
    }

    public InventoryWrapper() {
        this(ROW_SIZE, "");
    }

    /**
     * Gets the item at the specified slot
     * 
     * @param slot The slot to get the item from
     * @return The item at the specified slot
     */
    public ItemStack getItem(int slot) {
        return inventory.getItem(slot);
    }

    /**
     * Sets the item at the specified slot
     * 
     * @param slot The slot to set the item at
     * @param item The item to set
     */
    public void setItem(int slot, ItemStack item) {
        inventory.setItem(slot, item);
    }

    /**
     * Fills the inventory with the provided item
     * 
     * @param item The item to fill the inventory with
     */
    public Set<Integer> fill(ItemStack item) {
        final Set<Integer> modified = new java.util.HashSet<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            setItem(i, item);
            modified.add(i);
        }
        return modified;
    }

    /**
     * Fills the area with the provided item
     * 
     * @param item  The item to fill the area with
     * @param start The start of the area
     * @param end   The end of the area
     * @return A set of the slots that were modified
     */
    public Set<Integer> fill(ItemStack item, int start, int end) {
        final Set<Integer> modified = new java.util.HashSet<>();
        for (int i = start; i < end; i++) {
            setItem(i, item);
            modified.add(i);
        }
        return modified;
    }

    /**
     * Fills the inventory with the provided item, ignoring any slots that are
     * already
     * 
     * @param item The item to fill the inventory with
     * 
     * @return A set of the slots that were modified
     */
    public Set<Integer> fillIgnoreFull(ItemStack item) {
        final Set<Integer> modified = new java.util.HashSet<>();
        for (int i = 0; i < inventory.getSize(); i++) {
            if (getItem(i) == null) {
                setItem(i, item);
                modified.add(i);
            }
        }
        return modified;
    }

    /**
     * Fills the area with the provided item, ignoring any slots that are already
     * filled
     * 
     * @param item  The item to fill the area with
     * @param start
     * @param end
     */
    public Set<Integer> fillIgnoreFull(ItemStack item, int start, int end) {
        final Set<Integer> modified = new java.util.HashSet<>();
        for (int i = start; i < end; i++) {
            if (getItem(i) == null) {
                setItem(i, item);
                modified.add(i);
            }
        }

        return modified;
    }

    /**
     * Fills the column with the provided item
     * 
     * @param item The item to fill the column with
     * @param x    The column to fill
     */
    public Set<Integer> column(ItemStack item, int x) {
        final Set<Integer> modified = new java.util.HashSet<>();
        for (int y = 0; y < inventory.getSize() / ROW_SIZE; y++) {
            setItem(toSlot(x, y), item);
            modified.add(toSlot(x, y));
        }

        return modified;
    }

    /**
     * Fills the column with the provided item, ignoring any slots that are already
     * 
     * @param item The item to fill the column with
     * @param x    The column to fill
     */
    public Set<Integer> columnIgnoreFull(ItemStack item, int x) {
        final Set<Integer> modified = new java.util.HashSet<>();
        for (int y = 0; y < inventory.getSize() / ROW_SIZE; y++) {
            int slot = toSlot(x, y);
            if (getItem(slot) == null) {
                setItem(slot, item);
                modified.add(slot);
            }
        }
        return modified;
    }

    /**
     * Fills the row with the provided item
     * 
     * @param item The item to fill the row with
     * @param y    The row to fill
     */
    public Set<Integer> row(ItemStack item, int y) {
        final Set<Integer> modified = new java.util.HashSet<>();
        for (int x = 0; x < ROW_SIZE; x++) {
            final int slot = toSlot(x, y);
            setItem(slot, item);
            modified.add(slot);
        }
        return modified;
    }

    /**
     * Fills the row with the provided item, ignoring any slots that are already
     * 
     * @param item The item to fill the row with
     * @param y    The row to fill
     */
    public Set<Integer> rowIgnoreFull(ItemStack item, int y) {
        final Set<Integer> modified = new java.util.HashSet<>();
        for (int x = 0; x < ROW_SIZE; x++) {
            final int slot = toSlot(x, y);
            if (getItem(slot) == null) {
                setItem(slot, item);
                modified.add(toSlot(x, y));
            }
        }
        return modified;
    }

    /**
     * Fills the border of the inventory with the provided item
     * 
     * @param item The item to fill the border with
     */
    public Set<Integer> border(ItemStack item) {
        Set<Integer> fill0 = fill(item, 0, ROW_SIZE - 1);
        Set<Integer> fill1 = fill(item, inventory.getSize() - ROW_SIZE, inventory.getSize());
        Set<Integer> column0 = column(item, 0);
        Set<Integer> column1 = column(item, ROW_SIZE - 1);

        return Sets.union(Sets.union(fill0, fill1), Sets.union(column0, column1));
    }

    /**
     * Fills the border of the inventory with the provided item, ignoring any slots
     * that are already occupied
     * 
     * @param item The item to fill the border with
     */
    public Set<Integer> borderIgnoreFull(ItemStack item) {
        Set<Integer> fill0 = fillIgnoreFull(item, 0, ROW_SIZE - 1);
        Set<Integer> fill1 = fillIgnoreFull(item, inventory.getSize() - ROW_SIZE, inventory.getSize());
        Set<Integer> column0 = columnIgnoreFull(item, 0);
        Set<Integer> column1 = columnIgnoreFull(item, ROW_SIZE - 1);

        return Sets.union(Sets.union(fill0, fill1), Sets.union(column0, column1));
    }

    /**
     * Surrounds the given slot with the provided item.
     * The slot given is always surrounded in a 3x3 grid if possible. If the slot is
     * on the edge of the inventory, the slots that can be surrounded will be and
     * those outside the inventory will be ignored
     * 
     * @param item The item to surround the slot with
     * @param slot The slot to surround
     * 
     * @return all slots modified
     */
    public Set<Integer> surround(ItemStack item, int slot) {
        Set<Integer> modified = new java.util.HashSet<>();
        int cornerX = slot % ROW_SIZE - 1;
        int cornerY = slot / ROW_SIZE - 1;

        for (int x = cornerX; x < cornerX + 3; x++) {
            for (int y = cornerY; y < cornerY + 3; y++) {

                if (x == slot % ROW_SIZE && y == slot / ROW_SIZE) {
                    continue;
                }

                if (x >= 0 && x < ROW_SIZE && y >= 0 && y < inventory.getSize() / ROW_SIZE) {
                    final int s = toSlot(x, y);
                    setItem(s, item);
                    modified.add(s);
                }
            }
        }

        return modified;
    }

    /**
     * Surrounds the given slot with the provided item.
     * The slot given is always surrounded in a 3x3 grid if possible. If the slot is
     * on the edge of the inventory, the slots that can be surrounded will be and
     * those outside the inventory will be ignored
     * 
     * @param item The item to surround the slot with
     * @param slot The slot to surround
     * 
     * @return all slots modified
     */
    @SuppressWarnings("all")
    public Set<Integer> surroundIgnoreFull(ItemStack item, int slot) {

        Set<Integer> modified = new java.util.HashSet<>();

        int cornerX = slot % ROW_SIZE - 1;
        int cornerY = slot / ROW_SIZE - 1;

        for (int x = cornerX; x < cornerX + 3; x++) {
            for (int y = cornerY; y < cornerY + 3; y++) {

                if (x == slot % ROW_SIZE && y == slot / ROW_SIZE) {
                    continue;
                }

                if (x >= 0 && x < ROW_SIZE && y >= 0 && y < inventory.getSize() / ROW_SIZE) {
                    final int s = toSlot(x, y);
                    if (getItem(s) == null) {
                        setItem(s, item);
                        modified.add(s);
                    }
                }
            }
        }

        return modified;
    }

    public void clear() {
        inventory.clear();
    }

    public void clear(int slot) {
        inventory.clear(slot);
    }

    public static int toSlot(int x, int y) {
        return x + y * ROW_SIZE;
    }

}
