package villagist.villageism.building.component;

import net.minecraft.block.BlockState;
import villagist.villageism.building.math.CuboidSize;

public class Flat extends Cuboid{
    public Flat(int x, int z, BlockState material) {
        super(material, new CuboidSize(x, 1, z));
    }
}
