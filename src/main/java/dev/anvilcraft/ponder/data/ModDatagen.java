package dev.anvilcraft.ponder.data;

import dev.anvilcraft.lib.v2.registrum.providers.ProviderType;
import dev.anvilcraft.ponder.AnvilCraftPonder;
import dev.anvilcraft.ponder.data.lang.PonderLangHandler;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import static dev.anvilcraft.ponder.AnvilCraftPonder.REGISTRUM;

@EventBusSubscriber(modid = AnvilCraftPonder.MOD_ID)
public class ModDatagen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {}

    /**
     * 初始化生成器
     */
    public static void init() {
        REGISTRUM.addDataGenerator(ProviderType.LANG, PonderLangHandler::init);
    }
}
