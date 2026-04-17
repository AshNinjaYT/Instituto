package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//TODO Posar anotacions JPA

public class Estacio {


    private String id;
    private String nom;
    private String comarca;
    private int altitudMaxima;
    private String web;
    private String qualificacio;
    private double percentatgePistesObertes;
    private List<Pista> pistes = new ArrayList<>();

    protected Estacio() {
    }

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

    public int getAltitudMaxima() {
        return altitudMaxima;
    }

    public void setAltitudMaxima(int altitudMaxima) {
        this.altitudMaxima = altitudMaxima;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getQualificacio() {
        return qualificacio;
    }

    public void setQualificacio(String qualificacio) {
        this.qualificacio = qualificacio;
    }

    public double getPercentatgePistesObertes() {
        return percentatgePistesObertes;
    }

    public void setPercentatgePistesObertes(double estatOberturaPercentatge) {
        this.percentatgePistesObertes = estatOberturaPercentatge;
    }

    public List<Pista> getPistes() {
        return pistes;
    }

    public void setPistes(List<Pista> pistes) {
        this.pistes = pistes;
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Estacio estacio = (Estacio) o;
        return altitudMaxima == estacio.altitudMaxima &&
                Double.compare(percentatgePistesObertes, estacio.percentatgePistesObertes) == 0 &&
                Objects.equals(id, estacio.id) && Objects.equals(nom, estacio.nom) &&
                Objects.equals(comarca, estacio.comarca) && Objects.equals(web, estacio.web) &&
                Objects.equals(qualificacio, estacio.qualificacio) &&
                getPistes().size() == estacio.getPistes().size() &&
                getPistes().containsAll(estacio.getPistes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, comarca, altitudMaxima, web, qualificacio, percentatgePistesObertes, getPistes());
    }
}
