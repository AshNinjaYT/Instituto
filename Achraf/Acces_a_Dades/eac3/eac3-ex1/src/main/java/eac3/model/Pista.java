/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eac3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author professor
 */
@XmlRootElement(name = "pista")
//@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "nom", "color", "oberta","longitud", "desnivell","pendentMax" ,"gruix","qualitat", "remuntadors"})
public class Pista {

    private String id;         
    private String nom;
    private String color;
    private boolean oberta; 
    private Integer longitud; 
    private Integer desnivell;
    private Integer pendentMax;    
    private Integer gruix;     
    private String qualitat;   
    private List<String> remuntadors= new ArrayList<>(); 

    public Pista() {

    }

    public Pista(String id, String nom, String color, boolean oberta, Integer longitud, Integer desnivell, Integer pendentMax, Integer gruix, String qualitat, List<String> remuntadors) {
        this.id = id;
        this.nom = nom;
        this.color = color;
        this.oberta = oberta;
        this.longitud = longitud;
        this.desnivell = desnivell;
        this.pendentMax = pendentMax;
        this.gruix = gruix;
        this.qualitat = qualitat;
        this.remuntadors = remuntadors;
    }

 

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @XmlElement
    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    @XmlElement
    public Integer getDesnivell() {
        return desnivell;
    }

    public void setDesnivell(Integer desnivell) {
        this.desnivell = desnivell;
    }

    @XmlElement
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @XmlElement
    public boolean isOberta() {
        return oberta;
    }

    public void setOberta(boolean oberta) {
        this.oberta = oberta;
    }
    
    @XmlElement
    public Integer getGruix() {
        return gruix;
    }

    public void setGruix(Integer gruix) {
        this.gruix = gruix;
    }
    
    @XmlElement
    public String getQualitat() {
        return qualitat;
    }

    public void setQualitat(String qualitat) {
        this.qualitat = qualitat;
    }
 
    
    @XmlElement
    public Integer getPendentMax() {
        return pendentMax;
    }

    public void setPendentMax(Integer pendentMax) {
        this.pendentMax=pendentMax;
    }
 
     
    

    @XmlElementWrapper(name = "remuntadors")
    @XmlElement(name = "remuntador")
    public List<String> getRemuntadors() {
        return remuntadors;
    }

    public void setRemuntadors(List<String> remuntadors) {
        this.remuntadors.clear();
        this.remuntadors.addAll(remuntadors);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.nom);
        hash = 47 * hash + Objects.hashCode(this.color);
        hash = 47 * hash + (this.oberta ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.longitud);
        hash = 47 * hash + Objects.hashCode(this.desnivell);
        hash = 47 * hash + Objects.hashCode(this.pendentMax);
        hash = 47 * hash + Objects.hashCode(this.gruix);
        hash = 47 * hash + Objects.hashCode(this.qualitat);
        hash = 47 * hash + Objects.hashCode(this.remuntadors);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pista other = (Pista) obj;
        if (this.oberta != other.oberta) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.qualitat, other.qualitat)) {
            return false;
        }
        if (!Objects.equals(this.longitud, other.longitud)) {
            return false;
        }
        if (!Objects.equals(this.desnivell, other.desnivell)) {
            return false;
        }
        if (!Objects.equals(this.pendentMax, other.pendentMax)) {
            return false;
        }
        if (!Objects.equals(this.gruix, other.gruix)) {
            return false;
        }
        return Objects.equals(this.remuntadors, other.remuntadors);
    }
    

    
    
}
