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
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM pista")) {
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new GestorExcepcio("Error al borrar las pistas: " + ex.getMessage());
            }
        }

        /**
         * Afegeix una pista a la base de dades
         *
         * @param pista una pista
         * @throws GestorExcepcio en cas que no es pugui afegir
         */
        public void afegeixPista(Pista pista) throws GestorExcepcio {
            String sql = "INSERT INTO pista (id, nom, color, oberta, especificacions, condicions, remuntadors_acces) " +
                        "VALUES (?, ?, ?, ?, ROW(?, ?, ?)::dades_tecniques, ROW(?, ?)::estat_neu, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, pista.getId());
                ps.setString(2, pista.getNom());
                ps.setString(3, pista.getColor());
                ps.setBoolean(4, pista.isOberta());
                ps.setInt(5, pista.getLongitud());
                ps.setInt(6, pista.getDesnivell());
                ps.setInt(7, pista.getPendentMax());
                ps.setInt(8, pista.getGruix());
                ps.setString(9, pista.getQualitat());
                
                // Convertir List<String> a java.sql.Array para PostgreSQL
                java.sql.Array remuntadorsArray = con.createArrayOf("varchar", pista.getRemuntadorsAcces().toArray());
                ps.setArray(10, remuntadorsArray);
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                throw new GestorExcepcio("Error al añadir la pista: " + ex.getMessage());
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
            String sql = "SELECT id, nom, color, oberta, " +
                        "(especificacions).longitud_m AS longitud_m, " +
                        "(especificacions).desnivell_m AS desnivell_m, " +
                        "(especificacions).pendent_max_pct AS pendent_max_pct, " +
                        "(condicions).gruix_cm AS gruix_cm, " +
                        "(condicions).qualitat AS qualitat, " +
                        "remuntadors_acces " +
                        "FROM pista WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return new Pista(
                            rs.getString("id"),
                            rs.getString("nom"),
                            rs.getString("color"),
                            rs.getBoolean("oberta"),
                            rs.getInt("longitud_m"),
                            rs.getInt("desnivell_m"),
                            rs.getInt("pendent_max_pct"),
                            rs.getInt("gruix_cm"),
                            rs.getString("qualitat"),
                            Arrays.asList((String[]) rs.getArray("remuntadors_acces").getArray())
                        );
                    }
                }
            } catch (SQLException ex) {
                throw new GestorExcepcio("Error al obtener la pista: " + ex.getMessage());
            }
            throw new GestorExcepcio("No se ha encontrado la pista con id: " + id);
        }

        /**
         * Esborra una pista per id
         *
         * @param id l'identificador de la pista
         * @throws GestorExcepcio en cas que no es pugui esborrar
         */
        public void esborraPista(String id) throws GestorExcepcio {
            String sql = "DELETE FROM pista WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, id);
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new GestorExcepcio("No se ha podido borrar la pista con id: " + id);
                }
            } catch (SQLException ex) {
                throw new GestorExcepcio("Error al borrar la pista: " + ex.getMessage());
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
            List<Pista> result = new ArrayList<>();
            String sql = "SELECT id, nom, color, oberta, " +
                        "(especificacions).longitud_m AS longitud_m, " +
                        "(especificacions).desnivell_m AS desnivell_m, " +
                        "(especificacions).pendent_max_pct AS pendent_max_pct, " +
                        "(condicions).gruix_cm AS gruix_cm, " +
                        "(condicions).qualitat AS qualitat, " +
                        "remuntadors_acces " +
                        "FROM pista WHERE ? = ANY(remuntadors_acces)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, remuntador);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        result.add(new Pista(
                            rs.getString("id"),
                            rs.getString("nom"),
                            rs.getString("color"),
                            rs.getBoolean("oberta"),
                            rs.getInt("longitud_m"),
                            rs.getInt("desnivell_m"),
                            rs.getInt("pendent_max_pct"),
                            rs.getInt("gruix_cm"),
                            rs.getString("qualitat"),
                            Arrays.asList((String[]) rs.getArray("remuntadors_acces").getArray())
                        ));
                    }
                }
            } catch (SQLException ex) {
                throw new GestorExcepcio("Error al obtener pistas por remontador: " + ex.getMessage());
            }
            return result;
        }

        /**
         * Actualitza les dades d'una pista existent
         *
         * @param pista la pista amb les dades actualitzades
         * @throws GestorExcepcio en cas que no es pugui actualitzar o no existeixi
         */
        public void actualitzaPista(Pista pista) throws GestorExcepcio {
            String sql = "UPDATE pista SET nom = ?, color = ?, oberta = ?, " +
                        "especificacions = ROW(?, ?, ?)::dades_tecniques, " +
                        "condicions = ROW(?, ?)::estat_neu, " +
                        "remuntadors_acces = ? " +
                        "WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, pista.getNom());
                ps.setString(2, pista.getColor());
                ps.setBoolean(3, pista.isOberta());
                ps.setInt(4, pista.getLongitud());
                ps.setInt(5, pista.getDesnivell());
                ps.setInt(6, pista.getPendentMax());
                ps.setInt(7, pista.getGruix());
                ps.setString(8, pista.getQualitat());
                
                java.sql.Array remuntadorsArray = con.createArrayOf("varchar", pista.getRemuntadorsAcces().toArray());
                ps.setArray(9, remuntadorsArray);
                
                ps.setString(10, pista.getId());
                
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new GestorExcepcio("No se ha podido actualizar: no existe la pista con id: " + pista.getId());
                }
            } catch (SQLException ex) {
                throw new GestorExcepcio("Error al actualizar la pista: " + ex.getMessage());
            }
        }

    }


