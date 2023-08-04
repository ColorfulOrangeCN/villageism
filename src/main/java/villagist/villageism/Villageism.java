package villagist.villageism;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import villagist.villageism.villager.SmartVillgerEntity;

public class Villageism implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    public static final EntityType<SmartVillgerEntity> SMART_VILLAGER = Registry.register(Registry.ENTITY_TYPE,
            new Identifier("villageism", "smart_villager"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, SmartVillgerEntity::new)
                    .dimensions(EntityDimensions.fixed(0.8f, 1.8f)).build());
    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(SMART_VILLAGER, SmartVillgerEntity.createMobAttributes());
    }
}
