package dam.psp.eac1.exercici5;

import java.util.HashSet;
import java.util.Set;

/**
 * Recurs compartit per tots els fils de treballadors del centre logístic.
 * Acumula els totals de paquets i el pes total processat.
 * 
 * S'utilitzen mètodes sincronitzats (synchronized) per garantir que múltiples
 * fils no modifiquin aquests comptadors al mateix temps (evitant condicions de cursa).
 */
public class EstatCentreDistribucio {
    private int totalProcessats = 0;
    private int totalAcceptats = 0;
    private int totalRebutjats = 0;
    private double pesTotalProcessat = 0.0;
    private final Set<String> filsParticipants = new HashSet<>();

    // Mètode sincronitzat per registrar l'operació de manera atòmica i segura
    public synchronized void registrarProcessament(ResultatProcessament resultat, double pesKg) {
        if (resultat == null) {
            throw new IllegalArgumentException("El resultat no pot ser null.");
        }

        totalProcessats++;
        if (resultat.isAcceptat()) {
            totalAcceptats++;
        } else {
            totalRebutjats++;
        }
        pesTotalProcessat += pesKg;
        filsParticipants.add(resultat.getNomFil());
    }

    public synchronized int getTotalProcessats() {
        return totalProcessats;
    }

    public synchronized int getTotalAcceptats() {
        return totalAcceptats;
    }

    public synchronized int getTotalRebutjats() {
        return totalRebutjats;
    }

    public synchronized double getPesTotalProcessat() {
        return pesTotalProcessat;
    }

    public synchronized int getQuantitatFilsParticipants() {
        return filsParticipants.size();
    }

    // Comprovació de coherència matemàtica de les dades finals
    public synchronized boolean esCoherent(int totalEsperat) {
        return (totalProcessats == totalEsperat) && 
               (totalAcceptats + totalRebutjats == totalProcessats);
    }
}
