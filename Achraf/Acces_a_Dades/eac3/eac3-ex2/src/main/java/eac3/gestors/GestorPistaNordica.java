package eac3.gestors;

import eac3.dao.PistaNordicaDao;
import eac3.model.PistaNordica;
import java.util.List;

/**
 * Gestor CRUD per a l'entitat PistaNordica.
 */

//TODO Posar les anotacions de Spring i/o Lombok
public class GestorPistaNordica {

    PistaNordicaDao pistaNordicaDao;

    /**
     * Recupera les pistes nòrdiques filtrades segons si són trepitjades.
     *
     * @param trepitjada valor de l'atribut pel qual es filtra la consulta.
     * @return llista de pistes nòrdiques que compleixen el criteri indicat.
     */
    public List<PistaNordica> obtePistesNordiquesPerTrepitjada(boolean trepitjada) {
        return pistaNordicaDao.getByTrepitjada(trepitjada);
    }
}
