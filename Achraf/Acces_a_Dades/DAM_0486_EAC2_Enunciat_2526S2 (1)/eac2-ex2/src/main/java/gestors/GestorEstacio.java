package gestors;

import java.util.List;
import javax.persistence.EntityManager;

import model.Estacio;
import model.Pista;

/**
 * Gestor CRUD per a l'entitat Estacio.
 */
public class GestorEstacio {

    private final EntityManager em;

    /**
     * Crea un gestor d'estacions utilitzant l'EntityManager rebut.
     *
     * @param em gestor de persistència JPA que s'utilitzarà per accedir a la BD.
     */
    public GestorEstacio(EntityManager em) {
        this.em = em;
    }

    /**
     * Afegeix una estació a la base de dades.
     *
     * @param estacio estació a persistir.
     * @throws GestorExcepcio si ja existeix una estació amb el mateix identificador
     */
    public void afegeixEstacio(Estacio estacio) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Elimina una estació existent de la base de dades.
     *
     * @param id identificador de l'estació a eliminar.
     * @throws GestorExcepcio si l'estació no existeix.
     */
    public void esborraEstacio(String id) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Recupera totes les estacions persistides.
     *
     * @return llista amb totes les estacions disponibles.
     */
    public List<Estacio> obteTotesEstacions() {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Recupera totes les pistes associades a una estació.
     *
     * @param idEstacio identificador de l'estació de la qual es volen obtenir les pistes.
     * @return llista de pistes de l'estació indicada.
     * @throws GestorExcepcio si l'estació no existeix.
     */
    public List<Pista> obtePistes(String idEstacio) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }
}
