package ucu.edu.apps;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Flower {

    private FlowerStats stats = new FlowerStats();
    private double price;
    private double sepalLength;

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
