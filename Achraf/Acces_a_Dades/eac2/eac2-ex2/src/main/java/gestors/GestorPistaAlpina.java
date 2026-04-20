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
        // Verificamos si la estación existe primero
        if (em.find(model.Estacio.class, idEstacio) == null) {
            throw new GestorExcepcio("L'estació no existeix");
        }
        
        // Creamos la consulta en lenguaje JPQL usando parámetros dinámicos
        String jpql = "SELECT p FROM PistaAlpina p WHERE p.estacio.id = :idEstacio AND p.color = :color";
        javax.persistence.TypedQuery<PistaAlpina> query = em.createQuery(jpql, PistaAlpina.class);
        
        // Sustituimos los parámetros por las variables pasadas a la función
        query.setParameter("idEstacio", idEstacio);
        query.setParameter("color", color);
        
        // Retornamos los resultados en forma de lista
        return query.getResultList();
    }
}
