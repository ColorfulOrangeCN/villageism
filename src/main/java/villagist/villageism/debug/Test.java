package villagist.villageism.debug;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import villagist.villageism.command.CommandArgument;
import villagist.villageism.command.CommandMethod;

public class Test {
    @CommandMethod(value = "test", permission = 2, isSourceNecessary = true)
    public static int test(ServerCommandSource source, @CommandArgument("message") String message) {
        source.sendFeedback(new LiteralText(message), false);
        return 1;
    }

    @CommandMethod(value = "budget", permission = 0, isSourceNecessary = true)
    public static int test2(ServerCommandSource source, @CommandArgument("message") String message) {
        source.sendFeedback(new LiteralText(message), false);
        source.sendFeedback(new LiteralText(message), false);
        source.sendFeedback(new LiteralText(message), false);
        return 1;
    }

    @CommandMethod(value = "evaluate", permission = 0, isSourceNecessary = true)
    public static int test3(ServerCommandSource source, @CommandArgument(value = "value", argParameters = {1, 2}) Integer value) {
        source.sendFeedback(new LiteralText(String.format("%d", value)), false);
        return 1;
    }
}
