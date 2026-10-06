package dev.wtfim.registry;

import dev.wtfim.WTFIM;
import dev.wtfim.item.CopperShearsItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(WTFIM.MOD_ID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.register(
            "test_item",
            () -> new Item(new Item.Properties())
    );

    public static final DeferredItem<SwordItem> COPPER_SWORD = ITEMS.register(
            "copper_sword",
            () -> new SwordItem(
                    ModToolTiers.COPPER,
                    new Item.Properties().attributes(
                            SwordItem.createAttributes(ModToolTiers.COPPER, 2, -2.4F)
                    )
            )
    );

    public static final DeferredItem<AxeItem> COPPER_AXE = ITEMS.register(
            "copper_axe",
            () -> new AxeItem(
                    ModToolTiers.COPPER,
                    new Item.Properties().attributes(
                            AxeItem.createAttributes(ModToolTiers.COPPER, 6.0F, -3.2F)
                    )
            )
    );

    public static final DeferredItem<PickaxeItem> COPPER_PICKAXE = ITEMS.register(
            "copper_pickaxe",
            () -> new PickaxeItem(
                    ModToolTiers.COPPER,
                    new Item.Properties().attributes(
                            PickaxeItem.createAttributes(ModToolTiers.COPPER, 0.0F, -2.8F)
                    )
            )
    );

    public static final DeferredItem<ShovelItem> COPPER_SHOVEL = ITEMS.register(
            "copper_shovel",
            () -> new ShovelItem(
                    ModToolTiers.COPPER,
                    new Item.Properties().attributes(
                            ShovelItem.createAttributes(ModToolTiers.COPPER, 0.5F, -3.0F)
                    )
            )
    );

    public static final DeferredItem<HoeItem> COPPER_HOE = ITEMS.register(
            "copper_hoe",
            () -> new HoeItem(
                    ModToolTiers.COPPER,
                    new Item.Properties().attributes(
                            HoeItem.createAttributes(ModToolTiers.COPPER, -2.0F, -2.0F)
                    )
            )
    );

    public static final DeferredItem<ShearsItem> COPPER_SHEARS = ITEMS.register(
            "copper_shears",
            () -> new CopperShearsItem(new Item.Properties().durability(300))
    );

    public static final DeferredItem<ArmorItem> COPPER_HELMET =
            registerCopperArmor("copper_helmet", ArmorItem.Type.HELMET);

    public static final DeferredItem<ArmorItem> COPPER_CHESTPLATE =
            registerCopperArmor("copper_chestplate", ArmorItem.Type.CHESTPLATE);

    public static final DeferredItem<ArmorItem> COPPER_LEGGINGS =
            registerCopperArmor("copper_leggings", ArmorItem.Type.LEGGINGS);

    public static final DeferredItem<ArmorItem> COPPER_BOOTS =
            registerCopperArmor("copper_boots", ArmorItem.Type.BOOTS);

    private ModItems() {
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    private static DeferredItem<ArmorItem> registerCopperArmor(String name, ArmorItem.Type type) {
        return ITEMS.register(
                name,
                () -> new ArmorItem(
                        ModArmorMaterials.COPPER,
                        type,
                        new Item.Properties().durability(200)
                )
        );
    }
}
