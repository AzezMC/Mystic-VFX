package net.azezmc.mysticvfx.client.utils;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.util.math.Vec3d;

public class ParticleUtils {

    public static void spawnLine(ClientWorld world, ParticleEffect particle, Vec3d start, Vec3d end, int count, double speed) {
        for (int i = 0; i <= count; i++) {
            Vec3d pos = MathUtils.lerp(start, end, (float) i / count);
            world.addParticle(particle, pos.x, pos.y, pos.z, 0, speed, 0);
        }
    }

    public static void spawnCircle(ClientWorld world, ParticleEffect particle, Vec3d center, float radius, int count, double speed) {
        double angleStep = Math.PI * 2 / count;
        for (int i = 0; i < count; i++) {
            double angle = i * angleStep;
            double x = center.x + Math.cos(angle) * radius;
            double z = center.z + Math.sin(angle) * radius;
            world.addParticle(particle, x, center.y, z, 0, speed, 0);
        }
    }

    public static void spawnSphere(ClientWorld world, ParticleEffect particle, Vec3d center, float radius, int count, double speed) {
        for (int i = 0; i < count; i++) {
            double phi = Math.acos(1.0 - 2.0 * Math.random());
            double theta = Math.random() * Math.PI * 2;

            double x = center.x + radius * Math.sin(phi) * Math.cos(theta);
            double y = center.y + radius * Math.sin(phi) * Math.sin(theta);
            double z = center.z + radius * Math.cos(phi);

            double vx = (x - center.x) * speed;
            double vy = (y - center.y) * speed;
            double vz = (z - center.z) * speed;

            world.addParticle(particle, x, y, z, vx, vy, vz);
        }
    }

    public static void spawnImplosion(ClientWorld world, ParticleEffect particle, Vec3d center, float radius, int count, double speed) {
        for (int i = 0; i < count; i++) {
            double phi = Math.acos(1.0 - 2.0 * Math.random());
            double theta = Math.random() * Math.PI * 2;

            double x = center.x + radius * Math.sin(phi) * Math.cos(theta);
            double y = center.y + radius * Math.sin(phi) * Math.sin(theta);
            double z = center.z + radius * Math.cos(phi);

            double vx = (center.x - x) * speed;
            double vy = (center.y - y) * speed;
            double vz = (center.z - z) * speed;

            world.addParticle(particle, x, y, z, vx, vy, vz);
        }
    }

    public static void spawnTornado(ClientWorld world, ParticleEffect particle, Vec3d bottomCenter, float maxHeight, float maxRadius, int density) {
        for (int i = 0; i < density; i++) {
            float heightPercent = (float) Math.random();
            float currentHeight = heightPercent * maxHeight;
            float currentRadius = heightPercent * maxRadius;

            double angle = Math.random() * Math.PI * 2;

            double x = bottomCenter.x + Math.cos(angle) * currentRadius;
            double y = bottomCenter.y + currentHeight;
            double z = bottomCenter.z + Math.sin(angle) * currentRadius;

            double vx = -Math.sin(angle) * 0.5;
            double vy = 0.1;
            double vz = Math.cos(angle) * 0.5;

            world.addParticle(particle, x, y, z, vx, vy, vz);
        }
    }

    public static void spawnDoubleHelix(ClientWorld world, ParticleEffect particle, Vec3d start, Vec3d end, float radius, int density, float twists) {
        double dist = start.distanceTo(end);
        Vec3d dir = end.subtract(start).normalize();
        Vec3d perp1 = dir.crossProduct(new Vec3d(0, 1, 0)).normalize();
        if (perp1.lengthSquared() == 0) perp1 = dir.crossProduct(new Vec3d(1, 0, 0)).normalize();
        Vec3d perp2 = dir.crossProduct(perp1).normalize();

        for (int i = 0; i <= density; i++) {
            float t = (float) i / density;
            Vec3d basePos = start.add(dir.multiply(t * dist));

            double angle = t * Math.PI * 2 * twists;

            Vec3d offset1 = perp1.multiply(Math.cos(angle) * radius).add(perp2.multiply(Math.sin(angle) * radius));
            Vec3d offset2 = perp1.multiply(Math.cos(angle + Math.PI) * radius).add(perp2.multiply(Math.sin(angle + Math.PI) * radius));

            Vec3d p1 = basePos.add(offset1);
            Vec3d p2 = basePos.add(offset2);

            world.addParticle(particle, p1.x, p1.y, p1.z, 0, 0, 0);
            world.addParticle(particle, p2.x, p2.y, p2.z, 0, 0, 0);
        }
    }

    public static void spawnCrescent(ClientWorld world, ParticleEffect particle, Vec3d center, Vec3d lookDir, float radius, float sweepAngle, int count) {
        float angleStep = sweepAngle / count;
        float startAngle = -sweepAngle / 2f;

        for (int i = 0; i <= count; i++) {
            float currentAngle = (float) Math.toRadians(startAngle + (i * angleStep));
            double x = center.x + Math.cos(currentAngle) * radius;
            double z = center.z + Math.sin(currentAngle) * radius;

            world.addParticle(particle, x, center.y, z, 0, 0, 0);
        }
    }

    public static void spawnShockwave(ClientWorld world, ParticleEffect particle, Vec3d center, float speed, int count) {
        for (int i = 0; i < count; i++) {
            double angle = Math.random() * Math.PI * 2;
            double vx = Math.cos(angle) * speed;
            double vy = 0.05;
            double vz = Math.sin(angle) * speed;

            world.addParticle(particle, center.x, center.y + 0.1, center.z, vx, vy, vz);
        }
    }

    public static void spawnRisingAura(ClientWorld world, ParticleEffect particle, Vec3d targetPos, float radius, float heightLimit, int density) {
        for (int i = 0; i < density; i++) {
            double angle = Math.random() * Math.PI * 2;
            double currentRadius = Math.random() * radius;

            double x = targetPos.x + Math.cos(angle) * currentRadius;
            double y = targetPos.y + (Math.random() * 0.5);
            double z = targetPos.z + Math.sin(angle) * currentRadius;

            world.addParticle(particle, x, y, z, 0, 0.2 + (Math.random() * 0.3), 0);
        }
    }
}