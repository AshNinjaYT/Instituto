public class Pais {
    //Màxim nombre de medalles que pot guanyar un País
    static final int MAX_MEDALLES_PAIS = 100;

    String nom;
    private int numMedalles;
    private Medalla[] medallesGuanyades;

    public int getNumMedalles() {
        return numMedalles;
    }

    Pais (String nom) {
        this.nom = nom;
        numMedalles = 0;
        medallesGuanyades = new Medalla[ MAX_MEDALLES_PAIS];
    }

    public boolean novaMedalla(Medalla m) {
        boolean resultat = true;
        try {
            medallesGuanyades[numMedalles] = m;
            numMedalles++;
        }
        catch (IndexOutOfBoundsException e) {
            System.out.println("Aquest país no pot guanyar més medalles");
            resultat = false;
        }
        return resultat;
    }

    void mostrarMedalles(){
        for (int medalla=0; medalla < numMedalles; medalla++) {
            System.out.println(medallesGuanyades[medalla].descripcio());
        }
    }
}
