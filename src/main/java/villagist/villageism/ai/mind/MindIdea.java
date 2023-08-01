package villagist.villageism.ai.mind;

import java.util.HashSet;

public abstract class MindIdea {
    public abstract HashSet<MindIdea> getRequirements();


    protected boolean isRequirementsRealistic() {
        HashSet<MindIdea> set = getRequirements();
        boolean real = true;
        for (MindIdea requirement : set) {
            real &= requirement.isRealistic();
        }
        return real;
    }
    public boolean isRealistic() {
        return isRequirementsRealistic();
    }
}
