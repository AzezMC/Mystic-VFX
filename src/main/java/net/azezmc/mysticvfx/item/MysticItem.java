package net.azezmc.mysticvfx.item;

import net.azezmc.mysticvfx.network.PacketIds;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MysticItem extends Item {
    public MysticItem(Settings settings) { super(settings); }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (world.isClient) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeString("crescent_slash");

            ClientPlayNetworking.send(PacketIds.C2S_USE_SKILL, buf);
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
}