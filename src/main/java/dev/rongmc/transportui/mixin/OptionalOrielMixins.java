package dev.rongmc.transportui.mixin;

import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public final class OptionalOrielMixins implements IMixinConfigPlugin {
    private static final Set<String> OPTIONAL = Set.of("MtrModMenuMixin", "OpenMtrSettingsMixin", "FilterButtonAppearanceMixin");

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        final String name = mixinClassName.substring(mixinClassName.lastIndexOf('.') + 1);
        if (name.equals("YteModMenuMixin")) {
            return FabricLoader.getInstance().isModLoaded("oriel") && FabricLoader.getInstance().isModLoaded("yte");
        }
        return !OPTIONAL.contains(name) || FabricLoader.getInstance().isModLoaded("oriel");
    }

    @Override public void onLoad(String mixinPackage) { }
    @Override public String getRefMapperConfig() { return null; }
    @Override public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }
    @Override public List<String> getMixins() { return null; }
    @Override public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo info) { }
    @Override public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo info) { }
}
