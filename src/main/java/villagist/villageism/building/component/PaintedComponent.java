package villagist.villageism.building.component;

import villagist.villageism.building.Component;

public abstract class PaintedComponent implements Component {
    Painter painter;
    public PaintedComponent(Painter painter) {
        this.painter = painter;
    }
}
