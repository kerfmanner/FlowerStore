package ucu.edu.apps;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class FlowerBucket {

    private List<FlowerPack> flowerPacks;

    public FlowerBucket() {
        flowerPacks = new ArrayList<FlowerPack>();
    }

    public void add(FlowerPack flowerPack) {
        if (flowerPacks == null) {
            flowerPacks = new ArrayList<FlowerPack>();
        }
        flowerPacks.add(flowerPack);
    }

    public double getPrice() {
        double price = 0;
        for (FlowerPack i : flowerPacks) {
            price += i.getPrice();
        }
        return price;
    }

    public void clear() {
        flowerPacks = new ArrayList<FlowerPack>();
    }
}
