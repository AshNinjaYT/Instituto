package dam.psp.eac1.exercici5;

import java.util.concurrent.Callable;

/**
 * Tasca concurrent que simula el processament individual d'un paquet.
 * Implementa Callable per poder retornar el ResultatProcessament al fil principal.
 */
public class TascaProcessarPaquet implements Callable<ResultatProcessament> {
    private final Paquet paquet;
    private final EstatCentreDistribucio estatCompartit;

    public TascaProcessarPaquet(Paquet paquet, EstatCentreDistribucio estatCompartit) {
        if (paquet == null) {
            throw new IllegalArgumentException("El paquet no pot ser null.");
        }
        if (estatCompartit == null) {
            throw new IllegalArgumentException("L'estat compartit no pot ser null.");
        }
        this.paquet = paquet;
        this.estatCompartit = estatCompartit;
    }

    @Override
    public ResultatProcessament call() {
        String nomFil = Thread.currentThread().getName();

        // Simulem una petita feina de pesat i escaneig de paquet
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Lògica de negoci: si el paquet supera el pes màxim permès de 30 kg, es rebutja
        boolean acceptat;
        String motiu;
        if (paquet.getPesKg() > 30.0) {
            acceptat = false;
            motiu = "Rebutjat: pes superior a 30kg";
        } else {
            acceptat = true;
            motiu = "Acceptat: enviament assignat correctament";
        }

        ResultatProcessament resultat = new ResultatProcessament(
                paquet.getId(),
                acceptat,
                motiu,
                nomFil
        );

        // Actualitzem el recurs compartit de manera segura
        estatCompartit.registrarProcessament(resultat, paquet.getPesKg());

        return resultat;
    }
}
