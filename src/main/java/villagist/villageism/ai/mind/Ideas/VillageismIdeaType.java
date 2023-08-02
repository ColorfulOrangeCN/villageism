package villagist.villageism.ai.mind.Ideas;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import villagist.villageism.ai.mind.Status.VillageismStatus;

public interface VillageismIdeaType {

    // Return a map of idea modifiers still required by the action represented by this idea, considering its current weight.
    Object2LongMap<VillageismIdeaType> getRequirements(VillageismStatus stat, long currentWeight);

}
