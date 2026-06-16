package net.azezmc.mysticvfx.client.vfx.impl;

import net.azezmc.mysticvfx.client.vfx.VFXInstance;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;

public class ParticleEmitterVFX extends VFXInstance {
    private final Entity target;
    private final ParticleEffect particle;
    private final Vec3d offset;
    private final int rate;
    private final double speed;

    public ParticleEmitterVFX(Entity target, ParticleEffect particle, Vec3d offset, int maxAge, int rate, double speed) {
        super(target.getPos(), maxAge);
        this.target = target;
        this.particle = particle;
        this.offset = offset;
        this.rate = rate;
        this.speed = speed;
    }

    @Override
    public void tick() {
        super.tick();
        if (target == null || target.isRemoved()) {
            this.isDead = true;
            return;
        }

        this.pos = target.getPos().add(offset);

        if (MinecraftClient.getInstance().world != null) {
            for (int i = 0; i < rate; i++) {
                double vx = (Math.random() - 0.5) * speed;
                double vy = (Math.random() - 0.5) * speed;
                double vz = (Math.random() - 0.5) * speed;
                MinecraftClient.getInstance().world.addParticle(particle, pos.x, pos.y, pos.z, vx, vy, vz);
            }
        }
    }

    @Override
    public void render(MatrixStack matrices, float tickDelta) {
    }
}