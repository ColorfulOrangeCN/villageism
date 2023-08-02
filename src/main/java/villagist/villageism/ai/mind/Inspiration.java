package villagist.villageism.ai.mind;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import villagist.villageism.ai.mind.status.Status;

public interface Inspiration {

    // Insert initial ideas into the heap in each cycle of the main loop.
    // Things other than status may be considered in the future, for example inertial weight/focus weight,
    // so here may be some additional variables or sth else. It's not the final form.
    Object2LongMap<Idea> refreshMinds(Status stat, long currentWeight);

}
