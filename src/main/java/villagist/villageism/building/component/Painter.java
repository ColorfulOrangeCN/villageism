package villagist.villageism.building.component;

import net.minecraft.block.BlockState;

public interface Painter {
    BlockState getState(int x, int y, int z);
}
