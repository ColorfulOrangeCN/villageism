package villagist.villageism.building;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.HashMap;

public class BuildingSite {
    HashMap<BlockPos, BlockState> todo, done;
    BuildingSite(BuildingPlan plan) {
        todo = plan.plan;
        done = new HashMap<>();
    }
}
