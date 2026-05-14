package eac3.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estacio {

    @Id
    private String id;
    private String nom;
    private String comarca;
    private int altitudMaxima;
    private String web;
    private String qualificacio;
    private double percentatgePistesObertes;

    @OneToMany(mappedBy = "estacio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude // Evita recursividad infinita por relación bidireccional
    @EqualsAndHashCode.Exclude
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


}
