package eac3.model;

import jakarta.persistence.Entity;
import java.util.Objects;


@Entity
//TODO Posar les anotacions de Spring i/o Lombok
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.color);
        hash = 89 * hash + this.pendentMax;
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

        final PistaAlpina other = (PistaAlpina) obj;
        if (this.pendentMax != other.pendentMax) {
            return false;
        }
        return Objects.equals(this.color, other.color);
    }

}
