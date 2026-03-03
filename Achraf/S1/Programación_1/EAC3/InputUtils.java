import java.util.Scanner;

public class InputUtils {

    // Fent la variable estàtica, assegurem que només es fa servir un Scanner
    // i que, a més, tothom fa servir el mateix.
    private static Scanner scan = new Scanner(System.in);

    // Definim quin és el codi d'error
    public static final int INPUT_ERROR = -1;

    // La lectura d'un String mai pot donar un error
    public static String readLine() {
        return scan.nextLine();
    }

    // La lectura d'un enter pot donar error si el que hi ha a l'entrada no és un
    // enter. Cal controlar-lo
    public static int readInt() {
        while (true) {
            if (scan.hasNextInt()) {
                return scan.nextInt();
            } else {
                System.out.println("El valor tiene que ser numerico. Vuelve a intentarlo: ");
                // Ignora el valor erroni
                scan.next();
            }
        }
    }

    // Caldria definir un mètode per tancar l'Scanner
    public static void close() {
        scan.close();
    }
}