package bombastian.better.minecarts.mixin;

import bombastian.better.minecarts.BetterMinecarts;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerMixin {

/*
	@Inject(at = @At("HEAD"), method = "dropItem(Lnet/minecraft/item/ItemStack;ZZ)Lnet/minecraft/entity/ItemEntity;", cancellable = true)
	public void dropItemM(ItemStack stack, boolean throwRandomly, boolean retainOwnership, CallbackInfoReturnable<ItemEntity> cir) {
		// This code is injected at the start of when a player drops an item
		PlayerEntity player = ( PlayerEntity ) ( Object ) this;

		BetterMinecarts.LOGGER.info(String.valueOf(cir.getReturnValue() != null));

		double d = player.getEyeY() - (double)0.3f;
		ItemEntity itemEntity = new ItemEntity(player.getWorld(), player.getX(), d, player.getZ(), stack);
		cir.setReturnValue(itemEntity);
	}
*/
}