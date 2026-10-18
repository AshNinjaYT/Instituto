package dam.psp.eac1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CorrectorProcessos {
    public static final int CODI_TIMEOUT = -1;
    public static final long TEMPS_MAXIM_PROCES_SEGONS = 3L;

    private static final Path DIRECTORI_RESULTATS = Path.of("resultats");

    public static void main(String[] args) throws Exception {
        CorrectorProcessos corrector = new CorrectorProcessos();
        List<Lliurament> lliuraments = CarregadorLliuraments.carregar();
        List<ResultatProces> resultats = corrector.corregirLliuraments(lliuraments);
        corrector.mostrarResum(resultats);
    }

    public ResultatProces corregirUnLliurament(String alumneId, Path ruta)
            throws IOException, InterruptedException {
        if (alumneId == null || alumneId.isBlank()) {
            throw new IllegalArgumentException("L'identificador de l'alumne no pot ser buit.");
        }
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta del lliurament no pot ser null.");
        }

        Files.createDirectories(DIRECTORI_RESULTATS);
        Path fitxerSortida = DIRECTORI_RESULTATS.resolve(alumneId + ".txt");

        List<String> ordre = construirOrdreValidador(alumneId, ruta);
        ProcessBuilder pb = prepararProces(ordre, fitxerSortida);

        long inici = System.currentTimeMillis();
        Process proces = pb.start();
        int codiRetorn = esperarProces(proces);
        long durada = System.currentTimeMillis() - inici;

        return new ResultatProces(alumneId, codiRetorn, fitxerSortida, durada);
    }

    public List<String> construirOrdreValidador(String alumneId, Path ruta) {
        // Valido los parametros para no recibir nada nulo o vacio
        if (alumneId == null || alumneId.isBlank()) {
            throw new IllegalArgumentException("L'identificador de l'alumne no pot ser buit.");
        }
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta del lliurament no pot ser null.");
        }

        // Obtenemos el comando de java actual y el classpath del sistema
        String javaHome = System.getProperty("java.home");
        String javaCmd = Path.of(javaHome, "bin", "java").toString();
        String classpath = System.getProperty("java.class.path");

        // Montamos la lista de comandos para ejecutar el proceso con ProcessBuilder
        List<String> ordre = new ArrayList<>();
        ordre.add(javaCmd);
        ordre.add("-cp");
        ordre.add(classpath);
        ordre.add("dam.psp.eac1.ValidadorLliurament");
        ordre.add(alumneId);
        ordre.add(ruta.toString());

        return ordre;
    }

    public ProcessBuilder prepararProces(List<String> ordre, Path fitxerSortida) {
        // Validamos que la orden no sea nula ni vacia y no tenga valores vacios
        if (ordre == null || ordre.isEmpty()) {
            throw new IllegalArgumentException("L'ordre no pot ser buida.");
        }
        for (String argument : ordre) {
            if (argument == null || argument.isBlank()) {
                throw new IllegalArgumentException("L'ordre no pot contenir elements nuls o buits.");
            }
        }
        if (fitxerSortida == null) {
            throw new IllegalArgumentException("El fitxer de sortida no pot ser null.");
        }

        // Creamos el ProcessBuilder con la lista de argumentos
        ProcessBuilder pb = new ProcessBuilder(ordre);

        // Redirigimos la salida estandar al fichero indicado
        pb.redirectOutput(fitxerSortida.toFile());

        // Unimos los errores a la misma salida para que quede todo en el mismo fichero
        pb.redirectErrorStream(true);

        return pb;
    }

    public int esperarProces(Process proces) throws InterruptedException {
        // Validamos que el proceso no sea null
        if (proces == null) {
            throw new IllegalArgumentException("El procés no pot ser null.");
        }

        // Esperamos como maximo el tiempo configurado (TEMPS_MAXIM_PROCES_SEGONS)
        boolean haFinalitzat = proces.waitFor(TEMPS_MAXIM_PROCES_SEGONS, TimeUnit.SECONDS);

        // Si ha terminado a tiempo, devolvemos su codigo de salida
        if (haFinalitzat) {
            return proces.exitValue();
        }

        // Si se pasa del tiempo limite, lo matamos a la fuerza para que no se quede colgado
        proces.destroyForcibly();
        return CODI_TIMEOUT;
    }

    public List<ResultatProces> corregirLliuraments(List<Lliurament> lliuraments)
            throws IOException, InterruptedException {
        // Validamos la lista y que ninguno de sus elementos sea null
        if (lliuraments == null) {
            throw new IllegalArgumentException("La llista de lliuraments no pot ser null.");
        }
        for (Lliurament ll : lliuraments) {
            if (ll == null) {
                throw new IllegalArgumentException("La llista de lliuraments no pot contenir valors null.");
            }
        }

        // Creamos la carpeta donde guardaremos los resultados si no existe ya
        Files.createDirectories(DIRECTORI_RESULTATS);

        // FASE 1: Lanzar todos los procesos hijos sin esperar a que acaben uno a uno
        List<ProcesEnExecucio> processosLlançats = new ArrayList<>();
        for (Lliurament ll : lliuraments) {
            Path fitxerSortida = DIRECTORI_RESULTATS.resolve(ll.getAlumneId() + ".txt");
            List<String> ordre = construirOrdreValidador(ll.getAlumneId(), ll.getRuta());
            ProcessBuilder pb = prepararProces(ordre, fitxerSortida);

            long tempsInici = System.currentTimeMillis();
            Process p = pb.start();
            processosLlançats.add(new ProcesEnExecucio(ll.getAlumneId(), p, fitxerSortida, tempsInici));
        }

        // FASE 2: Recogida. Ahora que ya estan todos arrancados en paralelo, esperamos que acaben
        List<ResultatProces> resultats = new ArrayList<>();
        for (ProcesEnExecucio pe : processosLlançats) {
            int codiRetorn = esperarProces(pe.proces);
            long duradaTotal = System.currentTimeMillis() - pe.iniciMs;
            resultats.add(new ResultatProces(pe.alumneId, codiRetorn, pe.fitxerSortida, duradaTotal));
        }

        return resultats;
    }

    public void mostrarResum(List<ResultatProces> resultats) {
        if (resultats == null || resultats.stream().anyMatch(java.util.Objects::isNull)) {
            throw new IllegalArgumentException("La llista de resultats no pot ser null ni contenir valors null.");
        }

        long correctes = resultats.stream().filter(ResultatProces::isCorrecte).count();
        long errors = resultats.size() - correctes;

        System.out.println("TOTAL_PROCESSOS=" + resultats.size());
        System.out.println("CORRECTES=" + correctes);
        System.out.println("ERRORS=" + errors);
    }

    public static class ResultatProces {
        private final String alumneId;
        private final int codiRetorn;
        private final Path fitxerSortida;
        private final long duradaMs;

        public ResultatProces(String alumneId, int codiRetorn, Path fitxerSortida, long duradaMs) {
            if (alumneId == null || alumneId.isBlank()) {
                throw new IllegalArgumentException("L'identificador de l'alumne no pot ser buit.");
            }
            if (fitxerSortida == null) {
                throw new IllegalArgumentException("El fitxer de sortida no pot ser null.");
            }
            if (duradaMs < 0L) {
                throw new IllegalArgumentException("La durada no pot ser negativa.");
            }
            this.alumneId = alumneId;
            this.codiRetorn = codiRetorn;
            this.fitxerSortida = fitxerSortida;
            this.duradaMs = duradaMs;
        }

        public String getAlumneId() {
            return alumneId;
        }

        public int getCodiRetorn() {
            return codiRetorn;
        }

        public Path getFitxerSortida() {
            return fitxerSortida;
        }

        public long getDuradaMs() {
            return duradaMs;
        }

        public boolean isCorrecte() {
            return codiRetorn == ValidadorLliurament.CODI_CORRECTE;
        }

        @Override
        public String toString() {
            return "ResultatProces{" +
                    "alumneId='" + alumneId + '\'' +
                    ", codiRetorn=" + codiRetorn +
                    ", fitxerSortida=" + fitxerSortida +
                    ", duradaMs=" + duradaMs +
                    '}';
        }
    }

    private static class ProcesEnExecucio {
        private final String alumneId;
        private final Process proces;
        private final Path fitxerSortida;
        private final long iniciMs;

        private ProcesEnExecucio(String alumneId, Process proces, Path fitxerSortida, long iniciMs) {
            if (alumneId == null || alumneId.isBlank()) {
                throw new IllegalArgumentException("L'identificador de l'alumne no pot ser buit.");
            }
            if (proces == null) {
                throw new IllegalArgumentException("El procés no pot ser null.");
            }
            if (fitxerSortida == null) {
                throw new IllegalArgumentException("El fitxer de sortida no pot ser null.");
            }
            if (iniciMs < 0L) {
                throw new IllegalArgumentException("L'instant d'inici no pot ser negatiu.");
            }
            this.alumneId = alumneId;
            this.proces = proces;
            this.fitxerSortida = fitxerSortida;
            this.iniciMs = iniciMs;
        }
    }
}
