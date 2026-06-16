package net.azezmc.mysticvfx;

import com.google.gson.JsonObject;
import net.azezmc.mysticvfx.client.data.VFXParser;
import net.azezmc.mysticvfx.client.utils.ColorCurve;
import net.azezmc.mysticvfx.client.vfx.VFXManager;
import net.azezmc.mysticvfx.client.vfx.impl.MagicCircleVFX;
import net.azezmc.mysticvfx.network.PacketIds;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.lwjgl.glfw.GLFW;

public class MysticVFXModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!client.isPaused()) VFXManager.tick();
        });

        WorldRenderEvents.LAST.register(context -> {
            VFXManager.render(context.matrixStack(), context.tickDelta());
        });

        ClientPlayNetworking.registerGlobalReceiver(PacketIds.S2C_PLAY_VFX, (client, handler, buf, responseSender) -> {
            String skillName = buf.readString();
            double x = buf.readDouble();
            double y = buf.readDouble();
            double z = buf.readDouble();

            client.execute(() -> {
                Vec3d pos = new Vec3d(x, y, z);

                // 1. Đọc file JSON dựa trên tên skill (VD: assets/mysticvfx/vfx_data/crescent_slash.json)
                Identifier skillId = new Identifier("mysticvfx", skillName);
                JsonObject vfxData = VFXParser.loadVFXData(skillId);

                // Bỏ qua nếu không có data để tránh crash
                if (vfxData == null || vfxData.size() == 0) {
                    System.err.println("[MysticVFX] Thiếu file JSON cho: " + skillName);
                    return;
                }

                // 2. Bóc tách dữ liệu và chạy hiệu ứng
                if (skillName.equals("crescent_slash")) {
                    // Lấy thông số từ JSON (có fallback giá trị mặc định nếu file thiếu)
                    int maxAge = vfxData.has("maxAge") ? vfxData.get("maxAge").getAsInt() : 20;
                    float size = vfxData.has("size") ? vfxData.get("size").getAsFloat() : 5.0f;
                    ColorCurve colorCurve = VFXParser.parseColorCurve(vfxData, "colors");

                    // Khởi tạo hiệu ứng (Tạm dùng MagicCircleVFX để test, truyền tham số đầu tiên của curve)
                    Identifier texture = new Identifier("mysticvfx", "textures/vfx/crescent.png");
                    float[] startColor = colorCurve.get(0.0f);

                    MagicCircleVFX effect = new MagicCircleVFX(
                            pos, maxAge, size, texture,
                            startColor[0], startColor[1], startColor[2], startColor[3], 15f
                    );

                    // 3. Đưa vào Manager để tự động render và xóa khi hết hạn
                    VFXManager.add(effect);
                }
            });
        });

        KeyBinding skillKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.mysticvfx.use_skill", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "category.mysticvfx.keys"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (skillKey.wasPressed()) {
                PacketByteBuf buf = PacketByteBufs.create();
                buf.writeString("ultimate_sky_rift");
                ClientPlayNetworking.send(PacketIds.C2S_USE_SKILL, buf);
            }
        });
    }
}
