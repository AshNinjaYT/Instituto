package cat.exemple;

import java.math.BigDecimal;
import java.util.List;
import cat.exemple.dao.ProducteDAO;
import cat.exemple.model.Producte;
import cat.exemple.util.HibernateUtil;

public class Application {
    private static ProducteDAO producteDAO = new ProducteDAO();

    public static void main(String[] args) {
        try {
            System.out.println("Iniciant aplicació Hibernate amb PostgreSQL...");
            // Crear i guardar productes
            crearProductes();
            // Obtenir tots els productes
            mostrarTotsElsProductes();
            // Actualitzar un producte
            actualitzarProducte();
            // Cercar per nom
            cercarProductesPerNom("por");
            // Eliminar un producte
            eliminarProducte();
            // Mostrar tots els productes després d'eliminar
            mostrarTotsElsProductes();
            System.out.println("Fi de l'aplicació");
        } catch (Exception e) {
            System.err.println("Error en l'aplicació: " +
                    e.getMessage());
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }

    private static void crearProductes() {
        System.out.println("\n=== Creant productes ===");
        Producte p1 = new Producte("Portàtil XPS", new BigDecimal("1299.99"), 10);
        Producte p2 = new Producte("Teclat Mecànic", new BigDecimal("89.99"), 25);
        Producte p3 = new Producte("Ratolí Gaming", new BigDecimal("49.99"), 30);
        producteDAO.save(p1);
        producteDAO.save(p2);
        producteDAO.save(p3);
        System.out.println("Productes creats amb èxit");
    }

    private static void mostrarTotsElsProductes() {
        System.out.println("\n=== Tots els productes ===");
        List<Producte> productes = producteDAO.getAll();
        if (productes.isEmpty()) {
            System.out.println("No hi ha productes a la base de dades.");
        } else {
            for (Producte p : productes) {
                System.out.println(p);
            }
        }
    }

    private static void actualitzarProducte() {
        System.out.println("\n=== Actualitzant producte ===");
        // Obtenir un producte per actualitzar
        List<Producte> productes = producteDAO.getAll();
        if (!productes.isEmpty()) {
            Producte producte = productes.get(0);
            System.out.println("Producte abans d'actualitzar: " +
                    producte);
            // Actualitzar propietats
            producte.setPreu(new BigDecimal("1199.99"));
            producte.setStock(producte.getStock() - 2);
            // Guardar canvis
            producteDAO.update(producte);
            // Mostrar producte actualitzat
            producte = producteDAO.getById(producte.getId());
            System.out.println("Producte després d'actualitzar: " +
                    producte);
        } else {
            System.out.println("No hi ha productes per actualitzar.");
        }
    }

    private static void cercarProductesPerNom(String nom) {
        System.out.println("\n=== Cercant productes amb nom que contingui '" + nom + "' ===");
        List<Producte> productes = producteDAO.findByName(nom);
        if (productes.isEmpty()) {
            System.out.println("Cap producte trobat amb el nom '" +
                    nom + "'");
        } else {
            for (Producte p : productes) {
                System.out.println(p);
            }
        }
    }

    private static void eliminarProducte() {
        System.out.println("\n=== Eliminant producte ===");
        // Obtenir tots els productes
        List<Producte> productes = producteDAO.getAll();
        if (!productes.isEmpty()) {
            // Eliminem l'últim producte de la llista
            Producte producteAEliminar = productes.get(productes.size() - 1);
            System.out.println("Eliminant: " + producteAEliminar);
            producteDAO.delete(producteAEliminar);
            System.out.println("Producte eliminat");
        } else {
            System.out.println("No hi ha productes per eliminar.");
        }
    }
}