package dam.psp.eac1.exercici5;

/**
 * Representa un paquet que arriba al centre de distribució logístic.
 * És la classe de dades que representa el treball a processar.
 */
public class Paquet {
    private final String id;
    private final double pesKg;
    private final String destinacio;

    public Paquet(String id, double pesKg, String destinacio) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'identificador del paquet no pot ser buit.");
        }
        if (destinacio == null || destinacio.isBlank()) {
            throw new IllegalArgumentException("La destinació no pot ser buida.");
        }
        if (pesKg <= 0) {
            throw new IllegalArgumentException("El pes del paquet ha de ser positiu.");
        }
        this.id = id;
        this.pesKg = pesKg;
        this.destinacio = destinacio;
    }

    public String getId() {
        return id;
    }

    public double getPesKg() {
        return pesKg;
    }

    public String getDestinacio() {
        return destinacio;
    }

    @Override
    public String toString() {
        return "Paquet{" + id + ", pes=" + String.format("%.2f", pesKg) + "kg, dest=" + destinacio + "}";
    }
}
