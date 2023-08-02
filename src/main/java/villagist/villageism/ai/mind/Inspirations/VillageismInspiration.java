package villagist.villageism.ai.mind.Inspirations;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import villagist.villageism.ai.mind.Ideas.VillageismIdeaType;
import villagist.villageism.ai.mind.Status.VillageismStatus;

public interface VillageismInspiration {

    // Insert initial ideas into the heap in each cycle of the main loop.
    // Things other than status may be considered in the future, for example inertial weight/focus weight,
    // so here may be some additional variables or sth else. It's not the final form.
    Object2LongMap<VillageismIdeaType> refreshMinds(VillageismStatus stat, long currentWeight);

}
