package villagist.villageism.building;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildingPlan {
    HashMap<BlockPos, BlockState> plan;

    public BuildingPlan() {
        plan = new HashMap<>();
    }
    public PlacedBlock[] addComponent(@NotNull Component component, BlockPos anchor) {
        ArrayList<PlacedBlock> trash = new ArrayList<>();
        for (PlacedBlock block : component.getStructure()) {
            BlockState old = plan.put(anchor.add(block.pos()), block.block());
            if (old != null) {
                trash.add(new PlacedBlock(block.pos(), old));
            }
        }
        return trash.toArray(new PlacedBlock[0]);
    }
}
