import java.io.*;
import java.util.Scanner;

public class EAC6S22425 {
    private SupermarketList supermarketList;
    private Scanner scanner;

    public static void main(String[] args) {
        EAC6S22425 program = new EAC6S22425();
        program.start();
    }

    public EAC6S22425() {
        supermarketList = new SupermarketList();
        scanner = new Scanner(System.in);
    }

    public void start() {
        SupermarketList sul = new SupermarketList();
        UtilsIO io = new UtilsIO();
        FileUtils fu = new FileUtils(Constants.DEFAULT_DATA_DIRECTORY);
        boolean loop = true;
        while (loop) {
            io.showMenu(Constants.START_MENU);
            int option = io.askForInteger(Constants.MESSAGE_ASK_OPTION_VALUE, Constants.ERROR_NO_INTEGER);
            switch (option) {
                case 1:
                    enterSupermarketData(io);
                    break;
                case 2:
                    addProduct(io);
                    break;
                case 3:
                    io.showSupermarkets(supermarketList.listToString());
                    break;
                case 4:
                    // exportSupermarkets();
                    break;
                case 5:
                    // importSupermarkets();
                    break;
                case 0:
                    loop = false;
                    return;
                default:
                    io.showError(Constants.MESSAGE_NOT_VALID_OPTION);
            }
        }
    }

    private void enterSupermarketData(UtilsIO io) {
        String name = io.askForString(Constants.MESSAGE_INSERT_MARKET_NAME, Constants.ERROR_EMPTY_STRING);
        String city = io.askForString(Constants.MESSAGE_INSERT_CITY, Constants.ERROR_EMPTY_STRING);
        float longitude = io.askForFloat(Constants.MESSAGE_INSERT_LONGITUDE, Constants.ERROR_NO_FLOAT);
        float latitude = io.askForFloat(Constants.MESSAGE_INSERT_LATITUDE, Constants.ERROR_NO_FLOAT);

        Supermarket supermarket = new Supermarket(name, city, longitude, latitude);
        supermarketList.addSupermarket(supermarket);
    }

    private void addProduct(UtilsIO io) {
        String name = io.askForString(Constants.MESSAGE_INSERT_MARKET_NAME, Constants.ERROR_EMPTY_STRING);
        String city = io.askForString(Constants.MESSAGE_INSERT_CITY, Constants.ERROR_EMPTY_STRING);
        Supermarket supermarket = supermarketList.lookForSupermarket(name, city);
        if (supermarket == null) {
            io.showError(Constants.SUPERMARKET_ERROR_EXIST);
        } else {
            String productName = io.askForString(Constants.MESSAGE_INSERT_NAME_PRODUCT,
                    Constants.MESSAGE_DEFAULT_ERROR_STRING);
            float price = io.askForFloat(Constants.MESSAGE_INSERT_PRICE,
                    Constants.MESSAGE_DEFAULT_ERROR_NEGATIVE_INTEGER);
            supermarket.addProduct(name, price);
        }
    }
}