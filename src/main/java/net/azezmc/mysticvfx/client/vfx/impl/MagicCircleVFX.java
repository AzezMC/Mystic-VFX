package net.azezmc.mysticvfx.client.vfx.impl;

import com.mojang.blaze3d.systems.RenderSystem;
import net.azezmc.mysticvfx.client.utils.MathUtils;
import net.azezmc.mysticvfx.client.utils.RenderUtils;
import net.azezmc.mysticvfx.client.vfx.VFXInstance;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class MagicCircleVFX extends VFXInstance {
    private final Identifier texture;
    private final float size, r, g, b, a, speed;

    // Hàm khởi tạo bắt buộc phải khớp 100% tham số khi gọi "new MagicCircleVFX"
    public MagicCircleVFX(Vec3d pos, int maxAge, float size, Identifier texture, float r, float g, float b, float a, float speed) {
        super(pos, maxAge); // Gọi constructor của VFXInstance
        this.size = size; this.texture = texture;
        this.r = r; this.g = g; this.b = b; this.a = a; this.speed = speed;
    }

    @Override
    public void render(MatrixStack matrices, float tickDelta) {
        Vec3d camPos = MinecraftClient.getInstance().gameRenderer.getCamera().getPos();

        matrices.push();
        matrices.translate(pos.x - camPos.x, pos.y - camPos.y, pos.z - camPos.z);
        MathUtils.rotateY(matrices, age, tickDelta, speed);

        RenderUtils.setupAdditiveBlend(false);
        RenderSystem.setShaderTexture(0, texture);

        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE_LIGHT);

        Matrix4f mat = matrices.peek().getPositionMatrix();
        float h = size / 2f;

        buffer.vertex(mat, -h, 0, -h).color(r, g, b, a).texture(0, 0).light(RenderUtils.MAX_LIGHT).next();
        buffer.vertex(mat, -h, 0, h).color(r, g, b, a).texture(0, 1).light(RenderUtils.MAX_LIGHT).next();
        buffer.vertex(mat, h, 0, h).color(r, g, b, a).texture(1, 1).light(RenderUtils.MAX_LIGHT).next();
        buffer.vertex(mat, h, 0, -h).color(r, g, b, a).texture(1, 0).light(RenderUtils.MAX_LIGHT).next();

        Tessellator.getInstance().draw();
        RenderUtils.resetRender();
        matrices.pop();
    }
}