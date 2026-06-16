package net.azezmc.mysticvfx.client.utils;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import java.util.List;

public class RibbonUtils {

    public static void drawTrail(BufferBuilder buffer, Matrix4f matrix, Vector3f camLook, List<Vec3d> points, float width, float[] rgb, float alpha, int light, boolean fadeToTail) {
        if (points.size() < 2) return;

        for (int i = 0; i < points.size() - 1; i++) {
            Vector3f current = new Vector3f((float) points.get(i).x, (float) points.get(i).y, (float) points.get(i).z);
            Vector3f next = new Vector3f((float) points.get(i + 1).x, (float) points.get(i + 1).y, (float) points.get(i + 1).z);

            Vector3f dir = new Vector3f(next).sub(current).normalize();

            Vector3f perp = new Vector3f();
            dir.cross(camLook, perp);
            perp.normalize().mul(width / 2f);

            float ratio1 = (float) i / (points.size() - 1);
            float ratio2 = (float) (i + 1) / (points.size() - 1);

            float a1 = fadeToTail ? alpha * (1f - ratio1) : alpha;
            float a2 = fadeToTail ? alpha * (1f - ratio2) : alpha;

            buffer.vertex(matrix, current.x - perp.x, current.y - perp.y, current.z - perp.z).color(rgb[0], rgb[1], rgb[2], a1).texture(ratio1, 0).light(light).next();
            buffer.vertex(matrix, current.x + perp.x, current.y + perp.y, current.z + perp.z).color(rgb[0], rgb[1], rgb[2], a1).texture(ratio1, 1).light(light).next();
            buffer.vertex(matrix, next.x + perp.x, next.y + perp.y, next.z + perp.z).color(rgb[0], rgb[1], rgb[2], a2).texture(ratio2, 1).light(light).next();
            buffer.vertex(matrix, next.x - perp.x, next.y - perp.y, next.z - perp.z).color(rgb[0], rgb[1], rgb[2], a2).texture(ratio2, 0).light(light).next();
        }
    }
}