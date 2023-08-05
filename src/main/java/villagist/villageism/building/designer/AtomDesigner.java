package villagist.villageism.building.designer;

import villagist.villageism.building.Designer;

public abstract class AtomDesigner implements Designer {
    @Override
    public void registerDesigner(Designer designer) {}

    @Override
    public Designer[] getDesigners() {
        return new Designer[0];
    }
}
