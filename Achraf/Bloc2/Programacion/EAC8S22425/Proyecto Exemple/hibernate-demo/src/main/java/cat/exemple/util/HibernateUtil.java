package cat.exemple.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import cat.exemple.model.Producte;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            // Crear instància de Configuration
            Configuration configuration = new Configuration();
            // Configurar hibernate a partir de l'arxiu hibernate.cfg.xml
            configuration.configure("hibernate.cfg.xml");
            // Registrar les classes del model
            configuration.addAnnotatedClass(Producte.class);
            // Crear el ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            // Construir el SessionFactory
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            System.out.println("Hibernate SessionFactory inicialitzat amb èxit");
        } catch (Throwable ex) {
            System.err.println("Error en la inicialització de SessionFactory: " + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null && !sessionFactory.isClosed()) {
            sessionFactory.close();
            System.out.println("SessionFactory tancat");
        }
    }
}
