package model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Representa una pista d'esquí de la taula 'pistes'
 * Conté informació sobre les característiques físiques, condicions de neu i remuntadors
 */
@Entity
@Table(name = "pistes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Tabla única como pide el enunciado
public abstract class Pista {


    @Id
    @Column(length = 14) // El id debe ser de 14 caracteres
    private String id;
    
    @Column(length = 100) // Nombre máximo de 100 caracteres
    private String nom;
    private int longitud;
    private boolean oberta;
    private int desnivell;
    private int gruixNeu;
    private String qualitatNeu;
    private boolean iluminada;
    
    @ManyToOne
    private Estacio estacio;

    // ==================== CONSTRUCTORS ====================

    /**
     * Constructor complet amb tots els paràmetres
     */
    protected Pista() {
        // Required by JPA
    }

    public Pista(String id, String nom, int longitud, boolean oberta,
                 int desnivell, int gruixNeu, String qualitatNeu, boolean iluminada) {
        this.id = id;
        this.nom = nom;
        this.longitud = longitud;
        this.oberta = oberta;
        this.desnivell = desnivell;
        this.gruixNeu = gruixNeu;
        this.qualitatNeu = qualitatNeu;
        this.iluminada = iluminada;
    }

    // ==================== GETTERS ====================

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getLongitud() {
        return longitud;
    }

    public boolean isOberta() {
        return oberta;
    }

    public int getDesnivell() {
        return desnivell;
    }

    public int getGruixNeu() {
        return gruixNeu;
    }

    public String getQualitatNeu() {
        return qualitatNeu;
    }

    public boolean isIluminada() {
        return iluminada;
    }

    public Estacio getEstacio() {
        return estacio;
    }

    // ==================== SETTERS ====================

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public void setOberta(boolean oberta) {
        this.oberta = oberta;
    }

    public void setDesnivell(int desnivell) {
        this.desnivell = desnivell;
    }

    public void setGruixNeu(int gruixNeu) {
        this.gruixNeu = gruixNeu;
    }

    public void setQualitatNeu(String qualitatNeu) {
        this.qualitatNeu = qualitatNeu;
    }

    public void setIluminada(boolean iluminada) {
        this.iluminada = iluminada;
    }

    public void setEstacio(Estacio estacio) {
        this.estacio = estacio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pista pista = (Pista) o;
        return Objects.equals(id, pista.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
