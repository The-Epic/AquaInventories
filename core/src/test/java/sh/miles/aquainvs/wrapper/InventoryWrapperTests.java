package sh.miles.aquainvs.wrapper;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.bukkit.inventory.ItemStack;
import org.junit.jupiter.api.Test;

import sh.miles.aquainvs.BasicTest;

public class InventoryWrapperTests extends BasicTest {

    @Test
    public void testInventoryWrapper() {
        assertDoesNotThrow(() -> new InventoryWrapper(), "InventoryWrapper constructor failed");
        assertDoesNotThrow(() -> new InventoryWrapper(9), "InventoryWrapper size constructor failed");
        assertDoesNotThrow(() -> new InventoryWrapper("title"), "InventoryWrapper title constructor failed");
        assertDoesNotThrow(() -> new InventoryWrapper(9, "title"),
                "InventoryWrapper size and title constructor failed");

        assertThrows(IllegalArgumentException.class, () -> new InventoryWrapper(10),
                "InventoryWrapper size constructor failed to throw exception");

        assertThrows(NullPointerException.class, () -> new InventoryWrapper((String) null),
                "InventoryWrapper title constructor failed to throw exception");
    }

    @Test
    public void testInventoryWrapperSetSlot() {
        InventoryWrapper wrapper = new InventoryWrapper();
        final ItemStack item = new ItemStack(org.bukkit.Material.STONE);

        wrapper.setItem(0, item);

        assertEquals(item, wrapper.getItem(0), "InventoryWrapper setItem failed");
    }

    @Test
    public void testInventoryWrapperFill() {
        InventoryWrapper wrapper = new InventoryWrapper(54);
        final ItemStack item = new ItemStack(org.bukkit.Material.STONE);

        wrapper.fill(item);

        for (int i = 0; i < 54; i++) {
            assertEquals(item, wrapper.getItem(i), "InventoryWrapper fill failed");
        }
    }

    @Test
    public void testInventoryWrapperClear() {
        InventoryWrapper wrapper = new InventoryWrapper(54);
        final ItemStack item = new ItemStack(org.bukkit.Material.STONE);

        wrapper.fill(item);
        wrapper.clear();

        for (int i = 0; i < 54; i++) {
            assertNull(wrapper.getItem(i), "InventoryWrapper clear failed");
        }
    }

    @Test
    public void testInventoryWrapperClearSlot() {
        InventoryWrapper wrapper = new InventoryWrapper(54);
        final ItemStack item = new ItemStack(org.bukkit.Material.STONE);

        wrapper.fill(item);
        wrapper.clear(0);

        assertNull(wrapper.getItem(0), "InventoryWrapper clear slot failed");
    }

    @Test
    public void testInventoryWrapperColumn() {
        InventoryWrapper wrapper = new InventoryWrapper(54);
        final ItemStack item = new ItemStack(org.bukkit.Material.STONE);

        wrapper.column(item, 0);

        for (int i = 0; i < 54; i += 9) {
            assertEquals(item, wrapper.getItem(i), "InventoryWrapper fill column failed");
        }
    }

    @Test
    public void testInventoryWrapperRow() {
        InventoryWrapper wrapper = new InventoryWrapper(54);
        final ItemStack item = new ItemStack(org.bukkit.Material.STONE);

        wrapper.row(item, 0);

        for (int i = 0; i < 9; i++) {
            assertEquals(item, wrapper.getItem(i), "InventoryWrapper fill row failed");
        }
    }

    @Test
    public void testInventoryWrapperBorder() {
        InventoryWrapper wrapper = new InventoryWrapper(54);
        final ItemStack item = new ItemStack(org.bukkit.Material.STONE);

        wrapper.border(item);

        for (int i = 0; i < 54; i++) {
            if (i < 9 || i > 44 || i % 9 == 0 || i % 9 == 8) {
                assertEquals(item, wrapper.getItem(i), "InventoryWrapper fill border failed");
            }
        }
    }

    @Test
    public void testInventoryWrapperSurround() {
        InventoryWrapper wrapper = new InventoryWrapper(54);
        final ItemStack surroundItem = new ItemStack(org.bukkit.Material.STONE);
        final ItemStack centerItem = new ItemStack(org.bukkit.Material.DIRT);

        wrapper.setItem(23, centerItem);

        wrapper.surround(surroundItem, 23);

        // check if the center item is still there and the surrounding items are correct
        assertEquals(centerItem, wrapper.getItem(23), "InventoryWrapper surround failed");
        assertEquals(surroundItem, wrapper.getItem(22), "InventoryWrapper surround failed");
        assertEquals(surroundItem, wrapper.getItem(24), "InventoryWrapper surround failed");
        assertEquals(surroundItem, wrapper.getItem(31), "InventoryWrapper surround failed");
        assertEquals(surroundItem, wrapper.getItem(33), "InventoryWrapper surround failed");
    }

}
