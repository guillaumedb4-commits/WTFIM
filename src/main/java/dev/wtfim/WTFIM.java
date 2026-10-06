package dev.wtfim;

import com.mojang.logging.LogUtils;
import dev.wtfim.compat.OvergearedTradeCompat;
import dev.wtfim.registry.ModArmorMaterials;
import dev.wtfim.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(WTFIM.MOD_ID)
public final class WTFIM {
    public static final String MOD_ID = "wtfim";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WTFIM(IEventBus modEventBus) {
        ModArmorMaterials.register(modEventBus);
        ModItems.register(modEventBus);
        NeoForge.EVENT_BUS.register(OvergearedTradeCompat.class);
        LOGGER.info("WTFIM initialized");
    }
}
