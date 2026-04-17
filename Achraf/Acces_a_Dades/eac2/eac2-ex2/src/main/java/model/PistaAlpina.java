package model;

import java.util.Objects;

//TODO Posar anotacions JPA
public class PistaAlpina extends Pista {


    private String color;
    private int pendentMax;

    protected PistaAlpina() {
        // Required by JPA
    }

    public PistaAlpina(String id, String nom, int longitud, boolean isOberta,
                       int desnivell, String color, int gruixNeu, String qualitatNeu,
                       int pendentMax, boolean iluminada) {
        super(id, nom, longitud, isOberta, desnivell, gruixNeu, qualitatNeu, iluminada);
        this.color = color;
        this.pendentMax = pendentMax;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPendentMax() {
        return pendentMax;
    }

    public void setPendentMax(int pendentMax) {
        this.pendentMax = pendentMax;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PistaAlpina that = (PistaAlpina) o;
        return pendentMax == that.pendentMax && Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), color, pendentMax);
    }
}
