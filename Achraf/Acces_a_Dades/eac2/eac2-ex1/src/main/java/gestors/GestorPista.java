/*
 * Gestor de pistes d'esquí
 * Gestiona les operacions CRUD sobre la taula 'pistes' de PostgreSQL
 */
package gestors;

import model.Pista;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Gestor de pistes que proporciona operacions CRUD sobre la taula 'pistes'
 * @author joan
 */
public class GestorPista {

    Connection con;

    public GestorPista(Connection con) {
        this.con = con;
    }

    /**
     * Esborra tot el contingut de la taula pista
     *
     * @throws GestorExcepcio en cas que no es pugui esborrar
     */
    public void esborraTot() throws GestorExcepcio {
        //TODO Implementa el mètode
    }

    /**
     * Afegeix una pista a la base de dades
     *
     * @param pista una pista
     * @throws GestorExcepcio en cas que no es pugui afegir
     */
    public void afegeixPista(Pista pista) throws GestorExcepcio {
        //TODO Implementa el mètode
    }

    /**
     * Obté una pista pel seu identificador
     *
     * @param id l'identificador d'una pista
     * @return la pista
     * @throws GestorExcepcio en cas que no s'hagi pogut obtenir
     */
    public Pista obtePista(String id) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("TODO Implementa el mètode");
    }

    /**
     * Esborra una pista per id
     *
     * @param id l'identificador de la pista
     * @throws GestorExcepcio en cas que no es pugui esborrar
     */
    public void esborraPista(String id) throws GestorExcepcio {
        //TODO Implementa el mètode
    }

    /**
     * Obté les pistes que utilitzen un remuntador específic
     *
     * @param remuntador el nom del remuntador
     * @return una llista amb les pistes que utilitzen aquest remuntador
     * @throws GestorExcepcio en cas que no s'hagi pogut obtenir les pistes
     */
    public List<Pista> obtePistesPerRemuntador(String remuntador) throws GestorExcepcio {
        //TODO Implementa el mètode
        throw new UnsupportedOperationException("TODO Implementa el mètode");
    }

}

