import java.util.Scanner;

public class InputUtils {
    static final Scanner scanner = new Scanner(System.in);

    public static String readLine() {
        return scanner.nextLine();
    }

    public static int readInt() {
        String input = scanner.nextLine();
        if (isNumeric(input)) {
            return Integer.parseInt(input);
        } else {
            System.out.println("Error: Si us plau, introdueix un número enter.");
            return -1; // Codi d'error
        }
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
