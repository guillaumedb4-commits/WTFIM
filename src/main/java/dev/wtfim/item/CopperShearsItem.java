package dev.wtfim.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;

public final class CopperShearsItem extends ShearsItem {
    public CopperShearsItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
        return repairCandidate.is(Items.COPPER_INGOT)
                || super.isValidRepairItem(stack, repairCandidate);
    }
}
