package ucu.edu.apps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Flower {

    private FlowerStats stats;
    private double price;
    private double sepalLength;

    public Flower() {
        stats = new FlowerStats();
        price = 0.0;
        sepalLength = 0.0;
    }

    public Flower(Flower flower) {
        sepalLength = flower.sepalLength;
        price = flower.price;
        stats = new FlowerStats(flower.stats);
    }

    public void setFlowerType(FlowerType type) {
        stats.setFlowerType(type);
    }

    public FlowerType getFlowerType() {
        return stats.getFlowerType();
    }

    public void setColor(FlowerColor color) {
        stats.setColor(color);
    }

    public FlowerColor getColor() {
        return stats.getColor();
    }
}
