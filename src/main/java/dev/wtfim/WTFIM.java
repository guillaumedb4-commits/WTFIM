package dev.wtfim;

import com.mojang.logging.LogUtils;
import dev.wtfim.registry.ModItems;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(WTFIM.MOD_ID)
public final class WTFIM {
    public static final String MOD_ID = "wtfim";
    public static final Logger LOGGER = LogUtils.getLogger();

    public WTFIM(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        LOGGER.info("WTFIM 0.1.0-alpha foundation initialized");
    }
}
