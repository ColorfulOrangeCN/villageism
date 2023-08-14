package villagist.villageism.building.designer;

import net.minecraft.block.BlockState;
import villagist.villageism.building.Style;

public interface StaticStyle extends Style {
    @Override
    default BlockState getMaterial(String name) {
        try {
            return (BlockState) this.getClass().getDeclaredField(name).get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new IllegalArgumentException();
        }
    }
}
