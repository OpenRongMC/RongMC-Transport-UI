package dev.rongmc.transportui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TransportUiSettings extends Screen {
    private static final Logger LOGGER = LoggerFactory.getLogger("RongMC Transport UI");
    private static final Path PREFERENCE = FabricLoader.getInstance().getConfigDir()
            .resolve("rongmc-transport-ui.txt");
    private static boolean useOriel = load();
    private final Screen previous;

    private TransportUiSettings(Screen previous) {
        super(Component.translatable("rongmc.transportui.title"));
        this.previous = previous;
    }

    public static void open(Screen previous) {
        Minecraft.getInstance().gui.setScreen(new TransportUiSettings(previous));
    }

    static boolean saveMode(boolean oriel) {
        try {
            Files.createDirectories(PREFERENCE.getParent());
            Files.writeString(PREFERENCE, oriel ? "oriel\n" : "classic\n");
            useOriel = oriel;
            return true;
        } catch (IOException error) {
            LOGGER.warn("Could not save the transport UI preference", error);
            return false;
        }
    }

    public static boolean useOriel() {
        return useOriel && FabricLoader.getInstance().isModLoaded("oriel");
    }

    private static boolean load() {
        try {
            return !Files.exists(PREFERENCE) || !Files.readString(PREFERENCE).strip().equals("classic");
        } catch (IOException error) {
            LOGGER.warn("Could not read the transport UI preference", error);
            return false;
        }
    }

    @Override
    protected void init() {
        Button toggle = Button.builder(modeLabel(), button -> {
            button.setMessage(saveMode(!useOriel) ? modeLabel()
                    : Component.translatable("rongmc.transportui.save_failed"));
        }).bounds(width / 2 - 104, height / 2 - 23, 208, 20).build();
        toggle.active = FabricLoader.getInstance().isModLoaded("oriel");
        addRenderableWidget(toggle);
        addRenderableWidget(Button.builder(Component.translatable("gui.done"), button -> onClose())
                .bounds(width / 2 - 104, height / 2 + 8, 208, 20).build());
    }

    static Component modeLabel() {
        return Component.translatable(useOriel() ? "rongmc.transportui.oriel" : "rongmc.transportui.classic");
    }

    @Override
    public void onClose() {
        final Screen target = FabricLoader.getInstance().isModLoaded("oriel")
                ? OrielModeSwitch.reopen(previous) : previous;
        Minecraft.getInstance().gui.setScreen(target);
    }

    public static final class Menu implements ModMenuApi {
        @Override
        public ConfigScreenFactory<?> getModConfigScreenFactory() {
            return TransportUiSettings::new;
        }
    }
}
