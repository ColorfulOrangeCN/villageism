package villagist.villageism.ai.mind;

import java.util.ArrayList;
import java.util.HashSet;

public class MindSet {
    HashSet<MindIdea> minds;
    ArrayList<Inspiration> inspirations;

    public void addInspiration(Inspiration inspiration) {
        inspirations.add(inspiration);
    }
    public void refreshMind() {
        for (Inspiration inspiration : inspirations) {
            inspiration.refreshMinds(minds);
        }
    }
}
