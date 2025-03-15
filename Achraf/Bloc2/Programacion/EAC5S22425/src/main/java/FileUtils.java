import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {
    private final String dataDirectory;

    // create constructur
    public FileUtils(String directoryName) {

        File directory = new File(directoryName);
        // Verifica si el directorio no existe, y en ese caso lo crea
        if (!directory.exists()) {
            directory.mkdirs(); // Crea el directorio
        }
        // Guarda la ruta del directorio de datos usando la ubicación del usuario y el separador del sistema en uso
        this.dataDirectory = System.getProperty("user.dir") + File.separator + directoryName;
    }

    // Devuelve la ruta del directorio donde se guardan los archivos
    public String getDataDirectory() {
        return dataDirectory;
    }

    // Devuelve la ruta completa de un archivo dentro del directorio de datos
    public String getFilePath(String fileName) {
        return dataDirectory + File.separator + fileName;
    }

    // Crea un nuevo archivo si no existe
    public boolean createFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) { // Verifica que el nombre del archivo no sea nulo o vacío
            return false;
        }
        File file = new File(getFilePath(fileName)); // Crea un objeto File con la ruta completa
        try {
            if (fileExists(fileName)) { // Verifica si el archivo ya existe
                return false;
            } else {
                return file.createNewFile(); // Crea el archivo y devuelve true si se crea correctamente
            }
        } catch (IOException e) {
            return false; // Si ocurre un error al crear el archivo, devuelve false
        }
    }

    // Elimina un archivo si existe
    public boolean deleteFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) { // Verifica que el nombre del archivo no sea nulo o vacío
            return false;
        }
        File file = new File(getFilePath(fileName)); // Crea un objeto File con la ruta completa
        if (fileExists(fileName)) { // Verifica si el archivo existe
            return file.delete(); // Intenta eliminar el archivo y devuelve true si tiene éxito
        } else {
            return false;
        }
    }

    // Comprueba si un archivo existe en el directorio de datos
    public boolean fileExists(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }
        File file = new File(getFilePath(fileName)); // Crea un objeto File con la ruta completa
        return file.exists(); // Devuelve true si el archivo existe
    }

    // Extrae el contenido de un archivo y lo devuelve como un String
    public String extractSupermarketList(String fileName) {
        if (fileName == null || fileName.isEmpty()) { // Verifica que el nombre del archivo no sea nulo o vacío
            return null;
        }

        File file = new File(getFilePath(fileName)); // Crea un objeto File con la ruta completa
        if (!file.exists()) { // Si el archivo no existe, retorna null
            return null;
        }

        StringBuilder content = new StringBuilder(); // Almacena el contenido del archivo
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) { // Lee el archivo línea por línea
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // Agrega cada línea al StringBuilder
            }
        } catch (IOException e) {
            return null; // Si ocurre un error al leer el archivo, devuelve null
        }
        return content.toString().replaceAll("\r\n", "\n"); // Normaliza los saltos de línea
    }

    // Inserta información de un supermercado en un archivo
    public boolean insertMarketInfoIntoFile(String fileName, String supermarketName, String supermarketCity,
            float supermarketLongitude, float supermarketLatitude) {
        // Verifica que ninguno de los parámetros obligatorios sea nulo o vacío
        if (fileName == null || fileName.isEmpty() || supermarketName == null || supermarketName.isEmpty()
                || supermarketCity == null || supermarketCity.isEmpty()) {
            return false;
        }

        File file = new File(getFilePath(fileName)); // Crea un objeto File con la ruta completa

        // Formatea la fecha y hora actual en un formato predefinido
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_FORMAT);
        String timestamp = LocalDateTime.now().format(formatter);

        // Construye la línea de información del supermercado a insertar
        String line = timestamp + "," + supermarketName + "," + supermarketCity + "," + supermarketLongitude + ","
                + supermarketLatitude + "\n";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            return true; // Retorna true si la escritura fue exitosa
        } catch (IOException e) {
            return false; // Si ocurre un error al escribir, devuelve false
        }

    }
}// Fi FileUtils
