public class JocsOlimpics2 {

    public static void main(String[] args) {
 
        //Creació interactiva dels objectes necessaris per assignar una medalla
        //Aquí et sentiràs com un campió olímpic!
        System.out.println("*** GUANYA LA TEVA MEDALLA ****");
        System.out.print("Quin és el teu país? ");
        String nomPais = InputUtils.readLine();
        Pais elTeuPais = new Pais(nomPais);
        System.out.print("Quin és el teu nom? ");
        String elTeuNom = InputUtils.readLine();
        System.out.print("Quin és el teu esport favorit? ");
        String elTeuEsport = InputUtils.readLine();
        Atleta tu = new Atleta(elTeuNom, elTeuPais, elTeuEsport);
        System.out.print("En quina posició has quedat (1, 2 o 3)? ");
        int laTevaPosicio = InputUtils.readInt();
        Medalla laTevaMedalla = new Medalla(laTevaPosicio, tu);
        System.out.println(laTevaMedalla.descripcio() );
        System.out.println("Medalles de l'atleta " + tu.nom + " : " + tu.getNumMedalles());
        System.out.println("Medalles del país " + elTeuPais.nom + " : " + elTeuPais.getNumMedalles());

        InputUtils.close();
    }
}
