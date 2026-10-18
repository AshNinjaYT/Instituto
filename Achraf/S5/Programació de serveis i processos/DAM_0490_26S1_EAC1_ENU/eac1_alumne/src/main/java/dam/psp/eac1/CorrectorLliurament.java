package dam.psp.eac1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CorrectorLliurament {
    public boolean validar(Path ruta) {
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta del lliurament no pot ser null.");
        }

        try {
            Path fitxerLliurament = obtenirRutaFitxerLliurament(ruta);
            String contingut = llegirContingut(fitxerLliurament);
            return esLliuramentCorrecte(contingut);
        } catch (IllegalArgumentException | IOException e) {
            return false;
        }
    }

    public Path obtenirRutaFitxerLliurament(Path ruta) {
        // Valido que la ruta no sea null
        if (ruta == null) {
            throw new IllegalArgumentException("La ruta no pot ser null.");
        }

        // Si es una carpeta/directorio, buscamos el archivo lliurament.txt dentro
        if (Files.isDirectory(ruta)) {
            return ruta.resolve("lliurament.txt");
        }

        // Si ya nos pasan directamente la ruta a un fichero regular, la devolvemos tal cual
        if (Files.isRegularFile(ruta)) {
            return ruta;
        }

        // Si no es ninguna de las dos cosas, lanzamos error
        throw new IllegalArgumentException("La ruta no és ni un directori ni un fitxer regular: " + ruta);
    }

    public String llegirContingut(Path fitxer) throws IOException {
        // Valido que el fichero no sea null
        if (fitxer == null) {
            throw new IllegalArgumentException("El fitxer no pot ser null.");
        }

        // Comprobamos si el fichero existe, si es un archivo regular y si se puede leer
        if (!Files.exists(fitxer) || !Files.isRegularFile(fitxer) || !Files.isReadable(fitxer)) {
            throw new IOException("El fitxer no existeix, no és regular o no es pot llegir: " + fitxer);
        }

        // Leemos todo el contenido del archivo, quitamos espacios a los extremos con trim() y a minusculas
        String contingut = Files.readString(fitxer);
        return contingut.trim().toLowerCase();
    }

    public boolean esLliuramentCorrecte(String contingut) {
        // Valido que el texto no sea null
        if (contingut == null) {
            throw new IllegalArgumentException("El contingut no pot ser null.");
        }

        // Solo retornamos true si el texto es exactamente "correcte"
        return "correcte".equals(contingut);
    }
}

