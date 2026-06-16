package net.azezmc.mysticvfx.client.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.Vec3d;

public class SoundUtils {
    public static void play(SoundEvent sound, Vec3d pos, float volume, float pitch) {
        if (MinecraftClient.getInstance().world == null) return;
        MinecraftClient.getInstance().world.playSound(
                pos.x, pos.y, pos.z, sound, SoundCategory.PLAYERS, volume, pitch, false
        );
    }
}