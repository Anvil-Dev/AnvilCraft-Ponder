package dev.anvilcraft.ponder.client;

import com.tterrag.registrate.providers.ProviderType;
import dev.anvilcraft.ponder.AnvilCraftPonder;
import dev.anvilcraft.ponder.AnvilCraftPonderScenes;
import dev.anvilcraft.ponder.AnvilCraftPonderTags;
import dev.anvilcraft.ponder.data.lang.PonderLangHandler;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.jetbrains.annotations.NotNull;

import static dev.dubhe.anvilcraft.AnvilCraft.REGISTRATE;

@Mod(value = AnvilCraftPonder.MOD_ID, dist = Dist.CLIENT)
public class AnvilCraftPonderClient implements PonderPlugin {
    public AnvilCraftPonderClient(@NotNull IEventBus modBus, @NotNull ModContainer container) {
        PonderIndex.addPlugin(this);
        REGISTRATE.addDataGenerator(ProviderType.LANG, PonderLangHandler::init);
    }

    /**
     * @return the ModID of the mod that added this plugin
     */
    @Override
    public String getModId() {
        return AnvilCraftPonder.MOD_ID;
    }

    /**
     * Register all the Ponder Scenes added by your Mod
     */
    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        AnvilCraftPonderScenes.register(helper);
    }

    /**
     * Register all the Ponder Tags added by your Mod
     */
    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        AnvilCraftPonderTags.register(helper);
    }
}
