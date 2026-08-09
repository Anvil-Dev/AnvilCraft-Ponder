package dev.anvilcraft.ponder;

import com.mojang.logging.LogUtils;
import dev.anvilcraft.lib.v2.registrum.Registrum;
import dev.anvilcraft.ponder.data.ModDatagen;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@Mod(AnvilCraftPonder.MOD_ID)
public class AnvilCraftPonder {
    public static final String MOD_ID = "anvilcraft_ponder";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrum REGISTRUM = Registrum.create(MOD_ID);

    public AnvilCraftPonder(IEventBus modEventBus, ModContainer modContainer) {
        ModDatagen.init();
    }

    public static ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
