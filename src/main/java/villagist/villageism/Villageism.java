package villagist.villageism;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.LiteralText;
import villagist.villageism.building.component.Cuboid;
import villagist.villageism.building.component.Pillar;
import villagist.villageism.building.component.slope.UniqueMaterialHalfSlope;
import villagist.villageism.building.component.slope.UniqueMaterialSlope;

public class Villageism implements ModInitializer {
    /**
     * Runs the mod initializer.
     */
    @Override
    public void onInitialize() {
        Cuboid.registerDebugCommand();
        Pillar.registerDebugCommand();
        UniqueMaterialSlope.registerDebugCommand();
        UniqueMaterialHalfSlope.registerDebugCommand();
    }
}
