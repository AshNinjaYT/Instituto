package gestors;

import java.util.List;
import javax.persistence.EntityManager;

import model.PistaNordica;

/**
 * Gestor CRUD per a l'entitat PistaNordica.
 */
public class GestorPistaNordica {

    private final EntityManager em;

    /**
     * Crea un gestor de pistes nòrdiques utilitzant l'EntityManager rebut.
     *
     * @param em gestor de persistència JPA que s'utilitzarà per accedir a la BD.
     */
    public GestorPistaNordica(EntityManager em) {
        this.em = em;
    }

    /**
     * Recupera les pistes nòrdiques filtrades segons si són trepitjades.
     *
     * @param trepitjada valor de l'atribut pel qual es filtra la consulta.
     * @return llista de pistes nòrdiques que compleixen el criteri indicat.
     */
    public List<PistaNordica> obtePistesNordiquesPerTrepitjada(boolean trepitjada) {
        return em.createNamedQuery("PistaNordica.findByTrepitjada", PistaNordica.class)
                .setParameter("trepitjada", trepitjada)
                .getResultList();

    }
}
