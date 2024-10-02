package Achraf.Ej2.Questionario;

public class Calculadora {
    int numeroSuma1;  // Primer número para la suma
    int numeroSuma2;  // Segundo número para la suma
    int numeroMult1;  // Primer número para la multiplicación
    int numeroMult2;  // Segundo número para la multiplicación
    
    public void sumar() {
        // Calcula la suma de numeroSuma1 y numeroSuma2, y muestra el resultado
        System.out.println("La suma és: " + (numeroSuma1 + numeroSuma2));
    }
    
    public void multiplicar() {
        // Calcula la multiplicación de numeroMult1 y numeroMult2, y muestra el resultado
        System.out.println("La multiplicació és: " + (numeroMult1 * numeroMult2));
    }
}
