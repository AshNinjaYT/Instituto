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
        if (!directory.exists()) {
            directory.mkdirs();
        }
        this.dataDirectory = System.getProperty("user.dir") + File.separator + directoryName;
    }

    public String getDataDirectory() {
        return dataDirectory;
    }

    public String getFilePath(String fileName) {
        return dataDirectory + File.separator + fileName;
    }

    private void inicialitzeWorkDirectory() {

    }

    public boolean createFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false; // No imprime nada si el texto del menú es null o vacío
        }
        File file = new File(getFilePath(fileName));
        try {
            if (fileExists(fileName)) {
                return false;
            } else {
                return file.createNewFile();
            }
        } catch (IOException e) {
            return false;
        }
    }

    public boolean deleteFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false; // No imprime nada si el texto del menú es null o vacío
        }
        File file = new File(getFilePath(fileName));
        if (fileExists(fileName)) {
            return file.delete();
        } else {
            return false;
        }
    }

    public boolean fileExists(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false; // No imprime nada si el texto del menú es null o vacío
        }
        File file = new File(getFilePath(fileName));
        return file.exists();
    }

    public String extractSupermarketList(String fileName) {
        // Verificar si el nombre del archivo es nulo o vacío
        if (fileName == null || fileName.isEmpty()) {
            return null; // Si el nombre de archivo es nulo o vacío, retornamos null
        }

        File file = new File(getFilePath(fileName));
        if (!file.exists()) {
            return null; // Si el archivo no existe, retornamos null
        }

        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // Se añaden los saltos de línea al final de cada línea
            }
        } catch (IOException e) {
            return null; // En caso de error al leer el archivo, retornamos null
        }

        // Normalizamos los saltos de línea a "\n" (Unix/Linux) antes de devolver el contenido
        return content.toString().replaceAll("\r\n", "\n");
    }

    public boolean insertMarketInfoIntoFile(String fileName, String supermarketName, String supermarketCity,
            float supermarketLongitude,
            float supermarketLatitude) {

    }
}// Fi FileUtils
