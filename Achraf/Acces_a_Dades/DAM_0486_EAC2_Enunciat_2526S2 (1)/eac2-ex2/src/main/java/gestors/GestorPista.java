/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestors;

import javax.persistence.EntityManager;

import model.Pista;

/**
 *
 * @author joan
 */
public class GestorPista {

    private final EntityManager em;

    /**
     * Crea un gestor de pistes utilitzant l'EntityManager rebut.
     *
     * @param em gestor de persistència JPA que s'utilitzarà per accedir a la BD.
     */
    public GestorPista(EntityManager em) {
        this.em = em;
    }

    /**
     * Afegeix una pista a una estació existent.
     *
     * @param idEstacio identificador de l'estació on s'afegirà la pista.
     * @param pista     pista a afegir.
     * @throws GestorExcepcio si l'estació no existeix o si la pista ja existeix.
     */
    public void afegeixPista(String idEstacio, Pista pista) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Elimina una pista existent de la base de dades i recalcula
     * el percentatge de pistes obertes de la seva estació.
     *
     * @param idPista identificador de la pista a eliminar.
     * @throws GestorExcepcio si la pista no existeix
     */
    public void esborraPista(String idPista) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Actualitza el gruix de neu d'una pista aplicant-hi un increment.
     *
     * @param idPista   identificador de la pista a actualitzar.
     * @param increment valor a sumar al gruix de neu actual.
     * @throws GestorExcepcio si la pista no existeix.
     */
    public void actualitzaGruixNeuPista(String idPista, int increment) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Actualitza l'estat d'obertura d'una pista i recalcula el percentatge
     * de pistes obertes de l'estació a la qual pertany.
     *
     * @param idPista identificador de la pista a actualitzar.
     * @param oberta  nou valor de l'estat d'obertura.
     * @throws GestorExcepcio si la pista no existeix.
     */
    public void actualitzaObertaPista(String idPista, boolean oberta) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }
}
