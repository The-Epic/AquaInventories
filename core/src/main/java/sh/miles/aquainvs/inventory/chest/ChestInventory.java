package sh.miles.aquainvs.inventory.chest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.NonNull;
import sh.miles.aquainvs.inventory.InventoryUtils;
import sh.miles.aquainvs.items.Button;

public class ChestInventory {

    @Getter
    private final String title;
    @Getter
    private final int size;

    private final Inventory bukkitInventory;
    private Map<Integer, Button> contents;

    public ChestInventory(@NonNull final String title, final int rows) {

        if (!InventoryUtils.isValidRows(rows)) {
            throw new IllegalArgumentException(
                    rows + " is not a valid rows value it must be between 1 and 6 inclusive");
        }

        this.title = title;
        this.size = rows * 9;

        this.contents = new HashMap<>();
        this.bukkitInventory = Bukkit.createInventory(null, this.size, this.title);
    }

    /**
     * Adds an item to the first empty slot
     * 
     * @param button the button to add to the slot
     * @return true if it was placed, false if it was not placed
     */
    public boolean add(final Button button) {
        final int firstEmpty = this.bukkitInventory.firstEmpty();
        if (firstEmpty == -1) {
            return false;
        }

        this.bukkitInventory.setItem(firstEmpty, button.getItem());
        this.contents.put(firstEmpty, button);

        return true;
    }

    /**
     * Sets an item at the specified slot in the inventory
     * 
     * @param slot   the slot to set at
     * @param button the button to set
     */
    public void set(final int slot, @NonNull final Button button) {
        this.bukkitInventory.setItem(slot, button.getItem());
        this.contents.put(slot, button);
    }

    /**
     * Removes an item from the inventory
     * 
     * @param slot the slot to remove the item from
     * @return a button if an item was removed otherwise it will be null
     */
    public Button remove(final int slot) {
        if (this.contents.containsKey(slot)) {
            this.bukkitInventory.setItem(slot, new ItemStack(Material.AIR));
            return this.contents.get(slot);
        }
        return null;
    }

    /**
     * fills the given slots with the provided button
     * 
     * @param slots       the slots to place the items in
     * @param button      the button to place in the slots
     * @param shouldPlace the conditions for the item being set the given ItemStack
     *                    is the current item in the slot and it MAY be null
     */
    public void fill(@NonNull final int[] slots, @NonNull final Button button, Predicate<ItemStack> shouldPlace) {
        for (int slot : slots) {
            final ItemStack curItem = this.bukkitInventory.getItem(slot);
            if (shouldPlace.test(curItem)) {
                set(slot, button);
            }
        }
    }

    /**
     * fills the given slots with the provided button
     * 
     * @param stream      the stream of ints to place the items in
     * @param button      the button to place in the slots
     * @param shouldPlace the conditions for the item being set the given ItemStack
     *                    is the current item in the slot and it MAY be null
     */
    public void fill(@NonNull final IntStream stream, @NonNull final Button button, Predicate<ItemStack> shouldPlace) {
        fill(stream.toArray(), button, shouldPlace);
    }

    /**
     * fills the given slots with the provided button
     * 
     * @param slots  the slots to place the items in
     * @param button the button to place in the slots
     */
    public void fill(@NonNull final int[] slots, @NonNull final Button button) {
        fill(slots, button, InventoryUtils.NO_CONDITION);
    }

    /**
     * fills the given slots with the provided button
     * 
     * @param stream the stream of ints to place the items in
     * @param button the button to place in the slots
     */
    public void fill(@NonNull final IntStream stream, @NonNull final Button button) {
        fill(stream.toArray(), button);
    }

    public void contents(@NonNull final Map<Integer, Button> contents) {
        this.contents = contents;
        this.bukkitInventory.clear();
        for (Entry<Integer, Button> entry : contents.entrySet()) {
            this.bukkitInventory.setItem(entry.getKey(), entry.getValue().getItem());
        }
    }

    /**
     * Open the inventory for the provided entity
     * 
     * @param entity the entity to open the inventory for
     */
    public void open(HumanEntity entity) {
        entity.openInventory(this.bukkitInventory);
    }

    /**
     * Gets an item from the designated slot
     * 
     * @param slot the slot
     * @return the Button
     */
    @Nullable
    public Button item(final int slot) {
        return this.contents.get(slot);
    }

    @NonNull
    public Set<HumanEntity> viewers(){
        return new HashSet<>(this.bukkitInventory.getViewers());
    }
}
