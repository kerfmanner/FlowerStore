import ucu.edu.apps.Flower;
import ucu.edu.apps.FlowerBucket;
import ucu.edu.apps.FlowerColor;
import ucu.edu.apps.FlowerType;
import ucu.edu.apps.FlowerPack;
import ucu.edu.apps.FlowerStats;
import ucu.edu.apps.Store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class StoreTest {
    private static final double ROSE_SEPAL_LENGTH = 5.0;
    private static final double TULIP_SEPAL_LENGTH = 4.0;
    private static final double CHAMOMILE_SEPAL_LENGTH = 3.5;

    private static final int ROSE_PRICE = 10;
    private static final int TULIP_PRICE = 8;
    private static final int ROSE_COUNT = 10;
    private static final int TULIP_COUNT = 5;
    private static final int REQUEST_THREE = 3;
    private static final int REQUEST_FOUR = 4;
    private static final int REQUEST_FIVE = 5;
    private static final int REQUEST_TWENTY = 20;
    private static final int EXPECTED_SIX_LEFT = 6;

    private Store store;
    private Flower rose;
    private Flower tulip;

    @BeforeEach
    public void setUp() {
        store = new Store();

        rose = new Flower();
        rose.setFlowerType(FlowerType.ROSE);
        rose.setPrice(ROSE_PRICE);
        rose.setColor(FlowerColor.RED);
        rose.setSepalLength(ROSE_SEPAL_LENGTH);

        tulip = new Flower();
        tulip.setFlowerType(FlowerType.TULIP);
        tulip.setPrice(TULIP_PRICE);
        tulip.setColor(FlowerColor.YELLOW);
        tulip.setSepalLength(TULIP_SEPAL_LENGTH);

        store.addFlowerPack(new FlowerPack(rose, ROSE_COUNT));
        store.addFlowerPack(new FlowerPack(tulip, TULIP_COUNT));
    }

    @Test
    public void testAddFlowerPack() {
        FlowerStats stats = rose.getStats();
        FlowerBucket result = store.search(stats, REQUEST_THREE);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(
                1,
                result.getFlowerPacks().size(),
                "Should find only rose pack");
        Assertions.assertEquals(
                REQUEST_THREE,
                result.getFlowerPacks().get(0).getCount(),
                "Returned pack should contain 3 roses");
    }

    @Test
    public void testSearchReducesStoreCount() {
        FlowerStats stats = rose.getStats();
        FlowerBucket result = store.search(stats, REQUEST_FOUR);

        Assertions.assertEquals(1, result.getFlowerPacks().size());
        Assertions.assertEquals(
                REQUEST_FOUR,
                result.getFlowerPacks().get(0).getCount());

        FlowerBucket originalBucket = store.getStoreFlowerBucket();
        Assertions.assertEquals(
                EXPECTED_SIX_LEFT,
                originalBucket.getFlowerPacks().get(0).getCount(),
                "Store pack should now have 6 left");
    }

    @Test
    public void testSearchMultipleTypes() {
        FlowerStats tulipStats = tulip.getStats();
        FlowerBucket result = store.search(tulipStats, REQUEST_FIVE);

        Assertions.assertEquals(1, result.getFlowerPacks().size());
        Assertions.assertEquals(
                REQUEST_FIVE,
                result.getFlowerPacks().get(0).getCount());
        Assertions.assertEquals(
                FlowerType.TULIP,
                result.getFlowerPacks().get(0)
                        .getFlower()
                        .getFlowerType());
    }

    @Test
    public void testSearchMoreThanAvailable() {
        FlowerStats stats = rose.getStats();
        FlowerBucket result = store.search(stats, REQUEST_TWENTY);

        Assertions.assertEquals(
                ROSE_COUNT,
                result.getFlowerPacks().get(0).getCount(),
                "Should return only available 10 roses");
    }

    @Test
    public void testSearchNonMatching() {
        Flower randomFlower = new Flower();
        randomFlower.setFlowerType(FlowerType.CHAMOMILE);
        randomFlower.setColor(FlowerColor.BLUE);
        randomFlower.setSepalLength(CHAMOMILE_SEPAL_LENGTH);
        FlowerStats nonMatchingStats = randomFlower.getStats();

        FlowerBucket result = store.search(nonMatchingStats, REQUEST_FIVE);

        Assertions.assertTrue(
                result.getFlowerPacks().isEmpty(),
                "No matching flowers should be found for non-existing type");
    }
}
