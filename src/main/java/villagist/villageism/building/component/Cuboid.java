package villagist.villageism.building.component;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;
import villagist.villageism.building.PlacedBlock;
import villagist.villageism.building.math.CuboidSize;

public class Cuboid extends UniqueMaterial {
    CuboidSize target;
    Cuboid( BlockState material, CuboidSize target) {
        super(material);
        this.target = target;
    }

    @Override
    public @NotNull PlacedBlock[] getStructure() {
        PlacedBlock[] goal = new PlacedBlock[target.getSize()];
        int cur = 0;
        for (int x = 0; x < target.getX(); ++x) {
            for (int y = 0; y < target.getY(); ++y) {
                for (int z = 0; z < target.getZ(); ++z) {
                    goal[cur++] = new PlacedBlock(new Vec3i(x, y, z), material);
                }
            }
        }
        return goal;
    }
}
