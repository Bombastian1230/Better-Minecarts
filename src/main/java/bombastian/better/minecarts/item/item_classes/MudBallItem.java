package bombastian.better.minecarts.item.item_classes;

import bombastian.better.minecarts.entity.custom.MudBallEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class MudBallItem extends Item {
 
    public MudBallItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        player.playSound(SoundEvents.BLOCK_WOOL_BREAK, 1.0F, 1.0F);
        player.getItemCooldownManager().set(this, 5);
        
        if(!world.isClient) {
            MudBallEntity mudBallEntity = new MudBallEntity(world, player);
            mudBallEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0f, 1.5f, 1.0F);

            world.spawnEntity(mudBallEntity);
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));
        if(!player.getAbilities().creativeMode) {
            itemStack.decrement(1);
        }
        
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
