import java.util.Scanner;

public class UtilsIO {
    Scanner scanner = new Scanner(System.in);

    public void showMenu(String menuText) {
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public void showError(String menuText) {

    }

    public void showInfo(String menuText) {

    }

    public String askForString(String message, String errorMessage) {
        System.out.println(message);
        String result = scanner.nextLine()
        return result;
    }

    public int askForInteger(String message, String errorMessage) {
        System.out.println(message);
        return;
    }

    public float askForFloat(String message, String errorMessage) {
        return;
    }

    public void showSupermarkets(String supermarketsList) {

    }
}
