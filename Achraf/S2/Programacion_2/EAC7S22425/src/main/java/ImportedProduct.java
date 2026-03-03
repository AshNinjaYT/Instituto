/**
 * Represents an imported product, which includes a base product and its country
 * of origin.
 * <p>
 * This class encapsulates a {@link Product} object and adds information about
 * the country
 * where the product was originally produced or manufactured.
 * </p>
 */
public class ImportedProduct {
    Product product;
    String countryOfOrigin;

    /**
     * Constructs an ImportedProduct with the specified product and country of
     * origin.
     *
     * @param product         the base product (cannot be null)
     * @param countryOfOrigin the country of origin (cannot be null or empty)
     * @throws IllegalArgumentException if:
     *                                  <ul>
     *                                  <li>The product is null</li>
     *                                  <li>The country of origin is null or
     *                                  empty</li>
     *                                  </ul>
     */
    public ImportedProduct(Product product, String countryOfOrigin) {
        if (product == null) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
        }
        if (countryOfOrigin == null || countryOfOrigin.isEmpty()) {
            throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
        }
        this.product = product;
        this.countryOfOrigin = countryOfOrigin;
    }

    /**
     * Gets the base product associated with this imported product.
     *
     * @return the base product
     */
    public Product getProduct() {
        return product;

    }

    /**
     * Gets the country of origin for this imported product.
     *
     * @return the country of origin
     */
    public String getCountryOfOrigin() {
        return countryOfOrigin;

    }

    /**
     * Returns a string representation of the imported product.
     *
     * @return a string in the format "[name]}. [price], [stock], "COSMETIC",
     *         [Brand]" for cosmetic products,
     *         or "[name]}. [price], [stock], "FOOD", [expiration date]" for food
     *         products.
     */
    @Override
    public String toString() {
        return product.productToString() + "\n";
    }
}