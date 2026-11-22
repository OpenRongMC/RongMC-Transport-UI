package dev.rongmc.transportui.mixin;

import com.mojang.renderpearl.api.pipeline.RenderPipeline;
import dev.rongmc.transportui.OrielTransportAppearance;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Pseudo
@Mixin(targets = {"ziyue.filters.Filter", "ziyue.filters.gui.IconButton"}, remap = false)
abstract class FilterButtonAppearanceMixin {
    @Redirect(method = "extractContents", at = @At(value = "INVOKE", ordinal = 0,
            target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/renderpearl/api/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V"))
    private void rongmc$drawFilterBackground(GuiGraphicsExtractor graphics, RenderPipeline pipeline, Identifier sprite,
            int x, int y, int width, int height) {
        final AbstractWidget widget = (AbstractWidget) (Object) this;
        if (!OrielTransportAppearance.filterButton(graphics, x, y, width, height,
                widget.active, widget.isHoveredOrFocused() || width == 32)) {
            graphics.blitSprite(pipeline, sprite, x, y, width, height);
        }
    }
}
