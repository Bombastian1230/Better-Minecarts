package bombastian.better.minecarts.item;

import bombastian.better.minecarts.BetterMinecarts;
import bombastian.better.minecarts.item.item_classes.MudBallItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {

    // public static final Item MUD_BALL = new MudBall(new FabricItemSettings());
    public static final MudBallItem MUD_BALL_ITEM = new MudBallItem(new Item.Settings().maxCount(16));

    public static void registerModItems() {
        Registry.register(Registries.ITEM, new Identifier(BetterMinecarts.MOD_ID, "mud_ball"), MUD_BALL_ITEM);
    }
}
