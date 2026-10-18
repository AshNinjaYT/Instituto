/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eac3.gestors;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import eac3.model.Pista;

/**
 * Classe amb mètodes per fer la transformació 
 * @author professor
 */
public class Utilitats {

    static JAXBContext jaxbContext;
    static Marshaller jaxbMarshaller;
    static Unmarshaller jaxbUnmarshaller;    

    static  {
        try {
            jaxbContext = JAXBContext.newInstance(Pista.class);
            jaxbMarshaller = jaxbContext.createMarshaller();    
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
            System.err.println(ex.getMessage());
        }
    }
  


    
    
/**
 * Obte la representació XML d'un objecte en forma d'String.
 * S'assumeix que la representacio és correcta.
 * @param obj objecte a representar en XML
 * @return representacio XML de l'objecte indicat pel paràmetre
 * @throws agerException si es produeix algun error en la conversió
 */
    
    public static String formaObjecteXML(Pista obj) throws ManagerException {
        
        try {
            StringWriter sw = new StringWriter();
            
            jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
           
            jaxbMarshaller.marshal(obj, sw);
            
            return sw.toString();
            
        } catch (JAXBException ex) {
            throw new ManagerException(ex.getMessage());
        }

    }
 /**
  * Obte un objecte a partir de la seva representació en XML continguda en un String.
  * @param dades representació XML d'un objecte
  * @return objecte dades corresponent a la representació XML continguda al paràmetre
  * @throws ManagerException si es produeix algun error en la conversió
  */   
    public static Pista obteObjecte(String dades) throws ManagerException {
        
        try {
            StringReader reader = new StringReader(dades);
            jaxbUnmarshaller.setEventHandler(new javax.xml.bind.helpers.DefaultValidationEventHandler());
            Pista c = (Pista) jaxbUnmarshaller.unmarshal(reader);
            return c;
            
        } catch (JAXBException ex) {
            ex.printStackTrace();
            throw new ManagerException(ex.getMessage());
        }
    
    }

}
