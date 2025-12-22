package dev.anvilcraft.ponder.client.screen;

import net.createmod.ponder.foundation.ui.PonderIndexScreen;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddonPonderIndexScreen extends PonderIndexScreen {
    @Override
    protected void init() {
        super.init();
        List<ItemEntry> items = new ArrayList<>(this.items);
        this.items.clear();
        items.stream().filter(entry -> {
            ItemLike item = entry.item();
            if (item == null) return false;
            ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.asItem());
            return id.getNamespace().contains("anvilcraft");
        }).forEach(this.items::add);
        items.sort(Comparator.comparing(ItemEntry::key));
    }
}
