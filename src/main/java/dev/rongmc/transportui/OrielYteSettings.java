package dev.rongmc.transportui;

import com.google.gson.JsonObject;
import dev.oriel.mod.client.OrielScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import top.xfunny.mod.client.InitClient;

public final class OrielYteSettings extends OrielScreen {
    private final Screen parent;

    public OrielYteSettings(Screen parent) {
        super("rongmc_transport_ui:ui/yte-settings.html", null, -1);
        this.parent = parent;
        driver().onMessage("toggle", payload -> {
            var config = InitClient.getConfig();
            config.hideTestWatermark = !config.hideTestWatermark;
            config.writeConfig();
            updatePage();
        });
        driver().onMessage("back", payload -> onClose());
        updatePage();
    }

    private void updatePage() {
        JsonObject data = new JsonObject();
        data.addProperty("label", Component.translatable("gui.yte.hide_watermark").getString());
        data.addProperty("value", Component.translatable(InitClient.getConfig().hideTestWatermark
                ? "options.on" : "options.off").getString());
        data.addProperty("done", Component.translatable("gui.done").getString());
        driver().push(data);
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().gui.setScreen(parent);
    }
}
