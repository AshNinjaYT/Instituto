import java.util.ArrayList;
import java.util.List;

public class SupermarketList {
    List<Supermarket> supermarketList;

    public SupermarketList() {
        supermarketList = new ArrayList<>();

    }

    public void addSupermarket(Supermarket supermarket) {
        if (supermarket == null) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
        }
        supermarketList.add(supermarket);
    }

    public Supermarket lookForSupermarket(String name, String city) {
        if (name == null || name.isEmpty() || city == null || city.isEmpty()) {
            return null;
        }
        for (Supermarket supermarket : supermarketList) {
            if (supermarket.name.equalsIgnoreCase(name) && supermarket.city.equalsIgnoreCase(city)) {
                return supermarket;
            }
        }

        return null;
    }

    public SupermarketList filterByName(String name) {
        SupermarketList filteredList = new SupermarketList();

        if (name == null || name.isEmpty()) {
            return filteredList; // Retorna una llista buida si el nom és null o buit
        }

        for (Supermarket supermarket : supermarketList) {
            if (supermarket.name.equalsIgnoreCase(name)) {
                filteredList.addSupermarket(supermarket); // Afegeix el supermercat a la llista filtrada
            }
        }

        return filteredList; // Retorna la llista amb els supermercats que coincideixen amb el nom
    }

    public SupermarketList filterByCity(String city) {
        SupermarketList filteredList = new SupermarketList();

        if (city == null || city.isEmpty()) {
            return filteredList; // Retorna una llista buida si ciutat és null o buit
        }

        for (Supermarket supermarket : supermarketList) {
            if (supermarket.city.equalsIgnoreCase(city)) {
                filteredList.addSupermarket(supermarket); // Afegeix el supermercat a la llista filtrada
            }
        }

        return filteredList; // Retorna la llista amb els supermercats que coincideixen amb la ciutat
    }

    public String listToString() {
        StringBuilder sb = new StringBuilder();

        for (Supermarket supermarket : supermarketList) {
            sb.append(supermarket.supermarketToString());// Utilitzem el mètode supermarketToString() de Supermarket

        }
        return sb.toString();

    }
}
