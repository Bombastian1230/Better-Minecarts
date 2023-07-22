package bombastian.better.minecarts.mixin;

import bombastian.better.minecarts.interfaces.ImplementedInventory;
import bombastian.better.minecarts.screenhandler.FurnaceMinecartScreenHandler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceMinecartEntity.class)
public abstract class FurnaceMinecartMixin extends AbstractMinecartEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    protected FurnaceMinecartMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Shadow protected abstract Item getItem();


    @Unique private DefaultedList<ItemStack> items = DefaultedList.ofSize(9, ItemStack.EMPTY);
    @Unique private World world;
    @Unique private final FurnaceMinecartEntity furnaceMinecart = (FurnaceMinecartEntity) (Object) this;
    @Unique
    private SimpleInventory simpleInventory = new SimpleInventory(9);


    @Inject(at = {@At("HEAD")}, method = "interact", cancellable = true)
    public void interactM(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        world = furnaceMinecart.getWorld();


        if(!world.isClient()) {
            player.openHandledScreen(new SimpleNamedScreenHandlerFactory(((syncId, playerInventory, player1) -> new FurnaceMinecartScreenHandler(syncId, playerInventory, simpleInventory)), getDisplayName()));

            items = simpleInventory.stacks;
        }

        cir.setReturnValue(ActionResult.success(world.isClient()));
    }

    // GUI
    @Override
    public Text getDisplayName() {
        return Text.literal("Furnace Minecart");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FurnaceMinecartScreenHandler(syncId, playerInventory, this);
    }

    @SuppressWarnings("AddedMixinMembersNamePattern")
    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.items);
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.items);
        return nbt;
    }
}
