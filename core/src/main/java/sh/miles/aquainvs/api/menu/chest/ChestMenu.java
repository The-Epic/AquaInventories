package sh.miles.aquainvs.api.menu.chest;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;

import lombok.NonNull;
import sh.miles.aquainvs.api.menu.Initializable;
import sh.miles.aquainvs.api.menu.Slotted;
import sh.miles.aquainvs.api.view.Viewable;
import sh.miles.aquainvs.inventory.chest.ChestInventory;
import sh.miles.aquainvs.items.Button;

public abstract class ChestMenu implements Initializable, Slotted, Viewable {

    private final ChestInventory chest;

    protected ChestMenu(@NonNull final String title, final int size) {
        this.chest = new ChestInventory(title, size);

        init();
    }

    @Override
    public void open(HumanEntity human) {
        chest.open(human);
    }

    @Override
    public void close(HumanEntity human) {
        human.closeInventory();
    }

    @Override
    public void update(HumanEntity human) {
        throw new UnsupportedOperationException("ChestMenu's can not be updated");
    }

    @Override
    public Set<HumanEntity> viewers() {
        return chest.viewers();
    }

    @Override
    public Button get(int slot) {
        return this.chest.item(slot);
    }

    protected void set(final int slot, @NonNull final Button button) {
        this.chest.set(slot, button);
    }

    protected boolean add(@NonNull final Button button) {
        return this.chest.add(button);

    }

    protected void fill(@NonNull final IntStream stream, Button button) {
        this.chest.fill(stream, button);
    }

    protected void fill(@NonNull final int[] slots, Button button) {
        this.chest.fill(slots, button);
    }

    protected void fill(@NonNull final IntStream stream, @NonNull final Button button,
            @NonNull final Predicate<ItemStack> condition) {
        this.chest.fill(stream, button, condition);
    }

    protected void fill(@NonNull final int[] slots, @NonNull final Button button,
            @NonNull final Predicate<ItemStack> condition) {
        this.chest.fill(slots, button, condition);
    }

    public String title() {
        return this.chest.getTitle();
    }

    public int size() {
        return this.chest.getSize();
    }
}
