package model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.Objects;

@Entity
@DiscriminatorValue("RAQUETES")
@NamedQueries({
    @NamedQuery(
        name = "ItinerariRaquetes.findByTempsEstimatMaxim",
        query = "SELECT i FROM ItinerariRaquetes i WHERE i.tempsEstimat <= :tempsMaxim"
    )
})
public class ItinerariRaquetes extends Pista {

    private String dificultat;
    private boolean circular;
    private boolean senyalitzat;
    private int tempsEstimat;

    protected ItinerariRaquetes() {
        // Required by JPA
    }

    public ItinerariRaquetes(String id, String nom, int longitud, boolean isOberta,
                             int desnivell, int gruixNeu, String qualitatNeu, boolean iluminada,
                             String dificultat, boolean circular, boolean senyalitzat,
                             int tempsEstimat) {
        super(id, nom, longitud, isOberta, desnivell, gruixNeu, qualitatNeu, iluminada);
        this.dificultat = dificultat;
        this.circular = circular;
        this.senyalitzat = senyalitzat;
        this.tempsEstimat = tempsEstimat;
    }

    public String getDificultat() {
        return dificultat;
    }

    public void setDificultat(String dificultat) {
        this.dificultat = dificultat;
    }

    public boolean isCircular() {
        return circular;
    }

    public void setCircular(boolean circular) {
        this.circular = circular;
    }

    public boolean isSenyalitzat() {
        return senyalitzat;
    }

    public void setSenyalitzat(boolean senyalitzat) {
        this.senyalitzat = senyalitzat;
    }

    public int getTempsEstimat() {
        return tempsEstimat;
    }

    public void setTempsEstimat(int tempsEstimatMinuts) {
        this.tempsEstimat = tempsEstimatMinuts;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItinerariRaquetes that = (ItinerariRaquetes) o;
        return circular == that.circular && senyalitzat == that.senyalitzat && tempsEstimat == that.tempsEstimat && Objects.equals(dificultat, that.dificultat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dificultat, circular, senyalitzat, tempsEstimat);
    }
}
