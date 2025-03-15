public class Constants {

    // Diseño para la tabla
    public static final String SEPARADOR = "------------------------------------------------------------------------------------------";
    public static final String DATE_FORMAT = "yyyyMMddHHmm";
    public static final String PRINT_FORMAT = "%-15s %-20s %-25s %-13s %-13s%n";

    // Backend
    public static final String DEFAULT_FILE_NAME = "supermarkets.txt";
    public static final String DEFAULT_DATA_DIRECTORY = "data";

    // Contenido de la tabla
    public static final String MESSAGE_DEFAULT_ASK_NUMBER = "Introdueixi un valor enter per l'opció";
    public static final String MESSAGE_DEFAULT_ASK_NAME = "Introdueixi el nom del supermercat";
    public static final String MESSAGE_DEFAULT_ASK_CITY = "A quina ciutat es troba?";
    public static final String MESSAGE_DEFAULT_ASK_LENGTH = "Introdueixi la longitud on es troba el supermercat";
    public static final String MESSAGE_DEFAULT_ASK_LATITUDE = "Introdueixi la latitud on es troba el supermercat";
    public static final String MESSAGE_DEFAULT_ASK_STRING = "Introdueixi una cadena de text";
    public static final String MESSAGE_DEFAULT_ERROR_STRING = "S'ha introduït un text buit";
    public static final String MESSAGE_DEFAULT_ASK_INTEGER = "Introdueixi un valor enter";
    public static final String MESSAGE_DEFAULT_ERROR_INTEGER = "El valor introduït no correspon a un enter";
    public static final String MESSAGE_DEFAULT_ASK_FLOAT = "Introdueixi un valor amb decimals per l'opció";
    public static final String MESSAGE_DEFAULT_ERROR_FLOAT = "El valor introduït no correspon a un nombre amb decimals";

    // Texto del menu
    public static final String ERROR_MENU_HEADER = "ERROR";
    public static final String MESSAGE_NOT_VALID_OPTION = "No s'ha introduït una opció vàlida";
    public static final String INFO_MENU_HEADER = "INFO";
    public static final String MESSAGE_ERROR_DELETION_CANCELLED = "Cancel·lat per petició de l'usuari";
    public static final String START_MENU_HEADER = "GESTIO IOC SUPERMARKETS";
    public static final String START_MENU = """
            1) Afegir supermercat al llistat.
            2) Veure el llistat de supermercats.
            3) Esborrar arxiu de sortida.
            0) Sortir.
            """;
}
