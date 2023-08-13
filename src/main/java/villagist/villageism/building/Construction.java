package villagist.villageism.building;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Construction {
    HashMap<BlockPos, BlockState> plan;
    ArrayList<PlacedBlock> trash;

    public Construction() {
        plan = new HashMap<>();
        trash = new ArrayList<>();
    }
    public Construction addComponent(@NotNull Component component, BlockPos anchor) {
        trash = new ArrayList<>();
        for (PlacedBlock block : component.getStructure()) {
            BlockState old = plan.put(anchor.add(block.pos()), block.block());
            if (old != null) {
                trash.add(new PlacedBlock(block.pos(), old));
            }
        }
        return this;
    }
    public ArrayList<PlacedBlock> serialisePlan() {
        ArrayList<PlacedBlock> ret = new ArrayList<>();
        for (Map.Entry<BlockPos, BlockState> entry : plan.entrySet()) {
            ret.add(new PlacedBlock(entry.getKey(), entry.getValue()));
        }
        return ret;
    }

    public ArrayList<PlacedBlock> getTrash() {
        return trash;
    }
}
