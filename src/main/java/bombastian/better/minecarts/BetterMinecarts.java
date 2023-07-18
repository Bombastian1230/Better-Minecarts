package bombastian.better.minecarts;

import bombastian.better.minecarts.screenhandler.FurnaceMinecartScreenHandler;
import net.fabricmc.api.ModInitializer;


import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bombastian.better.minecarts.entity.ModEntitys;
import bombastian.better.minecarts.item.ModItemGroup;
import bombastian.better.minecarts.item.ModItems;

public class BetterMinecarts implements ModInitializer {
	public static final String MOD_ID = "betterminecrats";


	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static ScreenHandlerType<FurnaceMinecartScreenHandler> FURNACE_MINECART_SCREEN_HANDLER = new ScreenHandlerType(FurnaceMinecartScreenHandler::new, FeatureSet.empty());

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Fabric world!");

		ModItemGroup.registerItemGroups();
		ModItems.registerModItems();
		ModEntitys.registerModEntitys();
		Registry.register(Registries.SCREEN_HANDLER, new Identifier(BetterMinecarts.MOD_ID, "furnace_minecart_screen_handler"), FURNACE_MINECART_SCREEN_HANDLER);
	}
}