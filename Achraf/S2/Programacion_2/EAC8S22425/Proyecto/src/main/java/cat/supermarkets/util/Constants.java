package cat.supermarkets.util;

public class Constants {

    public static final String MESSAGE_DEFAULT_ASK_STRING = "Introdueixi una cadena de text";
    public static final String MESSAGE_DEFAULT_ERROR_STRING = "S'ha introduït un text buit";
    public static final String MESSAGE_DEFAULT_ASK_INTEGER = "Introdueixi un valor enter";
    public static final String MESSAGE_DEFAULT_ERROR_INTEGER = "El valor introduït no correspon a un enter";
    public static final String MESSAGE_DEFAULT_ASK_FLOAT = "Introdueixi un valor amb decimals per l'opció";
    public static final String MESSAGE_DEFAULT_ERROR_FLOAT = "El valor introduït no correspon a un nombre amb decimals";
    public static final String MESSAGE_ASK_OPTION_VALUE = "Introdueixi un valor enter per l'opció";
    public static final String MESSAGE_NOT_VALID_OPTION = "No s'ha introduït una opció vàlida";
    public static final String MESSAGE_INSERT_MARKET_NAME = "Introdueixi el nom del supermercat";
    public static final String MESSAGE_INSERT_CITY = "A quina ciutat es troba?";
    public static final String MESSAGE_INSERT_LONGITUDE = "Introdueixi la longitud on es troba el supermercat";
    public static final String MESSAGE_INSERT_LATITUDE = "Introdueixi la latitud on es troba el supermercat";
    public static final String MESSAGE_CONFIRM_DELETION = "Està segur que vol esborrar el supermercat? (S/N)";
    public static final String MESSAGE_CONFIRMED_DELETION = "Supermercat esborrat correctament";
    public static final String MESSAGE_MARKET_ADDED_SUCCESSFULLY = "Supermercat afegit correctament";
    public static final String ERROR_NO_INTEGER = "No s'ha introduït un número enter";
    public static final String ERROR_EMPTY_STRING = "S'ha introduït un text buit";
    public static final String ERROR_DELETION_CANCELED = "Cancel·lat per petició de l'usuari";
    public static final String ERROR_SUPERMARKET_NOT_FOUND = "Supermercat no trobat";
    public static final String ERROR_SUPERMARKET_ALREADY_ON_LIST = "El supermercat ja es troba a la llista";
    public static final String ERROR_SUPERMARKET_LIST_EMPTY_OR_INCORRECT = "La llista de supermercats és buida o incorrecta";
    public static final String MESSAGE_SEPARATOR = "--------------------------------------------------------------------------------------------------------------";
    public static final String START_MENU_HEADER = "GESTIO IOC SUPERMARKETS";
    public static final String START_MENU = """
            1) Afegir supermercat.
            2) Esborrar supermercat.
            3) Llistar supermercats.
            4) Editar supermercat.
            0) Sortir.
            """;
    public final static String LIST_HEADER = "SUPERMARKET                   CITY                            LONGITUDE   LATITUDE";
    public final static String TEMP_LIN = "-----------------------------------------------------------------------------------";
    public final static String SUPERMARKET_BOARD_FORMAT = "%-28s  %-29s %11.7f %11.7f";
    public static final String EMPTY_SPACE = "";
    public static final String ERROR_HEADER = "ERROR";
    public static final String INFO_HEADER = "INFO";

    // Solicitudes de entrada
    public static final String PROMPT_OPTION = "Selecciona una opció: ";
    public static final String PROMPT_SUPERMARKET_NAME = "Introdueix el nom del supermercat: ";
    public static final String ERROR_EMPTY_NAME = "El nom no pot estar buit.";

    public static final String PROMPT_SUPERMARKET_CITY = "Introdueix la ciutat del supermercat: ";
    public static final String ERROR_EMPTY_CITY = "La ciutat no pot estar buida.";

    public static final String PROMPT_LONGITUDE = "Introdueix la longitud: ";
    public static final String ERROR_INVALID_LONGITUDE = "Has d'introduir un número decimal vàlid.";

    public static final String PROMPT_LATITUDE = "Introdueix la latitud: ";
    public static final String ERROR_INVALID_LATITUDE = "Has d'introduir un número decimal vàlid.";

    public static final String PROMPT_SUPERMARKET_ID_DELETE = "Introdueix l'ID del supermercat a esborrar: ";
    public static final String ERROR_INVALID_ID = "Has d'introduir un número enter vàlid.";

    public static final String PROMPT_SUPERMARKET_ID_EDIT = "Introdueix l'ID del supermercat a editar: ";

    // Mensajes de éxito
    public static final String SUCCESS_SUPERMARKET_ADDED = "Supermercat afegit amb èxit.";
    public static final String ERROR_ADDING_SUPERMARKET = "Error afegint el supermercat: ";

    public static final String SUCCESS_SUPERMARKET_DELETED = "Supermercat esborrat amb èxit.";

    public static final String NO_SUPERMARKETS_REGISTERED = "No hi ha supermercats registrats.";
    public static final String ERROR_LISTING_SUPERMARKETS = "Error llistant els supermercats: ";

    public static final String SUCCESS_SUPERMARKET_UPDATED = "Supermercat actualitzat amb èxit.";
    public static final String ERROR_EDITING_SUPERMARKET = "Error editant el supermercat: ";

    // Mensajes de salida
    public static final String EXIT_MESSAGE = "Sortint de l'aplicació...";

    // Mensajes de error
    public static final String ERROR_INVALID_OPTION = "Opció no vàlida. Selecciona un número entre 0 i 4.";
    public static final String ERROR_FINDING_SUPERMARKET = "No s'ha trobat cap supermercat amb l'ID especificat.";
    public static final String ERROR_DELETING_SUPERMARKET = "Error esborrant el supermercat: ";
}