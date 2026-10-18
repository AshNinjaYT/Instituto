package dam.psp.eac1.exercici5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Programa principal de l'exercici 5.
 * Simula el funcionament concurrent d'un centre logístic i de distribució.
 */
public class CentreDistribucioMain {
    // Definim com a mínim 100 elements (aquí en posem 250) i un mínim de 4 fils
    private static final int TOTAL_ELEMENTS = 250;
    private static final int MIDA_POOL_FILS = 4;

    public static void main(String[] args) {
        System.out.println("--- INICI PROCESSAMENT CENTRE DISTRIBUCIÓ LOGÍSTIC ---");

        // 1. Generació d'elements de prova automàtica
        List<Paquet> paquets = generarPaquets(TOTAL_ELEMENTS);

        // 2. Estat compartit on s'acumulen les dades
        EstatCentreDistribucio estatCompartit = new EstatCentreDistribucio();

        // 3. Creació del pool de fils amb ExecutorService (4 fils)
        ExecutorService executor = Executors.newFixedThreadPool(MIDA_POOL_FILS);

        // 4. Enviament de tasques concurrents
        List<Future<ResultatProcessament>> futures = new ArrayList<>();
        for (Paquet p : paquets) {
            TascaProcessarPaquet tasca = new TascaProcessarPaquet(p, estatCompartit);
            futures.add(executor.submit(tasca));
        }

        // 5. Recollida de resultats dels Future
        List<ResultatProcessament> resultats = new ArrayList<>();
        for (Future<ResultatProcessament> f : futures) {
            try {
                resultats.add(f.get());
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error recollint tasca: " + e.getMessage());
            }
        }

        // 6. Tancament ordenat de l'ExecutorService
        executor.shutdown();
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        // 7. Sortida final estructurada amb comprovació de coherència
        boolean coherent = estatCompartit.esCoherent(TOTAL_ELEMENTS) && (resultats.size() == TOTAL_ELEMENTS);

        System.out.println("TOTAL_ELEMENTS=" + TOTAL_ELEMENTS);
        System.out.println("TOTAL_PROCESSATS=" + estatCompartit.getTotalProcessats());
        System.out.println("TOTAL_OK=" + estatCompartit.getTotalAcceptats());
        System.out.println("TOTAL_ERROR=" + estatCompartit.getTotalRebutjats());
        System.out.printf("PES_TOTAL_PROCESSAT=%.2f kg%n", estatCompartit.getPesTotalProcessat());
        System.out.println("FILS_UTILITZATS=" + estatCompartit.getQuantitatFilsParticipants());
        System.out.println("COHERENT=" + coherent);
    }

    /**
     * Mètode auxiliar per generar una llista d'elements de prova amb dades variades.
     */
    private static List<Paquet> generarPaquets(int quantitat) {
        List<Paquet> llista = new ArrayList<>();
        Random rnd = new Random(12345); // Llavor prefixada per tenir sempre resultats reproduïbles
        String[] destinacions = {"Barcelona", "Lleida", "Tarragona", "Girona", "Reus", "Terrassa"};

        for (int i = 1; i <= quantitat; i++) {
            String id = String.format("PAQ-%04d", i);
            // La majoria de paquets pesen entre 2 i 28 kg (acceptats), però alguns pesen fins a 35 kg (rebutjats)
            double pes = 2.0 + (rnd.nextDouble() * 33.0);
            String ciutat = destinacions[rnd.nextInt(destinacions.length)];
            llista.add(new Paquet(id, pes, ciutat));
        }

        return llista;
    }
}
