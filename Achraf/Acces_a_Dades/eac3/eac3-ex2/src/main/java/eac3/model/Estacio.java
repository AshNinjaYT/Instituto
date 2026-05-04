package eac3.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//TODO Posar les anotacions de Spring i/o Lombok
public class Estacio {

    private String id;
    private String nom;
    private String comarca;
    private int altitudMaxima;
    private String web;
    private String qualificacio;
    private double percentatgePistesObertes;

    @OneToMany(mappedBy = "estacio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pista> pistes = new ArrayList<>();

    public Estacio(String id, String nom, String comarca, int altitudMaxima,
            String web, String qualificacio, double percentatgePistesObertes) {
        this.id = id;
        this.nom = nom;
        this.comarca = comarca;
        this.altitudMaxima = altitudMaxima;
        this.web = web;
        this.qualificacio = qualificacio;
        this.percentatgePistesObertes = percentatgePistesObertes;
    }

    public double calcularPercentatgeObertura() {
        if (pistes == null || pistes.isEmpty()) {
            percentatgePistesObertes = 0.0;
            return 0.0;
        }

        long obertes = pistes.stream().filter(Pista::isOberta).count();
        double percentatge = (obertes * 100.0) / pistes.size();
        percentatgePistesObertes = percentatge;
        return percentatge;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
        hash = 59 * hash + Objects.hashCode(this.nom);
        hash = 59 * hash + Objects.hashCode(this.comarca);
        hash = 59 * hash + this.altitudMaxima;
        hash = 59 * hash + Objects.hashCode(this.web);
        hash = 59 * hash + Objects.hashCode(this.qualificacio);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.percentatgePistesObertes) ^ (Double.doubleToLongBits(this.percentatgePistesObertes) >>> 32));
        hash = 59 * hash + Objects.hashCode(this.pistes);
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
        if (this.altitudMaxima != other.altitudMaxima) {
            return false;
        }
        if (Double.doubleToLongBits(this.percentatgePistesObertes) != Double.doubleToLongBits(other.percentatgePistesObertes)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.comarca, other.comarca)) {
            return false;
        }
        if (!Objects.equals(this.web, other.web)) {
            return false;
        }
        if (!Objects.equals(this.qualificacio, other.qualificacio)) {
            return false;
        }

        boolean result = this.pistes.size() == other.pistes.size() && this.pistes.containsAll(other.pistes);
        return result;

    }

}
