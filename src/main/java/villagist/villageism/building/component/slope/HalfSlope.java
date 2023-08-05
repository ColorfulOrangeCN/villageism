package villagist.villageism.building.component.slope;

import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;

public abstract class HalfSlope extends Slope {

    protected Vec3i cutoff;
    protected int cornerX, cornerZ;
    protected int originX, originZ;

    // cutoff: can only be (±1, 0, ±1). decides which half is to be removed.

    public HalfSlope(int xLen, int zLen, float gradient, int isFloor, Direction direction, Vec3i cutoff) {
        super(xLen, zLen, gradient, isFloor, direction);
        this.cutoff = cutoff;
        int cx = cutoff.getX(), cz = cutoff.getZ();
        int ox = -cx, oz = -cz;
        cx = (cx + 1) >> 1;
        cz = (cz + 1) >> 1;
        ox = (ox + 1) >> 1;
        oz = (oz + 1) >> 1;
        cornerX = cx * (xLen - 1);
        cornerZ = cz * (zLen - 1);
        originX = ox * (xLen - 1);
        originZ = oz * (zLen - 1);
    }

    public HalfSlope(int xLen, int zLen, Direction direction, Vec3i cutoff) {
        this(xLen, zLen, DEFAULT_GRADIENT, DEFAULT_ISFLOOR, direction, cutoff);
    }

    protected int _getDist(int x1, int z1, int x2, int z2) {
        return Math.abs(x1 - x2) + Math.abs(z1 - z2);
    }

    @Override
    protected boolean _exist(int x, int y, int z) {
        return _getDist(x, z, cornerX, cornerZ) >= _getDist(x, z, originX, originZ);
    }

}
