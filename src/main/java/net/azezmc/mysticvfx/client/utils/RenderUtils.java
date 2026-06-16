package net.azezmc.mysticvfx.client.utils;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.azezmc.mysticvfx.client.render.VFXRenderTypes;
import net.minecraft.client.render.GameRenderer;

public class RenderUtils {
    public static final int MAX_LIGHT = 15728880;

    public static void setupAdditiveBlend(boolean seeThroughWalls) {
        RenderSystem.setShader(VFXRenderTypes.ADDITIVE_GLOW::getShader);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionColorTexLightmapProgram);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE);
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        if (seeThroughWalls) RenderSystem.disableDepthTest();
    }

    public static void setupAlphaBlend() {
        RenderSystem.setShader(GameRenderer::getPositionColorTexLightmapProgram);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
    }

    public static void setupSolidModel() {
        RenderSystem.setShader(GameRenderer::getRenderTypeSolidProgram);
        RenderSystem.enableCull();
        RenderSystem.depthMask(true);
    }

    public static void resetRender() {
        RenderSystem.depthMask(true);
        RenderSystem.enableCull();
        RenderSystem.enableDepthTest();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }
}