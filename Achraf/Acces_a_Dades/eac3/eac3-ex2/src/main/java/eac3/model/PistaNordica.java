package eac3.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PistaNordica extends Pista {

    private String estil;
    private boolean trepitjada;

    public PistaNordica(String id, String nom, int longitud, boolean isOberta,
            int desnivell, int gruixNeu, String qualitatNeu,
            boolean iluminada, String estil, boolean trepitjada) {
        super(id, nom, longitud, isOberta, desnivell, gruixNeu, qualitatNeu, iluminada);
        this.estil = estil;
        this.trepitjada = trepitjada;
    }

}
