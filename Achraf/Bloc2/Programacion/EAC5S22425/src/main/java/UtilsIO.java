import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilsIO {
    Scanner scanner = new Scanner(System.in);

    public void showMenu(String menuText) {
        if (menuText == null || menuText.isEmpty()) {
            return; // No imprime nada si el texto del menú es null o vacío
        }
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public void showError(String menuText) {
        if (menuText == null || menuText.isEmpty()) {
            return; // No imprime nada si el texto del menú es null o vacío
        }
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public void showInfo(String menuText) {
        if (menuText == null || menuText.isEmpty()) {
            return; // No imprime nada si el texto del menú es null o vacío
        }
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public String askForString(String message, String errorMessage) {
        Scanner scn = new Scanner(System.in);
        System.out.println(message);

        String result;
        do {
            result = scn.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println(errorMessage);
            }
        } while (result.isEmpty());
        scn.close();
        return result;

    }

    public int askForInteger(String message, String errorMessage) {
        Scanner scn = new Scanner(System.in);
        System.out.println(message);
        int result = 0;
        do {
            try {
                result = scn.nextInt();
                scn.nextLine();
            } catch (InputMismatchException e) {
                System.out.println(errorMessage);
                scn.nextLine();
            }
        } while (scn.hasNextInt());
        scn.close();
        return result;
    }

    public float askForFloat(String message, String errorMessage) {
        Scanner scn = new Scanner(System.in);
        System.out.println(message);
        float result = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                result = Float.parseFloat(scn.nextLine().trim());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }

        scn.close();
        return result;
    }

    public void showSupermarkets(String supermarketsList) {

    }
}
