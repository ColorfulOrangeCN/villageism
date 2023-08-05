package villagist.villageism.building.designer;

import villagist.villageism.building.Component;
import villagist.villageism.building.Designer;
import villagist.villageism.building.DesignerType;
import villagist.villageism.building.Style;
@DesignerType("NoEffect")
public class NoEffectDesigner extends AtomDesigner{
    @Override
    public Component[] designFor(Style style, Designer parentDesigner) {
        return new Component[0];
    }
}
