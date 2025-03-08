public class TestJocsOlimpicsUnitat4 {

        public static void main(String[] args) {

                Pais pais1 = new Pais("Catalunya");

                Atleta atleta1 = new Atleta("Santi", pais1, "Futbol 5");
                Medalla m1 = new Medalla(2, atleta1);

                Atleta[] equipFutbol5 = new Atleta[5];
                equipFutbol5[0] = atleta1;
                equipFutbol5[1] = new Atleta("Joel", pais1, "Futbol 5");
                equipFutbol5[2] = new Atleta("Raul", pais1, "Futbol 5");
                equipFutbol5[3] = new Atleta("Miquel", pais1, "Futbol 5");
                equipFutbol5[4] = new Atleta("Joan", pais1, "Futbol 5");
                MedallaEquips m = new MedallaEquips(1, equipFutbol5);

                assert Medalla.getNumMedallesRepartides() == 2
                                : "ERROR: No controles bé el nombre de medalles repartides";
                System.out.println("Si has arribat aquí ja tens un punt dels 6 possibles!");

                assert atleta1.getNumMedalles() == 2 : "ERROR: No controles bé el nombre de medalles de l'atleta 1.";
                System.out.println("Si has arribat aquí ja tens 2 punts dels 6 possibles!");

                assert atleta1.mostrarMedalles()
                                .equals("""
                                                L'atleta Santi ha guanyat una medalla d'argent en obtenir la posició 2 a la seva prova de Futbol 5
                                                L'atleta Santi ha guanyat una medalla d'or en obtenir la posició 1 a la seva prova de Futbol 5
                                                """)
                                : "ERROR: No mostres correctament les medalles de l'atleta 1";
                System.out.println("Si has arribat aquí ja tens 4 punts dels 6 possibles!");

                assert pais1.getNumMedalles() == 2 : "ERROR: no controles bé el nombre de medalles del país 1";
                System.out.println("Si has arribat aquí ja tens 5 punts dels 6 possibles!");

                assert pais1.mostrarMedalles()
                                .equals("""
                                                L'atleta Santi ha guanyat una medalla d'argent en obtenir la posició 2 a la seva prova de Futbol 5
                                                L'equip format pels atletes [Santi, Joel, Raul, Miquel, Joan] han guanyat una medalla d'or en obtenir la posició 1 a la seva prova de Futbol 5
                                                """)
                                : "ERROR: No mostres correctament les medalles del país 1";

                System.out.println("Si has arribat aquí ja tens els 6 punts possibles!!!!");
                System.out.println(
                                "Però recorda que et poden descomptar punts si el codi que implementa les classes no està correctament implementat.");
                System.out.println("Revisa-les, si us plau!");

        }
}
