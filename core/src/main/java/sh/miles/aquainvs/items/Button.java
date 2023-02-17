package sh.miles.aquainvs.items;

import org.bukkit.inventory.ItemStack;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PUBLIC, staticName = "of")
public class Button {

    private final ItemStack item;
    private final ItemEvent event;

}
