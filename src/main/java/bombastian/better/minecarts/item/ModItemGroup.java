package bombastian.better.minecarts.item;

import bombastian.better.minecarts.BetterMinecarts;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


public class ModItemGroup {
    public static final ItemGroup MOD_GROUP = Registry.register(Registries.ITEM_GROUP,
    new Identifier(BetterMinecarts.MOD_ID, "mod_group"),
    FabricItemGroup.builder().displayName(Text.translatable("itemgroup.mod_group"))
            .icon(() -> new ItemStack(ModItems.MUD_BALL_ITEM)).entries((displayContext, entries) -> {
                entries.add(ModItems.MUD_BALL_ITEM);

                entries.add(Items.DIAMOND);


            }).build());

    public static void registerItemGroups() {
        BetterMinecarts.LOGGER.info("Registering item groups for " + BetterMinecarts.MOD_ID);
    }
}
