import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Represents a food product with expiration date tracking and special discount
 * rules.
 * <p>
 * This class extends {@link Product} and adds functionality for handling
 * expiration dates
 * and applying additional discounts for products close to their expiration
 * date.
 * </p>
 * 
 * @see Product
 */
public class FoodProduct extends Product {
    private LocalDate expirationDate;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);

    /**
     * Constructs a FoodProduct with specified parameters.
     *
     * @param name           the name of the product (inherited from Product)
     * @param price          the price of the product (must be > 0)
     * @param stock          the initial stock quantity (must be ≥ 0)
     * @param expirationDate the expiration date in "yyyyMMdd" format
     * @throws IllegalArgumentException if:
     *                                  <ul>
     *                                  <li>Name/price validation fails (inherited
     *                                  from Product)</li>
     *                                  <li>Expiration date is null, empty, or
     *                                  invalid format</li>
     *                                  <li>Expiration date is in the past</li>
     *                                  </ul>
     */
    public FoodProduct(String name, float price, int stock, String expirationDate) {
        super(name, price, stock);
        this.expirationDate = validateExpirationDate(expirationDate);
    }

    /**
     * Constructs a FoodProduct with default stock (0).
     *
     * @param name           the name of the product
     * @param price          the price of the product
     * @param expirationDate the expiration date in "yyyyMMdd" format
     * @see #FoodProduct(String, float, int, String)
     */
    public FoodProduct(String name, float price, String expirationDate) {
        this(name, price, 0, expirationDate);
    }

    /**
     * Applies a discount with additional reduction for products close to
     * expiration.
     *
     * @param baseDiscount the base discount percentage (0-100 inclusive)
     * @return the discounted price
     * @throws IllegalArgumentException if baseDiscount is outside 0-100 range
     */
    @Override
    public float applyDiscount(float baseDiscount) {
        if (baseDiscount < 0 || baseDiscount > 100) {
            throw new IllegalArgumentException(Constants.MESSAGE_DISCOUNT_INVALID);
        }
        float discount = baseDiscount;
        if (isCloseToExpiration()) {
            discount += Constants.FOOD_PRODUCT_EXPIRATION_DISCOUNT;
        }
        if (discount > 100) {
            discount = 100;
        }
        return price * (1 - discount / 100);
    }

    /**
     * Validates the expiration date.
     *
     * @param dateString the date string to validate and parse
     * @return the parsed LocalDate object
     * @throws IllegalArgumentException if:
     *                                  <ul>
     *                                  <li>Date string is null or empty</li>
     *                                  <li>Date format is invalid</li>
     *                                  <li>Date is in the past</li>
     *                                  </ul>
     */
    private LocalDate validateExpirationDate(String dateString) {
        if (dateString == null || dateString.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_EXPIRATION_DATE_NULL_OR_EMPTY);
        }
        try {
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);

            if (date.isBefore(LocalDate.now())) {
                throw new IllegalArgumentException(Constants.ERROR_EXPIRATION_DATE_PAST);
            }
            return date;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(Constants.ERROR_EXPIRATION_DATE_FORMAT);
        }
    }

    /**
     * Sets the expiration date of the product.
     *
     * @param dateString the date string to validate and parse
     * @see #validateExpirationDate(String)
     */
    private void setExpirationDate(String dateString) {
        this.expirationDate = validateExpirationDate(dateString);
    }

    /**
     * Checks if the product is close to expiration.
     *
     * @return true if the product expires within
     *         {@value Constants#DAYS_CLOSE_TO_EXPIRATION} days,
     *         false otherwise
     */
    private boolean isCloseToExpiration() {
        long daysToExpire = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        return daysToExpire <= Constants.DAYS_CLOSE_TO_EXPIRATION;
    }

    /**
     * Updates the expiration date with validation.
     *
     * @param newDate the new expiration date in "yyyyMMdd" format
     * @throws IllegalArgumentException if the new date fails validation
     * @see #setExpirationDate(String)
     */
    public void updateExpirationDate(String newDate) {
        validateExpirationDate(newDate);
        setExpirationDate(newDate);
    }

    /**
     * Gets the formatted expiration date.
     *
     * @return the expiration date in "yyyyMMdd" format
     */
    public String getExpirationDate() {
        return expirationDate.format(DATE_FORMATTER);
    }

    /**
     * Creates a string representation of the cosmetic product for the toString
     * method.
     *
     * @return A string in the format "[name], [price], [stock], COSMETIC, [brand]".
     */
    @Override
    protected String productToString() {
        return String.format("%s,%.2f,%d,%s,%s", name, price, stock, "FOOD", getExpirationDate());
    }
}