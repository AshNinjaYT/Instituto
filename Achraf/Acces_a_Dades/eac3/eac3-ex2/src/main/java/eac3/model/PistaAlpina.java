package eac3.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PistaAlpina extends Pista {

    private String color;
    private int pendentMax;

    public PistaAlpina(String id, String nom, int longitud, boolean isOberta,
            int desnivell, String color, int gruixNeu, String qualitatNeu,
            int pendentMax, boolean iluminada) {
        super(id, nom, longitud, isOberta, desnivell, gruixNeu, qualitatNeu, iluminada);
        this.color = color;
        this.pendentMax = pendentMax;
    }


}
