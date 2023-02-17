package sh.miles.aquainvs.api.item;

import java.util.Objects;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import lombok.Builder;
import lombok.NonNull;
import lombok.experimental.SuperBuilder;
import sh.miles.aquainvs.api.item.ItemBuilder.ItemBuilderBuilder;
import sh.miles.aquainvs.items.Button;
import sh.miles.aquainvs.items.ItemEvent;

@SuperBuilder
public class ButtonBuilder {

    protected ItemStack item;
    @Builder.Default
    protected ItemEvent event = (Player player, InventoryClickEvent e) -> {
    };

    public Button make() {
        Objects.requireNonNull(this.item);
        return Button.of(item, event);
    }

    public static Button of(@NonNull final ItemBuilderBuilder<?, ?> builder, @NonNull final ItemEvent event) {
        return ButtonBuilder.builder().item(builder.build().make()).event(event).build().make();
    }

    public static Button of(@NonNull final ItemBuilderBuilder<?, ?> builder) {
        return ButtonBuilder.builder().item(builder.build().make()).build().make();
    }
}
