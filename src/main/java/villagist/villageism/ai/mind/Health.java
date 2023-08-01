package villagist.villageism.ai.mind;

import villagist.villageism.ai.status.SelfStatus;

import java.util.HashSet;

public class Health extends MindIdea{
    SelfStatus self;
    @Override
    public HashSet<MindIdea> getRequirements() {
        HashSet<MindIdea> req = new HashSet<>();
        if (!self.isFullHP()) {
            req.add(new FullFeeling());
        }
        return req;
    }
}
