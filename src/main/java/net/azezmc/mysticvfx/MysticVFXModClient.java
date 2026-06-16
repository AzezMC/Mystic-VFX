package net.azezmc.mysticvfx;

import net.azezmc.mysticvfx.client.vfx.VFXManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class MysticVFXModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (!client.isPaused()) VFXManager.tick();
        });

        WorldRenderEvents.LAST.register(context -> {
            VFXManager.render(context.matrixStack(), context.tickDelta());
        });
    }
}
