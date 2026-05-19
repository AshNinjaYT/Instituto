/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.dao;

import eac3.model.PistaNordica;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import eac3.repository.PistaNordicaRepository;

/**
 * Component DAO per manegar els objectes de la classe PistaNordica a la base de
 * dades
 *
 * @author professor
 */
@Component
public class PistaNordicaDao {

    @Autowired
    private PistaNordicaRepository pistaNordicaRepository;

    /**
     * Consulta les pistes nòrdiques segons si la neu ès trepitjada o no
     *
     * @param trepitjada estat de trepitjada
     * @return la llista de pistes nòrdiques
     */
    public List<PistaNordica> getByTrepitjada(Boolean trepitjada) {
        return pistaNordicaRepository.getByTrepitjada(trepitjada);
    }

}
