public class JocsOlimpics2 {

    public static void main(String[] args) {
        //Comencem els jocs olímpics
        System.out.println("***Benvinguts al sistema de proves dels Jocs Olímpics de París 2024***");
        System.out.println("Com que acabem de començar, ara mateix s'han repartit " + Medalla.getNumMedallesRepartides() + " medalles");
        
        //Anem a crear un nou pais
        System.out.println("***Creació d'un país***");
        String paisTurquia = "Turquia";
        Pais p1 = new Pais(paisTurquia);
        // Provem que ha funcionat la creació del país
        System.out.println("El país es diu " + p1.nom + " i té " + p1.getNumMedalles() + " medalles");

        // Anem a crear un atleta
        System.out.println("***Creació d'un atleta***");
        String nomAtleta = "Yusuf Dikec";
        Pais paisAtleta = p1;
        String disciplina = "Tir olímpic";
        Atleta a1 = new Atleta(nomAtleta, paisAtleta, disciplina);

        //Provem que ha funcionat la creació de l'atleta
        System.out.println("L'atleta es diu " + a1.nom + " és de " + a1.pais.nom + " i participa a la competició de " + a1.disciplina);

        //Anem a crear una medalla
        System.out.println("***Creació d'una medalla***");
        int posicio = Medalla.MEDALLA_ARGENT;
        Medalla m1 = new Medalla(posicio, a1);

        //Provem que ha funcionat la creació de la medalla i que se li ha assignat a l'atleta
        System.out.println(m1.descripcio());
        
        //Comprovem que ara el país té una medalla
        System.out.println("El país " + p1.nom + " té " + p1.getNumMedalles() + " medalles");
 
        //Creació de diversos països, atletes i medalles
        Pais paisUSA = new Pais("Estats Units d'Amèrica");
        Atleta atletaUSA1 = new Atleta("Simone Biles", paisUSA, "Gimnàstica");
        m1 = new Medalla(Medalla.MEDALLA_OR, atletaUSA1);
        Atleta atletaUSA2 = new Atleta("Noah Lyles", paisUSA, "Atletisme");
        m1 = new Medalla(Medalla.MEDALLA_OR, atletaUSA2);

        Pais paisFrance = new Pais("França");
        Atleta atletaFrance1 = new Atleta("Léon Marchand", paisFrance, "Natació");
        m1 = new Medalla(Medalla.MEDALLA_OR, atletaFrance1);
        m1 = new Medalla(Medalla.MEDALLA_OR, atletaFrance1);
        m1 = new Medalla(Medalla.MEDALLA_OR, atletaFrance1);
        Atleta atletaFrance2 = new Atleta("Charline Picon", paisFrance, "Vela");
        m1 = new Medalla(Medalla.MEDALLA_BRONZE, atletaFrance2);

        //Diferentes proves
        System.out.println("***Fer diferentes proves amb les dades introduïdes");
        System.out.println("En Léon Marchand ha guanyat 6 medalles? " + (atletaFrance1.getNumMedalles() == 6));
        System.out.println("Estats Units ha guanyat més medalles que França? " + (paisUSA.getNumMedalles() > paisFrance.getNumMedalles()));
        System.out.println("En total s'han repartit " + Medalla.getNumMedallesRepartides() + " medalles");
        System.out.println("Estats Units ha guanyat: " + paisUSA.getNumMedalles() + " medalles");
        System.out.println("França ha guanyat: " + paisFrance.getNumMedalles() + " medalles");
        System.out.println("Turquia ha guanyat: " + p1.getNumMedalles() + " medalles");

        Pais catalunya = new Pais("Catalunya");
        Pais euskadi = new Pais("Euskadi");
        Pais galicia = new Pais("Galícia");

        Atleta pep = new Atleta("Pep", catalunya, "Handbol");
        Atleta patxi = new Atleta("Patxi", euskadi, "Aizkolari");
        Atleta iago = new Atleta("Iago", galicia, "Billarda");

        Medalla medallaPep = new Medalla(2, pep);
        System.out.println(medallaPep.descripcio());
        System.out.println("Medalles del país: " + catalunya.getNumMedalles());
        System.out.println("Medalles de l'atleta: " + pep.getNumMedalles());

        Medalla medallaPatxi = new Medalla(4, patxi);
        System.out.println(medallaPatxi.descripcio());
        System.out.println("Medalles del país: " + euskadi.getNumMedalles());
        System.out.println("Medalles de l'atleta: " + patxi.getNumMedalles());

        Medalla medallaIago = new Medalla(-1, iago);
        System.out.println(medallaIago.descripcio());
        System.out.println("Medalles del país: " + galicia.getNumMedalles());
        System.out.println("Medalles de l'atleta: " + iago.getNumMedalles());

        Atleta atletaNum = new Atleta("1", new Pais("2"), "3");
        Medalla medallaAtletaNum = new Medalla(1, atletaNum);
        System.out.println(medallaAtletaNum.descripcio());
     }
}