import ucu.edu.apps.FlowerBucket;
import ucu.edu.apps.FlowerPack;
import ucu.edu.apps.FlowerType;
import ucu.edu.apps.Flower;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Random;

public class FlowerBucketTest {
    private static final Random RANDOM_GENERATOR = new Random();
    private static final int MAX_QUANTITY = 1000;
    private static final int MAX_PRICE = 100;

    private FlowerBucket flowerBucket;

    @BeforeEach
    public void init() {
        flowerBucket = new FlowerBucket();
    }

    @Test
    public void testPrice() {
        int price = RANDOM_GENERATOR.nextInt(MAX_PRICE);
        int quantity = RANDOM_GENERATOR.nextInt(MAX_QUANTITY);
        // Flower flower = new Rose();
        Flower flower = new Flower();
        flower.setFlowerType(FlowerType.ROSE);
// CHECKSTYLE:OFF
        flower.setPrice(10);
// CHECKSTYLE:ON
        FlowerPack flowerPack = new FlowerPack(flower, quantity);
        flowerBucket.add(flowerPack);
        Assertions.assertEquals(price * quantity, flowerBucket.getPrice());
    }
}
