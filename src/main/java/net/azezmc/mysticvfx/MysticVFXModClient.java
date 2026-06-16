package net.azezmc.mysticvfx;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;

public class MysticVFXModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        WorldRenderEvents.LAST.register(context -> {
            
        });
    }
}
