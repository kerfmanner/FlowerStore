package ucu.edu.apps;

import java.util.ArrayList;
import java.util.List;

public class FlowerBucket {

    private List<FlowerPack> flowerPacks;

    public void add(FlowerPack flowerPack) {
        if (flowerPacks == null) {
            flowerPacks = new ArrayList<FlowerPack>();
        }
        flowerPacks.add(flowerPack);
    }
    
    public double getPrice(){
        double price = 0;
        for (FlowerPack i : flowerPacks) {
            price += i.getPrice();
        }
        return price;
    }
}
