package villagist.villageism.ai.mind;

import java.util.HashSet;

public interface Inspiration {
    // Produce mind ideas
    public void refreshMinds(HashSet<MindIdea> minds);
}
