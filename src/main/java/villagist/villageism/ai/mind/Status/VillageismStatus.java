package villagist.villageism.ai.mind.Status;

import it.unimi.dsi.fastutil.objects.Object2LongMap;

public class VillageismStatus {

    private Object2LongMap<String> statusSet;

    public VillageismStatus() {
        // TODO: Initialise itself.
    }

    public void update(Object gameContext) {
        // TODO: Update itself.
    }

    public long getStatusValue(String id) {
        return statusSet.getLong(id);
    }

}
