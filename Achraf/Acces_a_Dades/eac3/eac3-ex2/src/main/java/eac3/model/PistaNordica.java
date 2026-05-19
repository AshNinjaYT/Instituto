package eac3.model;


import java.util.Objects;



import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.estil);
        hash = 37 * hash + (this.trepitjada ? 1 : 0);
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

        final PistaNordica other = (PistaNordica) obj;
        if (this.trepitjada != other.trepitjada) {
            return false;
        }
        return Objects.equals(this.estil, other.estil);
    }
}
