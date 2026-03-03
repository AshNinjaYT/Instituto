public class MedallaEquips extends Medalla {
    private Atleta[] equip;

    MedallaEquips(int posicio, Atleta[] equip) {
        super(posicio, equip[0]); // Associem la medalla al primer membre de l'equip
        if (equip.length > 30) throw new IllegalArgumentException("Màxim 30 atletes per equip");
        this.equip = equip;
    }

    public String descripcio() {
        StringBuilder membres = new StringBuilder();
        for (Atleta atleta : equip) {
            membres.append(atleta.nom).append(", ");
        }
        return String.format("L'equip format pels atletes [%s] ha guanyat una medalla d'equip en la posició %d.", membres, posicio);
    }
}