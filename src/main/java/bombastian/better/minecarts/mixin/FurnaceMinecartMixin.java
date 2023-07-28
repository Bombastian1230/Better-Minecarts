package bombastian.better.minecarts.mixin;

import bombastian.better.minecarts.interfaces.ImplementedInventory;
import bombastian.better.minecarts.screenhandler.FurnaceMinecartScreenHandler;
import com.google.common.collect.Maps;
import net.minecraft.SharedConstants;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.vehicle.AbstractMinecartEntity;
import net.minecraft.entity.vehicle.FurnaceMinecartEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.screen.*;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;
import java.util.Map;

@Mixin(FurnaceMinecartEntity.class)
public abstract class FurnaceMinecartMixin extends AbstractMinecartEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    protected FurnaceMinecartMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Shadow protected abstract Item getItem();


    @Shadow private int fuel;
    @Unique private DefaultedList<ItemStack> items = DefaultedList.ofSize(3, ItemStack.EMPTY);
    @Unique private World world;
    @Unique private final FurnaceMinecartEntity furnaceMinecart = (FurnaceMinecartEntity) (Object) this;
    @Unique
    private SimpleInventory simpleInventory = new SimpleInventory(1);


    @Unique private static PropertyDelegate propertyDelegate;

    @Unique private static int fuelM = 0;
    @Unique private static int maxFuelTime = 0;

    @Unique private static Map<Item, Integer> createFuelTimeMap() {
        Map<Item, Integer> map = Maps.newLinkedHashMap();
        addFuel(map, (ItemConvertible)Items.LAVA_BUCKET, 20000);
        addFuel(map, (ItemConvertible)Items.COAL_BLOCK, 16000);
        addFuel(map, (ItemConvertible)Items.BLAZE_ROD, 2400);
        addFuel(map, (ItemConvertible)Items.COAL, 1600);
        addFuel(map, (ItemConvertible)Items.CHARCOAL, 1600);
        addFuel(map, (TagKey<Item>)ItemTags.LOGS, 300);
        addFuel(map, (TagKey<Item>)ItemTags.BAMBOO_BLOCKS, 300);
        addFuel(map, (TagKey<Item>)ItemTags.PLANKS, 300);
        addFuel(map, (ItemConvertible) Blocks.BAMBOO_MOSAIC, 300);
        addFuel(map, (TagKey<Item>)ItemTags.WOODEN_STAIRS, 300);
        addFuel(map, (ItemConvertible)Blocks.BAMBOO_MOSAIC_STAIRS, 300);
        addFuel(map, (TagKey<Item>)ItemTags.WOODEN_SLABS, 150);
        addFuel(map, (ItemConvertible)Blocks.BAMBOO_MOSAIC_SLAB, 150);
        addFuel(map, (TagKey<Item>)ItemTags.WOODEN_TRAPDOORS, 300);
        addFuel(map, (TagKey<Item>)ItemTags.WOODEN_PRESSURE_PLATES, 300);
        addFuel(map, (TagKey<Item>)ItemTags.WOODEN_FENCES, 300);
        addFuel(map, (TagKey<Item>)ItemTags.FENCE_GATES, 300);
        addFuel(map, (ItemConvertible)Blocks.NOTE_BLOCK, 300);
        addFuel(map, (ItemConvertible)Blocks.BOOKSHELF, 300);
        addFuel(map, (ItemConvertible)Blocks.CHISELED_BOOKSHELF, 300);
        addFuel(map, (ItemConvertible)Blocks.LECTERN, 300);
        addFuel(map, (ItemConvertible)Blocks.JUKEBOX, 300);
        addFuel(map, (ItemConvertible)Blocks.CHEST, 300);
        addFuel(map, (ItemConvertible)Blocks.TRAPPED_CHEST, 300);
        addFuel(map, (ItemConvertible)Blocks.CRAFTING_TABLE, 300);
        addFuel(map, (ItemConvertible)Blocks.DAYLIGHT_DETECTOR, 300);
        addFuel(map, (TagKey<Item>)ItemTags.BANNERS, 300);
        addFuel(map, (ItemConvertible)Items.BOW, 300);
        addFuel(map, (ItemConvertible)Items.FISHING_ROD, 300);
        addFuel(map, (ItemConvertible)Blocks.LADDER, 300);
        addFuel(map, (TagKey<Item>)ItemTags.SIGNS, 200);
        addFuel(map, (TagKey<Item>)ItemTags.HANGING_SIGNS, 800);
        addFuel(map, (ItemConvertible)Items.WOODEN_SHOVEL, 200);
        addFuel(map, (ItemConvertible)Items.WOODEN_SWORD, 200);
        addFuel(map, (ItemConvertible)Items.WOODEN_HOE, 200);
        addFuel(map, (ItemConvertible)Items.WOODEN_AXE, 200);
        addFuel(map, (ItemConvertible)Items.WOODEN_PICKAXE, 200);
        addFuel(map, (TagKey<Item>)ItemTags.WOODEN_DOORS, 200);
        addFuel(map, (TagKey<Item>)ItemTags.BOATS, 1200);
        addFuel(map, (TagKey<Item>)ItemTags.WOOL, 100);
        addFuel(map, (TagKey<Item>)ItemTags.WOODEN_BUTTONS, 100);
        addFuel(map, (ItemConvertible)Items.STICK, 100);
        addFuel(map, (TagKey<Item>)ItemTags.SAPLINGS, 100);
        addFuel(map, (ItemConvertible)Items.BOWL, 100);
        addFuel(map, (TagKey<Item>)ItemTags.WOOL_CARPETS, 67);
        addFuel(map, (ItemConvertible)Blocks.DRIED_KELP_BLOCK, 4001);
        addFuel(map, (ItemConvertible)Items.CROSSBOW, 300);
        addFuel(map, (ItemConvertible)Blocks.BAMBOO, 50);
        addFuel(map, (ItemConvertible)Blocks.DEAD_BUSH, 100);
        addFuel(map, (ItemConvertible)Blocks.SCAFFOLDING, 50);
        addFuel(map, (ItemConvertible)Blocks.LOOM, 300);
        addFuel(map, (ItemConvertible)Blocks.BARREL, 300);
        addFuel(map, (ItemConvertible)Blocks.CARTOGRAPHY_TABLE, 300);
        addFuel(map, (ItemConvertible)Blocks.FLETCHING_TABLE, 300);
        addFuel(map, (ItemConvertible)Blocks.SMITHING_TABLE, 300);
        addFuel(map, (ItemConvertible)Blocks.COMPOSTER, 300);
        addFuel(map, (ItemConvertible)Blocks.AZALEA, 100);
        addFuel(map, (ItemConvertible)Blocks.FLOWERING_AZALEA, 100);
        addFuel(map, (ItemConvertible)Blocks.MANGROVE_ROOTS, 300);
        return map;
    }

    @Unique
    private static void addFuel(Map<Item, Integer> fuelTimes, TagKey<Item> tag, int fuelTime) {
        Iterator<RegistryEntry<Item>> var3 = Registries.ITEM.iterateEntries(tag).iterator();

        while(var3.hasNext()) {
            RegistryEntry<Item> registryEntry = var3.next();
            if (!isNonFlammableWood((Item)registryEntry.value())) {
                fuelTimes.put((Item)registryEntry.value(), fuelTime);
            }
        }

    }

    @Unique
    private static void addFuel(Map<Item, Integer> fuelTimes, ItemConvertible item, int fuelTime) {
        Item item2 = item.asItem();
        if (isNonFlammableWood(item2)) {
            if (SharedConstants.isDevelopment) {
                throw (IllegalStateException) Util.throwOrPause(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item2.getName((ItemStack)null).getString() + " a furnace fuel. That will not work!"));
            }
        } else {
            fuelTimes.put(item2, fuelTime);
        }
    }

    @Unique
    private static boolean isNonFlammableWood(Item item) {
        return item.getRegistryEntry().isIn(ItemTags.NON_FLAMMABLE_WOOD);
    }



    // Injections
    @Inject(at = {@At("HEAD")}, method = "interact", cancellable = true)
    public void interactH(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        world = furnaceMinecart.getWorld();


        if(!world.isClient()) {
            player.openHandledScreen(new SimpleNamedScreenHandlerFactory(((syncId, playerInventory, player1) -> new FurnaceMinecartScreenHandler(syncId, playerInventory, simpleInventory, propertyDelegate)), getDisplayName()));

            items = simpleInventory.stacks;
        }

        cir.setReturnValue(ActionResult.success(world.isClient()));
    }

    @Inject(at = {@At("HEAD")}, method = "tick")
    public void tickT(CallbackInfo ci) {
        Item fuelItem;

        if() {

        }
    }

    @Unique
    private boolean isFuelItem(ItemStack stack) {
        return createFuelTimeMap().containsKey(stack.getItem());
    }

    @Unique
    private boolean isValidFuel(ItemStack stack) {
        ItemStack itemStack = (ItemStack)this.simpleInventory.getStack(0);
        return isFuelItem(stack) || stack.isOf(Items.BUCKET) && !itemStack.isOf(Items.BUCKET);
    }

    @Inject(at ={@At("TAIL")}, method = "<clinit>")
    private static void constructorT(CallbackInfo ci) {
        propertyDelegate = new PropertyDelegate() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> fuelM;
                    case 1 -> maxFuelTime;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> fuelM = value;
                    case 1 -> maxFuelTime = value;
                }
            }

            public int size() {
                return 2;
            }
        };
    }

    // GUI
    @Override
    public Text getDisplayName() {
        return Text.literal("Furnace Minecart");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new FurnaceMinecartScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    // Crafting

    @Unique
    private void resetFuelTime() {
        fuelM = 0;
    }

    // Nbt

    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.items);
        fuelM = nbt.getInt("furnace_minecart.progress");
    }

    public NbtCompound writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.items);
        nbt.putInt("furnace_minecart.progress", fuelM);
        return nbt;
    }
}
