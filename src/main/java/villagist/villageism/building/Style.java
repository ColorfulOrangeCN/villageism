package villagist.villageism.building;

import net.minecraft.block.BlockState;

public interface Style {
    BlockState getMaterial(String name);
}
