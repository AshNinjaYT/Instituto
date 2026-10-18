/*
 * Gestor de pistes d'esquí
 * Gestiona les operacions CRUD sobre la taula 'pistes' de PostgreSQL
 */
package gestors;

import model.Pista;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
        try {
            con.createStatement().executeUpdate("DELETE FROM pista");
        } catch (SQLException ex) {
            Logger.getLogger(GestorPista.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestorExcepcio("No s'ha pogut esborrar la taula pista");
        }
    }

    /**
     * Afegeix una pista a la base de dades
     *
     * @param pista una pista
     * @throws GestorExcepcio en cas que no es pugui afegir
     */
    public void afegeixPista(Pista pista) throws GestorExcepcio {
        try {
            // INSERT amb els tipus compostos: (longitud_m, desnivell_m, pendent_max_pct) i (gruix_cm, qualitat)
            // I l'array: remuntadors_acces
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO pista VALUES (?, ?, ?, ?, (?, ?, ?), (?, ?), ?)"
            );
            int i = 1;
            statement.setString(i++, pista.getId());
            statement.setString(i++, pista.getNom());
            statement.setString(i++, pista.getColor());
            statement.setBoolean(i++, pista.isOberta());

            // Dades tècniques (tipus compost dades_tecniques)
            statement.setObject(i++, pista.getLongitud());
            statement.setObject(i++, pista.getDesnivell());
            statement.setObject(i++, pista.getPendentMax());

            // Condicions de neu (tipus compost estat_neu)
            statement.setObject(i++, pista.getGruix());
            statement.setString(i++, pista.getQualitat());

            // Array de remuntadors
            statement.setArray(i++, con.createArrayOf("varchar", pista.getRemuntadorsAcces().toArray()));

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(GestorPista.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestorExcepcio("No s'ha pogut afegir la pista");
        }
    }

    /**
     * Obté una pista pel seu identificador
     *
     * @param id l'identificador d'una pista
     * @return la pista
     * @throws GestorExcepcio en cas que no s'hagi pogut obtenir
     */
    public Pista obtePista(String id) throws GestorExcepcio {
        try {
            PreparedStatement statement = con.prepareStatement(
                    "SELECT nom, color, oberta, "
                    + "(especificacions).longitud_m, (especificacions).desnivell_m, (especificacions).pendent_max_pct, "
                    + "(condicions).gruix_cm, (condicions).qualitat, "
                    + "remuntadors_acces "
                    + "FROM pista "
                    + "WHERE id = ?"
            );
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                int i = 1;
                Pista pista = new Pista(
                        id,
                        rs.getString(i++),           // nom
                        rs.getString(i++),           // color
                        rs.getBoolean(i++),          // oberta
                        rs.getInt(i++),    // longitud_m
                        rs.getInt(i++),    // desnivell_m
                        rs.getInt(i++),    // pendent_max_pct
                        rs.getInt(i++),    // gruix_cm
                        rs.getString(i++),           // qualitat
                        rs.getArray(i) != null ? Arrays.asList((String[]) rs.getArray(i).getArray()) : new ArrayList<>()  // remuntadors_acces
                );
                return pista;
            } else {
                throw new GestorExcepcio("La pista no existeix a la BD");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPista.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestorExcepcio("No s'ha pogut obtenir la pista");
        }
    }

    /**
     * Esborra una pista per id
     *
     * @param id l'identificador de la pista
     * @throws GestorExcepcio en cas que no es pugui esborrar
     */
    public void esborraPista(String id) throws GestorExcepcio {
        try {
            PreparedStatement statement = con.prepareStatement(
                    "DELETE FROM pista WHERE id = ?"
            );
            statement.setString(1, id);
            if (statement.executeUpdate() < 1) {
                throw new GestorExcepcio("La pista no existeix");
            }
        } catch (SQLException ex) {
            Logger.getLogger(GestorPista.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestorExcepcio("No s'ha pogut eliminar la pista");
        }
    }

    /**
     * Obté les pistes que utilitzen un remuntador específic
     *
     * @param remuntador el nom del remuntador
     * @return una llista amb les pistes que utilitzen aquest remuntador
     * @throws GestorExcepcio en cas que no s'hagi pogut obtenir les pistes
     */
    public List<Pista> obtePistesPerRemuntador(String remuntador) throws GestorExcepcio {
        try {
            List<Pista> listPistes = new ArrayList<>();

            PreparedStatement statement = con.prepareStatement(
                    "SELECT id, nom, color, oberta, "
                    + "(especificacions).longitud_m, (especificacions).desnivell_m, (especificacions).pendent_max_pct, "
                    + "(condicions).gruix_cm, (condicions).qualitat, "
                    + "remuntadors_acces "
                    + "FROM pista "
                    + "WHERE ? = ANY(remuntadors_acces)"
            );
            statement.setString(1, remuntador);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int i = 1;
                listPistes.add(new Pista(
                        rs.getString(i++),           // id
                        rs.getString(i++),           // nom
                        rs.getString(i++),           // color
                        rs.getBoolean(i++),          // oberta
                        rs.getInt(i++),    // longitud_m
                        rs.getInt(i++),    // desnivell_m
                        rs.getInt(i++),    // pendent_max_pct
                        rs.getInt(i++),    // gruix_cm
                        rs.getString(i++),           // qualitat
                        rs.getArray(i) != null ? Arrays.asList((String[]) rs.getArray(i).getArray()) : new ArrayList<>()  // remuntadors_acces
                ));
            }

            return listPistes;
        } catch (SQLException ex) {
            Logger.getLogger(GestorPista.class.getName()).log(Level.SEVERE, null, ex);
            throw new GestorExcepcio("No s'han pogut obtenir les pistes pel remuntador");
        }
    }

}

