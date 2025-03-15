import java.io.File;
import java.nio.channels.FileLock;
import java.util.Scanner;

public class EAC5S22425 {

  public static void main(String[] args) {
    EAC5S22425 program = new EAC5S22425();
    program.start();

  }

  public void start() {
    UtilsIO io = new UtilsIO();
    FileUtils fu = new FileUtils(Constants.DEFAULT_DATA_DIRECTORY);

    fu.getFilePath(Constants.DEFAULT_FILE_NAME); // Obtiene la ruta del archivo predeterminado
    io.showMenu(Constants.START_MENU_HEADER); // Muestra el encabezado del menú de inicio
    io.showMenu(Constants.START_MENU); // Muestra el menú de inicio con las opciones disponibles

    selectroMenu(fu, io); // Llama al método que gestiona la selección de opciones del menú

  }

  private void selectroMenu(FileUtils fu, UtilsIO io) {
    Scanner scanner = new Scanner(System.in);
    int opcion = scanner.nextInt();

    switch (opcion) {
      case 0:
        exit(fu, io);// Si la opción es 0, ejecuta la función para salir del programa
        break;
      case 1:
        questions(fu, io); // Si la opción es 1, inicia el proceso de preguntas para agregar un
                           // supermercado
        break;
      case 2:
        io.showSupermarkets(fu.extractSupermarketList(Constants.DEFAULT_FILE_NAME)); // Si la opción es 2, muestra la
                                                                                     // lista de supermercados
                                                                                     // almacenada en el archivo
        break;
      case 3:
        fu.deleteFile(Constants.DEFAULT_FILE_NAME); // Si la opción es 3, elimina el archivo de supermercados
        break;
      default:
        error(fu, io); // Si la opción no es válida, muestra un mensaje de error

    }

    // Cierra el Scanner (esto puede causar problemas si se vuelve a usar en otros
    // métodos)
    scanner.close();
  }

  private void questions(FileUtils fu, UtilsIO io) {
    // Pregunta al usuario por un número entero
    io.askForInteger(Constants.MESSAGE_DEFAULT_ASK_NUMBER, Constants.MESSAGE_DEFAULT_ERROR_INTEGER);
    // Pregunta por el nombre del supermercado
    String name = io.askForString(Constants.MESSAGE_DEFAULT_ASK_NAME, Constants.MESSAGE_DEFAULT_ERROR_STRING);
    // Pregunta por la ciudad donde se encuentra el supermercado
    String name_city = io.askForString(Constants.MESSAGE_DEFAULT_ASK_CITY, Constants.MESSAGE_DEFAULT_ERROR_STRING);
    // Pregunta por la longitud geográfica del supermercado
    float length = io.askForFloat(Constants.MESSAGE_DEFAULT_ASK_LENGTH, Constants.MESSAGE_DEFAULT_ERROR_FLOAT);
    // Pregunta por la latitud geográfica del supermercado
    float latitude = io.askForFloat(Constants.MESSAGE_DEFAULT_ASK_LATITUDE, Constants.MESSAGE_DEFAULT_ERROR_FLOAT);

    // Inserta la información del supermercado en el archivo
    fu.insertMarketInfoIntoFile(Constants.DEFAULT_FILE_NAME, name, name_city, length, latitude);
  }

  private void exit(FileUtils fu, UtilsIO io) {
    // Muestra un mensaje de información
    io.showInfo(Constants.INFO_MENU_HEADER);
    io.showInfo(Constants.MESSAGE_ERROR_DELETION_CANCELLED);

    System.exit(0); // Finaliza la ejecución del programa

  }

  private void error(FileUtils fu, UtilsIO io) {
    // Muestra un mensaje indicando que no es válida
    io.showInfo(Constants.ERROR_MENU_HEADER);
    io.showInfo(Constants.MESSAGE_NOT_VALID_OPTION);
  }

}
