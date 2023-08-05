package villagist.villageism.building.component;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Vec3i;
import villagist.villageism.building.PlacedBlock;

public class Pillar extends UniqueMaterial {
    protected int height;
    public Pillar(BlockState material, int height) {
        super(material);
        this.height = height;
    }

    @Override
    public PlacedBlock[] getStructure() {
        PlacedBlock[] goal = new PlacedBlock[height];
        for (int i = 0; i < height; ++i) {
            goal[i] = new PlacedBlock(new Vec3i(0, i, 0), material);
        }
        return goal;
    }
}
