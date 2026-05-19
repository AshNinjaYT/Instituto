/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestors;

import javax.persistence.EntityManager;

import model.Estacio;
import model.Pista;

import java.util.List;

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
        Estacio estacio = em.find(Estacio.class, idEstacio);
        if (estacio == null) {
            throw new GestorExcepcio("L'estacio no existeix");
        }

        Pista existent = em.find(Pista.class, pista.getId());
        if (existent != null) {
            throw new GestorExcepcio("La pista ja existeix");
        }

        em.getTransaction().begin();
        estacio.getPistes().add(pista);
        estacio.calcularPercentatgeObertura();
        em.merge(estacio);
        em.getTransaction().commit();
    }

    /**
     * Elimina una pista existent de la base de dades i recalcula
     * el percentatge de pistes obertes de la seva estació.
     *
     * @param idPista identificador de la pista a eliminar.
     * @throws GestorExcepcio si la pista no existeix
     */
    public void esborraPista(String idPista) throws GestorExcepcio {

        Pista pista = em.find(Pista.class, idPista);
        if (pista == null)
            throw new GestorExcepcio("La pista no existeix");

        em.getTransaction().begin();
        Estacio estacio = pista.getEstacio();
        estacio.getPistes().remove(pista);
        em.merge(estacio);
        em.remove(pista);
        estacio.calcularPercentatgeObertura();
        em.getTransaction().commit();
    }

    /**
     * Actualitza el gruix de neu d'una pista aplicant-hi un increment.
     *
     * @param idPista   identificador de la pista a actualitzar.
     * @param increment valor a sumar al gruix de neu actual.
     * @throws GestorExcepcio si la pista no existeix.
     */
    public void actualitzaGruixNeuPista(String idPista, int increment) throws GestorExcepcio {
        Pista pista = em.find(Pista.class, idPista);
        if (pista == null) {
            throw new GestorExcepcio("La pista no existeix");
        }

        em.getTransaction().begin();
        pista.setGruixNeu(pista.getGruixNeu() + increment);
        em.getTransaction().commit();
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
        Pista pista = em.find(Pista.class, idPista);
        if (pista == null) {
            throw new GestorExcepcio("La pista no existeix");
        }

        Estacio estacio = pista.getEstacio();

        em.getTransaction().begin();
        pista.setOberta(oberta);
        estacio.calcularPercentatgeObertura();
        em.merge(pista);
        em.merge(estacio);
        em.getTransaction().commit();

    }
}
