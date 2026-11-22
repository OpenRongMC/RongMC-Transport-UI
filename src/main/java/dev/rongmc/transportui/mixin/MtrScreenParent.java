package dev.rongmc.transportui.mixin;

import org.mtr.mapping.holder.Screen;
import org.mtr.mod.screen.MTRScreenBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = MTRScreenBase.class, remap = false)
public interface MtrScreenParent {
    @Accessor("previousScreen")
    Screen rongmc$getPreviousScreen();
}
