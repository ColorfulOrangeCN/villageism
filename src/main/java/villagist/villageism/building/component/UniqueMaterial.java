package villagist.villageism.building.component;

import net.minecraft.block.BlockState;
import villagist.villageism.building.Component;

public abstract class UniqueMaterial implements Component {
    protected BlockState material;
    UniqueMaterial(BlockState material) {
        this.material = material;
    }
}
