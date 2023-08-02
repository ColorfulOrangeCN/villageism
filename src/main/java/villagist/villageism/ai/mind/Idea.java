package villagist.villageism.ai.mind;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import villagist.villageism.ai.mind.status.Status;

public interface Idea {

    // Return a map of idea modifiers still required by the action represented by this idea, considering its current weight.
    Object2LongMap<Idea> getRequirements(Status stat, long currentWeight);

}
