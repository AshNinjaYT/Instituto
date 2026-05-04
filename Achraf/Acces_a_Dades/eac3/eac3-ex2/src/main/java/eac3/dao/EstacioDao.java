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

/**
 * Component DAO per manegar els objectes de la classe Estacio a la base de
 * dades
 *
 * @author professor
 */

//TODO Posar les anotacions de Spring i/o Lombok
public class EstacioDao {
    
    private EstacioRepository estacioRepository;

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
        //TODO Posar les instruccions que falten
        if () {
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
            for (Pista p : estacio.getPistes()) {
                //TODO Posar les instruccions que falten
            }
            //TODO Posar les instruccions que falten
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
        Estacio estacio = estacioRepository.findById(idEstacio).orElse(null);

        if (estacio == null) {
            throw new GestorExcepcio("L'estació amb id " + idEstacio + " no existeix");
        }

        if (estacio.getPistes().contains(pista)) {
            throw new GestorExcepcio("La pista amb id " + pista.getId() + " ja és a l'estació" + idEstacio);
        }

        pista.setEstacio(estacio);
        estacio.getPistes().add(pista);
        estacio.calcularPercentatgeObertura();

        //TODO Posar les instruccions que falten
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

        estacio.getPistes().remove(pista);
        estacio.calcularPercentatgeObertura();

        //TODO Posar les instruccions que falten

    }

}
