package bombastian.better.minecarts.mixin;

import bombastian.better.minecarts.BetterMinecarts;
import bombastian.better.minecarts.interfaces.ImplementedInventory;
import bombastian.better.minecarts.screenhandler.FurnaceMinecartScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FurnaceMinecartEntity.class)
public class FurnaceMinecartMixin implements NamedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);


    @Inject(at = {@At("HEAD")}, method = "interact", cancellable = true)
    public void interactM(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        FurnaceMinecartEntity furnaceMinecart = (FurnaceMinecartEntity) (Object) this;

        SimpleInventory inventory = new SimpleInventory(9);

        SimpleNamedScreenHandlerFactory namedScreenHandlerFactory = new SimpleNamedScreenHandlerFactory(() -> new FurnaceMinecartScreenHandler(0, player.getInventory(), inventory), "Furnace Minecart");

        cir.setReturnValue(ActionResult.success(furnaceMinecart.getWorld().isClient));
    }

    // GUI
    @Override
    public Text getDisplayName() {
        return Text.translatable(getDisplayName().getString());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FurnaceMinecartScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }
}
