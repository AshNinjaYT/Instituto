package gestors;

import java.util.List;
import javax.persistence.EntityManager;

import model.ItinerariRaquetes;

/**
 * Gestor CRUD per a l'entitat ItinerariRaquetes.
 */
public class GestorItinerariRaquetes {

    private final EntityManager em;

    /**
     * Crea un gestor d'itineraris de raquetes utilitzant l'EntityManager rebut.
     *
     * @param em gestor de persistència JPA que s'utilitzarà per accedir a la BD.
     */
    public GestorItinerariRaquetes(EntityManager em) {
        this.em = em;
    }

    /**
     * Recupera els itineraris de raquetes amb un temps estimat menor o igual
     * al valor indicat.
     *
     * @param tempsMaxim temps màxim (en minuts) admès per a la consulta.
     * @return llista d'itineraris de raquetes que compleixen el criteri de temps.
     */
    public List<ItinerariRaquetes> obteItinerarisRaquetesFinsTemps(int tempsMaxim) {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("Mètode no implementat");
    }
}
