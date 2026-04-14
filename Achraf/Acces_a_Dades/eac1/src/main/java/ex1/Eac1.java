/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ex1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author joan (Modificado)
 */
public class Eac1 {

    /**
     * Metodo principal del programa
     *
     * @param args los argumentos especificados en el enunciado del EAC
     */
    public static void main(String[] args) {
        
        // El numero de parametros no esta entre cuatro y cinco
        if (args.length < 4 || args.length > 5) {
            System.err.println("Error: El numero de parametros debe ser 4 o 5.");
            System.exit(2);
        }

        // El primer parametro no es C ni M
        String opcio = args[0].toUpperCase();
        if (!opcio.equals("C") && !opcio.equals("M")) {
            System.err.println("Error: El primer parametro debe ser C o M.");
            System.exit(2);
        }

        // El segundo parametro no es una carpeta valida o no se tiene permiso de escritura
        File destDir = new File(args[1]);
        if (!destDir.exists() || !destDir.isDirectory() || !destDir.canWrite()) {
            System.err.println("Error: El segundo parametro no es una carpeta valida o no tiene permiso de escritura.");
            System.exit(2);
        }

        // El tercer parametro no es ni M ni N
        String criteri = args[2].toUpperCase();
        if (!criteri.equals("M") && !criteri.equals("N")) {
            System.err.println("Error: El tercer parametro debe ser M o N.");
            System.exit(2);
        }

        long maximaMida = -1;
        String iniciNom = null;

        if (criteri.equals("M")) {
            // El cuarto parametro debe ser un numero entero si el tercero es M
            try {
                maximaMida = Long.parseLong(args[3]);
            } catch (NumberFormatException e) {
                System.err.println("Error: El cuarto parametro debe ser un numero entero.");
                System.exit(2);
            }
        } else {
            iniciNom = args[3];
        }

        boolean incloureOcults = false;
        // El quinto parametro no es una H
        if (args.length == 5) {
            if (!args[4].toUpperCase().equals("H")) {
                System.err.println("Error: El quinto parametro debe ser la letra H.");
                System.exit(2);
            }
            incloureOcults = true;
        }

        // Directorio de trabajo (origen)
        File workDir = new File(".");

        // Si es la opcion de Mover (M) y no tenemos permiso de escritura en la carpeta origen (para borrarlos tras mover)
        if (opcio.equals("M") && !workDir.canWrite()) {
            System.out.println("La operacion de mover no se puede realizar porque no hay permiso de escritura en la carpeta de origen.");
            return; // En este caso el enunciado indica de "mostrar un missatge", no forzar System.exit(2). Salimos amablemente.
        }

        // Declaracion de variables efectivamente finales para usarlas dentro de la lambda
        final long fMaximaMida = maximaMida;
        final String fIniciNom = iniciNom;
        final boolean fIncloureOcults = incloureOcults;

        // Creacion del FileFilter
        FileFilter filtre = file -> {
            // Solo procesamos archivos, no directorios
            if (!file.isFile()) {
                return false;
            }

            // Excluir ocultos si no esta la H
            if (file.isHidden() && !fIncloureOcults) {
                return false;
            }

            // Filtrado por Mida (M) o Nom (N)
            if (criteri.equals("M")) {
                if (file.length() > fMaximaMida) {
                    return false;
                }
            } else {
                if (!file.getName().startsWith(fIniciNom)) {
                    return false;
                }
            }

            return true;
        };

        // LLamada obligatoria a listFiles pasandole nuestro FileFilter
        File[] arxiusProcessar = workDir.listFiles(filtre);
        if (arxiusProcessar == null) {
            arxiusProcessar = new File[0];
        }

        int totalArxius = 0;
        long midaTotalBytes = 0;

        for (File f : arxiusProcessar) {
            // Ficheros sin permiso de lectura, mostramos un mensaje y seguimos con el siguiente
            if (!f.canRead()) {
                System.out.println("El archivo " + f.getName() + " no tiene permisos de lectura e sera ignorado.");
                continue;
            }

            File fitxerDesti = new File(destDir, f.getName());
            boolean success = false;

            try {
                if (opcio.equals("C")) {
                    Files.copy(f.toPath(), fitxerDesti.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    success = true;
                } else if (opcio.equals("M")) {
                    Files.move(f.toPath(), fitxerDesti.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    success = true;
                }
            } catch (IOException e) {
                System.out.println("Fallo al procesar el archivo: " + f.getName());
            }

            // Si se ha movido o copiado exitosamente, imprimimos el resultado de este archivo y acumulamos stats
            if (success) {
                totalArxius++;
                long midaFitxer = fitxerDesti.length();
                midaTotalBytes += midaFitxer;

                // Formateo del mensaje con (H) y la size con B
                String identificadorOcult = f.isHidden() ? " (H)" : "";
                System.out.println(f.getName() + identificadorOcult + " " + midaFitxer + "B");
            }
        }

        // Resumen final
        System.out.println("\nResumen:");
        System.out.println("Numero de ficheros procesados: " + totalArxius);
        System.out.println("Tamano total: " + midaTotalBytes + "B");
    }
}
