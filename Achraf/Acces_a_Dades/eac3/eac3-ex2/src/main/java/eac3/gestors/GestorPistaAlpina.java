package eac3.gestors;

import eac3.dao.PistaAlpinaDao;
import eac3.model.PistaAlpina;
import java.util.List;

//TODO Posar les anotacions de Spring i/o Lombok
public class GestorPistaAlpina {

    PistaAlpinaDao pistaAlpinaDao;

    /**
     * Obté les pistes alpines d'una estació filtrades per color.
     *
     * @param idEstacio identificador de l'estació.
     * @param color color de pista pel qual es vol filtrar.
     * @return llista de pistes alpines de l'estació amb el color indicat.
     * @throws GestorExcepcio si l'estació no existeix.
     */
    public List<PistaAlpina> obtePistesAlpinesEstacioPerColor(String idEstacio, String color) throws GestorExcepcio {
        return pistaAlpinaDao.getByColor(idEstacio, color);
    }
}
