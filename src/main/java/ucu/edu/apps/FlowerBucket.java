package ucu.edu.apps;

import java.util.ArrayList;
import java.util.List;

public class FlowerBucket {

    List<FlowerPack> flowerPacks;

    public void addFlowerPack(FlowerPack flowerPack) {
        if (flowerPacks == null) {
            flowerPacks = new ArrayList<FlowerPack>();
        }
        flowerPacks.add(flowerPack);
    }
}
