package villagist.villageism.ai.mind;

import it.unimi.dsi.fastutil.objects.Object2LongMap;
import villagist.villageism.ai.mind.status.Status;
import villagist.villageism.util.VillageismHeap;
import villagist.villageism.util.VillageismPair;

import java.util.Queue;

public class MindSet {

    // It should allow get an element by its key, while also allowing sort elements by their value.
    protected VillageismHeap<Idea> ideaHeap;

    // Same as the heap above in functions.
    protected VillageismHeap<Inspiration> inspirationHeap;

    // A structure that stores all the potential factors affecting the decisions of this mind.
    protected Status status;

    protected Queue<VillageismPair<Idea>> potentialDecisions;

    static final int considerationDepth = 114;

    public MindSet() {
        // TODO: Initialise the inspirations.
    }

    protected void apply(Object2LongMap<Idea> modifier) {
        for (Idea idea : modifier.keySet()) {
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

        for (Inspiration inspiration : inspirationHeap.keySet()) {
            this.apply(inspiration.refreshMinds(status, inspirationHeap.getLong(inspiration)));
        }

        int considerationCounter = considerationDepth;
        while (!ideaHeap.isEmpty() && considerationCounter != 0) {
            Idea idea = ideaHeap.top();

            Object2LongMap<Idea> modifier = idea.getRequirements(status, ideaHeap.getLong(idea));
            // Check whether the current action will be replaced.
            if (modifier.isEmpty()) {
                potentialDecisions.add(new VillageismPair<Idea>(idea, ideaHeap.getLong(idea)));
            }
            // If the idea affects itself, the change will also be applied here.
            this.apply(modifier);

            considerationCounter -= 1;
            ideaHeap.remove(idea);
        }
    }

    public VillageismPair<Idea> decide() {
        return potentialDecisions.poll();
    }

}
