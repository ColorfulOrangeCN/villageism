package villagist.villageism.villager;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.world.World;

// TODO This Class may not extends but temporarily does to pass the compiling.
public class SmartVillgerEntity extends VillagerEntity implements WithAttitudes {
    public SmartVillgerEntity(EntityType<? extends VillagerEntity> entityType, World world) {
        super(entityType, world);
        System.out.println("emmm");
    }

    @Override
    public Attitude[] getAttitudes() {
        return new Attitude[0];
    }

    @Override
    public void setAttitudes(Attitude[] attitudes) {

    }
}
