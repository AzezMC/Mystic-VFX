package net.azezmc.mysticvfx.client.utils;

import java.util.TreeMap;

public class ColorCurve {
    private final TreeMap<Float, float[]> keyframes = new TreeMap<>();

    public ColorCurve add(float percent, float r, float g, float b, float a) {
        keyframes.put(percent, new float[]{r, g, b, a});
        return this;
    }

    public float[] get(float percent) {
        if (keyframes.isEmpty()) return new float[]{1f, 1f, 1f, 1f};
        if (keyframes.size() == 1) return keyframes.firstEntry().getValue();

        var floor = keyframes.floorEntry(percent);
        var ceil = keyframes.ceilingEntry(percent);

        if (floor == null) return ceil.getValue();
        if (ceil == null) return floor.getValue();
        if (floor.getKey().equals(ceil.getKey())) return floor.getValue();

        float t = (percent - floor.getKey()) / (ceil.getKey() - floor.getKey());

        float[] c1 = floor.getValue();
        float[] c2 = ceil.getValue();
        return new float[]{
                c1[0] + (c2[0] - c1[0]) * t,
                c1[1] + (c2[1] - c1[1]) * t,
                c1[2] + (c2[2] - c1[2]) * t,
                c1[3] + (c2[3] - c1[3]) * t
        };
    }
}