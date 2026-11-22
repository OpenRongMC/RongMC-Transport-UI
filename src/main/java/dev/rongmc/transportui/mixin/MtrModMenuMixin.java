package dev.rongmc.transportui.mixin;

import dev.rongmc.transportui.OrielMtrSettings;
import dev.rongmc.transportui.TransportUiSettings;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "org.mtr.init.ModMenuConfig", remap = false)
abstract class MtrModMenuMixin {
    @Inject(method = "getModConfigScreenFactory", at = @At("RETURN"), cancellable = true)
    private void rongmc$selectSettings(CallbackInfoReturnable<ConfigScreenFactory<?>> callback) {
        ConfigScreenFactory<?> classic = callback.getReturnValue();
        ConfigScreenFactory<?> select = parent -> TransportUiSettings.useOriel()
                ? new OrielMtrSettings(parent) : classic.create(parent);
        callback.setReturnValue(select);
    }
}
