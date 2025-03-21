import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SupermarketListTest {

    private SupermarketList supermarketList;
    private Supermarket supermarket1;
    private Supermarket supermarket2;

    @BeforeEach
    void setUp() {
        supermarketList = new SupermarketList();
        supermarket1 = new Supermarket("MarketOne", "CityA", 10.0f, 20.0f);
        supermarket2 = new Supermarket("MarketTwo", "CityB", 30.0f, 40.0f);
    }

    /**
     * Test of addSupermarket method, of class SupermarketList.
     */
    @Test
    void testAddSupermarket_ValidSupermarket_AddsSuccessfully() {
        supermarketList.addSupermarket(supermarket1);
        assertTrue(supermarketList.supermarketList.contains(supermarket1));
    }
    @Test
    void testAddSupermarket_NullSupermarket_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> supermarketList.addSupermarket(null));
    }

    /**
     * Test of lookForSupermarket method, of class SupermarketList.
     */
    @Test
    void testLookForSupermarket_ExistingSupermarket_ReturnsSupermarket() {
        supermarketList.addSupermarket(supermarket1);
        assertEquals(supermarket1, supermarketList.lookForSupermarket("MarketOne", "CityA"));
    }
    @Test
    void testLookForSupermarket_NonExistentSupermarket_ReturnsNull() {
        assertNull(supermarketList.lookForSupermarket("NonExistent", "CityX"));
    }
    @Test
    void testLookForSupermarket_NullOrEmptyInputs_ReturnsNull() {
        assertNull(supermarketList.lookForSupermarket(null, "CityA"));
        assertNull(supermarketList.lookForSupermarket("MarketOne", null));
        assertNull(supermarketList.lookForSupermarket("", "CityA"));
        assertNull(supermarketList.lookForSupermarket("MarketOne", ""));
    }

    /**
     * Test of filterByName method, of class SupermarketList.
     */
    @Test
    void testFilterByName_ValidName_ReturnsFilteredSupermarkets() {
        supermarketList.addSupermarket(supermarket1);
        supermarketList.addSupermarket(supermarket2);
        SupermarketList result = supermarketList.filterByName("MarketOne");
        assertEquals(1, result.supermarketList.size());
        assertTrue(result.supermarketList.contains(supermarket1));
    }
    @Test
    void testFilterByName_NoMatch_ReturnsEmptyList() {
        supermarketList.addSupermarket(supermarket1);
        SupermarketList result = supermarketList.filterByName("MarketThree");
        assertTrue(result.supermarketList.isEmpty());
    }
    @Test
    void testFilterByName_NullOrEmptyName_ReturnsEmptyList() {
        assertTrue(supermarketList.filterByName(null).supermarketList.isEmpty());
        assertTrue(supermarketList.filterByName("").supermarketList.isEmpty());
    }

    /**
     * Test of filterByCity method, of class SupermarketList.
     */
    @Test
    void testFilterByCity_ValidCity_ReturnsFilteredSupermarkets() {
        supermarketList.addSupermarket(supermarket1);
        supermarketList.addSupermarket(supermarket2);
        SupermarketList result = supermarketList.filterByCity("CityA");
        assertEquals(1, result.supermarketList.size());
        assertTrue(result.supermarketList.contains(supermarket1));
    }
    @Test
    void testFilterByCity_NoMatch_ReturnsEmptyList() {
        supermarketList.addSupermarket(supermarket1);
        SupermarketList result = supermarketList.filterByCity("CityZ");
        assertTrue(result.supermarketList.isEmpty());
    }
    @Test
    void testFilterByCity_NullOrEmptyCity_ReturnsEmptyList() {
        assertTrue(supermarketList.filterByCity(null).supermarketList.isEmpty());
        assertTrue(supermarketList.filterByCity("").supermarketList.isEmpty());
    }

    /**
     * Test of listToString method, of class SupermarketList.
     */
    @Test
    void testListToString_EmptyList_ReturnsEmptyString() {
        assertEquals("", supermarketList.listToString());
    }
    @Test
    void testListToString_WithSupermarkets_ReturnsFormattedString() {
        supermarketList.addSupermarket(supermarket1);
        supermarketList.addSupermarket(supermarket2);
        String expected = supermarket1.supermarketToString() + supermarket2.supermarketToString();
        assertEquals(expected, supermarketList.listToString());
    }
}
