/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestors;

import javax.persistence.EntityManager;

import model.Estacio;
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
        // Buscar la estación por su ID
        Estacio e = em.find(Estacio.class, idEstacio);
        // Si no existe lanzamos error
        if (e == null) {
            throw new GestorExcepcio("L'estació no existeix");
        }
        // Buscar si ya existe una pista con el mismo id
        if (em.find(Pista.class, pista.getId()) != null) {
            throw new GestorExcepcio("La pista ja existeix");
        }
        
        try {
            // Empezamos la transacción
            em.getTransaction().begin();
            
            // Asignar la estación a la pista
            pista.setEstacio(e);
            // Añadir la pista a la lista de pistas de la estación
            e.getPistes().add(pista);
            // Recalcular el porcentaje de pistas abiertas
            e.calcularPercentatgeObertura();
            
            // Persistir la pista en la BD
            em.persist(pista);
            
            // Confirmar transacción
            em.getTransaction().commit();
        } catch (Exception ex) {
            // Si hay excepción, se hace un rollback para evitar inconsistencias
            em.getTransaction().rollback();
            throw new GestorExcepcio("Error: " + ex.getMessage());
        }
    }

    /**
     * Elimina una pista existent de la base de dades i recalcula
     * el percentatge de pistes obertes de la seva estació.
     *
     * @param idPista identificador de la pista a eliminar.
     * @throws GestorExcepcio si la pista no existeix
     */
    public void esborraPista(String idPista) throws GestorExcepcio {
        // Comprobar si la pista existe en base de datos
        Pista p = em.find(Pista.class, idPista);
        if (p == null) {
            throw new GestorExcepcio("La pista no existeix");
        }
        
        try {
            // Iniciar transacción
            em.getTransaction().begin();
            
            // Obtener la estación a la que pertenece
            Estacio e = p.getEstacio();
            // Eliminar la pista de la lista de la estación en memoria
            e.getPistes().remove(p);
            // Recalcular el porcentaje para la estación modificada
            e.calcularPercentatgeObertura();
            
            // Borrar la pista de la BD
            em.remove(p);
            
            // Finalizar transacción
            em.getTransaction().commit();
        } catch (Exception ex) {
            // Deshacer si hubo problemas
            em.getTransaction().rollback();
            throw new GestorExcepcio("Error: " + ex.getMessage());
        }
    }

    /**
     * Actualitza el gruix de neu d'una pista aplicant-hi un increment.
     *
     * @param idPista   identificador de la pista a actualitzar.
     * @param increment valor a sumar al gruix de neu actual.
     * @throws GestorExcepcio si la pista no existeix.
     */
    public void actualitzaGruixNeuPista(String idPista, int increment) throws GestorExcepcio {
        // Encontrar pista primero
        Pista p = em.find(Pista.class, idPista);
        if (p == null) {
            throw new GestorExcepcio("La pista no existeix");
        }
        
        try {
            // Comenzar cambios
            em.getTransaction().begin();
            // Modificar el grosor de la nieve usando el incremento dado
            p.setGruixNeu(p.getGruixNeu() + increment);
            // Confirmar y guardar el cambio
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new GestorExcepcio("Error: " + ex.getMessage());
        }
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
        // Buscar siempre la pista y comprobar su existencia fuera del try
        Pista p = em.find(Pista.class, idPista);
        if (p == null) {
            throw new GestorExcepcio("La pista no existeix");
        }
        
        try {
            // Empezar transacción para modificar datos
            em.getTransaction().begin();
            // Cambiar la propiedad abiert/cerrado
            p.setOberta(oberta);
            
            // Obtener su estación y volver a calcular el porcentaje de abiertas
            Estacio e = p.getEstacio();
            e.calcularPercentatgeObertura();
            
            // Terminar y volcar cambios a la base
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            throw new GestorExcepcio("Error: " + ex.getMessage());
        }
    }
}
