package dev.anvilcraft.ponder.mixin;

import net.createmod.ponder.foundation.ui.PonderIndexScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PonderIndexScreen.class)
public interface IPonderIndexScreen {
    @Invoker
    boolean invokeIsItemIncluded(PonderIndexScreen.ItemEntry entry);
}
