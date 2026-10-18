package eac3.model;

import java.util.Objects;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@NoArgsConstructor
public class ItinerariRaquetes extends Pista {

    private String dificultat;
    private boolean circular;
    private boolean senyalitzat;
    private int tempsEstimat;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.dificultat);
        hash = 53 * hash + (this.circular ? 1 : 0);
        hash = 53 * hash + (this.senyalitzat ? 1 : 0);
        hash = 53 * hash + this.tempsEstimat;
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
        if (!super.equals(obj)) {
            return false;
        }
        final ItinerariRaquetes other = (ItinerariRaquetes) obj;
        if (this.circular != other.circular) {
            return false;
        }
        if (this.senyalitzat != other.senyalitzat) {
            return false;
        }
        if (this.tempsEstimat != other.tempsEstimat) {
            return false;
        }
        return Objects.equals(this.dificultat, other.dificultat);
    }

}
