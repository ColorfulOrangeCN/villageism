package villagist.villageism;

import net.fabricmc.api.ModInitializer;
import villagist.villageism.building.component.Cuboid;
import villagist.villageism.building.component.Pillar;
import villagist.villageism.building.component.slope.UniqueMaterialHalfSlope;
import villagist.villageism.building.component.slope.UniqueMaterialSlope;
import villagist.villageism.command.CommandRegistry;
import villagist.villageism.debug.Test;

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
        CommandRegistry.COMPONENT.registerClass(Test.class);
//        try {
//            CommandRegistry.MOD.regsiter(this.getClass().getDeclaredMethod("test"));
//        } catch (NoSuchMethodException e) {
//            throw new IllegalArgumentException(e);
//        }
    }
}
