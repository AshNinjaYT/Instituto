package gestors;

import java.util.List;
import javax.persistence.EntityManager;
import model.PistaAlpina;

/**
 * Gestor CRUD per a l'entitat PistaAlpina.
 */
public class GestorPistaAlpina {

    private final EntityManager em;

    /**
     * Crea un gestor de pistes alpines utilitzant l'EntityManager rebut.
     *
     * @param em gestor de persistència JPA que s'utilitzarà per accedir a la BD.
     */
    public GestorPistaAlpina(EntityManager em) {
        this.em = em;
    }

    /**
     * Obté les pistes alpines d'una estació filtrades per color.
     *
     * @param idEstacio identificador de l'estació.
     * @param color color de pista pel qual es vol filtrar.
     * @return llista de pistes alpines de l'estació amb el color indicat.
     * @throws GestorExcepcio si l'estació no existeix.
     */
    public List<PistaAlpina> obtePistesAlpinesEstacioPerColor(String idEstacio, String color) throws GestorExcepcio {
            //TODO Implementa el mètode
            throw new UnsupportedOperationException("Mètode no implementat");
    }
}
