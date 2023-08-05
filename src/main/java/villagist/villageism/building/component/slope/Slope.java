package villagist.villageism.building.component.slope;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import villagist.villageism.building.Component;
import villagist.villageism.building.PlacedBlock;

import java.util.ArrayList;

public abstract class Slope implements Component {

    public static final float DEFAULT_GRADIENT = 1.0f;
    public static final int DEFAULT_ISFLOOR = 0;

    protected int xLen, zLen;
    protected float gradient;
    protected int isFloor;
    protected Direction direction;

    // direction: descending. (0, ±1, 0) is invalid
    // isFloor: +1(Ceiling), 0(Round), -1(Floor)
    public Slope(int xLen, int zLen, float gradient, int isFloor, Direction direction) {
        this.xLen = xLen;
        this.zLen = zLen;
        this.gradient = gradient;
        this.isFloor = isFloor;
        this.direction = direction;
    }

    public Slope(int xLen, int zLen, Direction direction) {
        this(xLen, zLen, DEFAULT_GRADIENT, DEFAULT_ISFLOOR, direction);
    }

    protected int getY(int x, int z) {
        float ret;
        if (direction == Direction.EAST) {
            ret = (float)(xLen - x - 1) * gradient;
        } else if (direction == Direction.WEST) {
            ret = (float)x * gradient;
        } else if (direction == Direction.SOUTH) {
            ret = (float)(zLen - z - 1) * gradient;
        } else if (direction == Direction.NORTH) {
            ret = (float)z * gradient;
        } else {
            ret = 0.0f;
        }
        if (isFloor == 1) {
            return (int) Math.ceil(ret);
        } else if (isFloor == -1) {
            return (int) Math.floor(ret);
        } else {
            return Math.round(ret);
        }
    }

    protected abstract BlockState getBlock(int x, int y, int z);

    protected boolean _exist(int x, int y, int z) {
        return true;
    }

    @Override
    public PlacedBlock[] getStructure() {
        ArrayList<PlacedBlock> goal = new ArrayList<>();
        for (int x = 0; x < xLen; ++x) {
            for (int z = 0; z < zLen; ++z) {
                int y = getY(x, z);
                if (!_exist(x, y, z)) continue;
                goal.add(new PlacedBlock(new Vec3i(x, y, z), getBlock(x, y, z)));
            }
        }
        return goal.toArray(new PlacedBlock[0]);
    }
}
