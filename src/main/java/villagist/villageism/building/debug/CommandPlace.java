package villagist.villageism.building.debug;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import villagist.villageism.building.Component;
import villagist.villageism.building.PlacedBlock;

import java.lang.reflect.Modifier;
import java.text.Normalizer;
import java.util.List;

/* Unfinished. */
@Deprecated
public class CommandPlace {

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
            dispatcher.register(CommandManager.literal("villageismplace")
                    .then(CommandManager.argument("component_name", StringArgumentType.string()))
                    .then(CommandManager.argument("block_state", BlockStateArgumentType.blockState()))
                    .then(CommandManager.argument("position", BlockPosArgumentType.blockPos()))
                    .executes(context -> {
                return execute(context.getSource(),
                        StringArgumentType.getString(context, "component_name"),
                        BlockStateArgumentType.getBlockState(context, "block_state").getBlockState(),
                        BlockPosArgumentType.getBlockPos(context, "position"));
            }));
        });
    }

    public static int execute(ServerCommandSource source, String componentName, BlockState block, BlockPos position) {
        try {
            ServerPlayerEntity player = source.getPlayer();
            try {
                Class componentType = Class.forName("villagist.villageism.building.component." + componentName);
                if (componentType.isAssignableFrom(Component.class) && !Modifier.isAbstract(componentType.getModifiers())) {
                    // Component component = componentType.getDeclaredConstructor();

                }
            } catch(ClassNotFoundException exception) {
                player.sendMessage(new LiteralText("Such component doesn't exist.")
                        .setStyle(Style.EMPTY.withFormatting(Formatting.RED)), false);
            }
        } catch (CommandSyntaxException exception) {
            List<ServerPlayerEntity> playerList = source.getWorld().getPlayers();
            for (ServerPlayerEntity player : playerList) {
                player.sendMessage(new LiteralText("Someone is trying to use /villageismplace on a command block.")
                        .setStyle(Style.EMPTY.withFormatting(Formatting.RED)), false);
            }
        }
        return 0;
    }

}
