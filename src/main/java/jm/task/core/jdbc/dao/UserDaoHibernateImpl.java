package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Util sessionUtil = new Util();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try {
            sessionUtil.openTransactionSession();
            String sql = "CREATE TABLE IF NOT EXISTS USERS (ID BIGINT(255) AUTO_INCREMENT PRIMARY KEY," +
                    " NAME VARCHAR(255), LASTNAME VARCHAR(255), AGE TINYINT(127) UNSIGNED)";
            Session session = sessionUtil.getSession();
            Query query = session.createNativeQuery(sql);
            query.executeUpdate();
            sessionUtil.closeTransactionSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            sessionUtil.openTransactionSession();
            String sql = "DROP TABLE IF EXISTS USERS";
            Session session = sessionUtil.getSession();
            Query query = session.createNativeQuery(sql);
            query.executeUpdate();
            sessionUtil.closeTransactionSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            session.save(new User(name, lastName, age));
            sessionUtil.closeTransactionSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            session.delete(session.find(User.class, id));
            sessionUtil.closeTransactionSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> addressList = new ArrayList<>();
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            criteria.from(User.class);
            addressList = session.createQuery(criteria).getResultList();
            sessionUtil.closeTransactionSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            sessionUtil.openTransactionSession();
            Session session = sessionUtil.getSession();
            session.createQuery("delete User").executeUpdate();
            sessionUtil.closeTransactionSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }
}