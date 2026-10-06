package dev.wtfim.compat;

import java.util.List;
import java.util.Set;

import dev.wtfim.WTFIM;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

public final class OvergearedTradeCompat {
    private static final Set<String> BLOCKED_FINISHED_COPPER_IDS = Set.of(
            "overgeared:copper_sword",
            "overgeared:copper_axe",
            "overgeared:copper_pickaxe",
            "overgeared:copper_shovel",
            "overgeared:copper_hoe",
            "overgeared:copper_helmet",
            "overgeared:copper_chestplate",
            "overgeared:copper_leggings",
            "overgeared:copper_boots"
    );

    private OvergearedTradeCompat() {
    }

    public static void onVillagerTrades(VillagerTradesEvent event) {
        if (!isSmithingProfession(event.getType())) {
            return;
        }

        int wrapped = 0;
        for (int level = 1; level <= 5; level++) {
            List<VillagerTrades.ItemListing> trades = event.getTrades().get(level);
            if (trades == null || trades.isEmpty()) {
                continue;
            }

            for (int index = 0; index < trades.size(); index++) {
                VillagerTrades.ItemListing original = trades.get(index);
                trades.set(index, (trader, random) -> filterOffer(original.getOffer(trader, random)));
                wrapped++;
            }
        }

        WTFIM.LOGGER.info("Installed Copper finished-trade filter for {} ({} listings wrapped)", event.getType(), wrapped);
    }

    private static boolean isSmithingProfession(VillagerProfession profession) {
        return profession == VillagerProfession.WEAPONSMITH
                || profession == VillagerProfession.TOOLSMITH
                || profession == VillagerProfession.ARMORER;
    }

    private static MerchantOffer filterOffer(MerchantOffer offer) {
        if (offer == null) {
            return null;
        }

        String resultId = BuiltInRegistries.ITEM.getKey(offer.getResult().getItem()).toString();
        if (BLOCKED_FINISHED_COPPER_IDS.contains(resultId)) {
            WTFIM.LOGGER.info("Suppressed obsolete finished Copper villager offer: {}", resultId);
            return null;
        }

        return offer;
    }
}
