package villagist.villageism.building.component;

import net.minecraft.block.BlockState;

public class UniqueMaterial implements Painter {
    protected BlockState material;
    UniqueMaterial(BlockState material) {
        this.material = material;
    }

    @Override
    public BlockState getState(int x, int y, int z) {
        return material;
    }
}
