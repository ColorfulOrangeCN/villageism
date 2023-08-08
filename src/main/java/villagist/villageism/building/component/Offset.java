package villagist.villageism.building.component;

import net.minecraft.util.math.Vec3i;
import villagist.villageism.building.Component;
import villagist.villageism.building.PlacedBlock;

public class Offset implements Component {
    Component component;
    Vec3i offset;
    public Offset(Component component, Vec3i offset) {
        this.component = component;
        this.offset = offset;
    }
    @Override
    public PlacedBlock[] getStructure() {
        PlacedBlock[] blocks = component.getStructure();
        PlacedBlock[] result = new PlacedBlock[blocks.length];
        for (int i = 0; i < result.length; ++i) {
            result[i] = blocks[i].add(offset);
        }
        return result;
    }
}
