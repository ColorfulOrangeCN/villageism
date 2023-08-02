package villagist.villageism.ai.mind;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import villagist.villageism.ai.mind.Ideas.VillageismIdeaType;
import villagist.villageism.ai.mind.Inspirations.VillageismInspiration;
import villagist.villageism.ai.mind.Status.VillageismStatus;
import villagist.villageism.util.VillageismHeap;
import villagist.villageism.util.VillageismPair;

import java.util.Queue;
import java.util.Set;

public class VillageismMindSet {

    // It should allow get an element by its key, while also allowing sort elements by their value.
    protected VillageismHeap<VillageismIdeaType> ideaHeap;

    // Same as the heap above in functions.
    protected VillageismHeap<VillageismInspiration> inspirationHeap;

    // A structure that stores all the potential factors affecting the decisions of this mind.
    protected VillageismStatus status;

    protected Queue<VillageismPair<VillageismIdeaType>> potentialDecisions;

    static final int considerationDepth = 114;

    public VillageismMindSet() {
        // TODO: Initialise the inspirations.
    }

    protected void apply(Object2LongMap<VillageismIdeaType> modifier) {
        for (VillageismIdeaType idea : modifier.keySet()) {
            long newWeight = ideaHeap.getLong(idea) + modifier.getLong(idea);
            ideaHeap.remove(idea);
            ideaHeap.insert(idea, newWeight);
        }
    }

    protected void reassignInspirationWeight(Object gameContext) {
        // TODO: Update the inspirations.
    }

    // The parameter just represents a series of parameters providing all the required detailed information.
    public void tick(Object gameContext) {
        status.update(gameContext);
        reassignInspirationWeight(gameContext);

        for (VillageismInspiration inspiration : inspirationHeap.keySet()) {
            this.apply(inspiration.refreshMinds(status, inspirationHeap.getLong(inspiration)));
        }

        int considerationCounter = considerationDepth;
        while (!ideaHeap.isEmpty() && considerationCounter != 0) {
            VillageismIdeaType idea = ideaHeap.top();

            Object2LongMap<VillageismIdeaType> modifier = idea.getRequirements(status, ideaHeap.getLong(idea));
            // Check whether the current action will be replaced.
            if (modifier.isEmpty()) {
                potentialDecisions.add(new VillageismPair<VillageismIdeaType>(idea, ideaHeap.getLong(idea)));
            }
            // If the idea affects itself, the change will also be applied here.
            this.apply(modifier);

            considerationCounter -= 1;
            ideaHeap.remove(idea);
        }
    }

    public VillageismPair<VillageismIdeaType> decide() {
        return potentialDecisions.poll();
    }

}
