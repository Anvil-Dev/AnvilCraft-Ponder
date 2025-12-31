package dev.anvilcraft.ponder.event;

import dev.anvilcraft.ponder.AnvilCraftPonder;
import dev.dubhe.anvilcraft.api.event.CheckIntegrationLoadedEvent;
import dev.dubhe.anvilcraft.api.event.GuideBookEvent;
import net.createmod.catnip.net.packets.ClientboundSimpleActionPacket;
import net.createmod.catnip.platform.CatnipServices;
import net.createmod.ponder.Ponder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = AnvilCraftPonder.MOD_ID)
public class AddonGuideEventListener {
    @SubscribeEvent
    public static void onHasGuide(GuideBookEvent.HasGuideBookEvent event) {
        event.hasGuideBook();
    }

    @SubscribeEvent
    public static void onHasGuide(CheckIntegrationLoadedEvent event) {
        if (event.getId().equals(Ponder.MOD_ID)) {
            event.setLoaded();
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onOpenGuide(GuideBookEvent.OpenGuideBookEvent event) {
        ServerPlayer player = event.getPlayer();
        Holder<SoundEvent> holder = BuiltInRegistries.SOUND_EVENT.wrapAsHolder(SoundEvents.BOOK_PAGE_TURN);
        Vec3 position = player.position();
        player.connection.send(new ClientboundSoundPacket(
            holder,
            SoundSource.MASTER,
            position.x,
            position.y,
            position.z,
            1.0F,
            1.0F,
            event.getLevel().random.nextLong()
        ));
        CatnipServices.NETWORK.sendToClient(
            player,
            new ClientboundSimpleActionPacket("openAnvilCraftPonder", AnvilCraftPonder.MOD_ID + "index")
        );
        event.setCanceled(true);
    }
}
