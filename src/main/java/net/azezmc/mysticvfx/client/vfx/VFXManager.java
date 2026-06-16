package net.azezmc.mysticvfx.client.vfx;

import net.minecraft.client.util.math.MatrixStack;
import java.util.ArrayList;
import java.util.List;

public class VFXManager {
    private static final List<VFXInstance> INSTANCES = new ArrayList<>();

    public static void add(VFXInstance vfx) {
        INSTANCES.add(vfx);
    }

    public static void tick() {
        INSTANCES.removeIf(vfx -> vfx.isDead);
        INSTANCES.forEach(VFXInstance::tick);
    }

    public static void render(MatrixStack matrices, float tickDelta) {
        INSTANCES.forEach(vfx -> vfx.render(matrices, tickDelta));
    }
}