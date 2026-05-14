package eac3.gestors;

import eac3.dao.ItinerariRaquetesDao;
import java.util.List;

import eac3.model.ItinerariRaquetes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Component gestor per manegar els itineraris de raquetes
 *
 * @author professor
 */
@Service
public class GestorItinerariRaquetes {

    @Autowired
    private ItinerariRaquetesDao itinerariRaquetesDao;

    /**
     * Recupera els itineraris de raquetes amb un temps estimat menor o igual al
     * valor indicat.
     *
     * @param tempsMaxim temps màxim (en minuts) admès per a la consulta.
     * @return llista d'itineraris de raquetes que compleixen el criteri de
     * temps.
     */
    public List<ItinerariRaquetes> obteItinerarisRaquetesFinsTemps(int tempsMaxim) {
        return itinerariRaquetesDao.getByTempsEstimat(tempsMaxim);
    }
}
