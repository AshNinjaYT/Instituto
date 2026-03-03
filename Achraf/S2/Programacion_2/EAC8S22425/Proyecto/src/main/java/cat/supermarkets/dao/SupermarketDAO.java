package cat.supermarkets.dao;
import org.hibernate.Session;
import org.hibernate.Transaction;

import cat.supermarkets.model.*;
import cat.supermarkets.util.*;

import java.util.List;

public class SupermarketDAO {

    public Long save(Supermarket supermarket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(supermarket);
            transaction.commit();
            return supermarket.getId();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public Supermarket getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Supermarket.class, id);
        }
    }

    public List<Supermarket> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Supermarket", Supermarket.class).getResultList();
        }
    }

    public void update(Supermarket supermarket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(supermarket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public void delete(Supermarket supermarket) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(supermarket);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    public List<Supermarket> searchByNameAndCity(String name, String city) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "from Supermarket where lower(name) like :name and lower(city) like :city",
                    Supermarket.class
            )
            .setParameter("name", "%" + name.toLowerCase() + "%")
            .setParameter("city", "%" + city.toLowerCase() + "%")
            .getResultList();
        }
    }
}