import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of {@link Supermarket} objects and provides
 * functionality
 * to manage and filter the list of supermarkets.
 */
public class SupermarketList {
    /**
     * The list of supermarkets.
     */
    private List<Supermarket> supermarketList;

    /**
     * Constructor to initialize the list.
     */
    public SupermarketList() {
        this.supermarketList = new ArrayList<>();
    }

    /**
     * Gets the size of the supermarket list.
     *
     * @return The number of supermarkets in the list
     */
    public int getSize() {
        return supermarketList.size();
    }

    /**
     * Gets the list of supermarkets.
     *
     * @return A new list containing all supermarkets in the list
     */
    public List<Supermarket> getSupermarketList() {
        return new ArrayList<>(supermarketList); // Return copy to avoid external modification
    }

    /**
     * Adds a supermarket to the list.
     *
     * @param supermarket The supermarket to add (cannot be null)
     * @throws IllegalArgumentException If the supermarket is null
     */
    public void addSupermarket(Supermarket supermarket) {
        if (supermarket == null) {
            throw new IllegalArgumentException("Supermarket cannot be null");
        }
        supermarketList.add(supermarket);
    }

    /**
     * Searches for a supermarket by its name and city.
     *
     * @param name The name of the supermarket to search for
     * @param city The city of the supermarket to search for
     * @return The matching supermarket, or null if no match is found
     */
    public Supermarket lookForSupermarket(String name, String city) {
        if (name == null || name.isEmpty() || city == null || city.isEmpty()) {
            return null;
        }

        for (Supermarket s : supermarketList) {
            if (s.getName().equals(name) && s.getCity().equals(city)) {
                return s;
            }
        }
        return null;
    }

    /**
     * Filters the list of supermarkets by name.
     *
     * @param name The name to filter by
     * @return A new {@link SupermarketList} containing only supermarkets with the
     *         specified name
     */
    public SupermarketList filterByName(String name) {
        SupermarketList filteredList = new SupermarketList();
        if (name == null || name.isEmpty()) {
            return filteredList;
        }

        for (Supermarket s : supermarketList) {
            if (s.getName().equals(name)) {
                filteredList.addSupermarket(s);
            }
        }
        return filteredList;
    }

    /**
     * Filters the list of supermarkets by city.
     *
     * @param city The city to filter by
     * @return A new {@link SupermarketList} containing only supermarkets in the
     *         specified city
     */
    public SupermarketList filterByCity(String city) {
        SupermarketList filteredList = new SupermarketList();
        if (city == null || city.isEmpty()) {
            return filteredList;
        }

        for (Supermarket s : supermarketList) {
            if (s.getCity().equals(city)) {
                filteredList.addSupermarket(s);
            }
        }
        return filteredList;
    }

    /**
     * Generates a string representation of the list of supermarkets.
     * <p>
     * Each supermarket is represented by its
     * {@link Supermarket#supermarketToString()} output,
     * concatenated without separators.
     * </p>
     *
     * @return A string containing the concatenated representations of all
     *         supermarkets in the list
     */
    public String listToString() {
        StringBuilder sb = new StringBuilder();
        for (Supermarket s : supermarketList) {
            sb.append(s.supermarketToString());
        }
        return sb.toString();
    }
}
