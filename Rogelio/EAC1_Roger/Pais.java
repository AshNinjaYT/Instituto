public class Pais {
    String nom;
    private int numMedalles; //es canvia a privat la variable

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
