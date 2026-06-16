package net.azezmc.mysticvfx.client.utils;

public class ColorUtils {

    public static float[] hexToRGB(int hex) {
        float r = ((hex >> 16) & 0xFF) / 255.0f;
        float g = ((hex >> 8) & 0xFF) / 255.0f;
        float b = (hex & 0xFF) / 255.0f;
        return new float[]{r, g, b};
    }

    public static float calculateFade(int age, int maxAge, float fadeInPercent, float fadeOutPercent) {
        float progress = (float) age / maxAge;
        if (progress < fadeInPercent) {
            return progress / fadeInPercent;
        } else if (progress > fadeOutPercent) {
            return 1.0f - ((progress - fadeOutPercent) / (1.0f - fadeOutPercent));
        }
        return 1.0f;
    }

    public static float[] lerpColor(float[] colorStart, float[] colorEnd, float progress) {
        float r = colorStart[0] + (colorEnd[0] - colorStart[0]) * progress;
        float g = colorStart[1] + (colorEnd[1] - colorStart[1]) * progress;
        float b = colorStart[2] + (colorEnd[2] - colorStart[2]) * progress;
        return new float[]{r, g, b};
    }
}