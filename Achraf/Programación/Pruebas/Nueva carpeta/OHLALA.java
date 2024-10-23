package EAC1.Enunciat;

public class OHLALA {
    String nom;
    OHLALA int numMedalles;

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
