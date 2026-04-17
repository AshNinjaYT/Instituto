package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa una pista d'esquí de la taula 'pistes'
 * Conté informació sobre les característiques físiques, condicions de neu i remuntadors
 */
public class Pista {

    // Camp clau
    private String id;              // Identificador únic de la pista (VARCHAR(10))

    // Camps bàsics
    private String nom;             // Nom de la pista (VARCHAR(100), NOT NULL)
    private String color;           // Color de la pista (VARCHAR(20)) - Verda, Blava, Vermella, Negra
    private boolean oberta;         // Estat de la pista (BOOLEAN) - Oberta o Tancada

    // Dades tècniques (tipus compost: dades_tecniques)
    private Integer longitud;           // Longitud en metres
    private Integer desnivell;          // Desnivell en metres
    private Integer pendentMax;      // Pendent màxim en percentatge

    // Condicions de neu (tipus compost: estat_neu)
    private Integer gruix;             // Gruix de neu en cm
    private String qualitat;              // Qualitat de la neu (VARCHAR(50))

    // Remuntadors d'accés (array: VARCHAR[])
    private List<String> remuntadorsAcces;  // Llista de remuntadors que donen accés


    // ==================== CONSTRUCTORS ====================

    /**
     * Constructor complet amb tots els paràmetres
     */
    public Pista(String id, String nom, String color, boolean oberta,
                 Integer longitud, Integer desnivell, Integer pendentMax,
                 Integer gruix, String qualitat, List<String> remuntadorsAcces) {
        this.id = id;
        this.nom = nom;
        this.color = color;
        this.oberta = oberta;
        this.longitud = longitud;
        this.desnivell = desnivell;
        this.pendentMax = pendentMax;
        this.gruix = gruix;
        this.qualitat = qualitat;
        this.remuntadorsAcces = remuntadorsAcces != null ? new ArrayList<>(remuntadorsAcces) : new ArrayList<>();
    }


    // ==================== GETTERS ====================

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getColor() {
        return color;
    }

    public boolean isOberta() {
        return oberta;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public Integer getDesnivell() {
        return desnivell;
    }

    public Integer getPendentMax() {
        return pendentMax;
    }

    public Integer getGruix() {
        return gruix;
    }

    public String getQualitat() {
        return qualitat;
    }

    public List<String> getRemuntadorsAcces() {
        return new ArrayList<>(remuntadorsAcces);
    }


    // ==================== SETTERS ====================

    public void setId(String id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setOberta(boolean oberta) {
        this.oberta = oberta;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public void setDesnivell(Integer desnivell) {
        this.desnivell = desnivell;
    }

    public void setPendentMax(Integer pendentMax) {
        this.pendentMax = pendentMax;
    }

    public void setGruix(Integer gruix) {
        this.gruix = gruix;
    }

    public void setQualitat(String qualitat) {
        this.qualitat = qualitat;
    }

    public void setRemuntadorsAcces(List<String> remuntadorsAcces) {
        this.remuntadorsAcces = remuntadorsAcces != null ? new ArrayList<>(remuntadorsAcces) : new ArrayList<>();
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pista pista = (Pista) o;
        return oberta == pista.oberta && Objects.equals(id, pista.id) && Objects.equals(nom, pista.nom)
                && Objects.equals(color, pista.color) && Objects.equals(longitud, pista.longitud)
                && Objects.equals(desnivell, pista.desnivell) && Objects.equals(pendentMax, pista.pendentMax)
                && Objects.equals(gruix, pista.gruix) && Objects.equals(qualitat, pista.qualitat)
                && Objects.equals(remuntadorsAcces, pista.remuntadorsAcces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, color, oberta, longitud, desnivell, pendentMax, gruix, qualitat, remuntadorsAcces);
    }
}

