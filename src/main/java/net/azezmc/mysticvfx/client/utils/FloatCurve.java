package net.azezmc.mysticvfx.client.utils;

import net.minecraft.util.math.MathHelper;
import java.util.TreeMap;

public class FloatCurve {
    private final TreeMap<Float, Float> keyframes = new TreeMap<>();

    public FloatCurve add(float percent, float value) {
        keyframes.put(percent, value);
        return this;
    }

    public float get(float percent) {
        if (keyframes.isEmpty()) return 0;
        if (keyframes.size() == 1) return keyframes.firstEntry().getValue();

        var floor = keyframes.floorEntry(percent);
        var ceil = keyframes.ceilingEntry(percent);

        if (floor == null) return ceil.getValue();
        if (ceil == null) return floor.getValue();
        if (floor.getKey().equals(ceil.getKey())) return floor.getValue();

        float t = (percent - floor.getKey()) / (ceil.getKey() - floor.getKey());
        return MathHelper.lerp(t, floor.getValue(), ceil.getValue());
    }
}