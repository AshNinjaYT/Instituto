import java.util.List;
import java.util.Locale;

public class EAC7S22425 {


    public static void main(String[] args) {
        EAC7S22425 program = new EAC7S22425();
        Locale.setDefault(Locale.US);
        program.start();
    }

    String marketFileName = Constants.SUPERMARKETS_FILE_NAME;
    String productsFileName = Constants.PRODUCTS_FILE_NAME;

    public void start() {
        UtilsIO io = new UtilsIO();
        FileUtils fu = new FileUtils(Constants.DEFAULT_DATA_DIRECTORY);
        SupermarketList supermarketList = new SupermarketList();
        boolean exitLoop = false;
        while (!exitLoop) {
            io.showMenu(Constants.START_MENU);
            int option = io.askForInteger(Constants.MESSAGE_ASK_OPTION_VALUE, Constants.ERROR_NO_INTEGER);
            switch (option) {
                case 1: {
                    addSupermarketToList(io, supermarketList);
                    break;
                }
                case 2: {
                    addProductToSupermarket(io, supermarketList);
                    break;
                }
                case 3: {
                    showSupermarketList(io, supermarketList);
                    break;
                }
                case 4: {
                    exportSupermarkets(io, fu, supermarketList);
                    break;
                }
                case 5: {
                    importSupermarkets(io, fu, supermarketList); // ¡CORREGIDO!
                    break;
                }
                case 0: {
                    exitLoop = true;
                    break;
                }
                default: {
                    io.showError(Constants.MESSAGE_NOT_VALID_OPTION);
                    break;
                }
            }
        }
    }

    private void showSupermarketList(UtilsIO io, SupermarketList supermarketList) {
        if (supermarketList.getSupermarketList().isEmpty()) {
            io.showError(Constants.ERROR_SUPERMARKET_LIST_EMPTY);
            return;
        }
        io.showSupermarkets(supermarketList.listToString());
    }

    private void addSupermarketToList(UtilsIO io, SupermarketList supermarketList) {
        String name = io.askForString(Constants.MESSAGE_INSERT_MARKET_NAME, Constants.ERROR_EMPTY_STRING);
        String city = io.askForString(Constants.MESSAGE_INSERT_CITY, Constants.ERROR_EMPTY_STRING);
        float longitude = io.askForFloat(Constants.MESSAGE_INSERT_LONGITUDE, Constants.ERROR_NO_FLOAT);
        float latitude = io.askForFloat(Constants.MESSAGE_INSERT_LATITUDE, Constants.ERROR_NO_FLOAT);
        Supermarket supermarket = new Supermarket(name, city, longitude, latitude);
        if (supermarketList.lookForSupermarket(name, city) != null) {
            io.showError(Constants.ERROR_SUPERMARKET_ALREADY_ON_LIST);
            return;
        }
        supermarketList.addSupermarket(supermarket);
        io.showInfo(Constants.MESSAGE_MARKET_ADDED_SUCCESSFULLY);
    }

    private void addProductToSupermarket(UtilsIO io, SupermarketList supermarketList) {
        String name = io.askForString(Constants.MESSAGE_INSERT_MARKET_NAME, Constants.ERROR_EMPTY_STRING);
        String city = io.askForString(Constants.MESSAGE_INSERT_CITY, Constants.ERROR_EMPTY_STRING);
        Supermarket supermarket = supermarketList.lookForSupermarket(name, city);
        if (supermarket == null) {
            io.showError(Constants.ERROR_SUPERMARKET_NOT_FOUND);
            return;
        }

        String productName = io.askForString(Constants.MESSAGE_INSERT_PRODUCT_NAME, Constants.ERROR_EMPTY_STRING);
        float price = io.askForFloat(Constants.MESSAGE_INSERT_PRODUCT_PRICE, Constants.MESSAGE_DEFAULT_ERROR_FLOAT);
        if (supermarket.hasProduct(productName)) {
            io.showError(Constants.ERROR_PRODUCT_ALREADY_EXISTS);
            return;
        }
        if (price <= 0) {
            io.showError(Constants.ERROR_PRICE_NEGATIVE_OR_ZERO);
            return;
        }

        io.showMenu(Constants.PRODUCT_MENU);
        int option = io.askForInteger(Constants.MESSAGE_ASK_OPTION_VALUE_PRODUCT, Constants.ERROR_NO_INTEGER);
        try {
            switch (option) {
                case 1: {
                    String expiration = io.askForString(Constants.MESSAGE_INSERT_DATA_CADUCITAT, Constants.ERROR_EMPTY_STRING);
                    supermarket.addProduct(new FoodProduct(productName, price, expiration));
                    break;
                }
                case 2: {
                    String brand = io.askForString(Constants.MESSAGE_INSERT_MARCA, Constants.ERROR_EMPTY_STRING);
                    supermarket.addProduct(new CosmeticProduct(productName, price, brand));
                    break;
                }
                default:
                    io.showError(Constants.MESSAGE_NOT_VALID_OPTION);
                    return;
            }
            io.showInfo(Constants.MESSAGE_PRODUCT_ADDED_SUCCESSFULLY);
        } catch (IllegalArgumentException e) {
            io.showError(e.getMessage());
        }
    }

    private void exportSupermarkets(UtilsIO io, FileUtils fu, SupermarketList supermarketList) {
        if (supermarketList == null || supermarketList.getSupermarketList().isEmpty()) {
            io.showError(Constants.ERROR_SUPERMARKET_LIST_EMPTY);
            return;
        }

        String supermarkets = Constants.SUPERMARKETS_FILE_NAME;
        String products = Constants.PRODUCTS_FILE_NAME;
        for (Supermarket supermarket : supermarketList.getSupermarketList()) {
            supermarkets += supermarket.supermarketToString();
            products += supermarket.productsToString();
        }

        try {
            fu.createFileFromString(marketFileName, supermarkets);
            fu.createFileFromString(productsFileName, products);
            io.showInfo(Constants.MESSAGE_FILES_CREATED);
        } catch (IllegalArgumentException e) {
            io.showError(e.getMessage());
        }
    }

    private void importSupermarkets(UtilsIO io, FileUtils fu, SupermarketList supermarketList) {
        if (!fu.fileExists(marketFileName) || !fu.fileExists(productsFileName)) {
            io.showError(Constants.ERROR_FILES_NOT_FOUND);
            return;
        }

        String marketFileContent = fu.extractFileIntoString(marketFileName);
        String productsFileContent = fu.extractFileIntoString(productsFileName);

        if (marketFileContent == null || productsFileContent == null) {
            io.showError(Constants.ERROR_FILES_NOT_FOUND);
            return;
        }

        if (marketFileContent.isEmpty() || productsFileContent.isEmpty()) {
            io.showError(Constants.ERROR_FILES_EMPTY);
            return;
        }

        String[] marketLines = marketFileContent.split("\n");
        String[] productsLines = productsFileContent.split("\n");

        for (String marketLine : marketLines) {
            try {
                String[] marketData = marketLine.split(",");
                Supermarket supermarket = new Supermarket(
                        marketData[0], marketData[1],
                        Float.parseFloat(marketData[2]),
                        Float.parseFloat(marketData[3])
                );
                supermarketList.addSupermarket(supermarket);

                for (String productLine : productsLines) {
                    String[] productData = productLine.split(",");
                    if (productData[0].equals(marketData[0]) && productData[1].equals(marketData[1])) {
                        String productName = productData[2];
                        float price = Float.parseFloat(productData[3]);
                        int stock = Integer.parseInt(productData[4]);
                        String type = productData[5];
                        String extra = productData[6];

                        Product product = null;
                        if (type.equalsIgnoreCase("food")) {
                            product = new FoodProduct(productName, price, stock, extra);
                        } else if (type.equalsIgnoreCase("cosmetic")) {
                            product = new CosmeticProduct(productName, price, stock, extra);
                        }

                        if (product != null) {
                            supermarket.addProduct(product);
                        }
                    }
                }
            } catch (Exception e) {
                io.showError(Constants.ERROR_IMPORTANT_SUPERMERCAT_O_PRODUCTE + e.getMessage());
                continue;
            }
        }

        io.showInfo(Constants.MESSAGE_FILES_IMPORTED);
    }
}
