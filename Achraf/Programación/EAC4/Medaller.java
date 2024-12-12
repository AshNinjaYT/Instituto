import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Medaller {
    private static List<Pais> paisosAmbMedalla = new ArrayList<>();

    public static void afegirPais(Pais p) {
        if (!paisosAmbMedalla.contains(p)) paisosAmbMedalla.add(p);
    }

    public static void mostrarMedaller() {
        for (Pais p : paisosAmbMedalla) {
            System.out.println(p.nom + ": " + p.getNumMedalles());
        }
    }

    public static void mostrarMedallerOrdenat() {
        paisosAmbMedalla.sort((p1, p2) -> p2.getNumMedalles() - p1.getNumMedalles());
        mostrarMedaller();
    }

    public static void mostrarMedallerOrdenatAlfabetic() {
        paisosAmbMedalla.sort(Comparator.comparing(p -> p.nom));
        mostrarMedaller();
    }
}