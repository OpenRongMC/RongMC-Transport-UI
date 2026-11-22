package dev.rongmc.transportui.mixin;

import dev.rongmc.transportui.OrielMtrSettings;
import dev.rongmc.transportui.TransportUiSettings;
import org.mtr.mapping.holder.MinecraftClient;
import org.mtr.mapping.holder.Screen;
import org.mtr.mod.screen.ConfigScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = MinecraftClient.class, remap = false)
abstract class OpenMtrSettingsMixin {
    @ModifyVariable(method = "openScreen", at = @At("HEAD"), argsOnly = true)
    private Screen rongmc$selectSettings(Screen screen) {
        if (screen != null && screen.data instanceof ConfigScreen && TransportUiSettings.useOriel()) {
            return new Screen(new OrielMtrSettings(net.minecraft.client.Minecraft.getInstance().gui.screen()));
        }
        return screen;
    }
}
