package kotlarchik.service;

import kotlarchik.model.Role;
import kotlarchik.dao.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ServiceRole implements DAO<Role, Integer> {
    private final SessionFactory factory;

    public ServiceRole(SessionFactory factory){
        this.factory = factory;
    }

    @Override
    public void create(Role role) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.save(role);
            session.beginTransaction().commit();
        }
    }

    @Override
    public Role read(Integer id) {
        try(Session session = factory.openSession()) {
            Role role = session.get(Role.class, id);
            return role;
        }
    }

    @Override
    public List<Role> readAll() {
        try(Session session = factory.openSession()) {
            Query<Role> result = session.createQuery("FROM Role");
            return result.list();
        }
    }

    @Override
    public void update(Role role) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.save(role);
            session.beginTransaction().commit();
        }
    }

    @Override
    public void delete(Role role) {
        try(Session session = factory.openSession()) {
            session.getTransaction();
            session.save(role);
            session.beginTransaction().commit();
        }
    }
}
