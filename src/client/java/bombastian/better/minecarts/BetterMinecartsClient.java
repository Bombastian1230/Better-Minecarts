package bombastian.better.minecarts;

import bombastian.better.minecarts.entity.ModEntitys;
import bombastian.better.minecarts.handledscreen.FurnaceMinecartScreen;
import bombastian.better.minecarts.screenhandler.FurnaceMinecartScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class BetterMinecartsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntitys.MUD_BALL_ENTITY_TYPE, FlyingItemEntityRenderer::new);

		HandledScreens.<FurnaceMinecartScreenHandler, FurnaceMinecartScreen>register(BetterMinecarts.FURNACE_MINECART_SCREEN_HANDLER, (gui, invenory, title) -> new FurnaceMinecartScreen(gui, invenory.player.getInventory(), title));
	}
}

