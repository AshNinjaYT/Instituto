public class Medalla {

    //També s'admetria una propietat privada per indicar si la medalla 
    // és o no vàlida. En aquest cas, seria sense setter
    static final int MEDALLA_NO_VALIDA = 0;
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
        //Només admet valors vàlids entre 1 i 3
        if (posicio >= MEDALLA_OR && posicio <= MEDALLA_BRONZE) {
            this.posicio = posicio;
            this.atleta = guanyador;
            if (atleta.getNumMedalles() < Atleta.MAX_MEDALLES_ATLETA &&
                atleta.pais.getNumMedalles() < Pais.MAX_MEDALLES_PAIS ) {
                atleta.pais.novaMedalla(this);
                atleta.guanyarMedalla(this);
                numMedallesRepartides++;
                }
            else
                this.posicio = MEDALLA_NO_VALIDA;                
        }
        else this.posicio = MEDALLA_NO_VALIDA;
    }

    public String descripcio() {
        String missatge;
        if (posicio == MEDALLA_NO_VALIDA)
            missatge = "MEDALLA NO VÀLIDA";
        else  {
            String medalla;
            switch (posicio) {
                case MEDALLA_OR :       medalla = "d'or";
                                        break;
                case MEDALLA_ARGENT :   medalla = "d'argent";
                                        break;
                case MEDALLA_BRONZE :   medalla = "de bronze";
                                        break;  
                default:                medalla = "NO DEFINIDA!!!";
            }
            missatge = "L'atleta " + atleta.nom + " ha guanyat una medalla " + medalla + " en obtenir la posició " + posicio + " a la seva prova de " + atleta.disciplina;
        }

        return missatge;
    }

}
