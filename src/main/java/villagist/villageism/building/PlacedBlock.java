package villagist.villageism.building;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.Vec3i;

public record PlacedBlock(Vec3i pos, BlockState block) {
    public PlacedBlock add(Vec3i offset) {
        return new PlacedBlock(pos.add(offset), block);
    }
}
