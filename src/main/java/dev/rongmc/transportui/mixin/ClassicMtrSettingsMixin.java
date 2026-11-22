package dev.rongmc.transportui.mixin;

import dev.rongmc.transportui.TransportUiSettings;
import org.mtr.mapping.holder.ClickableWidget;
import org.mtr.mapping.mapper.ButtonWidgetExtension;
import org.mtr.mapping.mapper.ScreenExtension;
import org.mtr.mapping.mapper.TextHelper;
import org.mtr.mod.screen.ConfigScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ConfigScreen.class, remap = false)
abstract class ClassicMtrSettingsMixin extends ScreenExtension {
    @Inject(method = "init2", at = @At("TAIL"))
    private void rongmc$addInterfaceSettings(CallbackInfo callback) {
        final var button = new ButtonWidgetExtension(4, 4, 74, 16,
                TextHelper.translatable("rongmc.transportui.style"), unused -> TransportUiSettings.open(this));
        addChild(new ClickableWidget(button));
    }
}
