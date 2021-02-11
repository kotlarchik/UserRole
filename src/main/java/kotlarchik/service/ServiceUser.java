package kotlarchik.service;

import kotlarchik.dao.DAO;
import kotlarchik.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceUser implements DAO<User, Integer> {
    private final SessionFactory factory;

    public ServiceUser(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(User user) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.save(user);
            session.beginTransaction().commit();
        }
    }

    @Override
    public User read(Integer id) {
        try(Session session = factory.openSession()) {
            User user = session.get(User.class, id);
            return user;
        }
    }

    @Override
    public List<User> readAll() {
        try(Session session = factory.openSession()) {
            Query<User> result = session.createQuery("FROM User");
            return result.list();
        }
    }

    @Override
    public void update(User user) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.update(user);
            session.beginTransaction().commit();
        }
    }

    @Override
    public void delete(User user) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.delete(user);
            session.beginTransaction().commit();
        }
    }
}
