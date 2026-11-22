package dev.rongmc.transportui;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.mtr.mapping.mapper.TransitUi;

public final class TransportUiClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        if (FabricLoader.getInstance().isModLoaded("oriel")) {
            TransitUi.install(new OrielTransportAppearance());
        }
    }
}
