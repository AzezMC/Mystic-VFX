package net.azezmc.mysticvfx.client.vfx;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

public abstract class VFXInstance {
    public Vec3d pos;
    public int age = 0;
    public int maxAge;
    public boolean isDead = false;

    public VFXInstance(Vec3d pos, int maxAge) {
        this.pos = pos;
        this.maxAge = maxAge;
    }

    public void tick() {
        if (age++ >= maxAge) isDead = true;
    }

    public abstract void render(MatrixStack matrices, float tickDelta);
}