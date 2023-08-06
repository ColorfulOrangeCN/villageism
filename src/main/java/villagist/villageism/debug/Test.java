package villagist.villageism.debug;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.LiteralText;
import villagist.villageism.command.CommandArgument;
import villagist.villageism.command.CommandMethod;

public class Test {
    @CommandMethod(value = "test", isSourceNecessary = true)
    public static int test(ServerCommandSource source, @CommandArgument("message") String message) {
        source.sendFeedback(new LiteralText(message), false);
        return 1;
    }
}
