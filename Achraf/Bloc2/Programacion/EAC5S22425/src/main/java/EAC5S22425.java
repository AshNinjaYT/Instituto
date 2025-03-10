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
    FileUtils fileUtils = new FileUtils(Constants.DEFAULT_DATA_DIRECTORY);
    System.out.println(fileUtils.getFilePath(Constants.DEFAULT_FILE_NAME));
    
    Scanner scanner = new Scanner(System.in);

    io.showMenu(Constants.START_MENU_HEADER);
    io.showMenu(Constants.START_MENU);
    int opcion = scanner.nextInt();

    switch (opcion) {
      case 0:
        break;
      case 1:
        io.askForInteger(Constants.MESSAGE_DEFAULT_ASK_NUMBER, Constants.MESSAGE_DEFAULT_ERROR_INTEGER);
        io.askForString(Constants.MESSAGE_DEFAULT_ASK_NAME, Constants.MESSAGE_DEFAULT_ERROR_STRING);
        io.askForString(Constants.MESSAGE_DEFAULT_ASK_CITY, Constants.MESSAGE_DEFAULT_ERROR_STRING);
        io.askForFloat(Constants.MESSAGE_DEFAULT_ASK_LONG, Constants.MESSAGE_DEFAULT_ERROR_FLOAT);
        io.askForFloat(Constants.MESSAGE_DEFAULT_ASK_LATITUD, Constants.MESSAGE_DEFAULT_ERROR_FLOAT);
        break;
      case 2:
        break;
      case 3:
        break;

    }
    scanner.close();
  }

}
