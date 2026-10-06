package dev.wtfim.registry;

import dev.wtfim.WTFIM;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public final class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, WTFIM.MOD_ID);

    public static final DeferredHolder<ArmorMaterial, ArmorMaterial> COPPER =
            ARMOR_MATERIALS.register("copper", ModArmorMaterials::createCopperArmorMaterial);

    private ModArmorMaterials() {
    }

    public static void register(IEventBus modEventBus) {
        ARMOR_MATERIALS.register(modEventBus);
    }

    private static ArmorMaterial createCopperArmorMaterial() {
        Map<ArmorItem.Type, Integer> defense = new EnumMap<>(ArmorItem.Type.class);
        defense.put(ArmorItem.Type.BOOTS, 1);
        defense.put(ArmorItem.Type.LEGGINGS, 3);
        defense.put(ArmorItem.Type.CHESTPLATE, 4);
        defense.put(ArmorItem.Type.HELMET, 2);
        defense.put(ArmorItem.Type.BODY, 4);

        return new ArmorMaterial(
                defense,
                8,
                SoundEvents.ARMOR_EQUIP_IRON,
                () -> Ingredient.of(Items.COPPER_INGOT),
                List.of(new ArmorMaterial.Layer(
                        ResourceLocation.fromNamespaceAndPath(WTFIM.MOD_ID, "copper")
                )),
                0.0F,
                0.0F
        );
    }
}
