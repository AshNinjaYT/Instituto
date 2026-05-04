package eac3.gestors;

import eac3.dao.EstacioDao;
import eac3.dao.PistaDao;
import eac3.model.Estacio;
import java.util.List;

/**
 * Gestor CRUD per a l'entitat Estacio.
 */

//TODO Posar les anotacions de Spring i/o Lombok
public class GestorEstacio {

    private EstacioDao estacioDao;

    private PistaDao pistaDao;

    /**
     * Afegeix una estació a la base de dades.
     *
     * @param estacio estació a persistir.
     * @throws GestorExcepcio si ja existeix una estació amb el mateix
     * identificador
     */
    public void afegeixEstacio(Estacio estacio) throws GestorExcepcio {
        estacioDao.insert(estacio);
    }

    /**
     * Elimina una estació existent de la base de dades.
     *
     * @param idEstacio identificador de l'estació a eliminar.
     * @throws GestorExcepcio si l'estació no existeix.
     */
    public void esborraEstacio(String idEstacio) throws GestorExcepcio {
        estacioDao.delete(idEstacio);
    }

    /**
     * Elimina totes les estacions de la base de dades.
     *
     */
    public void esborraTotesEstacions() {
        estacioDao.deleteAll();
    }

    /**
     * Recupera totes les estacions persistides.
     *
     * @return llista amb totes les estacions disponibles.
     */
    public List<Estacio> obteTotesEstacions() {
        return estacioDao.getAll();
    }

}
