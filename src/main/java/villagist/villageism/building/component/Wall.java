package villagist.villageism.building.component;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Direction;
import villagist.villageism.building.math.CuboidSize;

public class Wall extends Cuboid {
    Direction direction;
    public Wall(int length, int height, Direction direction, BlockState material) {
        super(material,
                new CuboidSize(direction.getVector().getX() * length,
                        direction.getVector().getY() * height,
                        direction.getVector().getZ() * length));
        this.material = material;
        this.direction = direction;
    }
}
