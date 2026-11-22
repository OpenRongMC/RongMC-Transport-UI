package dev.rongmc.transportui;

import com.google.gson.JsonObject;
import dev.oriel.mod.client.OrielScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.mtr.mod.client.DynamicTextureCache;
import org.mtr.mod.config.Client;
import org.mtr.mod.config.Config;
import org.mtr.mod.resource.OptimizedRendererWrapper;

public final class OrielMtrSettings extends OrielScreen {
    private final Screen previous;
    private final Client config = Config.getClient();

    public OrielMtrSettings(Screen previous) {
        super("rongmc_transport_ui:ui/settings.html", null, -1);
        this.previous = previous;
        driver().onMessage("toggle", payload -> {
            if (!payload.isJsonPrimitive() || !payload.getAsJsonPrimitive().isString()) {
                return;
            }
            switch (payload.getAsString()) {
                case "announcements" -> config.toggleChatAnnouncements();
                case "speech" -> config.toggleTextToSpeechAnnouncements();
                case "translucency" -> config.toggleHideTranslucentParts();
                case "language" -> config.cycleLanguageDisplay();
                case "font" -> config.toggleUseMTRFont();
                case "shadows" -> config.toggleDisableShadowsForShaders();
                case "rail" -> {
                    if (OptimizedRendererWrapper.isEnabled()) {
                        config.toggleDefaultRail3D();
                    }
                }
                default -> { return; }
            }
            Config.save();
            update();
        });
        driver().onMessage("numbers", payload -> {
            if (!payload.isJsonObject()) {
                return;
            }
            JsonObject data = payload.getAsJsonObject();
            if (data.has("texture") && data.get("texture").isJsonPrimitive()
                    && data.getAsJsonPrimitive("texture").isNumber()) {
                config.setDynamicTextureResolution(Math.clamp(data.get("texture").getAsInt(),
                        0, Client.DYNAMIC_RESOLUTION_COUNT - 1));
                DynamicTextureCache.instance.refresh();
            }
            if (data.has("oscillation") && data.get("oscillation").isJsonPrimitive()
                    && data.getAsJsonPrimitive("oscillation").isNumber()) {
                config.setVehicleOscillationMultiplier(Math.clamp(data.get("oscillation").getAsInt(),
                        0, Client.TRAIN_OSCILLATION_COUNT) / 10.0);
            }
            Config.save();
            update();
        });
        driver().onMessage("style", payload -> TransportUiSettings.open(this));
        driver().onMessage("support", payload -> org.mtr.mapping.holder.Util.getOperatingSystem()
                .open("https://www.patreon.com/minecraft_transit_railway"));
        driver().onMessage("back", payload -> onClose());
        update();
    }

    private void update() {
        JsonObject data = new JsonObject();
        data.addProperty("announcements", config.getChatAnnouncements());
        data.addProperty("speech", config.getTextToSpeechAnnouncements());
        data.addProperty("translucency", config.getHideTranslucentParts());
        data.addProperty("language", config.getLanguageDisplay().translationKey.getText().data.getString());
        data.addProperty("font", config.getUseMTRFont());
        data.addProperty("shadows", config.getDisableShadowsForShaders());
        data.addProperty("rail", config.getDefaultRail3D());
        data.addProperty("railAvailable", OptimizedRendererWrapper.isEnabled());
        data.addProperty("texture", config.getDynamicTextureResolution());
        data.addProperty("oscillation", Math.round(config.getVehicleOscillationMultiplier() * 10));
        data.addProperty("title", Component.translatable("rongmc.transportui.mtr").getString());
        for (String key : new String[]{"announcements", "speech", "translucency", "language", "font",
                "shadows", "rail", "texture", "oscillation"}) {
            data.addProperty(key + "Label", Component.translatable("rongmc.transportui." + key).getString());
        }
        data.addProperty("on", Component.translatable("options.on").getString());
        data.addProperty("off", Component.translatable("options.off").getString());
        data.addProperty("style", Component.translatable("rongmc.transportui.style").getString());
        data.addProperty("support", Component.translatable("gui.mtr.support").getString());
        data.addProperty("back", Component.translatable("gui.done").getString());
        driver().push(data);
    }

    Screen classicScreen() {
        return new org.mtr.mod.screen.ConfigScreen(
                previous == null ? null : new org.mtr.mapping.holder.Screen(previous));
    }

    @Override
    public void onClose() {
        Minecraft.getInstance().gui.setScreen(previous);
    }
}
