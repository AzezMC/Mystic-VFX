package net.azezmc.mysticvfx.network;

import net.minecraft.util.Identifier;

public class PacketIds {
    // Client gửi cho Server: "Tôi muốn dùng skill"
    public static final Identifier C2S_USE_SKILL = new Identifier("mysticvfx", "c2s_use_skill");

    // Server gửi cho toàn bộ Client: "Phát hiệu ứng VFX tại tọa độ này"
    public static final Identifier S2C_PLAY_VFX = new Identifier("mysticvfx", "s2c_play_vfx");
}