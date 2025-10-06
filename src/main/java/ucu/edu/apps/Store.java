package ucu.edu.apps;

import lombok.Getter;

@Getter
public class Store {
    private FlowerBucket storeFlowerBucket = new FlowerBucket();

    public void addFlowerPack(FlowerPack pack) {
        storeFlowerBucket.add(pack);
    }

    public FlowerBucket search(FlowerStats stats, int amount) {

        FlowerBucket requestedBucket = new FlowerBucket();
        for (FlowerPack pack : storeFlowerBucket.getFlowerPacks()) {
            if (stats.matching(pack)) {

                int count = Math.min(pack.getCount(), amount);
                requestedBucket.add(new FlowerPack(pack, count));
                amount -= count;
            }
            if (amount == 0) {
                break;
            }
        }

        return requestedBucket;
    }
}
