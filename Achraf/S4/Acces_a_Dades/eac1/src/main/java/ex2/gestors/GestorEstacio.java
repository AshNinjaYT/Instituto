package ex2.gestors;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ex2.model.Estacio;

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
        if (nomFitxer == null) {
            throw new GestorEstacioException("El nom del fitxer XML no pot ser nul");
        }
        try {
            JAXBContext context = JAXBContext.newInstance(Estacio.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Estacio) unmarshaller.unmarshal(new File(nomFitxer));
        } catch (JAXBException | IllegalArgumentException e) {
            throw new GestorEstacioException("Error en llegir el fitxer XML: " + nomFitxer, e);
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
        if (nomFitxer == null) {
            throw new GestorEstacioException("El nom del fitxer XML no pot ser nul");
        }
        try {
            JAXBContext context = JAXBContext.newInstance(Estacio.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(estacio, new File(nomFitxer));
        } catch (JAXBException | IllegalArgumentException e) {
            throw new GestorEstacioException("Error en gravar el fitxer XML: " + nomFitxer, e);
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
        if (nomFitxer == null) {
            throw new GestorEstacioException("El nom del fitxer JSON no pot ser nul");
        }
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(nomFitxer)) {
            return gson.fromJson(reader, Estacio.class);
        } catch (IOException e) {
            throw new GestorEstacioException("Error en llegir el fitxer JSON: " + nomFitxer, e);
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
        if (nomFitxer == null) {
            throw new GestorEstacioException("El nom del fitxer JSON no pot ser nul");
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(nomFitxer)) {
            gson.toJson(estacio, writer);
        } catch (IOException e) {
            throw new GestorEstacioException("Error en gravar el fitxer JSON: " + nomFitxer, e);
        }
    }

}
