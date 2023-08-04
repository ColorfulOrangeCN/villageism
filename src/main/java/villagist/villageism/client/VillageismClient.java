package villagist.villageism.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.VillagerEntityRenderer;
import villagist.villageism.Villageism;

@Environment(EnvType.CLIENT)
public class VillageismClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(Villageism.SMART_VILLAGER, VillagerEntityRenderer::new);
    }
}
