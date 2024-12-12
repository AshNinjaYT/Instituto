import java.util.Scanner;

public class InputUtils {
    private static Scanner scan = new Scanner(System.in);

    public static String readLine() {
        return scan.nextLine();
    }

    public static int readInt(String msg) {
        System.out.println(msg);
        int resultat;

        while (!scan.hasNextInt()) {
            System.out.println("El valor ha de ser numèric. Torna-ho a provar: ");
            scan.next();
        }

        resultat = scan.nextInt();
        return resultat;
    }

    public static int readInt(String msg, int valorMin, int valorMax) {
        int resultat;
        System.out.println(msg);
        while (!scan.hasNextInt()) {
            System.out.println("Error: Has d'introduir un número. Torna-ho a provar.");
            scan.next();
        }
        resultat = scan.nextInt();
        if (resultat < valorMin || resultat > valorMax) {
            System.out.println("Error: El valor ha d'estar entre " + valorMin + " i " + valorMax);
        }
        return resultat;
    }

    public static int readInt(String msg, String err) {
        int resultat;
        System.out.println(msg);
        while (!scan.hasNextInt()) {
            System.out.println(err);
            scan.next();
        }
        resultat = scan.nextInt();
        return resultat;
    }

    public static int readInt(String msg, int valorMin, int valorMax, String err) {
        int resultat;
        System.out.println(msg);
        do {
            while (!scan.hasNextInt()) {
                System.out.println(err);
                scan.next();
            }
            resultat = scan.nextInt();
            if (resultat < valorMin || resultat > valorMax) {
                System.out.println("Error: El valor ha d'estar entre " + valorMin + " i " + valorMax);
            }
        } while (resultat < valorMin || resultat > valorMax);

        return resultat;
    }

    public static void close() {
        scan.close();
    }
}