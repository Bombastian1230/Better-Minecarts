package bombastian.better.minecarts;

import bombastian.better.minecarts.entity.ModEntitys;
import bombastian.better.minecarts.handledscreen.FurnaceMinecartScreen;
import bombastian.better.minecarts.screenhandler.FurnaceMinecartScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class BetterMinecartsClient implements ClientModInitializer {

	public static ScreenHandlerType<FurnaceMinecartScreenHandler> FURNACE_MINECART_SCREEN_HANDLER = new ScreenHandlerType(FurnaceMinecartScreenHandler::new, FeatureSet.empty());


	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntitys.MUD_BALL_ENTITY_TYPE, (context) -> new FlyingItemEntityRenderer<>(context));

		Registry.register(Registries.SCREEN_HANDLER, new Identifier(BetterMinecarts.MOD_ID, "furnace_minecart_screen_handler"), FURNACE_MINECART_SCREEN_HANDLER);
		ScreenRegistry.register(FURNACE_MINECART_SCREEN_HANDLER, FurnaceMinecartScreen::new);
	}
}

