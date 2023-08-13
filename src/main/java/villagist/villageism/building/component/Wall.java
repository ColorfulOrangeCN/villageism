package villagist.villageism.building.component;

import net.minecraft.util.math.Direction;
import villagist.villageism.building.math.CuboidSize;

public class Wall extends Cuboid {
    Direction direction;
    public Wall(int length, int height, Direction direction, Painter painter) {
        super(painter,
                new CuboidSize(direction.getVector().getX() * length,
                        height,
                        direction.getVector().getZ() * length));
        this.painter = painter;
        this.direction = direction;
    }
}
