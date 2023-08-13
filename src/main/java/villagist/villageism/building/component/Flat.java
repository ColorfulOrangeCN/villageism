package villagist.villageism.building.component;

import villagist.villageism.building.math.CuboidSize;

public class Flat extends Cuboid{
    public Flat(int x, int z, Painter material) {
        super(material, new CuboidSize(x, 1, z));
    }
}
