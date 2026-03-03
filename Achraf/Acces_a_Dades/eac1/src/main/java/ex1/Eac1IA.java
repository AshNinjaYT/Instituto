package ex1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Classe principal per resoldre l'Exercici 1 de l'EAC1.
 * Creat com Eac1IA.java a petició de l'usuari.
 */
public class Eac1IA {

    /**
     * Mètode principal que s'executa a l'inici del programa.
     * Rep paràmetres per línia d'ordres per copiar o moure fitxers.
     *
     * @param args els arguments especificats en l'enunciat
     */
    public static void main(String[] args) {
        // Control d'errors: El nombre de paràmetres ha de ser entre quatre i cinc
        if (args.length < 4 || args.length > 5) {
            System.err.println("Error: El nombre de paràmetres ha de ser entre 4 i 5.");
            System.exit(2);
        }

        // Primer paràmetre: 'C' o 'M' (No distingeix majúscules/minúscules)
        String opcioAccioStr = args[0].toUpperCase();
        if (!opcioAccioStr.equals("C") && !opcioAccioStr.equals("M")) {
            System.err.println("Error: El primer paràmetre ha de ser 'C' o 'M'.");
            System.exit(2);
        }
        boolean moure = opcioAccioStr.equals("M");

        File origen = new File(".");
        
        // El document diu que si s'intenta moure ('M') i no hi ha permís d'escriptura, cal avisar.
        if (moure && !origen.canWrite()) {
            System.err.println("Error: L'operació no es pot fer perquè no hi ha permís d'escriptura a la carpeta origen.");
            System.exit(2);
        }

        // Segon paràmetre: nom de la carpeta destí
        File desti = new File(args[1]);
        if (!desti.exists() || !desti.isDirectory() || !desti.canWrite()) {
            System.err.println("Error: El segon paràmetre no és una carpeta vàlida o no s'hi té permís d'escriptura.");
            System.exit(2);
        }

        // Tercer paràmetre: 'M' o 'N'
        String opcioFiltreStr = args[2].toUpperCase();
        if (!opcioFiltreStr.equals("M") && !opcioFiltreStr.equals("N")) {
            System.err.println("Error: El tercer paràmetre ha de ser 'M' o 'N'.");
            System.exit(2);
        }

        // Quart paràmetre
        long midaMaxima = -1;
        String prefixNom = "";
        if (opcioFiltreStr.equals("M")) {
            try {
                midaMaxima = Long.parseLong(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("Error: El quart paràmetre ha de ser un número enter, si el tercer paràmetre és 'M'.");
                System.exit(2);
            }
        } else {
            prefixNom = args[3];
        }

        // Cinquè paràmetre (opcional)
        boolean incloureOcults = false;
        if (args.length == 5) {
            String opcioOcultStr = args[4].toUpperCase();
            if (!opcioOcultStr.equals("H")) {
                System.err.println("Error: El cinquè paràmetre no és una 'H'.");
                System.exit(2);
            }
            incloureOcults = true;
        }

        // Definició de variables finals per utilitzar dins la lambda de FileFilter
        final long filtreMidaMaxima = midaMaxima;
        final String filtrePrefix = prefixNom;
        final boolean filtreOcults = incloureOcults;

        // Crear el filtre utilitzant una funció lambda
        FileFilter filtre = (File fitxer) -> {
            // Només fitxers (no directoris)
            if (!fitxer.isFile()) {
                return false;
            }
            // Si el fitxer és ocult i no ens han demanat ocults, el descartem
            if (!filtreOcults && fitxer.isHidden()) {
                return false;
            }
            // Filtratge per mida ('M')
            if (opcioFiltreStr.equals("M")) {
                if (fitxer.length() > filtreMidaMaxima) {
                    return false;
                }
            } 
            // Filtratge per nom ('N')
            else {
                if (!fitxer.getName().startsWith(filtrePrefix)) {
                    return false;
                }
            }
            return true;
        };

        // Crida obligatòria al listFiles amb el FileFilter
        File[] fitxersTrobats = origen.listFiles(filtre);
        if (fitxersTrobats == null) {
            System.err.println("Error intern al llistar el directori.");
            System.exit(2);
        }

        int fitxersProcessats = 0;
        long midaTotalProcessada = 0;

        // Processament dels fitxers
        for (File fitxer : fitxersTrobats) {
            // "Per cada fitxer que no tingui permís de lectura, mostrar un missatge"
            if (!fitxer.canRead()) {
                System.out.println("No hi ha permís de lectura per al fitxer: " + fitxer.getName());
                continue;
            }

            File fitxerDesti = new File(desti, fitxer.getName());

            try {
                if (moure) {
                    Files.move(fitxer.toPath(), fitxerDesti.toPath(), StandardCopyOption.REPLACE_EXISTING);
                } else {
                    Files.copy(fitxer.toPath(), fitxerDesti.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }

                // Mostrar nom i etiqueta "(H)" si és ocult
                String etiquetaOcult = fitxer.isHidden() ? " (H)" : "";
                System.out.println(fitxer.getName() + etiquetaOcult + " " + fitxer.length() + " B");

                fitxersProcessats++;
                midaTotalProcessada += fitxer.length();

            } catch (IOException e) {
                System.err.println("S'ha produït un error d'E/S amb: " + fitxer.getName());
            }
        }

        // Resum final
        String accioCadenes = moure ? "moguts" : "copiats";
        System.out.println(fitxersProcessats + " fitxers " + accioCadenes + ", " + midaTotalProcessada + " B");
    }
}
