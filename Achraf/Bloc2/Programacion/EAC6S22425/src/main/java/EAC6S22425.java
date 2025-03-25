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
        Supermarket su;
        UtilsIO io = new UtilsIO();
        FileUtils fu = new FileUtils(Constants.DEFAULT_DATA_DIRECTORY);
        while (true) {
            io.showMenu(Constants.START_MENU);
            int option = io.askForInteger(Constants.MESSAGE_ASK_OPTION_VALUE, Constants.ERROR_NO_INTEGER);
            switch (option) {
                case 1:
                    enterSupermarketData(io, fu,su);
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    listSupermarkets();
                    break;
                case 4:
                    exportSupermarkets();
                    break;
                case 5:
                    importSupermarkets();
                    break;
                case 0:
                    return;
                default:
                    io.showError(Constants.MESSAGE_NOT_VALID_OPTION);
                    ;
            }
        }
    }

    private void enterSupermarketData(UtilsIO io, FileUtils fu, Supermarket su) {
        String supermarketName = io.askForString(Constants.MESSAGE_INSERT_MARKET_NAME, Constants.MESSAGE_ERROR_EMPTY_STRING);
        String supermarketCity = io.askForString(Constants.MESSAGE_INSERT_CITY, Constants.MESSAGE_ERROR_EMPTY_STRING);
        float supermarketLongitude = io.askForFloat(Constants.MESSAGE_INSERT_LONGITUDE, Constants.MESSAGE_ERROR_NO_FLOAT);
        float supermarketLatitude = io.askForFloat(Constants.MESSAGE_INSERT_LATITUDE, Constants.MESSAGE_ERROR_NO_FLOAT);
        su.
        
        fu.insertMarketInfoIntoFile(supermarketName, supermarketCity, supermarketLongitude, supermarketLatitude);
    }          
}