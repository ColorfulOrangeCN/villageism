package villagist.villageism.building.math;

import net.minecraft.util.math.Vec3i;

public final class CuboidSize extends Vec3i {
    public static CuboidSize getSingleBlock() {
        return new CuboidSize(1, 1, 1);
    }
    public CuboidSize(int x, int y, int z) {
        super(Math.abs(x), Math.abs(y), Math.abs(z));
    }
    public CuboidSize(Vec3i vec) {
        super(Math.abs(vec.getX()) + 1, Math.abs(vec.getY()) + 1, Math.abs(vec.getZ()) + 1);
    }
    public int getSize() {
        return getX() * getY() * getZ();
    }
    public CuboidSize enlarge(int times) {
        return new CuboidSize(getX() * times, getY() * times, getZ() * times);
    }
}
