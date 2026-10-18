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
        // Comprobar si la estación ya existe en la base de datos por su ID
        if (em.find(Estacio.class, estacio.getId()) != null) {
            throw new GestorExcepcio("L'estació ja existeix");
        }
        
        try {
            // Iniciar la transacción
            em.getTransaction().begin();
            // Guardar la estación
            em.persist(estacio);
            // Confirmar los cambios
            em.getTransaction().commit();
        } catch (Exception e) {
            // Deshacer los cambios en caso de que ocurra algún error
            em.getTransaction().rollback();
            throw new GestorExcepcio("Error: " + e.getMessage());
        }
    }

    /**
     * Elimina una estació existent de la base de dades.
     *
     * @param id identificador de l'estació a eliminar.
     * @throws GestorExcepcio si l'estació no existeix.
     */
    public void esborraEstacio(String id) throws GestorExcepcio {
        // Buscar la estación por su ID
        Estacio e = em.find(Estacio.class, id);
        
        // Si no se encuentra, lanzar la excepción
        if (e == null) {
            throw new GestorExcepcio("L'estació no existeix");
        }
        
        try {
            // Iniciar la transacción
            em.getTransaction().begin();
            // Eliminar la estación de la base de datos
            em.remove(e);
            // Confirmar los cambios
            em.getTransaction().commit();
        } catch (Exception ex) {
            // Deshacer los cambios si falla
            em.getTransaction().rollback();
            throw new GestorExcepcio("Error: " + ex.getMessage());
        }
    }

    /**
     * Recupera totes les estacions persistides.
     *
     * @return llista amb totes les estacions disponibles.
     */
    public List<Estacio> obteTotesEstacions() {
        // Ejecutar query para obtener todas las estaciones (SELECT sobre la entidad Estacio)
        return em.createQuery("SELECT e FROM Estacio e", Estacio.class).getResultList();
    }

    /**
     * Recupera totes les pistes associades a una estació.
     *
     * @param idEstacio identificador de l'estació de la qual es volen obtenir les pistes.
     * @return llista de pistes de l'estació indicada.
     * @throws GestorExcepcio si l'estació no existeix.
     */
    public List<Pista> obtePistes(String idEstacio) throws GestorExcepcio {
        // Buscar la estación
        Estacio e = em.find(Estacio.class, idEstacio);
        // Verificar si la estación devuelta es nula
        if (e == null) {
            throw new GestorExcepcio("L'estació no existeix");
        }
        // Devolver la lista de pistas asociadas
        return e.getPistes();
    }
}
