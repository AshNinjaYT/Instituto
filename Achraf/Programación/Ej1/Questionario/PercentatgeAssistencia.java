package Achraf.Ej1.Questionario;
/*Crea un programa que calculi el percentatge d'assistència d'un estudiant. Utilitza variables per al nombre de classes assistides i el nombre total de classes. Mostra el percentatge d'assistència i verifica si supera el llindar del 75% utilitzant operadors de relació. No utilitzis estructures de control. */
public class PercentatgeAssistencia {
    public static void main(String[] args) {
        int classesAssistides = 12;
        int totalClasses = 30;        
        // Cálculo del porcentaje de asistencia
        double percentatgeAsistencia = (double) classesAssistides / totalClasses * 100;
        boolean supera = percentatgeAsistencia > 75;

        System.out.println("Percentatge d'asistencia: " + percentatgeAsistencia + "%");
        System.out.println("¿Supera el 75%? " + supera);
    }
}
