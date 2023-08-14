package villagist.villageism.building.designer;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;

public class HouseStyle implements StaticStyle {
    public BlockState floor = Blocks.COBBLESTONE.getDefaultState();
    public BlockState ceiling = floor;
}
