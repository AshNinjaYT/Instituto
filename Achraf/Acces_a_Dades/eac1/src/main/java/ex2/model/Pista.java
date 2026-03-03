/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ex2.model;

import java.util.Objects;

/**
 *
 * @author joan
 */
//TODO Afegir anotacions JAXB i GSON
public class Pista {

    private String id;
    private String nom;
    private int longitud;
    private int desnivell;
    private String color;
    private boolean oberta;
    private int gruixNeu;
    private String qualitatNeu;

    public Pista() {
    }

    public Pista(String id, String nom, String color, String qualitatNeu, int gruixNeu, int longitud, int desnivell, boolean oberta) {
        this.id = id;
        this.nom = nom;
        this.color = color;
        this.qualitatNeu = qualitatNeu;
        this.gruixNeu = gruixNeu;
        this.longitud = longitud;
        this.desnivell = desnivell;
        this.oberta = oberta;
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

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getDesnivell() {
        return desnivell;
    }

    public void setDesnivell(int desnivell) {
        this.desnivell = desnivell;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getGruixNeu() {
        return gruixNeu;
    }

    public void setGruixNeu(int gruixNeu) {
        this.gruixNeu = gruixNeu;
    }

    public String getQualitatNeu() {
        return qualitatNeu;
    }

    public void setQualitatNeu(String qualitatNeu) {
        this.qualitatNeu = qualitatNeu;
    }

    public boolean isOberta() {
        return oberta;
    }

    public void setOberta(boolean oberta) {
        this.oberta = oberta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.nom);
        hash = 71 * hash + Objects.hashCode(this.color);
        hash = 71 * hash + Objects.hashCode(this.qualitatNeu);
        hash = 71 * hash + this.gruixNeu;
        hash = 71 * hash + this.longitud;
        hash = 71 * hash + this.desnivell;
        hash = 71 * hash + (this.oberta ? 1 : 0);
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
        if (this.gruixNeu != other.gruixNeu) {
            return false;
        }
        if (this.longitud != other.longitud) {
            return false;
        }
        if (this.desnivell != other.desnivell) {
            return false;
        }
        if (this.oberta != other.oberta) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        return Objects.equals(this.qualitatNeu, other.qualitatNeu);
    }

}
