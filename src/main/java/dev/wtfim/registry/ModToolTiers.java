package dev.wtfim.registry;

import dev.wtfim.WTFIM;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.SimpleTier;

public final class ModToolTiers {
    public static final TagKey<Block> INCORRECT_FOR_COPPER_TOOL = TagKey.create(
            Registries.BLOCK,
            ResourceLocation.fromNamespaceAndPath(WTFIM.MOD_ID, "incorrect_for_copper_tool")
    );

    public static final Tier COPPER = new SimpleTier(
            INCORRECT_FOR_COPPER_TOOL,
            350,
            6.0F,
            2.0F,
            13,
            () -> Ingredient.of(Items.COPPER_INGOT)
    );

    private ModToolTiers() {
    }
}
