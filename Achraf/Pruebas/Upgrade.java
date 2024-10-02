package Achraf.Pruebas;
import java.util.Random;
import java.util.Scanner;

public class Upgrade {
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        // Crear un objeto Random para generar números aleatorios
        Random random = new Random();

        // Solicitar al usuario que elija "Derecha" o "Izquierda"
        System.out.println("Elige una opción: 'Derecha' o 'Izquierda'");
        String opcion = scanner.nextLine().toLowerCase(); // Convertimos la entrada a minúsculas para mayor flexibilidad

        int numeroAleatorio = 0;

        // Verificamos la elección del usuario
        if (opcion.equals("derecha")) {
            // Generamos un número aleatorio entre 1 y 50
            numeroAleatorio = random.nextInt(50) + 1;
            System.out.println("El número aleatorio es: " + numeroAleatorio);

            // Verificamos si el número está en el rango correcto
            if (numeroAleatorio >= 1 && numeroAleatorio <= 50) {
                System.out.println("¡Felicidades! El número está en el rango de 1 a 50.");
            } else {
                System.out.println("Lo siento, el número no está en el rango de 1 a 50.");
            }

        } else if (opcion.equals("izquierda")) {
            // Generamos un número aleatorio entre 51 y 100
            numeroAleatorio = random.nextInt(50) + 51;
            System.out.println("El número aleatorio es: " + numeroAleatorio);

            // Verificamos si el número está en el rango correcto
            if (numeroAleatorio >= 51 && numeroAleatorio <= 100) {
                System.out.println("¡Felicidades! El número está en el rango de 51 a 100.");
            } else {
                System.out.println("Lo siento, el número no está en el rango de 51 a 100.");
            }

        } else {
            // Si la opción no es ni "Derecha" ni "Izquierda"
            System.out.println("Opción no válida. Debes elegir 'Derecha' o 'Izquierda'.");
        }

        // Cerrar el escáner
        scanner.close();
    }
}
