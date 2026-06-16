package net.azezmc.mysticvfx.client.utils;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;

public class MathUtils {

    public static void rotateY(MatrixStack matrices, float age, float tickDelta, float speed) {
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees((age + tickDelta) * speed));
    }

    public static Vec3d lerp(Vec3d start, Vec3d end, float tickDelta) {
        return new Vec3d(
                MathHelper.lerp(tickDelta, start.x, end.x),
                MathHelper.lerp(tickDelta, start.y, end.y),
                MathHelper.lerp(tickDelta, start.z, end.z)
        );
    }

    public static float easeOutCubic(float x) {
        return 1.0f - (float) Math.pow(1.0 - x, 3);
    }

    public static Vec3d quadraticBezier(Vec3d p0, Vec3d p1, Vec3d p2, float t) {
        float u = 1 - t;
        float tt = t * t;
        float uu = u * u;

        double x = uu * p0.x + 2 * u * t * p1.x + tt * p2.x;
        double y = uu * p0.y + 2 * u * t * p1.y + tt * p2.y;
        double z = uu * p0.z + 2 * u * t * p1.z + tt * p2.z;
        return new Vec3d(x, y, z);
    }

    public static Vec3d getSpiralPos(Vec3d center, float age, float radius, float height, float speed) {
        double angle = age * speed;
        double x = center.x + Math.cos(angle) * radius;
        double y = center.y + (age * height);
        double z = center.z + Math.sin(angle) * radius;
        return new Vec3d(x, y, z);
    }
}