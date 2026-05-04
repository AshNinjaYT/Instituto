/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.dao.EstacioDao;
import eac3.dao.PistaDao;
import eac3.model.Pista;

/**
 *
 * @author professor
 */

//TODO Posar les anotacions de Spring i/o Lombok
public class GestorPista {

    PistaDao pistaDao;

    EstacioDao estacioDao;

    /**
     * Afegeix una pista a una estació existent.
     *
     * @param idEstacio identificador de l'estació on s'afegirà la pista.
     * @param pista pista a afegir.
     * @throws GestorExcepcio si l'estació no existeix o si la pista ja
     * existeix.
     */
    public void afegeixPista(String idEstacio, Pista pista) throws GestorExcepcio {
        estacioDao.addPista(idEstacio, pista);
    }

    /**
     * Elimina totes les pistes de la base de dades
     *
     */
    public void esborraTotesPistes() {
        pistaDao.deleteAll();
    }

    /**
     * Actualitza el gruix de neu d'una pista aplicant-hi un increment.
     *
     * @param idPista identificador de la pista a actualitzar.
     * @param increment valor a sumar al gruix de neu actual.
     * @throws GestorExcepcio si la pista no existeix.
     */
    public void actualitzaGruixNeuPista(String idPista, int increment) throws GestorExcepcio {
        pistaDao.updateGruixNeu(idPista, increment);
    }

    /**
     * Actualitza l'estat d'obertura d'una pista i recalcula el percentatge de
     * pistes obertes de l'estació a la qual pertany.
     *
     * @param idPista identificador de la pista a actualitzar.
     * @param oberta nou valor de l'estat d'obertura.
     * @throws GestorExcepcio si la pista no existeix.
     */
    public void actualitzaObertaPista(String idPista, boolean oberta) throws GestorExcepcio {
        pistaDao.setObertura(idPista, oberta);
    }

    /**
     * Elimina una pista existent de la base de dades i recalcula el percentatge
     * de pistes obertes de la seva estació.
     *
     * @param idPista identificador de la pista a eliminar.
     * @throws GestorExcepcio si la pista no existeix
     */
    public void esborraPista(String idPista) throws GestorExcepcio {
        pistaDao.esborra(idPista);
    }

    /**
     * Obte una pista
     *
     * @param idPista l'identificador de la pista
     * @return la pista amb l'identificador
     * @throws GestorExcepcio si la pista no existeix
     */
    public Pista obtePista(String idPista) throws GestorExcepcio {
        return pistaDao.obtePista(idPista);
    }

}
