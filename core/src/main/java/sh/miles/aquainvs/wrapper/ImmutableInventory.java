package sh.miles.aquainvs.wrapper;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

/**
 * Immutable wrapper for InventoryWrapper.
 */
public class ImmutableInventory {

    private final InventoryWrapper inventory;

    public ImmutableInventory(InventoryWrapper inventory) {
        this.inventory = inventory;
    }

    public void open(HumanEntity humanEntity) {
        humanEntity.openInventory(inventory.getInventory());
    }

    public void close(HumanEntity humanEntity) {
        humanEntity.closeInventory();
    }

    public ItemStack getItem(int slot) {
        return inventory.getInventory().getItem(slot);
    }

    public ItemStack[] getContents() {
        return inventory.getInventory().getContents();
    }

    public InventoryWrapper mutableCopy() {
        return new InventoryWrapper(inventory);
    }

}
