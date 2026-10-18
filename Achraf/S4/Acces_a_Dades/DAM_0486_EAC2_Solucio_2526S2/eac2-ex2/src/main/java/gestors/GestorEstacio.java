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

        Estacio existent = em.find(Estacio.class, estacio.getId());
        if (existent != null) {
            throw new GestorExcepcio("L'estació ja existeix");
        }
        em.getTransaction().begin();
        em.merge(estacio);
        em.getTransaction().commit();
    }

    /**
     * Elimina una estació existent de la base de dades.
     *
     * @param id identificador de l'estació a eliminar.
     * @throws GestorExcepcio si l'estació no existeix.
     */
    public void esborraEstacio(String id) throws GestorExcepcio {
        Estacio existent = em.find(Estacio.class, id);
        if (existent == null) {
            throw new GestorExcepcio("L'estació no existeix");
        }
        em.getTransaction().begin();
        for (Pista p: existent.getPistes())
            em.remove(p);
        existent.getPistes().clear();
        em.remove(existent);
        em.getTransaction().commit();
    }

    /**
     * Recupera totes les estacions persistides.
     *
     * @return llista amb totes les estacions disponibles.
     */
    public List<Estacio> obteTotesEstacions() {
        return em.createNamedQuery("Estacio.findAll", Estacio.class)
                .getResultList();
    }


}
