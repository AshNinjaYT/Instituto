/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.dao;

import eac3.model.ItinerariRaquetes;
import java.util.List;
import eac3.repository.ItinerariRaquetesRepository;

/**
 * Component DAO per manegar els objectes de la classe ItinerariRaquetes a la
 * base de dades
 *
 * @author professor
 */

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItinerariRaquetesDao {
    
    private final ItinerariRaquetesRepository itinerariRaquetesRepository;

    /**
     * Consulta els itineraris de raquetes que tenen un temps estimat com a
     * màxim
     *
     * @param tempsEstimat el temps màxim
     * @return la llista d'itineraris de raquetes
     */
    public List<ItinerariRaquetes> getByTempsEstimat(int tempsEstimat) {
        return itinerariRaquetesRepository.getByTempsEstimat(tempsEstimat);
    }
}
