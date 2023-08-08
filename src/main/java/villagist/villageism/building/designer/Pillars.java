package villagist.villageism.building.designer;

import net.minecraft.util.math.BlockPos;
import villagist.villageism.building.Component;
import villagist.villageism.building.Designer;
import villagist.villageism.building.DesignerType;
import villagist.villageism.building.Style;
import villagist.villageism.building.component.Offset;
import villagist.villageism.building.component.Pillar;

@DesignerType("Pillars")
public class Pillars extends AtomDesigner {

    BlockPos[] positions;
    int height;
    Pillars(BlockPos[] positions, int height) {
        this.positions = positions;
        this.height = height;
    }
    @Override
    public Component[] designFor(Style style, Designer parentDesigner) {
        Component[] components = new Pillar[positions.length];
        Pillar pillarTemplate = new Pillar(style.getMaterial("pillar"), height);
        for (int i = 0; i < positions.length; ++i) {
            components[i] = new Offset(pillarTemplate, positions[i]);
        }
        return components;
    }
}
