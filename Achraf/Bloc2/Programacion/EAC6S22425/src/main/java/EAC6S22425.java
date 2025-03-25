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
        while (true) {
            io.showMenu(Constants.START_MENU);
            int option = io.askForInteger(Constants.MESSAGE_ASK_OPTION_VALUE, Constants.ERROR_NO_INTEGER);
            switch (option) {
                case 1:
                    sul.addSupermarket(enterSupermarketData(io, fu));
                    break;
                case 2:
                    // addProduct();
                    break;
                case 3:
                    io.showSupermarkets(sul.listToString());
                    break;
                case 4:
                    // exportSupermarkets();
                    break;
                case 5:
                    // importSupermarkets();
                    break;
                case 0:
                    return;
                default:
                    io.showError(Constants.MESSAGE_NOT_VALID_OPTION);
                    ;
            }
        }
    }

    private Supermarket enterSupermarketData(UtilsIO io, FileUtils fu) {
        String supermarketName = io.askForString(Constants.MESSAGE_INSERT_MARKET_NAME, Constants.ERROR_EMPTY_STRING);
        String supermarketCity = io.askForString(Constants.MESSAGE_INSERT_CITY, Constants.ERROR_EMPTY_STRING);
        float supermarketLongitude = io.askForFloat(Constants.MESSAGE_INSERT_LONGITUDE, Constants.ERROR_NO_FLOAT);
        float supermarketLatitude = io.askForFloat(Constants.MESSAGE_INSERT_LATITUDE, Constants.ERROR_NO_FLOAT);
        
        return new Supermarket(supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude);
    }
}