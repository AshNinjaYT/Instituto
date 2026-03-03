public class CosmeticProduct extends Product {
    private String brand;

    /**
     * Constructor for CosmeticProduct.
     *
     * @param name  The name of the cosmetic product.
     * @param price The price of the cosmetic product.
     * @param stock The stock of the cosmetic product.
     * @param brand The brand of the cosmetic product (cannot be null or empty).
     * @throws IllegalArgumentException If the brand is null or empty.
     */
    public CosmeticProduct(String name, float price, int stock, String brand) {
        super(name, price, stock);
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
        }
        this.brand = brand;
    }

    /**
     * Constructor for CosmeticProduct.
     *
     * @param name  The name of the cosmetic product.
     * @param price The price of the cosmetic product.
     * @param brand The brand of the cosmetic product (cannot be null or empty).
     * @throws IllegalArgumentException If the brand is null or empty.
     */
    public CosmeticProduct(String name, float price, String brand) {
        this(name, price, 0, brand);
        if (brand == null || brand.isEmpty()) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
        }
    }

    /**
     * Gets the brand of the cosmetic product.
     *
     * @return The brand.
     */
    public String getBrand() {
        return brand;

    }

    /**
     * Applies a discount to the cosmetic product.
     *
     * @param baseDiscount The base discount percentage to apply.
     * @return The discounted price.
     */
    @Override
    public float applyDiscount(float baseDiscount) {
        if (baseDiscount < 0 || baseDiscount > 100) {
            throw new IllegalArgumentException(Constants.MESSAGE_DISCOUNT_INVALID);
        }
        return price * (1 - baseDiscount / 100);
    }

    /**
     * Creates a string representation of the cosmetic product for the toString
     * method.
     *
     * @return A string in the format "[name], [price], [stock], COSMETIC, [brand]".
     */
    @Override
    protected String productToString() {
        return String.format("%s,%.2f,%d,%s,%s", name, price, stock, "COSMETIC", brand);
    }
}