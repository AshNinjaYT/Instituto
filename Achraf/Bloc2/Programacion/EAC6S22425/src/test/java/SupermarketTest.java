import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupermarketTest {

    private Supermarket supermarket;
    private static final float DELTA = 0.0001f;

    @BeforeEach
    void setUp() {
        supermarket = new Supermarket("TestMarket", "TestCity", 12.3456789f, 98.7654321f);
    }

    /**
     * Test of constructor, of class Supermarket.
     */
    @Test
    void testConstructor_ValidData_CreatesSupermarket() {
        assertEquals("TestMarket", supermarket.name);
        assertEquals("TestCity", supermarket.city);
        assertEquals(12.3456789f, supermarket.longitude, DELTA);
        assertEquals(98.7654321f, supermarket.latitude, DELTA);
        assertTrue(supermarket.products.isEmpty());
    }

    /**
     * Test of addProduct method, of class Supermarket.
     */
    @Test
    void testAddProduct_ValidProduct_AddsSuccessfully() {
        supermarket.addProduct("Apple", 1.99f);
        assertTrue(supermarket.hasProduct("Apple"));
        assertEquals(1.99f, supermarket.getPrice("Apple"), DELTA);
    }
    @Test
    void testAddProduct_NullOrEmptyName_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> supermarket.addProduct(null, 1.99f));
        assertThrows(IllegalArgumentException.class, () -> supermarket.addProduct("", 1.99f));
    }
    @Test
    void testAddProduct_NegativePrice_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> supermarket.addProduct("Apple", -1.0f));
    }
    @Test
    void testAddProduct_DuplicateProduct_ThrowsException() {
        supermarket.addProduct("Apple", 1.99f);
        assertThrows(IllegalArgumentException.class, () -> supermarket.addProduct("Apple", 2.99f));
    }

    /**
     * Test of getPrice method, of class Supermarket.
     */
    @Test
    void testGetPrice_ValidProduct_ReturnsCorrectPrice() {
        supermarket.addProduct("Banana", 0.99f);
        assertEquals(0.99f, supermarket.getPrice("Banana"), DELTA);
    }
    @Test
    void testGetPrice_NonExistentProduct_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> supermarket.getPrice("Orange"));
    }
    @Test
    void testGetPrice_NullOrEmptyName_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> supermarket.getPrice(null));
        assertThrows(IllegalArgumentException.class, () -> supermarket.getPrice(""));
    }

    /**
     * Test of updatePrice method, of class Supermarket.
     */
    @Test
    void testUpdatePrice_ValidUpdate_UpdatesSuccessfully() {
        supermarket.addProduct("Milk", 2.50f);
        supermarket.updatePrice("Milk", 2.75f);
        assertEquals(2.75f, supermarket.getPrice("Milk"), DELTA);
    }
    @Test
    void testUpdatePrice_NonExistentProduct_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> supermarket.updatePrice("Eggs", 3.50f));
    }
    @Test
    void testUpdatePrice_NegativePrice_ThrowsException() {
        supermarket.addProduct("Cheese", 5.00f);
        assertThrows(IllegalArgumentException.class, () -> supermarket.updatePrice("Cheese", -1.00f));
    }
    @Test
    void testUpdatePrice_NullOrEmptyName_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> supermarket.updatePrice(null, 2.00f));
        assertThrows(IllegalArgumentException.class, () -> supermarket.updatePrice("", 2.00f));
    }

    /**
     * Test of supermarketToString method, of class Supermarket.
     */
    @Test
    void testSupermarketToString_ValidSupermarket_ReturnsFormattedString() {
        String expected = String.format("%s,%s,%.7f,%.7f\n", "TestMarket", "TestCity", 12.3456789f, 98.7654321f);
        assertEquals(expected, supermarket.supermarketToString());
    }

    /**
     * Test of productsToString method, of class Supermarket.
     */
    @Test
    void testProductsToString_ValidProducts_ReturnsFormattedString() {
        supermarket.addProduct("Bread", 1.50f);
        supermarket.addProduct("Butter", 2.25f);
        String[] expectedLines = {"TestMarket,TestCity,Bread,1.50", "TestMarket,TestCity,Butter,2.25"};
        String[] actualLines = supermarket.productsToString().split("\n");
        assertTrue(java.util.Arrays.asList(actualLines).containsAll(java.util.Arrays.asList(expectedLines)));
        assertEquals(expectedLines.length, actualLines.length);
    }
}