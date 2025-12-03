package dev.anvilcraft.ponder;

import dev.anvilcraft.ponder.scene.AnvilScene;
import dev.anvilcraft.ponder.scene.logistics.ChuteScene;
import dev.anvilcraft.ponder.scene.logistics.MagneticChuteScene;
import dev.anvilcraft.ponder.scene.logistics.SlidingRailScene;
import dev.anvilcraft.ponder.scene.power.TransmissionPoleScene;
import dev.anvilcraft.ponder.scene.recipe.BlockRecipeScene;
import dev.anvilcraft.ponder.scene.recipe.BulgingScene;
import dev.anvilcraft.ponder.scene.recipe.CookingScene;
import dev.anvilcraft.ponder.scene.recipe.CorruptedBeaconScene;
import dev.anvilcraft.ponder.scene.recipe.HeaterScene;
import dev.dubhe.anvilcraft.integration.ponder.scene.recipe.IronTrapdoorScene;
import dev.anvilcraft.ponder.scene.recipe.ItemCompressScene;
import dev.anvilcraft.ponder.scene.recipe.ItemCrushScene;
import dev.anvilcraft.ponder.scene.recipe.MeshScene;
import dev.anvilcraft.ponder.scene.recipe.SpaceOvercompressorScene;
import dev.anvilcraft.ponder.scene.recipe.StampingScene;
import dev.anvilcraft.ponder.scene.redstone.BlockComparatorScene;
import dev.anvilcraft.ponder.scene.redstone.BlockDevourerScene;
import dev.anvilcraft.ponder.scene.redstone.BlockPlacerScene;
import dev.anvilcraft.ponder.scene.redstone.MagnetScene;
import dev.anvilcraft.ponder.scene.structure.ImpactPileScene;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class AnvilCraftPonderScenes {
    public static void register(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        // base
        AnvilScene.register(helper);
        // power
        TransmissionPoleScene.register(helper);
        // recipe
        BlockRecipeScene.register(helper);
        ItemCompressScene.register(helper);
        BulgingScene.register(helper);
        CookingScene.register(helper);
        IronTrapdoorScene.register(helper);
        StampingScene.register(helper);
        ItemCrushScene.register(helper);
        MeshScene.register(helper);
        HeaterScene.register(helper);
        CorruptedBeaconScene.register(helper);
        SpaceOvercompressorScene.register(helper);
        // redstone
        MagnetScene.register(helper);
        BlockComparatorScene.register(helper);
        BlockPlacerScene.register(helper);
        BlockDevourerScene.register(helper);
        // structure
        ImpactPileScene.register(helper);
        // logistics
        ChuteScene.register(helper);
        MagneticChuteScene.register(helper);
        SlidingRailScene.register(helper);
    }
}
