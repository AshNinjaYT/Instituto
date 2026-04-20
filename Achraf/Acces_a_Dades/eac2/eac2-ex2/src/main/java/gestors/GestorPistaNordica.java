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
        // Preparamos la frase JPQL para buscar según el campo booleano 'trepitjada'
        String jpql = "SELECT p FROM PistaNordica p WHERE p.trepitjada = :trepitjada";
        
        // Generamos la query estructurada
        javax.persistence.TypedQuery<PistaNordica> query = em.createQuery(jpql, PistaNordica.class);
        // Insertamos el valor pasado por argumento al atributo de la sentencia
        query.setParameter("trepitjada", trepitjada);
        
        // Entregamos los objetos encontrados
        return query.getResultList();
    }
}
