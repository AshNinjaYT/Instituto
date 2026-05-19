/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ex1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author joan
 */
public class Eac1 {

    /**
     * Mètode principal del programa
     *
     * @param args els arguments especificats en l'enunciat de l'EAC
     */
    public static void main(String[] args) {
        try {
            if (args.length < 4 || args.length > 5) {
                throw new IllegalArgumentException("Número de paràmetres incorrectes. Ha de ser entre 5 i 6");
            }

            File[] files = seleccionaFitxers(args);

            if (args[0].toUpperCase().equals("C")) {
                copiaFitxers(files, args[1]);
            } else if (args[0].toUpperCase().equals("M")) {
                // Només es poden moure fitxers si la carpeta té permís d'escriptura
                File carpeta = new File(System.getProperty("user.dir"));;
                if (carpeta.canWrite()) {
                    mouFitxers(files, args[1]);
                } else {
                    throw new IllegalArgumentException("La carpeta origen no té permís d'escriptura");
                }
            } else {
                throw new IllegalArgumentException("El primer paràmetre ha de ser una 'C' o una 'M'");
            }

        } catch (IllegalArgumentException e) {

            System.err.print(e.getMessage());
            System.exit(2);
        }

    }

    /**
     * Retorna els fitxers seleccionats segons els arguments del programa
     *
     * @param args els arguments
     * @return un array amb els fitxers seleccionats
     */
    private static File[] seleccionaFitxers(String[] args) {
        File dir = new File(System.getProperty("user.dir"));

        return dir.listFiles((file) -> {
            // Comprovem si s'han de mostrar els fitxers ocults
            boolean seleccionaOcults = false;
            if (args.length == 5) {
                if (args[4].toUpperCase().equals("H")) {
                    seleccionaOcults = true;
                } else {
                    throw new IllegalArgumentException("El cinquè paràmetre ha de ser H");
                }
            }
            /* Si s'han de mostrar ocults o el fitxer no és ocult,
                 mirar si s'han de seleccionar per mida o per nom */
            if (!file.isHidden() || seleccionaOcults) {
                switch (args[2].toUpperCase()) {
                    case "M" -> {
                        // Cal capturar excepció si no es pot fer la conversió
                        try {
                            if (file.length() <= Integer.parseInt(args[3])) {
                                return true;
                            }
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("El tercer paràmetre ha de ser un número si el segon és una M");
                        }
                    }
                    case "N" -> {
                        if (file.getName().startsWith(args[3])) {
                            return true;
                        }
                    }
                    default ->
                        throw new IllegalArgumentException("El tercer paràmetre ha de ser M o N");
                }
            }
            return false;
        });

    }

    /**
     * Esborra un conjunt de fitxers
     *
     * @param files array amb els fitxers a esborrar
     */
    private static void copiaFitxers(File[] files, String desti) {
        File carpetaDesti = new File(desti);
        if (carpetaDesti.isDirectory() && carpetaDesti.canWrite()) {
            System.out.println("Copiant fitxers ...");
            var bytesTotalsCopiats = 0;
            int numFitxersCopiats = 0;
            for (File file : files) {
                // Comprovem que no sigui un directori abans de copiar
                if (file.isFile()) {
                    try {

                        copiaDeBytes(new FileInputStream(file),
                                new FileOutputStream(new File(desti + "/" + file.getName())));
                    } catch (IOException ex) {
                        Logger.getLogger(Eac1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(file.getName() + (file.isHidden() ? " (H)" : "") + ", " + file.length() + " B");

                    // Acumulem la quantitat de fitxers i bytes
                    bytesTotalsCopiats += file.length();
                    numFitxersCopiats++;
                }
            }

            System.out.println("\n" + numFitxersCopiats + " fitxer/s copiat/s, " + bytesTotalsCopiats + " B");
        } else {
            throw new IllegalArgumentException("La carpeta de destí no és vàlida");
        }
    }

    private static void copiaDeBytes(InputStream input, OutputStream output)
            throws IOException {
        int bytesLlegits = 0;
        byte[] buffer = new byte[1024];
        while (bytesLlegits != -1) {
            output.write(buffer, 0, bytesLlegits);
            bytesLlegits = input.read(buffer);
        }
    }

    /**
     * Mostra per pantalla el llistat de fitxers
     *
     * @param files array de fitxers a mostrar
     */
    private static void mouFitxers(File[] files, String desti) {
        File carpetaDesti = new File(desti);
        if (carpetaDesti.isDirectory() && carpetaDesti.canWrite()) {
            System.out.println("Movent fitxers ...");
            int bytesTotalsCopiats = 0;
            int numFitxersMoguts = 0;
            for (File file : files) {
                // Comprovem que no sigui un directori abans de copiar
                if (file.isFile()) {

                    // Si el fitxer té permís d'escriptura
                    file.renameTo(new File(desti + "/" + file.getName()));
                    System.out.println(file.getName() + (file.isHidden() ? " (H)" : "") + ", " + file.length() + " B");

                    // Acumulem la quantitat de fitxers i bytes
                    bytesTotalsCopiats += file.length();
                    numFitxersMoguts++;
                }

            }

            System.out.println("\n" + numFitxersMoguts + " fitxer/s mogut/s");
        } else {
            throw new IllegalArgumentException("La carpeta de destí no és vàlida");
        }
    }

}
