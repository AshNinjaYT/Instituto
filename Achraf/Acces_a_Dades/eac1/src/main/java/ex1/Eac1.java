/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package ex1;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 *
 * @author joan (Modificat com a Eac1)
 */
public class Eac1 {

    /**
     * Mètode principal del programa
     *
     * @param args els arguments especificats en l'enunciat de l'EAC
     */
    public static void main(String[] args) {
        
        // Comprovació de nombre d'arguments
        if (args.length != 5) {
            System.err.println("Error: Nombre d'arguments incorrecte.");
            System.out.println("Us esperat: java Eac1 <opcio> <origen> <extensio> <text_contingut> <destinacio>");
            System.out.println("Exemple: java Eac1 c ./dir_origen txt hola ./dir_desti");
            return;
        }

        // Recol·lectem els arguments en variables més llegibles
        String actionStr = args[0].toLowerCase();
        String originDirPath = args[1];
        String extensionFilterStr = args[2];
        String contentFilterStr = args[3];
        String targetDirPath = args[4];

        // Validem l'opció (C = Copiar, M = Moure)
        boolean isMove = false;
        if (actionStr.equals("m")) {
            isMove = true;
        } else if (!actionStr.equals("c")) {
            System.err.println("Error: L'opcio (primer argument) ha de ser 'c' per copiar o 'm' per moure.");
            return;
        }

        // Objectes dels directoris d'origen i destinacio
        File originDir = new File(originDirPath);
        File targetDir = new File(targetDirPath);

        // Validem que el directori d'origen existeixi i sigui un directori navegable
        if (!originDir.exists() || !originDir.isDirectory()) {
            System.err.println("Error: El directori d'origen '" + originDirPath + "' no existeix o no es valid.");
            return;
        }

        // Validem el directori de destinacio, i intentem crear-lo si no existeix
        if (!targetDir.exists()) {
            boolean created = targetDir.mkdirs();
            if (!created) {
                System.err.println("Error: No s'ha pogut crear el directori de destinacio '" + targetDirPath + "'.");
                return;
            }
        } else if (!targetDir.isDirectory()) {
            System.err.println("Error: La ruta de destinacio '" + targetDirPath + "' ja existeix i no es un directori.");
            return;
        }

        // Creem el filtre per seleccionar quins fitxers del directori origen ens interesen
        // Hem de declarar contentFilterStr com a *effectively final* per la lambda.
        final String searchContent = contentFilterStr;

        FileFilter fileCriteria = file -> {
            // Nomes ens interesen arxius normals, no altres carpetes ni enllacos
            if (!file.isFile()) {
                return false;
            }

            // Comprovem si el nom acaba amb la extensio que el professor ha passat com argument
            if (!file.getName().endsWith("." + extensionFilterStr)) {
                return false;
            }

            // Comprovem si dins del fitxer de text existeix el substring (paraula o frase) de cerca
            try {
                String fileContent = new String(Files.readAllBytes(file.toPath()));
                if (!fileContent.contains(searchContent)) {
                    return false;
                }
            } catch (IOException e) {
                // Si per algun motiu el fitxer de text no es pot llegir, ens saltem l'arxiu i tornem false
                System.err.println("Advertencia: No s'ha pogut llegir " + file.getName());
                return false;
            }

            // L'arxiu reuneix totes les condicions, aixi que aquest si l'agafem
            return true;
        };

        // Apliquem el filtre que acabem de crear al directori origen i obtenim la llista de fitxers vàlids
        File[] matches = originDir.listFiles(fileCriteria);

        if (matches == null || matches.length == 0) {
            System.out.println("No s'ha trobat cap fitxer al directori origen que compleixi els criteris.");
            return;
        }

        // Processament dels fitxers (arxiu per arxiu)
        for (File currentFile : matches) {
            Path originPathInstance = currentFile.toPath();
            Path targetPathInstance = Paths.get(targetDirPath, currentFile.getName());

            try {
                if (isMove) {
                    // Moure arxiu: La mateixa comanda elimina l'original si es produeix l'exit de l'operacio
                    Files.move(originPathInstance, targetPathInstance, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("S'ha mogut el fitxer: " + currentFile.getName() + " -> " + targetPathInstance);
                } else {
                    // Copiar arxiu
                    Files.copy(originPathInstance, targetPathInstance, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("S'ha copiat el fitxer: " + currentFile.getName() + " -> " + targetPathInstance);
                }
            } catch (IOException e) {
                System.err.println("Error: S'ha produit un fallo inesperat al intentar " 
                                   + (isMove ? "moure" : "copiar") 
                                   + " el fitxer " + currentFile.getName() + ": " + e.getMessage());
            }
        }

        System.out.println("Processament Finalitzat.");
    }
}
