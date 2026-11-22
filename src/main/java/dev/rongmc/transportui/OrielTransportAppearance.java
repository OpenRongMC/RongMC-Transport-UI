package dev.rongmc.transportui;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import dev.oriel.mod.client.DocumentDriver;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import org.mtr.mapping.mapper.TransitUi;

import java.util.Set;
import java.util.WeakHashMap;

public final class OrielTransportAppearance implements TransitUi.Extension {
    private static final String RESOURCE = "rongmc_transport_ui:ui/";
    private static final Set<String> PACKAGES = Set.of(
            "org.mtr.", "top.xfunny.", "top.mcmtr.", "com.lx862.jcm.",
            "net.londonunderground.", "ru.weryskok.mtrrumetro.", "ziyue.tjmetro.", "ziyue.filters.");
    private final WeakHashMap<Screen, Layer> screens = new WeakHashMap<>();

    private static final java.util.Map<String, Layer> SKINS = new java.util.LinkedHashMap<>(32, 0.75f, true);

    public static boolean filterButton(GuiGraphicsExtractor graphics, int x, int y,
            int width, int height, boolean active, boolean highlighted) {
        if (!TransportUiSettings.useOriel() || width < 4 || height < 4) return false;
        final String state = !active ? "disabled" : highlighted ? "highlighted" : "enabled";
        final String key = width + "x" + height + "-" + state;
        final Layer layer = SKINS.computeIfAbsent(key, ignored -> {
            final Layer skin = new Layer("controls.html");
            final JsonObject data = new JsonObject();
            final JsonArray controls = new JsonArray();
            controls.add(new Control(0, 0, width, height, active, highlighted).serialize());
            data.add("buttons", controls);
            skin.driver.push(data);
            return skin;
        });
        if (SKINS.size() > 64) {
            final var oldest = SKINS.entrySet().iterator();
            oldest.next().getValue().driver.close();
            oldest.remove();
        }
        graphics.pose().pushMatrix();
        try {
            graphics.pose().translate(x, y);
            return layer.draw(graphics, width, height, -1, -1);
        } finally {
            graphics.pose().popMatrix();
        }
    }

    private static boolean supports(Screen screen) {
        if (screen == null || !TransportUiSettings.useOriel()) return false;
        for (Class<?> type = screen.getClass(); type != null; type = type.getSuperclass()) {
            final String name = type.getName();
            if (PACKAGES.stream().anyMatch(name::startsWith)) return true;
        }
        return false;
    }

    @Override
    public boolean background(Screen screen, GuiGraphicsExtractor graphics, int width, int height) {
        return supports(screen) && screens.computeIfAbsent(screen, ignored -> new Layer("appearance.html"))
                .draw(graphics, width, height, -1, -1);
    }

    @Override
    public boolean control(Screen screen, GuiGraphicsExtractor graphics, int x, int y,
            int width, int height, boolean enabled, boolean highlighted) {
        return supports(screen) && filterButton(graphics, x, y, width, height, enabled, highlighted);
    }

    @Override
    public void close(Screen screen) {
        final Layer layer = screens.remove(screen);
        if (layer != null) layer.driver.close();
    }

    private record Control(int x, int y, int width, int height, boolean enabled, boolean highlighted) {
        JsonObject serialize() {
            final JsonObject data = new JsonObject();
            data.addProperty("x", x);
            data.addProperty("y", y);
            data.addProperty("width", width);
            data.addProperty("height", height);
            data.addProperty("state", !enabled ? "disabled" : highlighted ? "highlighted" : "enabled");
            return data;
        }
    }

    private static final class Layer {
        private final DocumentDriver driver;
        private int width;
        private int height;

        Layer(String page) {
            driver = new DocumentDriver(() -> {}, RESOURCE + page, null, -1);
        }

        boolean draw(GuiGraphicsExtractor graphics, int width, int height, int mouseX, int mouseY) {
            if (width <= 0 || height <= 0) return false;
            if (this.width != width || this.height != height) {
                this.width = width;
                this.height = height;
                driver.resize(width, height);
            }
            if (driver.document() == null || driver.pageErrorCount() != 0) return false;
            driver.extract(graphics, mouseX, mouseY);
            return true;
        }
    }
}
