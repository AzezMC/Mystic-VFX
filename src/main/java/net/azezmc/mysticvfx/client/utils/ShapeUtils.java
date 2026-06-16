package net.azezmc.mysticvfx.client.utils;

import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Camera;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class ShapeUtils {

    public static void drawHorizontalQuad(BufferBuilder buffer, Matrix4f matrix, float size, float[] rgb, float a, int light) {
        float h = size / 2f;
        buffer.vertex(matrix, -h, 0, -h).color(rgb[0], rgb[1], rgb[2], a).texture(0, 0).light(light).next();
        buffer.vertex(matrix, -h, 0, h).color(rgb[0], rgb[1], rgb[2], a).texture(0, 1).light(light).next();
        buffer.vertex(matrix, h, 0, h).color(rgb[0], rgb[1], rgb[2], a).texture(1, 1).light(light).next();
        buffer.vertex(matrix, h, 0, -h).color(rgb[0], rgb[1], rgb[2], a).texture(1, 0).light(light).next();
    }

    public static void drawBillboard(BufferBuilder buffer, Matrix4f matrix, Camera camera, float size, float[] rgb, float a, int light) {
        Quaternionf rotation = camera.getRotation();
        Vector3f[] vertices = new Vector3f[]{
                new Vector3f(-1.0F, -1.0F, 0.0F),
                new Vector3f(-1.0F,  1.0F, 0.0F),
                new Vector3f( 1.0F,  1.0F, 0.0F),
                new Vector3f( 1.0F, -1.0F, 0.0F)
        };

        float h = size / 2f;
        for (int i = 0; i < 4; ++i) {
            vertices[i].rotate(rotation).mul(h);
        }

        buffer.vertex(matrix, vertices[0].x, vertices[0].y, vertices[0].z).color(rgb[0], rgb[1], rgb[2], a).texture(0, 1).light(light).next();
        buffer.vertex(matrix, vertices[1].x, vertices[1].y, vertices[1].z).color(rgb[0], rgb[1], rgb[2], a).texture(0, 0).light(light).next();
        buffer.vertex(matrix, vertices[2].x, vertices[2].y, vertices[2].z).color(rgb[0], rgb[1], rgb[2], a).texture(1, 0).light(light).next();
        buffer.vertex(matrix, vertices[3].x, vertices[3].y, vertices[3].z).color(rgb[0], rgb[1], rgb[2], a).texture(1, 1).light(light).next();
    }

    public static void drawLaserSegment(BufferBuilder buffer, Matrix4f matrix, Vector3f start, Vector3f end, float width, float[] rgb, float a, int light) {
        Vector3f dir = new Vector3f(end).sub(start).normalize();
        Vector3f perp = new Vector3f(-dir.y, dir.x, 0).normalize().mul(width / 2f);

        buffer.vertex(matrix, start.x - perp.x, start.y - perp.y, start.z - perp.z).color(rgb[0], rgb[1], rgb[2], a).texture(0, 0).light(light).next();
        buffer.vertex(matrix, start.x + perp.x, start.y + perp.y, start.z + perp.z).color(rgb[0], rgb[1], rgb[2], a).texture(1, 0).light(light).next();
        buffer.vertex(matrix, end.x + perp.x, end.y + perp.y, end.z + perp.z).color(rgb[0], rgb[1], rgb[2], a).texture(1, 1).light(light).next();
        buffer.vertex(matrix, end.x - perp.x, end.y - perp.y, end.z - perp.z).color(rgb[0], rgb[1], rgb[2], a).texture(0, 1).light(light).next();
    }
}