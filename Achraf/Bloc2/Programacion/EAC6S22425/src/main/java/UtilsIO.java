import java.util.Locale;
import java.util.Scanner;

public class UtilsIO {

    public void showAnyMessage(String header, String mainText) {
        if (mainText == null || mainText.isEmpty()
            || header == null || header.isEmpty()) {
            return;
        }
        System.out.println(Constants.EMPTY_SPACE);
        System.out.println(Constants.MESSAGE_SEPARATOR);
        System.out.println(header);
        System.out.println(Constants.MESSAGE_SEPARATOR);
        System.out.println(mainText);
        System.out.println(Constants.EMPTY_SPACE);
    }

    public void showMenu(String menuText) {
        showAnyMessage(Constants.START_MENU_HEADER, menuText);
    }

    public void showError(String menuText) {
        showAnyMessage(Constants.ERROR_HEADER, menuText);
    }

    public void showInfo(String menuText) {
        showAnyMessage(Constants.INFO_HEADER, menuText);
    }

    public String askForString(String message, String errorMessage){
        if (message == null || message.isEmpty()) 
        {
            message = Constants.MESSAGE_DEFAULT_ASK_STRING;
        }
        if (errorMessage == null || errorMessage.isEmpty()) 
        {
            errorMessage = Constants.MESSAGE_DEFAULT_ERROR_STRING;
        }
        Scanner scanner = new Scanner(System.in, "Cp850");
        System.out.println(message);
        String inputText = scanner.nextLine();
        while (inputText.isEmpty()){
            System.out.println(errorMessage);
            System.out.println(message);
            inputText = scanner.nextLine();
        }
        return inputText;
    }
    
    public int askForInteger(String message, String errorMessage) {
        if (message == null || message.isEmpty()) 
        {
            message = Constants.MESSAGE_DEFAULT_ASK_INTEGER;
        }
        if (errorMessage == null || errorMessage.isEmpty()) 
        {
            errorMessage = Constants.MESSAGE_DEFAULT_ERROR_INTEGER;
        }
        Scanner scan = new Scanner(System.in);
        int inputInt;
        boolean correct;
        do {
            System.out.print(message + "\n");
            correct = scan.hasNextInt();
            if (!correct) {
                scan.next();
                System.out.println(errorMessage);
            }
        } while (!correct);
        inputInt = scan.nextInt();
        scan.nextLine();
        return inputInt;
    }
    
    public float askForFloat(String message, String errorMessage) {
        if (message == null || message.isEmpty()) 
        {
            message = Constants.MESSAGE_DEFAULT_ASK_FLOAT;
        }
        if (errorMessage == null || errorMessage.isEmpty()) 
        {
            errorMessage = Constants.MESSAGE_DEFAULT_ERROR_FLOAT;
        }
        Scanner scan = new Scanner(System.in);
        scan.useLocale(Locale.US);
        float inputFloat;
        boolean correct;
        do {
            System.out.print(message + "\n");
            correct = scan.hasNextFloat();
            if (!correct) {
                scan.next();
                System.out.println(errorMessage);
            }
        } while (!correct);
        inputFloat = scan.nextFloat();
        scan.nextLine();
        return inputFloat;
    }
    
    public void showSupermarkets(String supermarketsList) {
        if (supermarketsList == null || supermarketsList.isEmpty()) {
            return;
        }
        System.out.println("\n");
        System.out.println(Constants.LIST_HEADER);
        System.out.println(Constants.TEMP_LIN);
        String[] supermarkets = supermarketsList.split("\n");
        for (String supermarket1 : supermarkets) {
            String[] supermarket = supermarket1.split(",");
            if (supermarket.length == 4) {
                try {
                    float longitude = Float.parseFloat(supermarket[2]);
                    float latitude = Float.parseFloat(supermarket[3]);
                    System.out.println(String.format(Constants.SUPERMARKET_BOARD_FORMAT, supermarket[0], supermarket[1], longitude, latitude));
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
    }
}