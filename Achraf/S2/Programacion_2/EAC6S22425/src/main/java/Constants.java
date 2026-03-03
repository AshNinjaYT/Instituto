public class Constants {

    public static final String FILE_NAME = ""; // Vacio hasta que añadan un archivo
    public static final String DEFAULT_DATA_DIRECTORY = "data";

    public static final String MESSAGE_DEFAULT_ASK_STRING = "Introdueixi una cadena de text";
    public static final String MESSAGE_DEFAULT_ERROR_STRING = "S'ha introduït un text buit";
    public static final String MESSAGE_DEFAULT_ASK_INTEGER = "Introdueixi un valor enter";
    public static final String MESSAGE_DEFAULT_ERROR_INTEGER = "El valor introduït no correspon a un enter";
    public static final String MESSAGE_DEFAULT_ERROR_NEGATIVE_INTEGER = "El preu no pot ser 0 o negatiu";
    public static final String MESSAGE_DEFAULT_ASK_FLOAT = "Introdueixi un valor amb decimals per l'opció";
    public static final String MESSAGE_DEFAULT_ERROR_FLOAT = "El valor introduït no correspon a un nombre amb decimals";
    public static final String MESSAGE_ASK_OPTION_VALUE = "Introdueixi un valor enter per l'opció";
    public static final String MESSAGE_NOT_VALID_OPTION = "No s'ha introduït una opció vàlida";
    public static final String ERROR_NO_INTEGER = "No s'ha introduït un número enter";
    public static final String ERROR_NO_FLOAT = "No s'ha introduït un número amb decimals";
    public static final String ERROR_EMPTY_STRING = "S'ha introduït un text buit";

    public static final String MESSAGE_INSERT_MARKET_NAME = "Introdueixi el nom del supermercat";
    public static final String MESSAGE_INSERT_CITY = "A quina ciutat es troba?";
    public static final String MESSAGE_INSERT_LONGITUDE = "Introdueixi la longitud on es troba el supermercat";
    public static final String MESSAGE_INSERT_LATITUDE = "Introdueixi la latitud on es troba el supermercat";
    public static final String MESSAGE_INSERT_NAME_PRODUCT = "Introdueixi el nom del producte";
    public static final String MESSAGE_INSERT_PRICE = "Introdueixi el preu del producte";

    public static final String MESSAGE_CONFIRM_DELETION = "Està segur que vol esborrar l'arxiu de sortida? (S/N)";
    public static final String MESSAGE_CONFIRMED_DELETION = "Arxiu esborrat i creat de nou satisfactòriament";
    public static final String MESSAGE_FILES_CREATED = "Supermercats i productes exportats correctament";
    public static final String MESSAGE_FILES_IMPORTED = "Supermercats i productes importats correctament";

    public static final String ERROR_DELETION_CANCELED = "Cancel·lat per petició de l'usuari";
    public static final String ERROR_DELETION = "No s'ha pogut esborrar l'arxiu";
    public static final String ERROR_FILE_NAME_NULL = "El nom de l'arxiu no pot ser null o buit";
    public static final String ERROR_FILE_EXISTS = "El nom de l'arxiu ja existeix";
    public static final String ERROR_CREATING_FILE = "Error creant l'arxiu";
    public static final String ERROR_FILE_NOT_EXIST = "L'arxiu no existeix";
    public static final String ERROR_WRITING_FILE = "Error escrivint a l'arxiu";
    public static final String ERROR_CONTENT_NULL = "El contingut de l'arxiu no pot ser null o buit";
    public static final String ERROR_FILES_NOT_FOUND = "Arxius no trobats";

    public static final String MESSAGE_SEPARATOR = "--------------------------------------------------------------------------------------------------------------";
    public static final String START_MENU_HEADER = "GESTIO IOC SUPERMARKETS";
    public static final String START_MENU = """
            1) Afegir supermercat.
            2) Afegir producte a un supermercat.
            3) Veure supermercats.
            4) Exportar supermercats i preus.
            5) Importar supermercats i preus.
            0) Sortir.
            """;
    public final static String LIST_HEADER = "SUPERMARKET         CITY                      LONGITUDE    LATITUDE";
    public final static String TEMP_LIN = "-----------------------------------------------------------------------------";
    public static final String EMPTY_SPACE = "";
    public static final String ERROR_HEADER = "ERROR";
    public static final String INFO_HEADER = "INFO";
    public static final String DATE_FORMAT = "yyyyMMddHHmm";
    public final static String SUPERMARKET_BOARD_FORMAT = "%-18s  %-24s %11.7f %11.7f";
    public final static String SUPERMARKET_SPLIT_FORMAT = "%s,%s,%.7f,%.7f\n";

    public final static String SUPERMARKET_ERROR_EPMTY = "No hi ha cap supermercat emmagatzemat";
    public final static String SUPERMARKET_ERROR_EXIST = "Supermercat no trobat";


}
