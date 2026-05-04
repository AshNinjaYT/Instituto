/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eac3.repository;

import eac3.model.PistaNordica;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository per manegar els objectes de la classe PistaNordica a la base de
 * dades
 *
 * @author professor
 */
@Repository
public interface PistaNordicaRepository extends JpaRepository<PistaNordica, String> {

    /**
     * Consulta les pistes nórdiques segons si la neu és trepitjada o no
     *
     * @param trepitjada estat de trepitjada
     * @return la llista de pistes nórdiques
     */
    @Query(value = "SELECT p FROM PistaNordica p WHERE p.trepitjada = :trepitjada")
    public List<PistaNordica> getByTrepitjada(@Param(value = "trepitjada") Boolean trepitjada);
}
