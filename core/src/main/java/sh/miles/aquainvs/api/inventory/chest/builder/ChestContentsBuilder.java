package sh.miles.aquainvs.api.inventory.chest.builder;

import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;
import sh.miles.aquainvs.items.Button;

/**
 * Builder for creating chest contents
 */
public class ChestContentsBuilder {

    private Map<Integer, Button> contents;

    private ChestContentsBuilder() {
        this.contents = new HashMap<>();
    }

    private ChestContentsBuilder(@NonNull final Map<Integer, Button> contents) {
        this.contents = contents;
    }

    public ChestContentsBuilder add(final int slot, final Button button) {
        this.contents.put(slot, button);
        return this;
    }

    public ChestContentsBuilder remove(final int slot) {
        this.contents.remove(slot);
        return this;
    }

    public ChestContentsBuilder addAll(final Map<Integer, Button> items) {
        this.contents.putAll(items);
        return this;
    }

    public ChestContentsBuilder set(final Map<Integer, Button> contents){
        this.contents = contents;
        return this;
    }

    public Map<Integer, Button> build() {
        return new HashMap<>(this.contents);
    }

    public static ChestContentsBuilder of(@NonNull final Map<Integer, Button> contents) {
        return new ChestContentsBuilder(contents);
    }

    public static ChestContentsBuilder builder() {
        return new ChestContentsBuilder();
    }

}
