package eac3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

/**
 * Representa una pista d'esquí de la taula 'pistes' Conté informació sobre les
 * característiques físiques, condicions de neu i remuntadors
 */
@Entity
//TODO Posar les anotacions de Spring i/o Lombok
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public abstract class Pista {

    @Id
    private String id;
    private String nom;
    private int longitud;
    private boolean oberta;
    private int desnivell;
    private int gruixNeu;
    private String qualitatNeu;
    private boolean iluminada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Estacio estacio;

    // ==================== CONSTRUCTORS ====================
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.nom);
        hash = 53 * hash + this.longitud;
        hash = 53 * hash + (this.oberta ? 1 : 0);
        hash = 53 * hash + this.desnivell;
        hash = 53 * hash + this.gruixNeu;
        hash = 53 * hash + Objects.hashCode(this.qualitatNeu);
        hash = 53 * hash + (this.iluminada ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.estacio);
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
        if (this.longitud != other.longitud) {
            return false;
        }
        if (this.oberta != other.oberta) {
            return false;
        }
        if (this.desnivell != other.desnivell) {
            return false;
        }
        if (this.gruixNeu != other.gruixNeu) {
            return false;
        }
        if (this.iluminada != other.iluminada) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.qualitatNeu, other.qualitatNeu)) {
            return false;
        }
        return Objects.equals(this.estacio.getId(), other.estacio.getId());
    }

}
