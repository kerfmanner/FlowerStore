package ucu.edu.apps;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

@Getter
@Setter
public class FlowerPack {
    private Flower flower;
    private int count;

    public FlowerPack(Flower flower, int count) {
        this.flower = new Flower(flower);
        this.count = count;
    }

    public FlowerPack(FlowerPack pack, int amount) {
        flower = pack.getFlower();
        pack.reduceCount(amount);
        count = amount;
    }

    public double getPrice() {
        return flower.getPrice() * count;
    }

    public FlowerStats getStats() {
        return flower.getStats();
    }

    @SneakyThrows
    public void reduceCount(int amount) {
        if (amount > this.count) {
            throw new IllegalArgumentException("Cannot reduce into negative.");
        }
        this.count -= amount;
    }

}
