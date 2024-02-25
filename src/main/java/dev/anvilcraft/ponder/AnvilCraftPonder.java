package dev.anvilcraft.ponder;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import dev.anvilcraft.ponder.data.ModDatagen;
import dev.anvilcraft.ponder.init.AddonBlocks;
import dev.anvilcraft.ponder.init.AddonItemGroups;
import dev.anvilcraft.ponder.init.AddonItems;
import dev.anvilcraft.lib.config.ConfigManager;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

@Mod(AnvilCraftPonder.MOD_ID)
public class AnvilCraftPonder {
    public static final String MOD_ID = "anvilcraft_ponder";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final AddonConfig CONFIG = ConfigManager.register(AnvilCraftPonder.MOD_ID, AddonConfig::new);
    public static final Registrate REGISTRATE = Registrate.create(MOD_ID);

    public AnvilCraftPonder(@NotNull IEventBus modEventBus, @NotNull ModContainer modContainer) {
        AddonItemGroups.register(modEventBus);
        AddonBlocks.register();
        AddonItems.register();
        ModDatagen.init();
    }

    public static @NotNull ResourceLocation of(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}
