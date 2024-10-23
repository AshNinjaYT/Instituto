package EAC2.Enunciat;

public class Atleta {
    String nom;
    Pais pais;
    String disciplina;
    private int numMedalles;

    public int getNumMedalles() {
        return numMedalles;
    }

    Atleta(String nom, Pais pais, String disciplina) {
        this.nom = nom;
        this.pais = pais;
        this.disciplina = disciplina;
    }

    void guanyarMedalla(Medalla m) {
        numMedalles++;
    }
}
