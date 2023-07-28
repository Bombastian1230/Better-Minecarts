package bombastian.better.minecarts;

import bombastian.better.minecarts.screenhandler.FurnaceMinecartScreenHandler;
import net.fabricmc.api.ModInitializer;


import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bombastian.better.minecarts.entity.ModEntitys;
import bombastian.better.minecarts.item.ModItemGroup;
import bombastian.better.minecarts.item.ModItems;

public class BetterMinecarts implements ModInitializer {
	public static final String MOD_ID = "betterminecarts";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//public static final Identifier anId = new Identifier(BetterMinecarts.MOD_ID , "assets/betterminecarts/textures/gui/container/furnace_minecart_gui.png");
	public static ScreenHandlerType<FurnaceMinecartScreenHandler> FURNACE_MINECART_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new Identifier("minecraft", "furnace_minecart"),
			new ScreenHandlerType<>(FurnaceMinecartScreenHandler::new, FeatureFlags.VANILLA_FEATURES));


	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModEntitys.registerModEntitys();
	}
}