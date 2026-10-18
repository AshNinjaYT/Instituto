package dam.psp.eac1;

public class MarcadorCorreccions {
    private int totalProcessats;
    private int totalCorrectes;
    private int totalErrors;
    private long tempsTotalProcessamentMs;

    // Protegemos el metodo registrar con synchronized para que solo un hilo a la vez pueda
    // modificar los contadores y evitar condiciones de carrera (race conditions).
    public synchronized void registrar(ResultatCorreccio resultat) {
        if (resultat == null) {
            throw new IllegalArgumentException("El resultat no pot ser null.");
        }

        // Seccion critica protegida: modificamos los contadores de forma segura
        totalProcessats++;
        if (resultat.isCorrecte()) {
            totalCorrectes++;
        } else {
            totalErrors++;
        }
        tempsTotalProcessamentMs += resultat.getTempsProcessamentMs();
    }

    // Ponemos synchronized tambien en los getters para asegurar visibilidad en memoria
    // y que no leamos un valor a medio escribir mientras otro hilo esta actualizando.
    public synchronized int getTotalProcessats() {
        return totalProcessats;
    }

    public synchronized int getTotalCorrectes() {
        return totalCorrectes;
    }

    public synchronized int getTotalErrors() {
        return totalErrors;
    }

    public synchronized long getTempsTotalProcessamentMs() {
        return tempsTotalProcessamentMs;
    }

    // El metodo resum debe ser synchronized para que todos los datos del informe
    // correspondan a la misma foto coherente en ese instante.
    public synchronized String resum() {
        return "MarcadorCorreccions{" +
                "totalProcessats=" + totalProcessats +
                ", totalCorrectes=" + totalCorrectes +
                ", totalErrors=" + totalErrors +
                ", tempsTotalProcessamentMs=" + tempsTotalProcessamentMs +
                '}';
    }
}
