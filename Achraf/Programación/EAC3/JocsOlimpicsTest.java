public class JocsOlimpicsTest {

    public static void main(String[] args) {
        // Variables para atleta 1
        Pais espanya = new Pais("Espanya");
        Pais francia = new Pais("Francia");
        Atleta atleta1 = new Atleta("Joan", espanya, "Ciclisme");
        Atleta atleta2 = new Atleta("Samuel", espanya, "Natacion");
        Atleta atleta3 = new Atleta("Mbappe", francia, "Futbol");

        // Codigo para generar 10 medallas a un atleta y afirmarlo
        for (int i = 0; i < 10; i++) {
            new Medalla(1, atleta1);
        }
        assert (atleta1.getNumMedalles() <= 10)
                : atleta1.nom + "Tine mas de 10 medallas, en total tiene: " + atleta1.getNumMedalles();

        System.out.println("Medalles de l'atleta " + atleta1.nom + " : " + atleta1.getNumMedalles());

        // Codigo para generar 3 medallas a un pais y afirmarlo
        if (atleta1.getNumMedalles() < 10) {
            for (int i = 0; i < 3; i++) {
                new Medalla(1, atleta1);
            }
        }

        assert atleta1.getNumMedalles() <= 100
                : espanya.nom + " tine mas de 100 medallas, en total tiene: " + espanya.getNumMedalles();

        System.out.println("Medalles de pais " + espanya.nom + " : " + espanya.getNumMedalles());

        // Codigo para generar 3 medallas a un ateleta diferente y afirmarlo
        for (int i = 0; i < 3; i++) {
            new Medalla(1, atleta2);
        }
        assert atleta2.getNumMedalles() <= 10 && espanya.getNumMedalles() <= 100
                : "El atleta " + atleta2.nom + "tiene mas de 10 medallas" + espanya.nom
                        + " tine mas de 100 medallas, en total tiene: " + espanya.getNumMedalles();

        System.out.println("El atleta " + atleta2.nom + " tiene " + atleta2.getNumMedalles() + " medallas" + " y "
                + espanya.nom + " tiene " + espanya.getNumMedalles());

        // Codigo para generar 1 medallas a un ateleta diferente y pais diferente y
        // afirmarlo
        for (int i = 0; i < 1; i++) {
            new Medalla(1, atleta3);
        }
        assert atleta3.getNumMedalles() <= 10 && francia.getNumMedalles() <= 100
                : "El atleta " + atleta3.nom + "tiene mas de 10 medallas" + espanya.nom
                        + " tine mas de 100 medallas, en total tiene: " + francia.getNumMedalles();

        System.out.println("El atleta " + atleta3.nom + " tiene " + atleta3.getNumMedalles() + " medallas" + " y "
                + francia.nom + " tiene " + francia.getNumMedalles());
    }
}