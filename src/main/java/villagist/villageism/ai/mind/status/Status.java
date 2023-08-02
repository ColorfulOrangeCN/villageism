package villagist.villageism.ai.mind.status;

import it.unimi.dsi.fastutil.objects.Object2LongMap;

public class Status {

    private Object2LongMap<String> statusSet;

    public Status() {
        // TODO: Initialise itself.
    }

    public void update(Object gameContext) {
        // TODO: Update itself.
    }

    public long getStatusValue(String id) {
        return statusSet.getLong(id);
    }

}
