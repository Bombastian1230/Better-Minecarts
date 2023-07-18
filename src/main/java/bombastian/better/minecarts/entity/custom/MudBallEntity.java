package bombastian.better.minecarts.entity.custom;

import bombastian.better.minecarts.entity.ModEntitys;
import bombastian.better.minecarts.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.BlazeEntity;
// import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;

public class MudBallEntity extends ThrownEntity implements FlyingItemEntity {

    public MudBallEntity(EntityType<? extends ThrownEntity> entityType, World world) {
        super(entityType, world);
    }

    public MudBallEntity(World world, LivingEntity owner) {
        super(ModEntitys.MUD_BALL_ENTITY_TYPE, owner, world);
    }

    public MudBallEntity(World world, double x, double y, double z) {
        super(ModEntitys.MUD_BALL_ENTITY_TYPE, x, y, z, world); 
    }


	protected Item getDefaultItem() {
		return ModItems.MUD_BALL_ITEM;
	}

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);

        Entity entity = entityHitResult.getEntity(); // Sets entity to the hit entity
        int i = entity instanceof BlazeEntity ? 3 : 1; // 3 Damage if blaze
        entity.damage(getWorld().getDamageSources().thrown(this, this.getOwner()), (float)i); // Damage
    
        if(entity instanceof LivingEntity livingEntity) {
            livingEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 20 * 3, 1, true, true, true), livingEntity);
        }
    }

    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if(!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte)3);
            this.kill();
        }
    }

    @Override
    protected void initDataTracker() {
        //throw new UnsupportedOperationException("Unimplemented method 'initDataTracker'");
    }

    @Override
    public ItemStack getStack() {
        return new ItemStack(getDefaultItem(), 1);
    }
    

    @Override
    public boolean shouldRender(double distance) {
        double d = this.getBoundingBox().getAverageSideLength() * 4.0;
        if (Double.isNaN(d)) {
            d = 4.0;
        }
        return distance < (d *= 1000.0) * d;
    }

}
