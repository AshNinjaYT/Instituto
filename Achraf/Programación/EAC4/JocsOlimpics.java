public class JocsOlimpics {

    public static void main(String[] args) {
        //Màxim d'elements
        final int MAX_ATLETES = 10;
        final int MAX_PAISOS = 5;

        //Arrays per controlar quins objectes ja tenim creats
        Atleta[] atletes = new Atleta[MAX_ATLETES];
        Pais[] paisos = new Pais[MAX_PAISOS];

        String unAltre;
        boolean trobat = false;
        int index;

        do {
            //Creació interactiva dels objectes necessaris per assignar una medalla
            //Aquí et sentiràs com un campió olímpic!
            System.out.println("*** REGISTRA UNA MEDALLA ****");  
            System.out.print("Quin és el país? ");
            String nomPais = InputUtils.readLine();
            try {
                //Comprovar si ja existeix el pais?
                trobat = false;
                index = 0;
                while (!trobat && paisos[index]!=null) {
                    if (paisos[index].nom.equals(nomPais)) trobat = true;
                    else index++;
                }

                //Si existeix el pais el recuperem, sino en fem un de nou
                Pais elPais;
                if (trobat)  elPais = paisos[index];
                else {
                    elPais = new Pais(nomPais);
                    paisos[index] = elPais;
                }

                System.out.print("Quin és el nom de l'atleta? ");
                String nomAtleta = InputUtils.readLine();
    
                //Comprovar si ja existeix l'atleta
                trobat = false;
                index = 0;
                while (!trobat && atletes[index]!=null) {
                    if (atletes[index].nom.equals(nomAtleta)) trobat = true;
                    else index++;
                }

                //Si existeix l'atleta el recuperem, sino en fem un de nou
                Atleta atleta;
                if (trobat)  atleta = atletes[index];
                else {
                    //Demanar l'esport de l'atleta
                    System.out.print("Quin és l'esport? ");
                    String esport = InputUtils.readLine();
                    atleta = new Atleta(nomAtleta, elPais, esport);
                    atletes[index] = atleta;
                }

                int laTevaPosicio = InputUtils.readInt("En quina posició has quedat (1, 2 o 3)?",1,3,"El valor ha de ser numèric. Torna-ho a provar: ");
                //Descarta el salt de línia de la lectura anterior
                InputUtils.readLine();
                Medalla laTevaMedalla = new Medalla(laTevaPosicio, atleta);
                System.out.println(laTevaMedalla.descripcio() );
                System.out.println();
                System.out.println("Medalles de l'atleta " + atleta.nom);
                atleta.mostrarMedalles();
                System.out.println();
                System.out.println("Medalles del país " + elPais.nom );
                elPais.mostrarMedalles();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ja hi han massa països o massa atletes. Aquesta medalla no la podem registrar.");
            }

            System.out.print("Vols fer un altre (S/N)? ");
            unAltre = InputUtils.readLine().toUpperCase();
        } while (!unAltre.equals("N"));

        
        InputUtils.close();
    }
}
