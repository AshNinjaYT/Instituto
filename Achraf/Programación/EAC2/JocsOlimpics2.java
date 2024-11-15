public class JocsOlimpics2 {

    public static void main(String[] args) {
        Pais catalunya = new Pais("Catalunya");
        Pais euskadi = new Pais("Euskadi");
        Pais galicia = new Pais("Galícia");

        Atleta pep = new Atleta("Pep", catalunya, "handbol");
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
        // Creació interactiva dels objectes necessaris per assignar una medalla
        // Aquí et sentiràs com un campió olímpic!
       /* System.out.println("*** GUANYA LA TEVA MEDALLA ****");
        System.out.print("Quin és el teu país? ");
        Scanner scan = new Scanner(System.in);
        String nomPais = scan.nextLine();
        Pais elTeuPais = new Pais(nomPais);
        System.out.print("Quin és el teu nom? ");
        String elTeuNom = scan.nextLine();
        System.out.print("Quin és el teu esport favorit? ");
        String elTeuEsport = scan.nextLine();
        Atleta tu = new Atleta(elTeuNom, elTeuPais, elTeuEsport);*/
    }
}