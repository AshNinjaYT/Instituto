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
        if (posicio <= 3 && posicio >= 1) {
            this.posicio = posicio;
            this.atleta = guanyador;
            atleta.pais.novaMedalla(this);
            atleta.guanyarMedalla(this);
            numMedallesRepartides++;
        } else {
            this.posicio = posicio;
            this.atleta = guanyador;
        }
    }

    public String descripcio() {
        String medallaNom;
        switch (posicio) {
            case 1:
                medallaNom = "d’or";
                break;
            case 2:
                medallaNom = "d'argent";
                break;
            case 3:
                medallaNom = "de bronze";
                break;
            default:
                medallaNom = "MEDALLA NO VÀLIDA";
        }
        ;
        return "L'atleta " + atleta.nom + " ha guanyat una medalla " + medallaNom + " en obtenir la posició " + posicio
                + " a la seva prova de " + atleta.disciplina;
    }

}
