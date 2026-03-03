package cat.supermarkets;

import java.util.Locale;
import cat.supermarkets.dao.SupermarketDAO;
import cat.supermarkets.model.Supermarket;
import cat.supermarkets.util.Constants;
import cat.supermarkets.util.UtilsIO;

public class App {
    private SupermarketDAO supermarketDAO; // DAO para operaciones con la base de datos
    private UtilsIO io; // Para manejar entrada/salida

    public static void main(String[] args) {
        App program = new App();
        Locale.setDefault(Locale.US);
        program.start();
    }

    public App() {
        this.supermarketDAO = new SupermarketDAO(); // DAO para la gestión de datos
        this.io = new UtilsIO(); // Instancia de los métodos de entrada/salida
    }

    public void start() {
        boolean exit = false;

        while (!exit) {
            // Mostrar menú principal
            io.showMenu(Constants.START_MENU); // Mostrar el menú al usuario

            // Capturar la opción del usuario
            int option = io.askForInteger(Constants.PROMPT_OPTION, Constants.ERROR_INVALID_OPTION);

            // Manejar las posibles opciones
            switch (option) {
                case 1:
                    addSupermarket(); // Añadir un supermercado
                    break;
                case 2:
                    deleteSupermarket(); // Eliminar supermercado
                    break;
                case 3:
                    listSupermarkets(); // Listar supermercados
                    break;
                case 4:
                    editSupermarket(); // Editar supermercado
                    break;
                case 0:
                    io.showInfo(Constants.EXIT_MESSAGE);
                    exit = true;
                    break;
                default:
                    io.showError(Constants.ERROR_INVALID_OPTION);
            }
        }
    }

    private void addSupermarket() {
        String name = io.askForString(Constants.PROMPT_SUPERMARKET_NAME, Constants.ERROR_EMPTY_NAME);
        String city = io.askForString(Constants.PROMPT_SUPERMARKET_CITY, Constants.ERROR_EMPTY_CITY);
        float longitude = io.askForFloat(Constants.PROMPT_LONGITUDE, Constants.ERROR_INVALID_LONGITUDE);
        float latitude = io.askForFloat(Constants.PROMPT_LATITUDE, Constants.ERROR_INVALID_LATITUDE);

        try {
            Supermarket supermarket = new Supermarket(name, city, longitude, latitude);
            supermarketDAO.save(supermarket);
            io.showInfo(Constants.SUCCESS_SUPERMARKET_ADDED);
        } catch (Exception e) {
            io.showError(Constants.ERROR_ADDING_SUPERMARKET + e.getMessage());
        }
    }

    private void deleteSupermarket() {
        Long id = (long) io.askForInteger(Constants.PROMPT_SUPERMARKET_ID_DELETE, Constants.ERROR_INVALID_ID);

        try {
            Supermarket supermarket = supermarketDAO.getById(id);
            if (supermarket != null) {
                supermarketDAO.delete(supermarket);
                io.showInfo(Constants.SUCCESS_SUPERMARKET_DELETED);
            } else {
                io.showError(Constants.ERROR_FINDING_SUPERMARKET);
            }
        } catch (Exception e) {
            io.showError(Constants.ERROR_DELETING_SUPERMARKET + e.getMessage());
        }
    }

    private void listSupermarkets() {
        try {
            var supermarkets = supermarketDAO.getAll();
            if (!supermarkets.isEmpty()) {
                StringBuilder list = new StringBuilder("Supermercats registrats:\n\n");
                for (Supermarket supermarket : supermarkets) {
                    list.append(supermarket.supermarketToString()).append("\n");
                }
                io.showSupermarkets(list.toString());
            } else {
                io.showInfo(Constants.NO_SUPERMARKETS_REGISTERED);
            }
        } catch (Exception e) {
            io.showError(Constants.ERROR_LISTING_SUPERMARKETS + e.getMessage());
        }
    }

    private void editSupermarket() {
        Long id = (long) io.askForInteger(Constants.PROMPT_SUPERMARKET_ID_EDIT, Constants.ERROR_INVALID_ID);

        try {
            Supermarket supermarket = supermarketDAO.getById(id);
            if (supermarket != null) {
                io.showInfo("Supermercat actual: " + supermarket.supermarketToString());

                String name = io.askForString("Nou nom (deixa buit per no canviar): ", "");
                String city = io.askForString("Nova ciutat (deixa buit per no canviar): ", "");
                Float longitude = io.askForFloat("Nova longitud (deixa buit per no canviar): ", null);
                Float latitude = io.askForFloat("Nova latitud (deixa buit per no canviar): ", null);

                // Solo actualiza campos que no están vacíos
                if (!name.isEmpty()) {
                    supermarket.setName(name);
                }
                if (!city.isEmpty()) {
                    supermarket.setCity(city);
                }
                if (longitude != null) {
                    supermarket.setLongitude(longitude);
                }
                if (latitude != null) {
                    supermarket.setLatitude(latitude);
                }

                supermarketDAO.update(supermarket);
                io.showInfo(Constants.SUCCESS_SUPERMARKET_UPDATED);
            } else {
                io.showError(Constants.ERROR_FINDING_SUPERMARKET);
            }
        } catch (Exception e) {
            io.showError(Constants.ERROR_EDITING_SUPERMARKET + e.getMessage());
        }
    }
}
