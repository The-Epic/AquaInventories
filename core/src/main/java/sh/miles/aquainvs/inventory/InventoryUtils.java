package sh.miles.aquainvs.inventory;

import java.util.Objects;
import java.util.function.Predicate;

import org.bukkit.inventory.ItemStack;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InventoryUtils {

    public static final Predicate<ItemStack> NO_CONDITION = (ItemStack item) -> true;
    public static final Predicate<ItemStack> EMPTY_SLOT_CONDITION = Objects::isNull;

    public static boolean isValidRows(int rows) {
        return ((rows <= 7) && (rows > 0));
    }

    public static boolean isValidSize(int size) {
        return ((size % 9 == 0) && (size <= 54) && (size > 0));
    }

    public static int[] columnSlots(final int columnNumber, final int inventorySize) {
        if (!isValidSize(inventorySize)) {
            throw new IllegalArgumentException("Invalid size of: " + inventorySize + " provided");
        }

        if (columnNumber > 8) {
            throw new IllegalArgumentException(
                    "Invalid column number of: " + columnNumber + " note: column numbers start at 0 and end at 8");
        }

        int[] slots = new int[inventorySize / 9];

        int counterActual = 0;
        for (int i = columnNumber; i < inventorySize; i += 9) {
            slots[counterActual] = i;
            counterActual++;
        }

        return slots;
    }

    public static int[] rowSlots(final int rowNumber, final int inventorySize) {
        if (!isValidSize(inventorySize)) {
            throw new IllegalArgumentException("Invalid size of: " + inventorySize + " provided");
        }

        if (rowNumber > 8) {
            throw new IllegalArgumentException(
                    "Invalid rowNumber of: " + rowNumber
                            + " note: row numbers start at 0 and end at the inventory size divided by 9 - 1 in this case: "
                            + ((inventorySize / 9) - 1));
        }

        int[] slots = new int[9];

        int counter = 0;
        int startSlot = rowNumber * 9;
        for (int i = startSlot; i < startSlot + 9; i += 1) {
            slots[counter] = i;
            counter++;
        }

        return slots;
    }

}