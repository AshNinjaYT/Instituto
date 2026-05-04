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
 * Component DAO per manegar els objectes de la classe Pista la base de dades
 *
 * @author professor
 */

//TODO Posar les anotacions de Spring i/o Lombok
public class PistaDao {

    private PistaRepository pistaRepository;

    private EstacioRepository estacioRepository;

    /**
     * Insereix una pista
     *
     * @param pista una pista
     * @throws GestorExcepcio si la pista ja existeix
     */
    public void inserir(Pista pista) throws GestorExcepcio {
        if (!pistaRepository.existsById(pista.getId())) {
            pistaRepository.save(pista);

        } else {
            throw new GestorExcepcio("La pista ja existeix");
        }
    }

    /**
     * Elimina una pista de la base de dades
     *
     * @param idPista de la pista
     * @throws GestorExcepcio si la pista no existeix
     */
    public void delete(String idPista) throws GestorExcepcio {
        Pista pista = pistaRepository.findById(idPista).orElse(null);
        if (pista != null) {
            Estacio estacio = pista.getEstacio();
            estacio.getPistes().remove(pista);
            estacioRepository.save(estacio);

            //TODO Posar les instruccions que falten
        } else {
            throw new GestorExcepcio("La pista no existeix");
        }
    }

    /**
     * Obté totes les pistes
     *
     * @return la llista de pistes
     */
    public List<Pista> getAll() {
        return pistaRepository.findAll();
    }

    /**
     * Elimina totes les pistes
     */
    public void deleteAll() {
        pistaRepository.deleteAll();
    }

    /**
     * Afegeix un gruix de neu a una pista
     *
     * @param idPista l'identificador de la pista
     * @param gruix el gruix a afegir en cm
     * @throws GestorExcepcio si la pista no existeix
     */
    public void updateGruixNeu(String idPista, int gruix) throws GestorExcepcio {
        Pista pista = pistaRepository.findById(idPista).orElse(null);
        if (pista != null) {
            pista.setGruixNeu(pista.getGruixNeu() + gruix);
            pistaRepository.save(pista);
        } else {
            throw new GestorExcepcio("La pista no existeix");
        }

    }

    /**
     * Canvia l'estat d'obertura d'una pista i recalcula el percentatge de
     * pistes obertes de l'estació
     *
     * @param idPista l'identificador de la pista
     * @param obertura l'estat d'obertura
     * @throws GestorExcepcio si la pista no existeix
     */
    @Transactional
    public void setObertura(String idPista, Boolean obertura) throws GestorExcepcio {
        Pista pista = pistaRepository.findById(idPista).orElse(null);
        if (pista != null) {
            Estacio estacio = pista.getEstacio();
            pista.setOberta(obertura);
            estacio.calcularPercentatgeObertura();

            pistaRepository.save(pista);
            estacioRepository.save(estacio);
        } else {
            throw new GestorExcepcio("La pista no existeix");
        }

    }

    /**
     * Elimina una pista de la base de dades
     *
     * @param idPista de la pista
     * @throws ManagerException si la pista no existeix
     */
    @Transactional
    public void esborra(String idPista) throws GestorExcepcio {
        Pista pista = pistaRepository.findById(idPista).orElse(null);
        if (pista != null) {
            pista.getEstacio().getPistes().remove(pista);
            estacioRepository.save(pista.getEstacio());
            //TODO Posar les instruccions que falten

        } else {
            throw new GestorExcepcio("La pista no existeix");
        }
    }

    public Pista obtePista(String idPista) throws GestorExcepcio {

        return pistaRepository.findById(idPista).orElseThrow(() -> new GestorExcepcio("Pista " + idPista + " no trobada."));

    }

}
