package net.azezmc.mysticvfx.client.utils;

public class TextureUtils {

    public static float[] getScrollingUV(float time, float speedU, float speedV) {
        float offsetU = (time * speedU) % 1.0f;
        float offsetV = (time * speedV) % 1.0f;

        return new float[]{
                offsetU,           offsetV,
                offsetU + 1.0f,    offsetV + 1.0f
        };
    }

    public static float[] getSpriteUV(int cols, int rows, int currentFrame) {
        int totalFrames = cols * rows;
        int frame = currentFrame % totalFrames;

        int col = frame % cols;
        int row = frame / cols;

        float width = 1.0f / cols;
        float height = 1.0f / rows;

        float u1 = col * width;
        float v1 = row * height;
        float u2 = u1 + width;
        float v2 = v1 + height;

        return new float[]{u1, v1, u2, v2};
    }
}