package dam.psp.eac1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CorrectorExecutor {
    public static final long TEMPS_MAXIM_TASCA_SEGONS = 3L;
    public static final long TEMPS_MAXIM_TANCAMENT_SEGONS = 5L;

    public static void main(String[] args) throws Exception {
        int midaPool = llegirMidaPool(args);

        CorrectorExecutor corrector = new CorrectorExecutor();
        List<Lliurament> lliuraments = CarregadorLliuraments.carregar();
        InformeExecucio informe = corrector.corregir(lliuraments, midaPool);

        System.out.println(informe);
    }

    private static int llegirMidaPool(String[] args) {
        if (args == null || args.length == 0) {
            return 3;
        }
        if (args.length > 1 || args[0] == null || args[0].isBlank()) {
            throw new IllegalArgumentException("Cal indicar com a màxim un argument amb la mida del pool.");
        }

        int midaPool = Integer.parseInt(args[0]);
        if (midaPool <= 0) {
            throw new IllegalArgumentException("La mida del pool ha de ser positiva.");
        }
        return midaPool;
    }

    public InformeExecucio corregir(List<Lliurament> lliuraments, int midaPool) throws Exception {
        if (lliuraments == null || lliuraments.stream().anyMatch(java.util.Objects::isNull)) {
            throw new IllegalArgumentException("La llista de lliuraments no pot ser null ni contenir valors null.");
        }
        if (midaPool <= 0) {
            throw new IllegalArgumentException("La mida del pool ha de ser positiva.");
        }

        long iniciPrograma = System.currentTimeMillis();
        MarcadorCorreccions marcador = new MarcadorCorreccions();
        ExecutorService executor = Executors.newFixedThreadPool(midaPool);
        List<ResultatCorreccio> resultats = new ArrayList<>();

        try {
            List<Future<ResultatCorreccio>> futures = enviarTasques(lliuraments, marcador, executor);
            resultats = recollirResultats(futures);
        } finally {
            tancarExecutor(executor);
        }

        long tempsRealMs = System.currentTimeMillis() - iniciPrograma;
        return new InformeExecucio(midaPool, tempsRealMs, marcador, resultats);
    }

    public List<Future<ResultatCorreccio>> enviarTasques(
            List<Lliurament> lliuraments,
            MarcadorCorreccions marcador,
            ExecutorService executor
    ) {
        // Validamos que ninguno de los parametros sea null
        if (lliuraments == null || marcador == null || executor == null) {
            throw new IllegalArgumentException("Cap dels paràmetres pot ser null.");
        }
        for (Lliurament ll : lliuraments) {
            if (ll == null) {
                throw new IllegalArgumentException("La llista de lliuraments no pot contenir valors null.");
            }
        }

        // Enviamos una tarea al pool de hilos por cada entrega
        List<Future<ResultatCorreccio>> futures = new ArrayList<>();
        for (Lliurament ll : lliuraments) {
            TascaCorreccio tasca = new TascaCorreccio(ll, marcador);
            Future<ResultatCorreccio> future = executor.submit(tasca);
            futures.add(future);
        }

        return futures;
    }

    public List<ResultatCorreccio> recollirResultats(List<Future<ResultatCorreccio>> futures) throws Exception {
        // Validamos que la lista de futuros no sea nula ni tenga elementos nulos
        if (futures == null) {
            throw new IllegalArgumentException("La llista de futures no pot ser null.");
        }
        for (Future<ResultatCorreccio> f : futures) {
            if (f == null) {
                throw new IllegalArgumentException("La llista de futures no pot contenir valors null.");
            }
        }

        // Recorremos los futuros recogiendo el resultado con un timeout
        List<ResultatCorreccio> resultats = new ArrayList<>();
        for (Future<ResultatCorreccio> future : futures) {
            try {
                // Esperamos como maximo TEMPS_MAXIM_TASCA_SEGONS
                ResultatCorreccio res = future.get(TEMPS_MAXIM_TASCA_SEGONS, TimeUnit.SECONDS);
                if (res != null) {
                    resultats.add(res);
                }
            } catch (TimeoutException e) {
                // Si tarda demasiado, cancelamos la tarea para no dejar hilos pillados
                future.cancel(true);
            } catch (ExecutionException | InterruptedException e) {
                // Si la tarea falla o el hilo se interrumpe, no la añadimos
            }
        }

        return resultats;
    }

    public void tancarExecutor(ExecutorService executor) throws InterruptedException {
        // Validamos que el executor no sea null
        if (executor == null) {
            throw new IllegalArgumentException("L'executor no pot ser null.");
        }

        // Le pedimos que cierre de forma ordenada (no acepta mas tareas nuevas)
        executor.shutdown();

        // Esperamos un maximo a que acaben las tareas que ya estaban en marcha
        boolean finalitzat = executor.awaitTermination(TEMPS_MAXIM_TANCAMENT_SEGONS, TimeUnit.SECONDS);

        // Si se acaba el tiempo y todavia quedan tareas pendientes, forzamos el cierre inmediato
        if (!finalitzat) {
            executor.shutdownNow();
        }
    }

    public static class InformeExecucio {
        private final int midaPool;
        private final long tempsRealMs;
        private final MarcadorCorreccions marcador;
        private final List<ResultatCorreccio> resultats;

        public InformeExecucio(int midaPool, long tempsRealMs, MarcadorCorreccions marcador, List<ResultatCorreccio> resultats) {
            if (midaPool <= 0) {
                throw new IllegalArgumentException("La mida del pool ha de ser positiva.");
            }
            if (tempsRealMs < 0L) {
                throw new IllegalArgumentException("El temps real no pot ser negatiu.");
            }
            if (marcador == null) {
                throw new IllegalArgumentException("El marcador no pot ser null.");
            }
            if (resultats == null || resultats.stream().anyMatch(java.util.Objects::isNull)) {
                throw new IllegalArgumentException("La llista de resultats no pot ser null ni contenir valors null.");
            }
            this.midaPool = midaPool;
            this.tempsRealMs = tempsRealMs;
            this.marcador = marcador;
            this.resultats = List.copyOf(resultats);
        }

        public int getMidaPool() {
            return midaPool;
        }

        public long getTempsRealMs() {
            return tempsRealMs;
        }

        public MarcadorCorreccions getMarcador() {
            return marcador;
        }

        public List<ResultatCorreccio> getResultats() {
            return resultats;
        }

        @Override
        public String toString() {
            return "InformeExecucio{" +
                    "midaPool=" + midaPool +
                    ", tempsRealMs=" + tempsRealMs +
                    ", marcador=" + marcador.resum() +
                    ", resultats=" + resultats.size() +
                    '}';
        }
    }
}
