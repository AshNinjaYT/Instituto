package ex2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlRootElement(name = "estacio")
public class Estacio {

    private String id;
    
    private String nom;
    private String comarca;
    private String web;
    
    @SerializedName("altitud_maxima")
    private int altitudMaxima;
    
    private String qualificacio;
    
    @SerializedName("estat_obertura_percentatge")
    private int estatObertura;

    private List<Pista> pistes = new ArrayList<>();

    public Estacio() {
    }

    public Estacio(String id, String nom, String comarca, String web, int altitudMaxima, String qualificacio, int estatObertura) {
        this.id = id;
        this.nom = nom;
        this.comarca = comarca;
        this.web = web;
        this.altitudMaxima = altitudMaxima;
        this.qualificacio = qualificacio;
        this.estatObertura = estatObertura;
    }

    @XmlAttribute
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getComarca() {
        return comarca;
    }

    public void setComarca(String comarca) {
        this.comarca = comarca;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    @XmlElement(name = "altitud-maxima")
    public int getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(int altitudMaxima) {
        this.altitudMaxima = altitudMaxima;
    }

    public String getQualificacio() {
        return qualificacio;
    }

    public void setQualificacio(String qualificacio) {
        this.qualificacio = qualificacio;
    }

    @XmlElement(name = "estat-obertura-percentatge")
    public int getEstatObertura() {
        return estatObertura;
    }

    public void setEstatObertura(int estatObertura) {
        this.estatObertura = estatObertura;
    }

    @XmlElementWrapper(name = "pistes")
    @XmlElement(name = "pista")
    public List<Pista> getPistes() {
        if (pistes == null) {
            pistes = new ArrayList<>();
        }
        return pistes;
    }

    public void setPistes(List<Pista> pistes) {
        this.pistes = pistes;
    }

    /**
     * Calcula el percentatge de pistes obertes
     */
    public void actualitzaEstatObertura() {
        if (this.pistes == null || this.pistes.isEmpty()) {
            this.estatObertura = 0;
            return;
        }
        int obertes = 0;
        for (Pista p : this.pistes) {
            if (p.isOberta()) {
                obertes++;
            }
        }
        this.estatObertura = (int) Math.round((double) obertes / this.pistes.size() * 100.0);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.nom);
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.comarca);
        hash = 11 * hash + Objects.hashCode(this.web);
        hash = 11 * hash + Objects.hashCode(this.pistes);
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
        final Estacio other = (Estacio) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.comarca, other.comarca)) {
            return false;
        }
        if (!Objects.equals(this.web, other.web)) {
            return false;
        }
        return Objects.equals(this.pistes, other.pistes);
    }

}
