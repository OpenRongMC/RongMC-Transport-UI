package dev.rongmc.transportui;

import dev.rongmc.transportui.mixin.MtrScreenParent;
import net.minecraft.client.gui.screens.Screen;
import org.mtr.mod.screen.ConfigScreen;

/** The optional Oriel types stay in this class, which is only loaded when the library is present. */
final class OrielModeSwitch {
    private OrielModeSwitch() { }

    static Screen reopen(Screen previous) {
        if (!TransportUiSettings.useOriel() && previous instanceof OrielMtrSettings settings) {
            return settings.classicScreen();
        }
        if (TransportUiSettings.useOriel() && previous instanceof ConfigScreen settings) {
            final var parent = ((MtrScreenParent) settings).rongmc$getPreviousScreen();
            return new OrielMtrSettings(parent == null ? null : parent.data);
        }
        return previous;
    }
}
