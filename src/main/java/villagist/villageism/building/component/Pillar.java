package villagist.villageism.building.component;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import org.spongepowered.asm.mixin.Debug;
import villagist.villageism.building.BuildingPlan;
import villagist.villageism.building.PlacedBlock;

import java.util.ArrayList;

public class Pillar extends UniqueMaterial {
    protected int height;
    public Pillar(BlockState material, int height) {
        super(material);
        this.height = height;
    }

    @Override
    public PlacedBlock[] getStructure() {
        PlacedBlock[] goal = new PlacedBlock[height];
        for (int i = 0; i < height; ++i) {
            goal[i] = new PlacedBlock(new Vec3i(0, i, 0), material);
        }
        return goal;
    }

    @Debug
    public static void registerDebugCommand() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("villageismcreatepillar")
                    .then(CommandManager.argument("block_state", BlockStateArgumentType.blockState())
                    .then(CommandManager.argument("starting_pos", BlockPosArgumentType.blockPos())
                    .then(CommandManager.argument("height", IntegerArgumentType.integer())
                    .executes(context -> {
                        BlockState block = BlockStateArgumentType.getBlockState(context, "block_state").getBlockState();
                        BlockPos position = BlockPosArgumentType.getBlockPos(context, "starting_pos");
                        int height = IntegerArgumentType.getInteger(context, "height");
                        ArrayList<PlacedBlock> blockList = new BuildingPlan().addComponent(new Pillar(block, height), position).serialisePlan();
                        for (PlacedBlock placement : blockList) {
                            context.getSource().getWorld().setBlockState(new BlockPos(placement.pos()), placement.block());
                        }
                        return 0;
                    })))));
        });
    }
}

