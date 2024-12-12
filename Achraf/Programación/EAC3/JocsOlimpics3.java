import java.util.ArrayList;
import java.util.Scanner;

public class JocsOlimpics3 {
    public static void main(String[] args) {
        // crear lArrayListes per guardar els atletes
        ArrayList<Pais> paisos = new ArrayList<>();
        ArrayList<Atleta> atletes = new ArrayList<>();;


        // el max de atletes i paisos
        final int MAX_PAISOS = 5;
        final int MAX_ATLETES = 10;


        Scanner scanner = new Scanner(System.in);
        boolean sortir = false;


        while (!sortir) {
            System.out.println("*** GUANYA LA TEVA MEDALLA ***");


            System.out.print("Quin es el teu pais? ");
            String nomPais = scanner.nextLine();
            Pais pais = buscarPais(paisos, nomPais);


            if (pais == null) {
                if (paisos.size() < MAX_PAISOS) {
                    pais = new Pais(nomPais);
                    paisos.add(pais);
                } else {
                    System.out.println("No es poden afegir més de " + MAX_PAISOS + " paisos.");
                    continue;
            }


            System.out.print("Quin és el teu nom? ");
            String nomAtleta = scanner.nextLine();
            Atleta atleta = buscarAtleta(atletes, nomAtleta, pais);


            if (atleta == null) {
                if (atletes.size() < MAX_ATLETES) {
                    System.out.print("Quin és el teu esport favorit? ");
                    String disciplina = scanner.nextLine();
                    atleta = new Atleta(nomAtleta, pais, disciplina);
                    atletes.add(atleta);
                } else {
                    System.out.println("No es poden afegir mes de " + MAX_ATLETES + " atletes.");
                    continue;
                }
            }


            System.out.print("En quina posició has quedat (1, 2 o 3)? ");
            int posicio = Integer.parseInt(scanner.nextLine());


            Medalla medalla = new Medalla(posicio, atleta);
            atleta.guanyarMedalla(medalla);


            System.out.println(medalla.descripcio());
            System.out.println("Medalles de l'atleta " + atleta.nom + ": " + atleta.getNumMedalles());
            System.out.println("Medalles del país " + pais.nom + ": " + pais.getNumMedalles());


            // Demanar si vol continuar
            System.out.print("Vols continuar? (sí/no): ");
            String resposta = scanner.nextLine();
            sortir = resposta.equalsIgnoreCase("no");
        }


        System.out.println("Gracies per participar als Jocs Olimpics!");
        scanner.close();}
    }


    // modo per buscar un país a la lArrayLista
     static Pais buscarPais(ArrayList<Pais> paisos, String nomPais) {
        for (Pais pais : paisos) {
            if (pais.nom.equalsIgnoreCase(nomPais)) {
                return pais;
            }
        }
        return null;
    }


    // modo per buscar un atleta a la lArrayLista
     static Atleta buscarAtleta(ArrayList<Atleta> atletes, String nomAtleta, Pais pais) {
        for (Atleta atleta : atletes) {
            if (atleta.nom.equalsIgnoreCase(nomAtleta) && atleta.pais.equals(pais)) {
                return atleta;
            }
        }
        return null;
    }
}

