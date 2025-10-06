package ucu.edu.apps;

public class FlowerPack {
    private Flower flower;
    private int count;

    public FlowerPack(Flower flower, int count) {
        this.flower = new Flower(flower);
        this.count = count;
    }

    public double getPrice() {
        return flower.getPrice() * count;
    }

}
