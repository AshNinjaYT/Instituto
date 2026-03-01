package cat.exemple.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import cat.exemple.model.Producte;
import cat.exemple.util.HibernateUtil;

public class ProducteDAO {
    /**
     * Guarda un producte a la base de dades.
     */
    public Long save(Producte producte) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Long id = (Long) session.save(producte);
            transaction.commit();
            return id;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Obté un producte pel seu identificador.
     */
    public Producte getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Producte.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Obté tots els productes.
     */
    public List<Producte> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Producte> query = session.createQuery("from Producte", Producte.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Actualitza un producte.
     */
    public void update(Producte producte) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(producte);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Elimina un producte.
     */
    public void delete(Producte producte) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(producte);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Cerca productes per nom.
     */
    public List<Producte> findByName(String nom) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Producte> query = session.createQuery(
                    "from Producte where nom like :nom", Producte.class);
            query.setParameter("nom", "%" + nom + "%");
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
