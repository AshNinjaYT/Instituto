package model;

import java.util.Objects;

import javax.persistence.Entity;

@Entity
public class PistaNordica extends Pista {

    private String estil;
    private boolean trepitjada;

    protected PistaNordica() {
        // Required by JPA
    }

    public PistaNordica(String id, String nom, int longitud, boolean isOberta,
                        int desnivell, int gruixNeu, String qualitatNeu,
                        boolean iluminada, String estil, boolean trepitjada) {
        super(id, nom, longitud, isOberta, desnivell, gruixNeu, qualitatNeu, iluminada);
        this.estil = estil;
        this.trepitjada = trepitjada;
    }

    public String getEstil() {
        return estil;
    }

    public void setEstil(String estil) {
        this.estil = estil;
    }

    public boolean isTrepitjada() {
        return trepitjada;
    }

    public void setTrepitjada(boolean trepitjada) {
        this.trepitjada = trepitjada;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PistaNordica that = (PistaNordica) o;
        return trepitjada == that.trepitjada && Objects.equals(estil, that.estil);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), estil, trepitjada);
    }
}
