package villagist.villageism.building.component;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Debug;
import villagist.villageism.building.Construction;
import villagist.villageism.building.PlacedBlock;
import villagist.villageism.building.math.CuboidSize;

import java.util.ArrayList;

public class Cuboid extends PaintedComponent {
    CuboidSize target;
    Cuboid( Painter painter, CuboidSize target) {
        super(painter);
        this.target = target;
    }

    @Override
    public @NotNull PlacedBlock[] getStructure() {
        PlacedBlock[] goal = new PlacedBlock[target.getSize()];
        int cur = 0;
        for (int x = 0; x < target.getX(); ++x) {
            for (int y = 0; y < target.getY(); ++y) {
                for (int z = 0; z < target.getZ(); ++z) {
                    goal[cur++] = new PlacedBlock(new Vec3i(x, y, z), painter.getState(x, y, z));
                }
            }
        }
        return goal;
    }

    @Debug
    public static void registerDebugCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(CommandManager.literal("villageismcreatecuboid")
                .then(CommandManager.argument("block_state", BlockStateArgumentType.blockState())
                .then(CommandManager.argument("starting_pos", BlockPosArgumentType.blockPos())
                .then(CommandManager.argument("extension_x", IntegerArgumentType.integer())
                .then(CommandManager.argument("extension_y", IntegerArgumentType.integer())
                .then(CommandManager.argument("extension_z", IntegerArgumentType.integer())
                .executes(context -> {
                    BlockState block = BlockStateArgumentType.getBlockState(context, "block_state").getBlockState();
                    BlockPos position = BlockPosArgumentType.getBlockPos(context, "starting_pos");
                    int extension_x = IntegerArgumentType.getInteger(context, "extension_x");
                    int extension_y = IntegerArgumentType.getInteger(context, "extension_y");
                    int extension_z = IntegerArgumentType.getInteger(context, "extension_z");
                    ArrayList<PlacedBlock> blockList = new Construction().addComponent(new Cuboid(new UniqueMaterial(block),
                            new CuboidSize(extension_x, extension_y, extension_z)), position).serialisePlan();
                    for (PlacedBlock placement : blockList) {
                        context.getSource().getWorld().setBlockState(new BlockPos(placement.pos()), placement.block());
                    }
                    return 0;
                }))))))));
    }
}
