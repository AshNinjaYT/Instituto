public abstract class Product implements Discountable {
  float price;
  String name;
  int stock;

  /**
   * Constructor for Product.
   *
   * @param name  The name of the product (cannot be null or empty).
   * @param price The price of the product (must be greater than 0).
   * @throws IllegalArgumentException If the name is null/empty or the price is
   *                                  invalid.
   */
  public Product(String name, float price) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
    }
    if (price <= 0) {
      throw new IllegalArgumentException(Constants.MESSAGE_PRICE_NEGATIV);
    }
    this.name = name;
    this.price = price;
    this.stock = 0;
  }

  /**
   * Constructor for Product.
   *
   * @param name  The name of the product (cannot be null or empty).
   * @param price The price of the product (must be greater than 0).
   * @param stock The stock of the product (must be non-negative).
   * @throws IllegalArgumentException If the name is null/empty or the price is
   *                                  invalid.
   */
  public Product(String name, float price, int stock) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException(Constants.MESSAGE_DEFAULT_ERROR_STRING);
    }
    if (price <= 0) {
      throw new IllegalArgumentException(Constants.MESSAGE_PRICE_NEGATIV);
    }
    if (stock < 0) {
      throw new IllegalArgumentException(Constants.MESSAGE_STOCK_NEGATIV);
    }
    this.name = name;
    this.price = price;
    this.stock = stock;
  }

  /**
   * Gets the name of the product.
   *
   * @return The name of the product.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the price of the product.
   *
   * @return The price of the product.
   */
  public float getPrice() {
    return price;
  }

  /**
   * Gets the stock of the product.
   *
   * @return The stock of the product.
   */
  public int getStock() {
    return stock;

  }

  /**
   * Sets the price of the product.
   *
   * @param price The new price (must be greater than 0).
   * @throws IllegalArgumentException If the price is invalid.
   */

  public void setPrice(float price) {
    if (price <= 0) {
      throw new IllegalArgumentException(Constants.MESSAGE_PRICE_NEGATIV);
    }
    this.price = price;
  }

  /**
   * Sets the stock of the product.
   * 
   * @param stock The new stock (must be non-negative).
   * @throws IllegalArgumentException If the stock is negative.
   */
  public void setStock(int stock) {
    if (stock < 0) {
      throw new IllegalArgumentException(Constants.MESSAGE_STOCK_NEGATIV);
    }
    this.stock = stock;
  }

  /**
   * Decrements the stock of the product by one.
   * 
   * @throws IllegalArgumentException If there's not enough stock to decrement.
   */
  public void decrementStock() {
    if (stock <= 0) {
      throw new IllegalArgumentException(Constants.MESSAGE_STOCK_NEGATIV);
    }
    stock--;

  }

  /**
   * Decrements the stock of the product by the number in the param.
   * 
   * @param quantity The number of products to decrement.
   * @throws IllegalArgumentException If the parameter is less than one.
   * @throws IllegalArgumentException If there's not enough stock to decrement.
   */
  public void decrementStock(int quantity) {
    if (quantity < 1) {
      throw new IllegalArgumentException(Constants.MESSAGE_QUANTITY_INVALID);
    }
    if (stock <= quantity) {
      throw new IllegalArgumentException(Constants.MESSAGE_STOCK_NEGATIV);
    }
    stock -= quantity;
  }

  /**
   * Returns a boolean indicating if there's stock of a product
   *
   * @return A boolean indicating if there's stock of a product
   */
  public boolean thereIsStock() {
    return stock > 0;
  }

  /**
   * Creates a string representation of the product for the toString method.
   *
   * @return A string in the format "[name], [price], [stock]".
   */
  protected String productToString() {
    return String.format("%s,%.2f,%d", name, price, stock);
  }

  /**
   * Returns a string representation of the product, taking the response of
   * productToString and adding a line break.
   * 
   * @see #productToString()
   * @return A string in the format "[name], [price], [stock]\n".
   */
  @Override
  public String toString() {
    return productToString() + "\n";
  }

  @Override
  public float applyDiscount(float baseDiscount) {
    if (baseDiscount < 0 || baseDiscount > 100) {
      throw new IllegalArgumentException(Constants.MESSAGE_DISCOUNT_INVALID);
    }
    return price * (1 - baseDiscount / 100);
  }
}