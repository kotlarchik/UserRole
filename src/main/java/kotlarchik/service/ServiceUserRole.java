package kotlarchik.service;

import kotlarchik.model.UserRole;
import kotlarchik.dao.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceUserRole implements DAO<UserRole, Integer> {
    private final SessionFactory factory;

    public ServiceUserRole(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(UserRole userRole) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.save(userRole);
            session.beginTransaction().commit();
        }
    }

    @Override
    public UserRole read(Integer id) {
        try(Session session = factory.openSession()) {
            UserRole userRole = session.get(UserRole.class, id);
            return userRole;
        }
    }

    @Override
    public List<UserRole> readAll() {
        try(Session session = factory.openSession()) {
            Query<UserRole> result = session.createQuery("FROM UserRole");
            return result.list();
        }
    }

    @Override
    public void update(UserRole userRole) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.update(userRole);
            session.beginTransaction().commit();
        }
    }

    @Override
    public void delete(UserRole userRole) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.delete(userRole);
            session.beginTransaction().commit();
        }
    }
}
