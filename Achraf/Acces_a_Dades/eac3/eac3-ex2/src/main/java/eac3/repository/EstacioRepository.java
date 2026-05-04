/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package eac3.repository;

import eac3.model.Estacio;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositori per manegar els objectes de la classe Estacio a la base de dades
 *
 * @author professor
 */
//TODO Posar les anotacions de Spring i/o Lombok
public interface EstacioRepository extends JpaRepository<Estacio, String> {

}
