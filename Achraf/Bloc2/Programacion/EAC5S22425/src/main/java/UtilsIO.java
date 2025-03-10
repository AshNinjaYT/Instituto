import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilsIO {
    //private static Scanner scanner = new Scanner(System.in);

    public void showMenu(String menuText) {
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public void showError(String menuText) {
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public void showInfo(String menuText) {
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public String askForString(String message, String errorMessage) {
        Scanner scn = new Scanner(System.in);
        System.out.println(message);
        String result = scn.nextLine();
        result
        if (result.isEmpty() || result == null) {
         System.out.println(errorMessage);
         scn.close();
         return askForString(message, errorMessage);
        }
        scn.close();
        return result;
    }

    public int askForInteger(String message, String errorMessage) {
        Scanner scn = new Scanner(System.in);
        System.out.println(message);
        int result = 0;
        try {
            result = scn.nextInt();
            scn.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(errorMessage);
            scn.nextLine();
            askForInteger(message, errorMessage);
        }
        scn.close();
        return result;
    }

    public float askForFloat(String message, String errorMessage) {
        Scanner scn = new Scanner(System.in);
        System.out.println(message);
        float result = scn.nextFloat();
        try {
            result = scn.nextFloat();
            scn.nextLine();
        } catch (InputMismatchException e) {
            System.out.println(errorMessage);
            scn.nextLine();
            askForFloat(message, errorMessage);
        }
        scn.close();
        return result;
    }

    public void showSupermarkets(String supermarketsList) {

    }
}
