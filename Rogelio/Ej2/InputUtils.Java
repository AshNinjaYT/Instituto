public class InputUtils {
    private static final Scanner scan = new Scanner(System.in);
    public static final int INPUT_ERROR = -1;
    
    public static String readLine(String b) {
        System.out.println(b);
        return scan.nextLine();
    }

    public static int readInt(String b) {
        System.out.println(b);
        if (scan.hasNextInt()) {
            int numero = scan.nextInt();
            scan.nextLine();
            return numero;
        } else {
            System.out.println("Error: Introdueix un número sencer vàlid.");
            scan.nextLine();
            return INPUT_ERROR;  // Codigo de error
        }
    }
}
