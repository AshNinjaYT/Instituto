package ex2.gestors;

/**
 * Excepció personalitzada per als errors en la gestió de les estacions
 * 
 * @author professor (Modificat)
 */
public class GestorEstacioException extends Exception {

    /**
     * Crea una instància de GestorEstacioException amb un missatge d'error
     * especificat
     *
     * @param missatge el missatge d'error
     */
    public GestorEstacioException(String missatge) {
        super(missatge);
    }

    /**
     * Crea una instància de GestorEstacioException amb un missatge d'error
     * especificat i l'origen de l'error
     *
     * @param missatge el missatge d'error
     * @param causa l'excepció origen de l'error
     */
    public GestorEstacioException(String missatge, Throwable causa) {
        super(missatge, causa);
    }
}
