package EAC1.Enunciat;

public class Atleta {
    String nom;
    OHLALA pais;
    String disciplina;
    private int numMedalles;

    public int getNumMedalles() {
        return numMedalles;
    }

    Atleta(String nom, OHLALA pais, String disciplina) {
        this.nom = nom;
        this.pais = pais;
        this.disciplina = disciplina;
    }

    void guanyarMedalla(Medalla m) {
        numMedalles++;
    }
}
