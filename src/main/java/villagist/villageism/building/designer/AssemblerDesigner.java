package villagist.villageism.building.designer;

import villagist.villageism.building.Component;
import villagist.villageism.building.Designer;
import villagist.villageism.building.Style;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AssemblerDesigner implements Designer {
    ArrayList<Designer> designers = new ArrayList<>();
    HashMap<String, Designer> map = new HashMap<>();
    @Override
    public Component[] designFor(Style style, Designer parentDesigner) {
        ArrayList<Component> res = new ArrayList<>();
        for (Designer designer : designers) {
            res.addAll(List.of(designer.designFor(style, this)));
        }
        return res.toArray(new Component[0]);
    }
    @Override
    public void registerDesigner(Designer designer) {
        designers.add(designer);
        map.put(designer.getName(), designer);
    }
    @Override
    public Designer[] getDesigners() {
        return designers.toArray(new Designer[0]);
    }
    public Designer searchDesigner(String name) {
        return map.get(name);
    }
}
