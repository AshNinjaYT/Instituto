package eac3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

}
