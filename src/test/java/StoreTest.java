import ucu.edu.apps.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class StoreTest {
    private Store store;
    private Flower rose;
    private Flower tulip;

    @BeforeEach
    public void setUp() {
        store = new Store();

        rose = new Flower();
        rose.setFlowerType(FlowerType.ROSE);
        rose.setPrice(10);
        rose.setColor(FlowerColor.RED);
        rose.setSepalLength(5.0);

        tulip = new Flower();
        tulip.setFlowerType(FlowerType.TULIP);
        tulip.setPrice(8);
        tulip.setColor(FlowerColor.YELLOW);
        tulip.setSepalLength(4.0);

        store.addFlowerPack(new FlowerPack(rose, 10));
        store.addFlowerPack(new FlowerPack(tulip, 5));
    }

    @Test
    public void testAddFlowerPack() {
        FlowerStats stats = rose.getStats();
        FlowerBucket result = store.search(stats, 3);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getFlowerPacks().size(), "Should find only rose pack");
        Assertions.assertEquals(3, result.getFlowerPacks().get(0).getCount(), "Returned pack should contain 3 roses");
    }

    @Test
    public void testSearchReducesStoreCount() {
        FlowerStats stats = rose.getStats();

        FlowerBucket result = store.search(stats, 4);

        Assertions.assertEquals(1, result.getFlowerPacks().size());
        Assertions.assertEquals(4, result.getFlowerPacks().get(0).getCount());

        FlowerPack originalPack = store.getStoreFlowerBucket().getFlowerPacks().get(0);
        Assertions.assertEquals(6, originalPack.getCount(), "Store pack should now have 6 left");
    }

    @Test
    public void testSearchMultipleTypes() {
        FlowerStats tulipStats = tulip.getStats();
        FlowerBucket result = store.search(tulipStats, 5);

        Assertions.assertEquals(1, result.getFlowerPacks().size());
        Assertions.assertEquals(5, result.getFlowerPacks().get(0).getCount());
        Assertions.assertEquals(FlowerType.TULIP, result.getFlowerPacks().get(0).getFlower().getFlowerType());
    }

    @Test
    public void testSearchMoreThanAvailable() {
        FlowerStats stats = rose.getStats();
        FlowerBucket result = store.search(stats, 20);

        Assertions.assertEquals(10, result.getFlowerPacks().get(0).getCount(),
                "Should return only available 10 roses");
    }

    @Test
    public void testSearchNonMatching() {
        Flower randomFlower = new Flower();
        randomFlower.setFlowerType(FlowerType.CHAMOMILE);
        randomFlower.setColor(FlowerColor.BLUE);
        randomFlower.setSepalLength(3.5);
        FlowerStats nonMatchingStats = randomFlower.getStats();

        FlowerBucket result = store.search(nonMatchingStats, 5);

        Assertions.assertTrue(result.getFlowerPacks().isEmpty(),
                "No matching flowers should be found for non-existing type");
    }
}
