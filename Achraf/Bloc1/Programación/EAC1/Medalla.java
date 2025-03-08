public class Medalla {

    static final int MEDALLA_OR = 1;
    static final int MEDALLA_ARGENT = 2;
    static final int MEDALLA_BRONZE = 3;

    private static int numMedallesRepartides = 0;
  
    int posicio;
    Atleta atleta;

    public static int getNumMedallesRepartides() {
        return numMedallesRepartides;
    }

    Medalla(int posicio, Atleta guanyador) {
        this.posicio = posicio;
        this.atleta = guanyador;
        atleta.pais.novaMedalla(this);
        atleta.guanyarMedalla(this);
        numMedallesRepartides++;
    }

    public String descripcio() {
        String laTevaMedalla = switch (posicio) {
            case 1 -> "1";
            case 2 -> "2";
            case 3 -> "3";
            default -> "sense medalla";
        };
        return "L'atleta " + atleta.nom + " ha guanyat una medalla en obtenir la posició " + posicio + " a la seva prova de " + atleta.disciplina;

}
}