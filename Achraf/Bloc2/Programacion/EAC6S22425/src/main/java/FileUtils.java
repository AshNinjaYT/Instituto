import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    private final String dataDirectory;

    public FileUtils(String directoryName) {
        dataDirectory = System.getProperty("user.dir") + File.separator + directoryName;
        inicialitzeWorkDirectory();
    }

    public String getDataDirectory() {
        return dataDirectory;
    }

    public String getFilePath(String fileName) {
        String directory = dataDirectory.concat(File.separator).concat(fileName);
        return directory;
    }

    private void inicialitzeWorkDirectory() {
        File fDir = new File(dataDirectory);
        if (!fDir.exists()) {
            fDir.mkdir();
        }
    }

    public void createFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_FILE_NAME_NULL);
        }
        if (fileExists(fileName)) {
            throw new IllegalArgumentException(Constants.ERROR_FILE_EXISTS);
        }
        File fFile = new File(getFilePath(fileName));
        try {
            fFile.createNewFile();
        } catch (IOException e) {
            throw new IllegalArgumentException(Constants.ERROR_CREATING_FILE);
        }
    }

    public void deleteFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_FILE_NAME_NULL);
        }
        if (!fileExists(fileName)) {
            throw new IllegalArgumentException(Constants.ERROR_FILE_NOT_EXIST);
        }
        new File(getFilePath(fileName)).delete();
    }

    public boolean fileExists(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }
        return new File(getFilePath(fileName)).exists();
    }

    public String extractFileIntoString(String fileName) {
        if (fileName == null || fileName.isEmpty() || !fileExists(fileName)) {
            return null;
        }
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            return null;
        }
        return content.toString();
    }

    public void createFileFromString(String fileName, String content) {
        if (fileName == null || fileName.isEmpty() || content == null || content.isEmpty()) {
            throw new IllegalArgumentException(Constants.ERROR_CONTENT_NULL);
        }
        if (fileExists(fileName)) {
            deleteFile(fileName);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getFilePath(fileName)))) {
            writer.write(content);
        } catch (IOException e) {
            throw new IllegalArgumentException(Constants.ERROR_WRITING_FILE);
        }
    }

}
