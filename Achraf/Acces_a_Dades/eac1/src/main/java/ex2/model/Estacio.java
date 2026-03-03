/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author joan
 */
//TODO Afegir anotacions JAXB i GSON
public class Estacio {

    private String id;
    private String nom;
    private String comarca;
    private String web;
    private int altitudMaxima;
    private String qualificacio;
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

    public int getEstatObertura() {
        return estatObertura;
    }

    public void setEstatObertura(int estatObertura) {
        this.estatObertura = estatObertura;
    }

    public List<Pista> getPistes() {
        return pistes;
    }

    public void setPistes(List<Pista> pistes) {
        this.pistes = pistes;
    }

    /**
     * Calcula el percentatge de pistes obertes
     */
    public void actualitzaEstatObertura() {
        //TODO
        throw new UnsupportedOperationException("Mètode no implementat");
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
