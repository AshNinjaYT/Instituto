/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eac3.repository;

import eac3.model.Pista;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository per manegar els objectes de la classe Track que son a la base de
 * dades
 *
 * @author professor
 */
import org.springframework.stereotype.Repository;

@Repository
public interface PistaRepository extends JpaRepository<Pista, String> {

}
