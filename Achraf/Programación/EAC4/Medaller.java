import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Medaller {
    private final List<Pais> medalles;

    public Medaller() {
        this.medalles = new ArrayList<>();
    }

    // Afegeix una medalla al país especificat
    public void afegirMedalla(String pais) {
        for (Pais paisMedalles : medalles) {
            if (paisMedalles.nom().equals(pais)) {
                paisMedalles.afegirMedalla();
                return;
            }
        }
        medalles.add(new PaisMedalles(pais, 1));
    }

    // Mostra el medaller sense cap ordenació
    public void mostrarMedaller() {
        System.out.println("Medaller:");
        for (Pais paisMedalles : medalles) {
            System.out.printf("%s: %d medalles%n", paisMedalles.getNomPais(), paisMedalles.getNumMedalles());
        }
    }/* 

    public static void mostrarMedallerOrdenat() {
        paisosAmbMedalla.sort((p1, p2) -> p2.getNumMedalles() - p1.getNumMedalles());
        mostrarMedaller();
    }

    public static void mostrarMedallerOrdenatAlfabetic() {
        paisosAmbMedalla.sort(Comparator.comparing(p -> p.nom));
        mostrarMedaller();
    }*/
}