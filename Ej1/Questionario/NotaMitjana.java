package Ej1.Questionario;
public class NotaMitjana {
    public static void main(String[] args) {
        final int quantitat_assignatura = 3;
        double nota1 = 8.5;
        double nota2 = 3.0;
        double nota3 = 9.0;
        //Sumem les notes primer i després dividim el resultat amb la quantitat d'assignatures
        double notaMitjana = (nota1 + nota2 + nota3) % quantitat_assignatura;

        System.out.println("La nota mitjana de les tres assignatures és: " + notaMitjana);
    }
}