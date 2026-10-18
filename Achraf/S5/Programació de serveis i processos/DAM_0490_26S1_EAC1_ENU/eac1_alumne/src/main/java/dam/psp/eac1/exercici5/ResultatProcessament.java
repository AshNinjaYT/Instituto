package dam.psp.eac1.exercici5;

/**
 * Guarda el resultat del processament d'un paquet.
 */
public class ResultatProcessament {
    private final String paquetId;
    private final boolean acceptat;
    private final String motiu;
    private final String nomFil;

    public ResultatProcessament(String paquetId, boolean acceptat, String motiu, String nomFil) {
        this.paquetId = paquetId;
        this.acceptat = acceptat;
        this.motiu = motiu;
        this.nomFil = nomFil;
    }

    public String getPaquetId() {
        return paquetId;
    }

    public boolean isAcceptat() {
        return acceptat;
    }

    public String getMotiu() {
        return motiu;
    }

    public String getNomFil() {
        return nomFil;
    }

    @Override
    public String toString() {
        return "ResultatProcessament{" +
                "paquetId='" + paquetId + '\'' +
                ", acceptat=" + acceptat +
                ", motiu='" + motiu + '\'' +
                ", nomFil='" + nomFil + '\'' +
                '}';
    }
}
