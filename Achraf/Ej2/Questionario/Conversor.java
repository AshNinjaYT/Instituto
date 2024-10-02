package Achraf.Ej2.Questionario;

public class Conversor {
    double euros, dolars = 1.2;  // Se declara una variable euros y dolars, a dolars se le otorga la tasa de cambio que es 1.2
    public void convertirADolars() {
        // Multiplacacion euros por dólares y se almacena el resultado en dolars
        dolars = euros * dolars;
        // Muestra el resultado de la conversión en la consola
        System.out.println(euros + " euros són " + dolars + " dòlars.");
    }    
}