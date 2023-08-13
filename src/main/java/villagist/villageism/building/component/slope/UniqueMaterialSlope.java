package villagist.villageism.building.component.slope;

import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Debug;
import villagist.villageism.building.Construction;
import villagist.villageism.building.PlacedBlock;

import java.util.ArrayList;

public class UniqueMaterialSlope extends Slope {

    protected BlockState material;

    public UniqueMaterialSlope(int xLen, int zLen, float gradient, int isFloor, Direction direction, BlockState material) {
        super(xLen, zLen, gradient, isFloor, direction);
        this.material = material;
    }

    public UniqueMaterialSlope(int xLen, int zLen, Direction direction, BlockState material) {
        this(xLen, zLen, DEFAULT_GRADIENT, DEFAULT_ISFLOOR, direction, material);
    }

    @Override
    protected BlockState getBlock(int x, int y, int z) {
        return material;
    }

    @Debug
    public static void registerDebugCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> dispatcher.register(CommandManager.literal("villageismcreateumslope")
                .then(CommandManager.argument("block_state", BlockStateArgumentType.blockState())
                .then(CommandManager.argument("starting_pos", BlockPosArgumentType.blockPos())
                .then(CommandManager.argument("extension_x", IntegerArgumentType.integer())
                .then(CommandManager.argument("extension_z", IntegerArgumentType.integer())
                .then(CommandManager.argument("gradient", FloatArgumentType.floatArg())
                .then(CommandManager.argument("floor_strategy", IntegerArgumentType.integer(-1, 1))
                .then(CommandManager.argument("direction_x", IntegerArgumentType.integer(-1, 1))
                .then(CommandManager.argument("direction_z", IntegerArgumentType.integer(-1, 1))
                .executes(context -> {
                    BlockState block = BlockStateArgumentType.getBlockState(context, "block_state").getBlockState();
                    BlockPos position = BlockPosArgumentType.getBlockPos(context, "starting_pos");
                    int xLen = IntegerArgumentType.getInteger(context, "extension_x");
                    int zLen = IntegerArgumentType.getInteger(context, "extension_z");
                    float gradient = FloatArgumentType.getFloat(context, "gradient");
                    int isFloor = IntegerArgumentType.getInteger(context, "floor_strategy");
                    int direction_x = IntegerArgumentType.getInteger(context, "direction_x");
                    int direction_z = IntegerArgumentType.getInteger(context, "direction_z");

                    if ((direction_x == 0 && direction_z == 0) || (direction_x != 0 && direction_z != 0)) {
                        context.getSource().getPlayer().sendMessage(new LiteralText("Invalid direction.")
                                .setStyle(Style.EMPTY.withFormatting(Formatting.RED)), false);
                        return 0;
                    }

                    Direction direction = Direction.getFacing((float)direction_x, 0.0f, (float)direction_z);
                    ArrayList<PlacedBlock> blockList = new Construction().addComponent(
                            new UniqueMaterialSlope(xLen, zLen, gradient, isFloor, direction, block), position).serialisePlan();
                    for (PlacedBlock placement : blockList) {
                        context.getSource().getWorld().setBlockState(new BlockPos(placement.pos()), placement.block());
                    }
                    return 0;
                })))))))))));
    }

}
