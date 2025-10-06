package ucu.edu.apps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class FlowerStats {

    private FlowerColor color;
    private FlowerType flowerType;

    public FlowerStats(){
        color = null;
        flowerType = null;
    }

    public FlowerStats(FlowerStats stats) {

        this.flowerType = stats.flowerType;
        this.color = stats.color;
    }

    public boolean matching(FlowerStats stats) {
        if (flowerType != null && !flowerType.equals(stats.flowerType)) {
            return false;
        }
        if (color != null && !color.equals(stats.color)) {
            return false;
        }
        return true;
    }

    public boolean matching(FlowerPack pack) {
        return this.matching(pack.getStats());
    }
}
