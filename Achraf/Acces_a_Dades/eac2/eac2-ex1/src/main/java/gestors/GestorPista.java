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
            // Preparamos la orden SQL para vaciar la tabla por completo
            try (PreparedStatement ps = con.prepareStatement("DELETE FROM pista")) {
                // Ejecutamos la consulta de borrado
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
            // Preparamos la sentencia SQL de inserción utilizando tipos complejos (ROW) para PostgreSQL
            String sql = "INSERT INTO pista (id, nom, color, oberta, especificacions, condicions, remuntadors_acces) " +
                        "VALUES (?, ?, ?, ?, ROW(?, ?, ?)::dades_tecniques, ROW(?, ?)::estat_neu, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // Asignamos todos los campos básicos al PreparedStatement
                ps.setString(1, pista.getId());
                ps.setString(2, pista.getNom());
                ps.setString(3, pista.getColor());
                ps.setBoolean(4, pista.isOberta());
                ps.setInt(5, pista.getLongitud());
                ps.setInt(6, pista.getDesnivell());
                ps.setInt(7, pista.getPendentMax());
                ps.setInt(8, pista.getGruix());
                ps.setString(9, pista.getQualitat());
                
                // Convertimos la lista Java de remontes a un Array SQL nativo de PostgreSQL
                java.sql.Array remuntadorsArray = con.createArrayOf("varchar", pista.getRemuntadorsAcces().toArray());
                ps.setArray(10, remuntadorsArray);
                
                // Ejecutamos la inserción en la base de datos
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
            // Creamos la select extrayendo los datos de los tipos estructurados (especificacions y condicions)
            String sql = "SELECT id, nom, color, oberta, " +
                        "(especificacions).longitud_m AS longitud_m, " +
                        "(especificacions).desnivell_m AS desnivell_m, " +
                        "(especificacions).pendent_max_pct AS pendent_max_pct, " +
                        "(condicions).gruix_cm AS gruix_cm, " +
                        "(condicions).qualitat AS qualitat, " +
                        "remuntadors_acces " +
                        "FROM pista WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // Introducimos el ID buscado
                ps.setString(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    // Si encontramos un resultado, creamos el objeto Pista y lo devolvemos
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
            // Si llegamos hasta aquí, significa que el ResultSet estaba vacío
            throw new GestorExcepcio("No se ha encontrado la pista con id: " + id);
        }

        /**
         * Esborra una pista per id
         *
         * @param id l'identificador de la pista
         * @throws GestorExcepcio en cas que no es pugui esborrar
         */
        public void esborraPista(String id) throws GestorExcepcio {
            // Construimos el comando DELETE condicional
            String sql = "DELETE FROM pista WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, id);
                
                // Ejecutamos y guardamos el número de filas afectadas
                int rows = ps.executeUpdate();
                
                // Si es 0, es que la pista no existía previamente
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
            // Inicializamos la lista de resultados donde iremos guardando pistas encontradas
            List<Pista> result = new ArrayList<>();
            
            // Usamos ANY(array) propio de Postgres para filtrar si el remontador forma parte del array
            String sql = "SELECT id, nom, color, oberta, " +
                        "(especificacions).longitud_m AS longitud_m, " +
                        "(especificacions).desnivell_m AS desnivell_m, " +
                        "(especificacions).pendent_max_pct AS pendent_max_pct, " +
                        "(condicions).gruix_cm AS gruix_cm, " +
                        "(condicions).qualitat AS qualitat, " +
                        "remuntadors_acces " +
                        "FROM pista WHERE ? = ANY(remuntadors_acces)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // Asignamos la variable a buscar
                ps.setString(1, remuntador);
                try (ResultSet rs = ps.executeQuery()) {
                    // Iteramos por todos los registros encontrados y los vamos añadiendo a nuestra lista 'result'
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
            // Hacemos el UPDATE modificando todos los campos e igualándolos al id
            String sql = "UPDATE pista SET nom = ?, color = ?, oberta = ?, " +
                        "especificacions = ROW(?, ?, ?)::dades_tecniques, " +
                        "condicions = ROW(?, ?)::estat_neu, " +
                        "remuntadors_acces = ? " +
                        "WHERE id = ?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // Ingresamos los datos que vamos a actualizar de la pista...
                ps.setString(1, pista.getNom());
                ps.setString(2, pista.getColor());
                ps.setBoolean(3, pista.isOberta());
                ps.setInt(4, pista.getLongitud());
                ps.setInt(5, pista.getDesnivell());
                ps.setInt(6, pista.getPendentMax());
                ps.setInt(7, pista.getGruix());
                ps.setString(8, pista.getQualitat());
                
                // Reconstruimos el array de Postgres con las Strings
                java.sql.Array remuntadorsArray = con.createArrayOf("varchar", pista.getRemuntadorsAcces().toArray());
                ps.setArray(9, remuntadorsArray);
                
                // Finalmente ponemos la condición de id de la cláusula WHERE
                ps.setString(10, pista.getId());
                
                // Ejecutamos y verificamos si se actualizó algo (Si devuelve 0 filas, falla)
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new GestorExcepcio("No se ha podido actualizar: no existe la pista con id: " + pista.getId());
                }
            } catch (SQLException ex) {
                throw new GestorExcepcio("Error al actualizar la pista: " + ex.getMessage());
            }
        }

    }


