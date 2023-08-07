package villagist.villageism.command;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.block.BlockState;
import net.minecraft.command.argument.BlockPosArgumentType;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.system.CallbackI;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Stack;

import static net.minecraft.server.command.CommandManager.literal;

public class CommandRegistry {
    final String[] prefix;
    public static CommandRegistry MOD = new CommandRegistry("villageism");
    public static CommandRegistry COMPONENT = new CommandRegistry("villageism", "component");

    public CommandRegistry(String... prefix) {
        this.prefix = prefix;
    }

    LiteralArgumentBuilder<ServerCommandSource> buildCommand(ArgumentBuilder<ServerCommandSource, ?> arguments) {
        LiteralArgumentBuilder<ServerCommandSource> res = null;
        for (int i = prefix.length - 1; i >= 0; --i) {
            String s = prefix[i];
            if (res == null) {
                res = literal(s).then(arguments);
            } else {
                res = literal(s).then(res);
            }
        }
        return res;
    }

    private void register(Method method/*, ArgumentType<?>... replaceArguments*/) throws IllegalStateException {
        CommandMethod anno = method.getAnnotation(CommandMethod.class);
        if (anno == null) {
            throw new IllegalStateException();
        }
        int permission = anno.permission();
        String commandName = anno.value();
        Parameter[] arguments = method.getParameters();
        String[] argNames = new String[method.getParameterCount()];
        Class<?>[] parameterTypes = method.getParameterTypes();
        Stack<ArgumentBuilder<ServerCommandSource, ?>> builders = new Stack<>();
        builders.push(literal(commandName).requires(source -> source.hasPermissionLevel(permission)));
        for (int i = anno.isSourceNecessary() ? 1 : 0; i < arguments.length; ++i) {
            Parameter a = arguments[i];
            var arga = a.getAnnotation(CommandArgument.class);
            argNames[i] = arga.value();
            ArgumentType<?> pass;
            if (arga.useDefaultType()) {
                if (parameterTypes[i].equals(Integer.class)) {
                    int[] argParameters = arga.argParameters();
                    if (argParameters.length == 0) {
                        pass = IntegerArgumentType.integer();
                    } else if (argParameters.length == 1) {
                        pass = IntegerArgumentType.integer(argParameters[0]);
                    } else if (argParameters.length == 2) {
                        pass = IntegerArgumentType.integer(argParameters[0], argParameters[1]);
                    } else {
                        throw new IllegalStateException();
                    }
                } else if (parameterTypes[i].equals(String.class)) {
                    pass = StringArgumentType.string();
                } else if (parameterTypes[i].equals(BlockPos.class)) {
                    pass = BlockPosArgumentType.blockPos();
                } else if (parameterTypes[i].equals(BlockState.class)) {
                    pass = BlockStateArgumentType.blockState();
                } // else if ...
                else {
                    throw new IllegalStateException();
                }
            } else {
                throw new IllegalStateException();
            }
            builders.push(CommandManager.argument(argNames[i], pass));
        }
        ArgumentBuilder<ServerCommandSource, ?> bd = builders.pop();
        bd = bd.executes((context) -> {
            Object[] args = new Object[method.getParameterCount()];
            if (anno.isSourceNecessary()) {
                args[0] = context.getSource();
            }
            int c = 0;
            for (int i = anno.isSourceNecessary() ? 1 : 0; i < arguments.length; ++i) {
                if (parameterTypes[i].equals(Integer.class)) {
                    args[i] = IntegerArgumentType.getInteger(context, argNames[i]);
                } else if (parameterTypes[i].equals(String.class)) {
                    args[i] = StringArgumentType.getString(context, argNames[i]);
                } else if (parameterTypes[i].equals(BlockPos.class)) {
                    args[i] = BlockPosArgumentType.getBlockPos(context, argNames[i]);
                } else if (parameterTypes[i].equals(BlockState.class)) {
                    args[i] = BlockStateArgumentType.getBlockState(context, argNames[i]);
                } else {
                    throw new IllegalStateException();
                }
            }
            try {
                return (Integer)method.invoke(null, args);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
        while (!builders.empty()) {
            bd = builders.pop().then(bd);
        }
        LiteralArgumentBuilder<ServerCommandSource> passer = buildCommand(bd);
        CommandRegistrationCallback.EVENT.register(((dispatcher, dedicated) -> dispatcher.register(passer)));
    }

    public <T> void registerClass(Class<T> cls) {
        for (Method method : cls.getDeclaredMethods()) {
            if (method.getAnnotation(CommandMethod.class) != null) {
                this.register(method);
            }
        }
    }
}
