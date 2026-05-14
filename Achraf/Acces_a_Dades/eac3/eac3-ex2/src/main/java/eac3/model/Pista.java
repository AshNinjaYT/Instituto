package eac3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Representa una pista d'esquí de la taula 'pistes' Conté informació sobre les
 * característiques físiques, condicions de neu i remuntadors
 */
@Entity
@Data
@NoArgsConstructor
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
    @ToString.Exclude // Evita recursividad infinita por relación bidireccional
    @EqualsAndHashCode.Exclude
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


}
