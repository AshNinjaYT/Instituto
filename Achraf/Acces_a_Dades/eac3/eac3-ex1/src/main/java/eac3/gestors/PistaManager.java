/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Pista;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
/**
 * Classe que gestiona la persistència dels objectes de la classe model.Pista
 *
 * @author professor
 */
public class PistaManager {

    //arrel del document (permet simplificar les expressions)
    //public static final String ARREL = "doc(\"pistes/pistes.xml\")/collection(\"pistes\")/";
    public static final String ARREL = "doc(\"pistes/pistes.xml\")/";

    private final XQConnection conn;

    public PistaManager(XQConnection conn) {
        this.conn = conn;
    }

    /**
     * Dóna d'alta una pista a la base de dades
     *
     * @param pista la pista a donar d'alta
     * @throws ManagerException en cas d'error, com per exemple clau duplicada
     */
    public void insert(Pista pista) throws ManagerException {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Elimina una pista de la base de dades
     *
     * @param id l'identificador de la pista
     * @throws ManagerException en cas d'error, com per exemple que no existeixi
     */
    public void delete(String id) throws ManagerException {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");
    }

    /**
     * Elimina totes les pistes de la base de dades
     *
     * @throws ManagerException
     */
    public void deleteAll() throws ManagerException {
    try {
            String consulta = "delete node " + ARREL + "pistes/pista";
            XQExpression exp = conn.createExpression();
            exp.executeQuery(consulta);
        } catch (XQException ex) {
            Logger.getLogger(PistaManager.class.getName()).log(Level.SEVERE, null, ex);
            throw new ManagerException("Error en esborrar totes les pistes", ex);
        }
    }

    /**
     * Obté una pista de la base de dades
     *
     * @param id l'identificador de la pista
     * @return l'objecte pista
     * @throws ManagerException en cas d'error, com per exemple que no existeixi
     */
    public Pista getPista(String id) throws ManagerException {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");

    }

    /**
     * Obtenir les pistes que tenen un tipus de remuntador
     *
     * @param tipusRemuntador tipus del remuntador
     * @return la llista de pistes
     */
    List<Pista> getPistesAmbRemuntador(String tipusRemuntador) throws ManagerException {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");

    }
}
