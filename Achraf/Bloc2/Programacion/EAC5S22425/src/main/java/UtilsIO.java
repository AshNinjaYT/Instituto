import java.util.InputMismatchException;
import java.util.Scanner;

public class UtilsIO {
    Scanner scanner = new Scanner(System.in);

    public void showMenu(String menuText) {
        // Verifica si el texto del menú es nulo o vacío
        if (menuText == null || menuText.isEmpty()) {
            return;
        }
        // Imprime una linea discontinua y el texto del menú
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public void showError(String menuText) {
        // Verifica si el texto del error es nulo o vacío
        if (menuText == null || menuText.isEmpty()) {
            return;
        }
        // Imprime una linea discontinua y el texto del error
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public void showInfo(String menuText) {
        // Verifica si el texto del error es nulo o vacío
        if (menuText == null || menuText.isEmpty()) {
            return;
        }
        // Imprime una linea discontinua y el texto del error
        System.out.println(Constants.SEPARADOR);
        System.out.println(menuText);
    }

    public String askForString(String message, String errorMessage) {
        // Pide al usuario una cadena de texto
        Scanner scn = new Scanner(System.in);
        System.out.println(message);
        String result;

        do {
            result = scn.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println(errorMessage); // Muestra mensaje de error si el input está vacío
            }
        } while (result.isEmpty()); // Sigue pidiendo el input hasta que no esté vacío

        return result;
    }

    public int askForInteger(String message, String errorMessage) {
        // Pide al usuario un número entero
        Scanner scn = new Scanner(System.in);
        System.out.println(message);
        int result = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                result = Integer.parseInt(scn.nextLine().trim()); // Convierte la entrada string a entero
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage); // Si la conversión falla, muestra mensaje de error
            }
        }
        return result;
    }

    public float askForFloat(String message, String errorMessage) {
        // Pide al usuario un número decimal
        Scanner scn = new Scanner(System.in);
        System.out.println(message);
        float result = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                result = Float.parseFloat(scn.nextLine().trim()); // Convierte la entrada string a float
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage); // Si la conversión falla, muestra mensaje de error
            }
        }
        return result;
    }

    public void showSupermarkets(String supermarketsList) {
        // Muestra una lista de supermercados con formato
        if (supermarketsList == null || supermarketsList.isEmpty()) {
            return; // Si la lista es nula o vacía, no muestra nada
        }

        // Imprime los encabezados de la tabla
        System.out.printf(Constants.PRINT_FORMAT, "DATETIME", "SUPERMARKET", "CITY", "LONGITUDE", "LATITUDE");
        System.out.println(Constants.SEPARADOR);

        String[] lines = supermarketsList.split("\n"); // Divide la lista en líneas

        for (String line : lines) {
            String[] elements = line.split(","); // Divide cada línea en elementos

            if (elements.length != 5) {
                continue; // Si no hay exactamente 5 elementos, salta la línea
            }

            // Imprime cada supermercado en el formato adecuado
            System.out.printf(Constants.PRINT_FORMAT,
                    elements[0], elements[1], elements[2], elements[3], elements[4]);
        }
    }
}