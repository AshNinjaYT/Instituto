/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.dao;

import eac3.gestors.GestorExcepcio;
import eac3.model.Estacio;
import eac3.model.PistaAlpina;
import eac3.repository.EstacioRepository;
import eac3.repository.PistaAlpinaRepository;
import java.util.List;

/**
 * Component DAO per manegar els objectes de la classe PistaAlpina a la base de
 * dades
 *
 * @author professor
 */

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PistaAlpinaDao {

    private final PistaAlpinaRepository pistaAlpinaRepository;

    private final EstacioRepository estacioRepository;

    /**
     * Consulta totes les pistes alpines
     *
     * @return la llista de pistes alpines
     */
    public List<PistaAlpina> getAll() {
        return pistaAlpinaRepository.findAll();
    }

    /**
     * Consulta les pistes alpines d'una estació que tenen un color determinat
     *
     * @param idEstacio
     * @param color
     * @return
     * @throws GestorExcepcio
     */
    public List<PistaAlpina> getByColor(String idEstacio, String color) throws GestorExcepcio {

        List<PistaAlpina> totesPistesAlpinesColor = pistaAlpinaRepository.getByColor(color);
        Estacio estacio = estacioRepository.findById(idEstacio).orElse(null);

        if (estacio == null) {
            throw new GestorExcepcio("Estacio " + idEstacio + " no existeix.");
        }

        return totesPistesAlpinesColor
                .stream()
                .filter(p -> p.getEstacio().getId().equals(estacio.getId()))
                .toList();

    }

}
