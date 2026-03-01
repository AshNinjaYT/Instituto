public class Constants {
    public static final String PRODUCTS_FILE_NAME = "products.txt";
    public static final String DEFAULT_DATA_DIRECTORY = "data";

    public static final String MESSAGE_DEFAULT_ASK_STRING = "Introdueixi una cadena de text";
    public static final String MESSAGE_DEFAULT_ERROR_STRING = "S'ha introduït un text buit";
    public static final String MESSAGE_DEFAULT_ASK_INTEGER = "Introdueixi un valor enter";
    public static final String MESSAGE_DEFAULT_ERROR_INTEGER = "El valor introduït no correspon a un enter";
    public static final String MESSAGE_DEFAULT_ASK_FLOAT = "Introdueixi un valor amb decimals per l'opció";
    public static final String MESSAGE_DEFAULT_ERROR_FLOAT = "El valor introduït no correspon a un nombre amb decimals";
    public static final String ERROR_FILE_NAME_NULL = "El nom de l'arxiu no pot ser null o buit";
    public static final String ERROR_FILE_EXISTS = "El nom de l'arxiu ja existeix";
    public static final String ERROR_CREATING_FILE = "Error creant l'arxiu";
    public static final String ERROR_FILE_NOT_EXIST = "L'arxiu no existeix";
    public static final String ERROR_WRITING_FILE = "Error escrivint a l'arxiu";
    public static final String ERROR_CONTENT_NULL = "El contingut de l'arxiu no pot ser null o buit";
    public static final String ERROR_EXPIRATION_DATE_NULL_OR_EMPTY = "La data de caducitat no pot ser null o buit";
    public static final String ERROR_EXPIRATION_DATE_FORMAT = "El format de la data de caducitat no és correcte. Ha de ser \"yyyyMMdd\"";
    public static final String MESSAGE_SEPARATOR = "--------------------------------------------------------------------------------------------------------------";
    public static final String START_MENU_HEADER = "GESTIO IOC SUPERMARKETS";
    public final static String LIST_HEADER = "SUPERMARKET                   CITY                            LONGITUDE   LATITUDE";
    public final static String TEMP_LIN = "-----------------------------------------------------------------------------------";
    public static final String START_MENU = """
            1) Afegir supermercat.
            2) Afegir producte a un supermercat.
            3) Veure supermercats.
            4) Exportar supermercats i preus.
            5) Importar supermercats i preus.
            0) Sortir.
            """;
    public static final String PRODUCT_MENU = """
            1) Alimentari
            2) Cosmètic
            """;
    public static final String MESSAGE_ASK_OPTION_VALUE = "Introdueixi un valor enter per l'opció";
    public static final String MESSAGE_ASK_OPTION_VALUE_PRODUCT = "Introdueixi una opció";
    public static final String ERROR_NO_INTEGER = "No s'ha introduït un número enter";
    public static final String MESSAGE_NOT_VALID_OPTION = "No s'ha introduït una opció vàlida";

    public final static String SUPERMARKET_BOARD_FORMAT = "%-28s  %-29s %11.7f %11.7f";
    public static final String DATE_FORMAT = "yyyyMMdd";
    public static final String EMPTY_SPACE = "";
    public static final String ERROR_HEADER = "ERROR";
    public static final String INFO_HEADER = "INFO";
    public static final int DAYS_CLOSE_TO_EXPIRATION = 7;
    public static final String ERROR_EXPIRATION_DATE_PAST = "La data de caducitat no pot ser anterior a la data actual";
    public static final String MESSAGE_INSERT_EXPIRATION_DATE = "Introdueix la data de caducitat (yyyyMMdd):";
    public static final int FOOD_PRODUCT_EXPIRATION_DISCOUNT = 20;

    public static final String MESSAGE_PRICE_NEGATIV = "El preu no pot ser 0 o negatiu";
    public static final String MESSAGE_STOCK_NEGATIV = "L'estoc no pot ser negatiu";
    public static final String MESSAGE_QUANTITY_INVALID = "La quantitat no pot ser menor a 1";
    public static final String MESSAGE_DISCOUNT_INVALID = "El descompte ha d'estar entre 0 i 100.";
    public static final String MESSAGE_PRODUCT_INVALID = "El descompte ha d'estar entre 0 i 100.";
    public static final String ERROR_SUPERMARKET_LIST_EMPTY = null;

    public static final String SUPERMARKETS_FILE_NAME = "supermarkets.txt";
    public static final String MESSAGE_INSERT_MARKET_NAME = "Introdueixi el nom del supermercat";
    public static final String MESSAGE_INSERT_CITY = "A quina ciutat es troba?";
    public static final String MESSAGE_INSERT_LONGITUDE = "Introdueixi la longitud on es troba el supermercat";
    public static final String MESSAGE_INSERT_LATITUDE = "Introdueixi la latitud on es troba el supermercat";
    public static final String MESSAGE_CONFIRM_DELETION = "Està segur que vol esborrar l'arxiu de sortida? (S/N)";
    public static final String MESSAGE_CONFIRMED_DELETION = "Arxiu esborrat i creat de nou satisfactòriament";
    public static final String MESSAGE_INSERT_PRODUCT_NAME = "Introdueixi el nom del producte";
    public static final String MESSAGE_INSERT_PRODUCT_PRICE = "Introdueixi el preu del producte";
    public static final String MESSAGE_INSERT_MARCA = "Introdueixi la marca";
    public static final String MESSAGE_INSERT_DATA_CADUCITAT = "Introdueixi la data de cadacuitat (yyyyMMdd)";
    public static final String MESSAGE_MARKET_ADDED_SUCCESSFULLY = "Supermercat afegit correctament";
    public static final String MESSAGE_PRODUCT_ADDED_SUCCESSFULLY = "Producte afegit correctament";
    public static final String MESSAGE_FILES_CREATED = "Supermercats i productes exportats correctament";
    public static final String MESSAGE_FILES_IMPORTED = "Supermercats i productes importats correctament";
    public static final String ERROR_NO_FLOAT = "No s'ha introduït un número amb decimals";
    public static final String ERROR_EMPTY_STRING = "S'ha introduït un text buit";
    public static final String ERROR_DELETION_CANCELED = "Cancel·lat per petició de l'usuari";
    public static final String ERROR_DELETION = "No s'ha pogut esborrar l'arxiu";
    public static final String ERROR_PRODUCT_NAME_NULL_OR_EMPTY = "El nom del producte no pot ser null o buit";
    public static final String ERROR_PRICE_NEGATIVE_OR_ZERO = "El preu no pot ser 0 o negatiu";
    public static final String ERROR_PRODUCT_ALREADY_EXISTS = "El producte ja existeix";
    public static final String ERROR_PRODUCT_NOT_FOUND = "El producte no existeix";
    public static final String ERROR_SUPERMARKET_NULL = "El supermercat no pot ser null";
    public static final String ERROR_SUPERMARKET_NOT_FOUND = "Supermercat no trobat";
    public static final String ERROR_SUPERMARKET_ALREADY_ON_LIST = "El supermercat ja es troba a la llista";
    public static final String ERROR_FILES_NOT_FOUND = "Arxiu/s no trobat/s";
    public static final String ERROR_FILES_EMPTY = "Arxiu/s buit/s";
    public static final String ERROR_SUPERMARKET_LIST_EMPTY_OR_INCORRECT = "La llista de supermercats és buida o incorrecta";
    public final static String SUPERMARKET_FILE_FORMAT = "%s,%s,%.7f,%.7f\n";
    public static final String PRODUCT_FILE_FORMAT = "%s,%s,%s,%.2f\n";
    public static final String ERROR_IMPORTANT_SUPERMERCAT_O_PRODUCTE = "Error important supermercat o producte: ";
    public static final String NOM_NULL = "El nom no pot ser null ni buit.";
    public static final String CITY_NULL = "La ciutat no pot ser null ni buida.";
    public static final String PRODUCT_NULL = "Error important supermercat o producte: ";
    public static final String PRODUCT_EXIST = "El producte ja existeix.";
    public static final String PRODUCT_NO_EXIST = "El producte ja existeix.";
    public static final String PRODUCT_NAME_NULL = "El nom del producte no pot ser null ni buit.";

}
