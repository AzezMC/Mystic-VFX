package net.azezmc.mysticvfx;

import net.azezmc.mysticvfx.network.PacketIds;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MysticVFX implements ModInitializer {
	public static final String MOD_ID = "mystic-vfx";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerPlayNetworking.registerGlobalReceiver(PacketIds.C2S_USE_SKILL, (server, player, handler, buf, responseSender) -> {
			String skillName = buf.readString();

			server.execute(() -> {
				if (skillName.equals("crescent_slash")) {
					BlockPos targetPos = player.getBlockPos().offset(player.getHorizontalFacing(), 2);
					player.getWorld().breakBlock(targetPos, true);

					PacketByteBuf vfxBuf = PacketByteBufs.create();
					vfxBuf.writeString(skillName);
					vfxBuf.writeDouble(player.getX());
					vfxBuf.writeDouble(player.getY());
					vfxBuf.writeDouble(player.getZ());

					for (ServerPlayerEntity trackingPlayer : PlayerLookup.tracking(player)) {
						ServerPlayNetworking.send(trackingPlayer, PacketIds.S2C_PLAY_VFX, vfxBuf);
					}
					ServerPlayNetworking.send(player, PacketIds.S2C_PLAY_VFX, vfxBuf);
				}
			});
		});
	}
}