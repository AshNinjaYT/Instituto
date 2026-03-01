import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Supermarket {

    public String name;
    public String city;
    public float longitude;
    public float latitude;
    Map<String, Float> products = new HashMap<>();

    public Supermarket(String name, String city, float longitude, float latitude) {
        this.name = name;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public boolean hasProduct(String productName) {
        boolean hasProduct = false;
        for (String i : products.keySet()) {
            if (i == productName) {
                hasProduct = true;
            }
        }
        return hasProduct;
    }

    public void addProduct(String productName, float price) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
        }
        if (price == 0 || price <= 0) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_NEGATIVE_INTEGER);
        }
        if (hasProduct(productName)) {
            throw new IllegalArgumentException("El producte ja existeix");
        }
        products.put(productName, price);
    }

    public float getPrice(String productName) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
        }
        if (!hasProduct(productName)) {
            throw new IllegalArgumentException("El producte no existeix");
        }
        return products.get(productName);
    }

    public void updatePrice(String productName, float price) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
        }
        if (price == 0 || price <= 0) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_NEGATIVE_INTEGER);
        }
        if (!hasProduct(productName)) {
            throw new IllegalArgumentException("El producte no existeix");
        }
        products.put(productName, price);
    }

    public String supermarketToString() {
        return String.format(/*Locale.US,*/ Constants.SUPERMARKET_SPLIT_FORMAT, name, city, longitude, latitude);
    }

    public String productsToString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Float> entry : products.entrySet()) {
            sb.append(name).append(",").append(city).append(",").append(entry.getKey()).append(",")
                    .append(String.format("%.2f", entry.getValue()).replace(",", ".")).append("\n");
        }
        return sb.toString().trim(); // Elimina el último salto de línea extra
    }
}