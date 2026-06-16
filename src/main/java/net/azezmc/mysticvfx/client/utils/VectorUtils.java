package net.azezmc.mysticvfx.client.utils;

import net.minecraft.util.math.Vec3d;

public class VectorUtils {
    public static Vec3d cubicBezier(Vec3d p0, Vec3d p1, Vec3d p2, Vec3d p3, float t) {
        float u = 1 - t;
        double x = u*u*u*p0.x + 3*u*u*t*p1.x + 3*u*t*t*p2.x + t*t*t*p3.x;
        double y = u*u*u*p0.y + 3*u*u*t*p1.y + 3*u*t*t*p2.y + t*t*t*p3.y;
        double z = u*u*u*p0.z + 3*u*u*t*p1.z + 3*u*t*t*p2.z + t*t*t*p3.z;
        return new Vec3d(x, y, z);
    }

    public static Vec3d getEntityLerpPos(net.minecraft.entity.Entity entity, float tickDelta) {
        double x = net.minecraft.util.math.MathHelper.lerp(tickDelta, entity.prevX, entity.getX());
        double y = net.minecraft.util.math.MathHelper.lerp(tickDelta, entity.prevY, entity.getY());
        double z = net.minecraft.util.math.MathHelper.lerp(tickDelta, entity.prevZ, entity.getZ());
        return new Vec3d(x, y, z);
    }
}