package bombastian.better.minecarts.entity;


import bombastian.better.minecarts.BetterMinecarts;
import bombastian.better.minecarts.entity.custom.MudBallEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntitys {
    public static final EntityType<MudBallEntity> MUD_BALL_ENTITY_TYPE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier(BetterMinecarts.MOD_ID),
            FabricEntityTypeBuilder.<MudBallEntity>create(SpawnGroup.MISC, MudBallEntity::new)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .trackRangeBlocks(100).trackedUpdateRate(10)
                .build()
    );


    public static void registerModEntitys() {
        BetterMinecarts.LOGGER.info("Registering item groups for " + BetterMinecarts.MOD_ID);
    }
}