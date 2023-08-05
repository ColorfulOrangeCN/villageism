package villagist.villageism.building.designer;

import villagist.villageism.building.DesignerType;
import villagist.villageism.building.math.CuboidSize;

@DesignerType("LandScope")
public class LandScope extends NoEffectDesigner {
    CuboidSize size;
    public LandScope() {
        this.size = CuboidSize.getSingleBlock();
    }
    public LandScope(CuboidSize size) {
        this.size = size;
    }

    public CuboidSize getSize() {
        return size;
    }
}
