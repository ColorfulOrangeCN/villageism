package villagist.villageism.building.math;

import net.minecraft.util.math.Vec3i;

public final class CuboidSize extends Vec3i {
    public static CuboidSize getSingleBlock() {
        return new CuboidSize(1, 1, 1);
    }
    public CuboidSize(int x, int y, int z) {
        super(x, y, z);
    }
    public CuboidSize(Vec3i vec) {
        super(Math.abs(vec.getX()), Math.abs(vec.getY()), Math.abs(vec.getZ()));
    }
    public int getSize() {
        return getX() * getY() * getZ();
    }
    public CuboidSize enlarge(int times) {
        return new CuboidSize(getX() * times, getY() * times, getZ() * times);
    }
}
