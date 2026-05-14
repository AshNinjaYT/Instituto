/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.dao;

import eac3.gestors.GestorExcepcio;
import eac3.model.Estacio;
import eac3.model.Pista;
import eac3.repository.EstacioRepository;
import eac3.repository.PistaRepository;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Component DAO per manegar els objectes de la classe Estacio a la base de
 * dades
 *
 * @author professor
 */
@Repository
public class EstacioDao {

    @Autowired
    private EstacioRepository estacioRepository;

    @Autowired
    private PistaRepository pistaRepository;

    /**
     * Elimina totes les estacions de la base de dades
     */
    public void deleteAll() {
        estacioRepository.deleteAll();
    }

    /**
     * Insereix una nova estació a la base de dades
     *
     * @param estacio l'estació a afegir
     * @throws GestorExcepcio si l'estació ja existeix
     */
    public void insert(Estacio estacio) throws GestorExcepcio {
        if (!estacioRepository.existsById(estacio.getId())) {
            estacioRepository.save(estacio);
        } else {
            throw new GestorExcepcio("L'estació ja existeix");
        }
    }

    /**
     * Elimina una estació de la base de dades i totes les seves pistes
     *
     * @param idEstacio l'id de l'estació
     * @throws GestorExcepcio si l'estació no existeix
     */
    public void delete(String idEstacio) throws GestorExcepcio {
        Estacio estacio = estacioRepository.findById(idEstacio).orElse(null);
        if (estacio != null) {
            estacioRepository.delete(estacio);
        } else {
            throw new GestorExcepcio("L'estació no existeix");
        }
    }

    /**
     * Obtenir totes les estacions
     *
     * @return la llista amb totes les estacions
     */
    public List<Estacio> getAll() {
        return estacioRepository.findAll();
    }

    /**
     * Obté l'estació a partir de l'identificador
     *
     * @param idEstacio l'identificador
     * @return l'objecte estació
     * @throws GestorExcepcio
     */
    public Estacio findById(String idEstacio) throws GestorExcepcio {
        return estacioRepository.findById(idEstacio).orElseThrow(() -> new GestorExcepcio(""));
    }

    /**
     * Afegeix una pista a una estació
     *
     * @param idEstacio el codi de l'estacio
     * @param pista la pista a afegir
     * @throws GestorExcepcio si la pista o l'estació no existeixen, o la pista
     * ja és a l'estació
     */
    @Transactional
    public void addPista(String idEstacio, Pista pista) throws GestorExcepcio {
        // Validación de existencia de la entidad base
        Estacio estacio = estacioRepository.findById(idEstacio).orElse(null);
        if (estacio == null) {
            throw new GestorExcepcio("No existe la estación con ID: " + idEstacio);
        }

        if (estacio.getPistes().contains(pista)) {
            throw new GestorExcepcio("La pista amb id " + pista.getId() + " ja és a l'estació" + idEstacio);
        }

        // Sincronización de la relación y actualización de estadísticas
        pistaRepository.save(pista);
        estacio.getPistes().add(pista);
        estacio.calcularPercentatgeObertura();

        estacioRepository.save(estacio);
    }

    /**
     * Treu una pista a una estacio
     *
     * @param idEstacio l'id de l'estacio
     * @param idPista l'id de la pista
     * @throws GestorExcepcio si la pista o estació no existeixen, o la pista no
     * és a l'estació
     */
    public void removePista(String idEstacio, String idPista) throws GestorExcepcio {
        Estacio estacio = estacioRepository.findById(idEstacio).orElse(null);
        Pista pista = pistaRepository.findById(idPista).orElse(null);

        if (estacio == null) {
            throw new GestorExcepcio("L'estació amb codi " + idEstacio + " no existeix");
        }
        if (pista == null) {
            throw new GestorExcepcio("La pista amb codi " + idPista + " no existeix");
        }
        if (!estacio.getPistes().remove(pista)) {
            throw new GestorExcepcio("La pista amb codi " + idPista + " no és a l'estació " + idEstacio);
        }

        // Actualización de consistencia tras eliminación
        estacio.getPistes().remove(pista);
        estacio.calcularPercentatgeObertura();

        pistaRepository.delete(pista);
        estacioRepository.save(estacio);

    }

}
