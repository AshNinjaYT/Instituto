/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.gestors;

import eac3.model.Pista;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import javax.xml.xquery.XQSequence;

/**
 * Classe que gestiona la persistència dels objectes de la classe model.Pista
 *
 * @author professor
 */
public class PistaManager {

    // arrel del document (permet simplificar les expressions)
    // public static final String ARREL =
    // "doc(\"pistes/pistes.xml\")/collection(\"pistes\")/";
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
        // Primero comprobamos si la pista ya está en la base de datos para no duplicarla
        try {
            getPista(pista.getId());
            // Si llegamos aquí es que no ha saltado el error de "no encontrada", por lo tanto ya existe
            throw new ManagerException("Ja existeix una pista amb aquest id");
        } catch (ManagerException e) {
            // Si el error es que ya existe, lo volvemos a lanzar hacia arriba
            if (e.getMessage().contains("Ja existeix"))
                throw e;
            // Si es cualquier otro error (seguramente que no la ha encontrado), podemos seguir
        }

        // Si todo es correcto, insertamos la nueva pista
        try {
            // Convertimos el objeto a XML con la utilidad que tenemos
            String xml = Utilitats.formaObjecteXML(pista);
            // Preparamos la consulta de XQuery Update para insertar el nodo en la colección
            String consulta = "insert node " + xml + " into " + ARREL + "pistes";
            XQExpression exp = conn.createExpression();
            // Ejecutamos con executeQuery porque es XQuery Update
            exp.executeQuery(consulta);
        } catch (XQException e) {
            throw new ManagerException("Error en inserir la pista", e);
        }
    }

    /**
     * Elimina una pista de la base de dades
     *
     * @param id l'identificador de la pista
     * @throws ManagerException en cas d'error, com per exemple que no existeixi
     */
    public void delete(String id) throws ManagerException {
        // Antes de borrar, miramos si la pista existe realmente
        getPista(id);
        try {
            // Consulta para borrar el nodo que tenga el id que nos pasan
            String consulta = "delete node " + ARREL + "pistes/pista[@id=\"" + id + "\"]";
            XQExpression exp = conn.createExpression();
            // Ejecutamos el borrado
            exp.executeQuery(consulta);
        } catch (XQException e) {
            throw new ManagerException("Error en eliminar la pista", e);
        }
    }

    /**
     * Elimina totes les pistes de la base de dades
     *
     * @throws ManagerException
     */
    public void deleteAll() throws ManagerException {
        try {
            // Borramos todos los nodos "pista" que cuelgan de "pistes"
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
        // Buscamos la pista por su atributo id
        String q = ARREL + "pistes/pista[@id=\"" + id + "\"]";
        try {
            XQExpression exp = conn.createExpression();
            XQResultSequence seq = exp.executeQuery(q);
            // Si no hay resultados, lanzamos error
            if (!seq.next()) {
                throw new ManagerException("Pista no trobada");
            } else {
                // Si la encontramos, pillamos el XML como String y lo transformamos a objeto Pista
                String xml = seq.getItemAsString(null);
                Pista p = Utilitats.obteObjecte(xml);
                return p;
            }
        } catch (XQException e) {
            throw new ManagerException("Error en obtenir la pista", e);
        }
    }

    /**
     * Obtenir les pistes que tenen un tipus de remuntador
     *
     * @param tipusRemuntador tipus del remuntador
     * @return la llista de pistes
     */
    List<Pista> getPistesAmbRemuntador(String tipusRemuntador) throws ManagerException {
        // Buscamos las pistas que tengan el tipo de remonte indicado en su lista
        String q = ARREL + "pistes/pista[remuntadors/remuntador[text() = \"" + tipusRemuntador + "\"]]";
        List<Pista> pistes = new ArrayList<>();
        try {
            XQExpression exp = conn.createExpression();
            XQResultSequence seq = exp.executeQuery(q);
            // Recorremos todos los resultados de la consulta
            while (seq.next()) {
                // Por cada resultado (XML), lo convertimos a objeto y lo añadimos a la lista
                String xml = seq.getItemAsString(null);
                Pista p = Utilitats.obteObjecte(xml);
                pistes.add(p);
            }
        } catch (XQException e) {
            throw new ManagerException("Error en obtenir les pistes", e);
        }
        // Devolvemos la lista llena
        return pistes;
    }
}
