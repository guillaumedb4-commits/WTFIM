package dev.wtfim.registry;

import dev.wtfim.WTFIM;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WTFIM.MOD_ID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.register(
            "test_item",
            () -> new Item(new Item.Properties())
    );

    private ModItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
