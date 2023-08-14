package villagist.villageism.building.designer;

import net.minecraft.util.math.Vec3i;
import villagist.villageism.building.Component;
import villagist.villageism.building.component.*;

import java.util.ArrayList;

public class Floor {
    Component[][] binder;
    Flat floor, ceiling;
    int height;
    public Floor(int x, int z, int height, HouseStyle style) {
        this.height = height;
        binder = new Component[x][z];
        floor = new Flat(x, z, new UniqueMaterial(style.floor));
        ceiling = new Flat(x, z, new UniqueMaterial(style.ceiling));
    }
    public Component[] generate() {
        ArrayList<Component> result = new ArrayList<>();
        // TODO
        result.add(floor);
        result.add(new Offset(ceiling, new Vec3i(0, height - 1, 0)));
        return result.toArray(new Component[0]);
    }
}
