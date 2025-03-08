public class Atleta {
    // Màxim nombre de medalles que pot guanyar un Atleta
    static final int MAX_MEDALLES_ATLETA = 10;

    String nom;
    Pais pais;
    String disciplina;
    private int numMedalles;
    private Medalla[] medallesGuanyades;

    public int getNumMedalles() {
        return numMedalles;
    }

    Atleta(String nom, Pais pais, String disciplina) {
        this.nom = nom;
        this.pais = pais;
        this.disciplina = disciplina;
        medallesGuanyades = new Medalla[MAX_MEDALLES_ATLETA];
    }

    boolean guanyarMedalla(Medalla m) {
        boolean resultat = true;
        try {
            medallesGuanyades[numMedalles] = m;
            numMedalles++;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Aquest atleta no pot guanyar més medalles");
            resultat = false;
        }
        return resultat;
    }

    String mostrarMedalles() {
        String resultado = new String();
        for (int medalla = 0; medalla < numMedalles; medalla++) {
            resultado = medallesGuanyades[medalla].descripcio();
            System.out.println(medallesGuanyades[medalla].descripcio());
        }
        return resultado;
    }
}
