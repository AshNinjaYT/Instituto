public class TestMedaller {

    public static void main(String[] args) {

        Pais p = new Pais("Catalunya");
        Atleta a = new Atleta("Santi", p, "Handbol");
        Medalla m = new Medalla(1, a);

        Pais p2 = new Pais("Andorra");
        Atleta a2 = new Atleta("Joan", p2, "Esquí");
        for (int pos = 1; pos <= 3; pos++) {
            Medalla m2 = new Medalla(pos, a2);
        }

        Pais p3 = new Pais("França");
        Atleta[] equip = new Atleta[5];
        equip[0] = new Atleta("François", p3, "Bàsquet");
        equip[1] = new Atleta("Pierre", p3, "Bàsquet");
        equip[2] = new Atleta("Marçal", p3, "Bàsquet");
        equip[3] = new Atleta("Jean", p3, "Bàsquet");
        equip[4] = new Atleta("Michel", p3, "Bàsquet");
        MedallaEquips me = new MedallaEquips(3, equip);
        for (int pos = 1; pos <= 3; pos++) {
            Medalla m3 = new Medalla(pos, equip[0]);
        }

        // Exercici 3
        Medaller.mostrarMedaller();

        // Exercici 4
        Medaller.mostrarMedallerOrdenat();
        Medaller.mostrarMedallerOrdenatAlfabetic();
    }

}
