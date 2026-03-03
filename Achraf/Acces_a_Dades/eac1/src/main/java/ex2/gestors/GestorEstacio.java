/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.gestors;

import ex2.model.Estacio;

/**
 *
 * @author joan
 */
public class GestorEstacio {

    /**
     * Retorna un objecte de classe Estacio a partir d'un fitxer XML
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut llegir el
     * fitxer
     */
    public Estacio llegirFitxerXML(String nomFitxer) throws GestorEstacioException {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Crea un fitxer XML a partir d'un objecte de la classe Estacio
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param estacio objecte de la classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut gravar el
     * fitxer
     */
    public void gravarFitxerXML(String nomFitxer, Estacio estacio) throws GestorEstacioException {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Retorna un objecte de classe Estacio a partir d'un fitxer JSON
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut llegir el
     * fitxer
     */
    public Estacio llegirFitxerJSON(String nomFitxer) throws GestorEstacioException {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Crea un fitxer JSON a partir d'un objecte de la classe Estacio
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param estacio objecte de la classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut escriure el
     * fitxer
     */
    public void gravarFitxerJSON(String nomFitxer, Estacio estacio) throws GestorEstacioException {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");
    }

}
