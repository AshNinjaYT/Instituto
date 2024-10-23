package EAC2.Enunciat;

public class Pais {
    String nom;
    private int numMedalles;

    public int getNumMedalles() {
        return numMedalles;
    }

    Pais (String nom) {
        this.nom = nom;
        numMedalles = 0;
    }

    public void novaMedalla(Medalla m) {
        numMedalles = numMedalles + 1;
    }
}
