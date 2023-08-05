package villagist.villageism.building;

import org.spongepowered.asm.mixin.Debug;

public interface Component {
    PlacedBlock[] getStructure();
}
