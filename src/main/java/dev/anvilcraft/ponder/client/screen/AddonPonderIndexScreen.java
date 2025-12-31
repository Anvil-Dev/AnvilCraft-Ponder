package dev.anvilcraft.ponder.client.screen;

import dev.anvilcraft.ponder.mixin.IPonderIndexScreen;
import net.createmod.catnip.layout.PaginationState;
import net.createmod.catnip.registry.RegisteredObjectsHelper;
import net.createmod.ponder.enums.PonderGuiTextures;
import net.createmod.ponder.foundation.PonderIndex;
import net.createmod.ponder.foundation.ui.PonderButton;
import net.createmod.ponder.foundation.ui.PonderIndexScreen;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ItemLike;

import java.util.Comparator;
import java.util.Map;

public class AddonPonderIndexScreen extends PonderIndexScreen {
    @Override
    protected void init() {
        super.init();

        this.items.clear();
        PonderIndex.getSceneAccess()
            .getRegisteredEntries()
            .stream()
            .map(Map.Entry::getKey)
            .distinct()
            .map(key -> new ItemEntry(RegisteredObjectsHelper.getItemOrBlock(key), key))
            .filter(entry -> entry.item() != null)
            .filter(entry -> {
                ItemLike item = entry.item();
                //noinspection ConstantValue
                if (item == null) return false;
                ResourceLocation id = BuiltInRegistries.ITEM.getKey(item.asItem());
                return id.getNamespace().contains("anvilcraft");
            })
            .filter(((IPonderIndexScreen) this)::invokeIsItemIncluded)
            .forEach(this.items::add);

        this.items.sort(Comparator.comparing(ItemEntry::key));

        int centerX = this.width / 2;
        int centerY = this.height / 2;
        int targetWidth = Mth.clamp(this.width - 180, 250, 400);
        int targetHeight = Mth.clamp(this.height - 140, 150, 300);

        this.maxScreenArea = new Rect2i(centerX - targetWidth / 2, centerY - targetHeight / 2, targetWidth, targetHeight);

        this.maxItemRows = (this.maxScreenArea.getHeight() + 8) / 36;
        this.maxItemsPerRow = (this.maxScreenArea.getWidth() + 8) / 36;
        this.maxItemsPerPage = this.maxItemRows * this.maxItemsPerRow;

        this.paginationState = new PaginationState(this.items.size() > this.maxItemsPerPage, this.maxItemsPerPage, this.items.size());

        setupItemsForPage();

        this.removeWidgets(this.prevPage, this.nextPage);

        if (!this.paginationState.usesPagination()) {
            return;
        }

        this.addRenderableWidget(
            this.prevPage = new PonderButton(
                centerX - 100,
                this.maxScreenArea.getY() + this.maxScreenArea.getHeight() + 10
            ).showing(PonderGuiTextures.ICON_PONDER_LEFT)
                .withCallback(() -> {
                    this.paginationState.previousPage();
                    updateAfterPaginationChange();
                })
                .setActive(false)
        );

        this.addRenderableWidget(
            this.nextPage = new PonderButton(
                centerX + 80,
                this.maxScreenArea.getY() + this.maxScreenArea.getHeight() + 10
            ).showing(PonderGuiTextures.ICON_PONDER_RIGHT)
                .withCallback(() -> {
                    this.paginationState.nextPage();
                    updateAfterPaginationChange();
                })
                .setActive(true)
        );

        this.prevPage.updateGradientFromState();
        this.nextPage.updateGradientFromState();
    }
}
