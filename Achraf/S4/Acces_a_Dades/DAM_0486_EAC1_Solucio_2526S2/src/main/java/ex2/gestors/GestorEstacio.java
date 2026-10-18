/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.gestors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ex2.model.Estacio;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author joan
 */
public class GestorEstacio {

    /**
     * Retorna un objecte de classe Estacio a partir d'un fitxer XML
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut llegir el
     * fitxer
     */
    public Estacio llegirFitxerXML(String nomFitxer) throws GestorEstacioException {
        try {
            JAXBContext context = JAXBContext.newInstance(Estacio.class);
            Unmarshaller um = context.createUnmarshaller();
            return (Estacio) um.unmarshal(new File(nomFitxer));
        } catch (Exception e) {
            e.printStackTrace();
            throw new GestorEstacioException("No s'ha pogut llegir el fitxer origen", e);
        }
    }

    /**
     * Crea un fitxer XML a partir d'un objecte de la classe Estacio
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param estacio objecte de la classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut gravar el
     * fitxer
     */
    public void gravarFitxerXML(String nomFitxer, Estacio estacio) throws GestorEstacioException {
        try {
            JAXBContext context = JAXBContext.newInstance(Estacio.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            m.marshal(estacio, new File(nomFitxer));
        } catch (Exception e) {
            throw new GestorEstacioException("No s'ha pogut escriure el fitxer destí", e);
        }
    }

    /**
     * Retorna un objecte de classe Estacio a partir d'un fitxer JSON
     *
     * @param nomFitxer el nom del fitxer
     * @return l'objecte de classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut llegir el
     * fitxer
     */
    public Estacio llegirFitxerJSON(String nomFitxer) throws GestorEstacioException {
        try {
            Gson gson = new Gson();
            File fitxer = new File(nomFitxer);
            Estacio estacio = gson.fromJson(new FileReader(fitxer), Estacio.class);

            return estacio;
        } catch (Exception e) {
            throw new GestorEstacioException("No s'ha pogut llegir el fitxer origen", e);
        }
    }

    /**
     * Crea un fitxer JSON a partir d'un objecte de la classe Estacio
     *
     * @param nomFitxer nom del fitxer que es crearà
     * @param estacio objecte de la classe Estacio
     * @throws ex2.gestors.GestorEstacioException si no s'ha pogut escriure el
     * fitxer
     */
    public void gravarFitxerJSON(String nomFitxer, Estacio estacio) throws GestorEstacioException {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            PrintStream fitxer = new PrintStream(new File(nomFitxer));
            fitxer.print(gson.toJson(estacio));
        } catch (Exception e) {
            throw new GestorEstacioException("No s'ha pogut escriure el fitxer destí", e);
        }
    }

}
