package dev.anvilcraft.ponder;

import com.tterrag.registrate.providers.ProviderType;
import dev.dubhe.anvilcraft.AnvilCraft;
import dev.dubhe.anvilcraft.api.integration.Integration;
import dev.dubhe.anvilcraft.api.integration.IntegrationType;
import dev.anvilcraft.ponder.data.PonderLangHandler;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.foundation.PonderIndex;
import net.minecraft.resources.ResourceLocation;

import static dev.dubhe.anvilcraft.AnvilCraft.REGISTRATE;

@Integration(value = "ponder", type = {IntegrationType.CLIENT, IntegrationType.DATA})
public class AnvilCraftPonderPlugin implements PonderPlugin {

    /**
     * @return the ModID of the mod that added this plugin
     */
    @Override
    public String getModId() {
        return AnvilCraft.MOD_ID;
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

    public void apply() {
        PonderIndex.addPlugin(new AnvilCraftPonderPlugin());
        REGISTRATE.addDataGenerator(ProviderType.LANG, PonderLangHandler::init);
    }
}
