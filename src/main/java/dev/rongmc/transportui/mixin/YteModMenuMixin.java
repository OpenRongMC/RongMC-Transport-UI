package dev.rongmc.transportui.mixin;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import dev.rongmc.transportui.OrielYteSettings;
import dev.rongmc.transportui.TransportUiSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "top.xfunny.entrypoint.ModMenu", remap = false)
abstract class YteModMenuMixin {
    @Inject(method = "getModConfigScreenFactory", at = @At("RETURN"), cancellable = true)
    private void rongmc$selectSettings(CallbackInfoReturnable<ConfigScreenFactory<?>> callback) {
        ConfigScreenFactory<?> classic = callback.getReturnValue();
        ConfigScreenFactory<?> selected = parent -> TransportUiSettings.useOriel()
                ? new OrielYteSettings(parent) : classic.create(parent);
        callback.setReturnValue(selected);
    }
}
