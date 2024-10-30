import java.util.Scanner;

public class JocsOlimpics {

    public static void main(String[] args) {
        //Començem els jocs olímpics
        System.out.println("***Benvinguts al sistema de proves dels Jocs Olímpics de París 2024***");
        System.out.println("Com que acabem de començar, ara mateix s'han repartit " + Medalla.getNumMedallesRepartides() + " medalles");
        
        //crearem el pais
        System.out.println("***Creació d'un país***");
        String paisTurquia = "Turquia";
        Pais p1 = new Pais(paisTurquia);
        // Provem que si ha funcionat
        System.out.println("El país es diu " + p1.nom + " i té " +0+ " medalles");

        // Crearem un atleta
        System.out.println("***Creació d'un atleta***");
        String nomAtleta = "Yusuf Dikec";
        Pais paisAtleta = p1;
        String disciplina = "Tir olímpic";
        Atleta a1 = new Atleta(nomAtleta, paisAtleta, disciplina);

        //Provem que ha funcionat la creació de l'atleta
        System.out.println("L'atleta es diu " +nomAtleta+" és de " + a1.pais.nom + " i participa a la competició de " + a1.disciplina);

        //Anem a crear una medalla
        System.out.println("***Creació d'una medalla***");
        int posicio = Medalla.MEDALLA_ARGENT;
        Medalla m1 = new Medalla(posicio, a1);

        //Provem que si ha funcionat
        System.out.println("L'atleta " + nomAtleta + " ha guanyat una medalla en obtenir la posició " + m1.posicio + " a la seva prova de Tir olímpic");

        //Comprovem si el pais te una medalla
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

        //Diferents proves per verificar-ho tot
        System.out.println("***Fer diferentes proves amb les dades introduïdes");
        System.out.println("En Léon Marchand ha guanyat 6 medalles? " + (atletaFrance1.getNumMedalles() == 6));
        System.out.println("Estats Units ha guanyat més medalles que França? " + (paisUSA.getNumMedalles() > paisFrance.getNumMedalles()));
        System.out.println("En total s'han repartit " + Medalla.getNumMedallesRepartides() + " medalles");
        System.out.println("Estats Units ha guanyat: " + paisUSA.getNumMedalles() + " medalles");
        System.out.println("França ha guanyat: " + paisFrance.getNumMedalles() + " medalles");
        System.out.println("Turquia ha guanyat: " + p1.getNumMedalles() + " medalles");
 
        //Creació interactiva dels objectes necessaris per assignar una medalla
        //Aquí et sentiràs com un campió olímpic!
        System.out.println("*** GUANYA LA TEVA MEDALLA ****");  
        System.out.print("Quin és el teu país? ");
        Scanner scan = new Scanner(System.in);
        String nomPais = scan.nextLine();;
        Pais elTeuPais = new Pais(nomPais);
        System.out.print("Quin és el teu nom? ");
        String elTeuNom = scan.nextLine();
        System.out.print("Quin és el teu esport favorit? ");
        String elTeuEsport = scan.nextLine();
        Atleta tu = new Atleta(elTeuNom, elTeuPais, elTeuEsport);
        System.out.print("En quina posició has quedat (1, 2 o 3)? ");
        int laTevaPosicio = scan.nextInt();
        Medalla laTevaMedalla = new Medalla(laTevaPosicio, tu);
        System.out.println(laTevaMedalla.descripcio());
        scan.close();
    }
}